package com.stardew.model;

import com.stardew.network.ClientConnectionThread;
import com.stardew.network.ListenerThread;

import java.util.ArrayList;

public class ServerApp {
    private static ListenerThread listenerThread;
    private static final ArrayList<ClientConnectionThread> clientConnection = new ArrayList<>();
    private static boolean exitFlag = false;


    public static void creatListenerThread(int port) {
        listenerThread = new ListenerThread(port);
    }

    public static void startListening() {
        listenerThread.start();
    }

    public static void addClientConnection(ClientConnectionThread clientConnectionThread) {
        if (clientConnectionThread != null && !clientConnection.contains(clientConnectionThread)) {
            clientConnection.add(clientConnectionThread);
        }
    }

    public static void removeClientConnection(ClientConnectionThread clientConnectionThread) {
        if (clientConnectionThread != null) {
            clientConnection.remove(clientConnectionThread);
            clientConnectionThread.end();
        }
    }

    public static boolean isEnded() {
        return exitFlag;
    }

    public static void endAll() {
        exitFlag = true;
        for (ClientConnectionThread connectionThread : clientConnection) {
            connectionThread.end();
        }
        listenerThread.end();
    }
}
