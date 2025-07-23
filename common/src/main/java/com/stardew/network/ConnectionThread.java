package com.stardew.network;

import com.stardew.utils.JSONUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ConnectionThread extends Thread {
    protected final DataInputStream dataInputStream;
    protected final DataOutputStream dataOutputStream;
    protected final BlockingQueue<Message> receivedMessagesQueue;
    protected Socket socket;
    protected AtomicBoolean end;


    public ConnectionThread(Socket socket) throws IOException {
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.receivedMessagesQueue = new LinkedBlockingQueue<>();
        this.end = new AtomicBoolean(false);
    }

    public Message sendAndWaitForResponse(Message message, int timeoutMilli) {
        sendMessage(message);
        try {
            return receivedMessagesQueue.poll(timeoutMilli, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.err.println("Request Timed out.");
            end();
            return null;
        }
    }

    public synchronized void sendMessage(Message message) {
        String JSONString = JSONUtils.toJson(message);

        try {
            dataOutputStream.writeUTF(JSONString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract boolean handleMessage(Message message);


    @Override
    public void run() {

        while (!end.get()) {
            try {
                String receivedStr = dataInputStream.readUTF();
                Message message = JSONUtils.fromJson(receivedStr);
                System.out.println("Received: " + message);  //TODO
                boolean handled = handleMessage(message);    //TODO
                if (!handled) {
                    receivedMessagesQueue.put(message);
                }
            } catch (SocketException se) {
                if (!end.get()) {
                    System.out.println("Socket Closed.");
                }
                break;
            } catch (EOFException eof) {
                if (!end.get()) {
                    System.out.println("Remote closed connection.");
                }
                break;
            } catch (IOException ioe) {
                if (!end.get()) {
                    System.out.println("IO Error.");
                }
                break;
            } catch (InterruptedException ie) {
                System.out.println("couldn't put message in Queue.");
                break;
            }
        }

        end();
    }

    public void end() {
        end.set(true);
        try {
            socket.close();
        } catch (IOException e) {}
    }
}
