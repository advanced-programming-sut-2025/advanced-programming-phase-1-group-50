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
    protected boolean handleMessage(Message message) {
        return messageHandler.handleMessage(message, this);
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
