package com.stardew.network;

import com.stardew.model.ServerApp;
import com.stardew.model.userInfo.User;

import java.io.IOException;
import java.net.Socket;

public class ClientConnectionThread extends ConnectionThread {
    private final MessageHandler messageHandler = MessageHandler.getInstance();
    private User user;


    public ClientConnectionThread(Socket socket) throws IOException {
        super(socket);
    }


    @Override
    public void run() {
        super.run();
        ServerApp.removeClientConnection(this);
    }


    @Override
    protected void handleMessage(Message message) {
        boolean success = messageHandler.handleMessage(message, this);
        if (!success) System.err.println("Couldn't handle message");
    }

    public synchronized void setUser(User user) {
        this.user = user;
    }

    public synchronized User getUser() {
        return user;
    }

    public boolean isLoggedIn() {
        return user != null;
    }
}
