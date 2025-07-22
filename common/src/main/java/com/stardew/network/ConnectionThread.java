package com.stardew.network;

import com.stardew.utils.JSONUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
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
                boolean handled = handleMessage(message); //TODO
                if (!handled) try {
                    receivedMessagesQueue.put(message);
                } catch (InterruptedException e) {}
            } catch (Exception e) {
                e.printStackTrace();
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
