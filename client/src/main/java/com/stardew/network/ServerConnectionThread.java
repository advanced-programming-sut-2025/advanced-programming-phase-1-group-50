package com.stardew.network;

import java.io.IOException;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ServerConnectionThread extends ConnectionThread {
    private final MessageHandler messageHandler = MessageHandler.getInstance();
    private final ConcurrentHashMap<String, BlockingQueue<Message>> responseQueues = new ConcurrentHashMap<>();


    public ServerConnectionThread(Socket socket) throws IOException {
        super(socket);
    }


    public Message sendAndWaitForResponse(Message message, int timeoutMilli) {
        String requestID = UUID.randomUUID().toString();
        message.setRequestID(requestID);

        BlockingQueue<Message> responseQueue = new ArrayBlockingQueue<>(1);
        responseQueues.put(requestID, responseQueue);

        sendMessage(message);

        try {
            return responseQueue.poll(timeoutMilli, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.err.println("Request Timed out.");
            end();
            return null;
        } finally {
            responseQueues.remove(requestID);
        }
    }


    @Override
    protected void handleMessage(Message message) {
        String requestID = message.getRequestID();

        if (requestID != null) {
            BlockingQueue<Message> queue = responseQueues.get(requestID);
            if (queue != null) {
                boolean success = queue.offer(message);
                if (!success) System.err.println("Couldn't put response in Queue.");
            } else {
                System.err.println("No waiting queue found for request ID: " + requestID);
            }
        }
        else {
            boolean success = messageHandler.handleMessage(message);
            if (!success) System.err.println("Couldn't handle message");
        }
    }
}
