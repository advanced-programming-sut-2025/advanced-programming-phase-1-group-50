package com.stardew.network;

import com.stardew.model.ServerApp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenerThread extends Thread {
    private final ServerSocket serverSocket;


    public ListenerThread(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleConnection(Socket socket) {
        if (socket == null) return;
        try {
            ClientConnectionThread connectionThread = new ClientConnectionThread(socket);
            ServerApp.addClientConnection(connectionThread);
            connectionThread.start();
        } catch (IOException e) {
            try {socket.close(); e.printStackTrace();} catch (IOException ex) {ex.printStackTrace();}
        }
    }


    @Override
    public void run() {
        while (!ServerApp.isEnded()) {
            try {
                Socket socket = serverSocket.accept();
                handleConnection(socket);
            } catch (Exception e) {
                break;
            }
        }

        end();
    }

    public void end() {
        try {
            serverSocket.close();
        } catch (IOException ignored) {}
    }
}
