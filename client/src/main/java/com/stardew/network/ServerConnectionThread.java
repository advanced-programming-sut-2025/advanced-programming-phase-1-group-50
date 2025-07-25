package com.stardew.network;

import java.io.IOException;
import java.net.Socket;

public class ServerConnectionThread extends ConnectionThread {
    private final MessageHandler messageHandler = MessageHandler.getInstance();


    public ServerConnectionThread(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected boolean handleMessage(Message message) {
        return messageHandler.handleMessage(message);
    }
}
