package com.stardew;

import com.stardew.model.ServerApp;

import java.util.Scanner;

public class ServerMain {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java tracker.TrackerMain <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);
        ServerApp.creatListenerThread(port);
        ServerApp.startListening();

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                ServerApp.endAll();
                break;
            }
        }
    }
}
