package com.stardew.network;

import java.io.IOException;
import java.net.Socket;

public class NetworkManager {
    private static ServerConnectionThread connection;

    public static void connect(String host, int port) throws IOException {
        connection = new ServerConnectionThread(new Socket(host, port));
        connection.start();
    }

    public static ServerConnectionThread getConnection() {
        return connection;
    }
}
