package com.stardew.network;

import com.stardew.model.Message;

import java.io.IOException;
import java.net.Socket;

public class ServerConnectionThread extends ConnectionThread {


    public ServerConnectionThread(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected boolean handleMessage(Message message) {
        //TODO
        return false;
    }
}
