package com.stardew.network;

import com.stardew.model.Message;
import com.stardew.model.ServerApp;

import java.io.IOException;
import java.net.Socket;

public class ClientConnectionThread extends ConnectionThread {


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
        //TODO
        return false;
    }
}
