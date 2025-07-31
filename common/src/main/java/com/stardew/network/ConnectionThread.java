package com.stardew.network;

import com.stardew.utils.JSONUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ConnectionThread extends Thread {
    protected final DataInputStream dataInputStream;
    protected final DataOutputStream dataOutputStream;
    protected final BlockingQueue<Message> outgoingMessagesQueue;
    protected final BlockingQueue<Message> incomingMessagesQueue;
    protected final AtomicBoolean running;
    protected Socket socket;


    public ConnectionThread(Socket socket) throws IOException {
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.outgoingMessagesQueue = new LinkedBlockingQueue<>();
        this.incomingMessagesQueue = new LinkedBlockingQueue<>();
        this.running = new AtomicBoolean(true);
    }

    public void sendMessage(Message message) {
        boolean success = outgoingMessagesQueue.offer(message);
        if (!success) System.err.println("Could not send message");
    }

    protected abstract void handleMessage(Message message);


    @Override
    public void run() {

        Thread sender = createSenderThread();
        sender.start();

        Thread handler = createHandlerThread();
        handler.start();

        while (running.get()) {
            try {
                int length = dataInputStream.readInt();
                byte[] data = new byte[length];
                dataInputStream.readFully(data);
                String receivedStr = new String(data, StandardCharsets.UTF_8);
                Message message = JSONUtils.fromJson(receivedStr);
                boolean success = incomingMessagesQueue.offer(message);
                if(message.getType().equals(MessageType.UPDATE_HOT_BAR)) System.out.println(message); //TODO
                if (!success) System.err.println("Could not put in incoming_messages_queue");
            } catch (SocketException se) {
                if (running.get()) System.out.println("Socket Closed.");
                break;
            } catch (EOFException eof) {
                if (running.get()) System.out.println("Remote closed connection.");
                break;
            } catch (IOException ioe) {
                if (running.get()) System.out.println("IO Error during read.");
                break;
            }
        }

        end();
    }


    private Thread createSenderThread() {
        Thread sender = new Thread(() -> {
            while (running.get()) {
                try {
                    Message msg = outgoingMessagesQueue.take();
                    String json = JSONUtils.toJson(msg);
                    byte[] data = json.getBytes(StandardCharsets.UTF_8);
                    dataOutputStream.writeInt(data.length);
                    dataOutputStream.write(data);
                    dataOutputStream.flush();
                } catch (InterruptedException interruptedException) {
                    System.err.println("Sender thread interrupted.");
                    running.set(false);
                } catch (IOException ioException) {
                    System.err.println("IO Exception in sender thread: " + ioException.getMessage());
                    running.set(false);
                }
            }
        }, "ConnectionThread-Sender");
        sender.setDaemon(true);
        return sender;
    }

    private Thread createHandlerThread() {
        Thread handler = new Thread(() -> {
            while (running.get()) {
                try {
                    Message msg = incomingMessagesQueue.take();
                    handleMessage(msg);
                } catch (InterruptedException interruptedException) {
                    System.err.println("Handler thread interrupted.");
                    running.set(false);
                }
            }
        }, "ConnectionThread-Handler");
        handler.setDaemon(true);
        return handler;
    }



    public void end() {
        running.set(false);
        try {
            socket.close();
        } catch (IOException e) {}
    }
}
