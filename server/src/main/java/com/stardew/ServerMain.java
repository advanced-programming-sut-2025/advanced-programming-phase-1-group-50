package com.stardew;

import com.stardew.controller.GameSessionController;
import com.stardew.model.ServerApp;
import com.stardew.repository.DatabaseInitializer;
import com.stardew.repository.DatabaseManager;

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


        DatabaseManager.initialize("stardew.sqlite");


        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                ServerApp.endAll();
                DatabaseManager.getInstance().close();
                GameSessionController.getInstance().stopAllGames();
                break;
            }
        }
    }
}
