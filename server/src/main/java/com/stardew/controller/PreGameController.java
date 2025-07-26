package com.stardew.controller;

import com.stardew.model.userInfo.User;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Lobby;
import com.stardew.network.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PreGameController {
    private static PreGameController instance;
    private final Map<Integer, ConcurrentHashMap<String, Boolean>> playersReadyStatusByID = new ConcurrentHashMap<>();
    private final Map<Integer, ConcurrentHashMap<String, String>> playersFarmStatusByID = new ConcurrentHashMap<>();


    private PreGameController() {}

    public static synchronized PreGameController getInstance() {
        if (instance == null) {
            instance = new PreGameController();
        }
        return instance;
    }


    public void addNewPreGame(Lobby lobby) {
        if (lobby == null) return;
        int id = lobby.getId();
        ConcurrentHashMap<String, Boolean> playersReady = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, String> playersFarmSelected = new ConcurrentHashMap<>();
        for (User user : lobby.getUsers()) {
            playersReady.put(user.getUsername(), false);
            playersFarmSelected.put(user.getUsername(), "null");
        }
        playersReadyStatusByID.put(id, playersReady);
        playersFarmStatusByID.put(id, playersFarmSelected);
    }


    public void handlePlayerReady(Message message, ClientConnectionThread connection) {
        int id = message.getIntFromBody("lobbyID");
        Boolean isReady = message.getFromBody("isReady");
        String farmSelected = message.getFromBody("farmSelected");

        Map<String, Boolean> playersReadyStatusInLobby = playersReadyStatusByID.get(id);
        Map<String, String> playersFarmStatusInLobby = playersFarmStatusByID.get(id);
        String username = connection.getUser().getUsername();

        if (playersReadyStatusInLobby.containsKey(username)) {
            playersReadyStatusInLobby.put(username, isReady);
            playersFarmStatusInLobby.put(username, farmSelected);
        }


        boolean isAllReady = true;
        for (String username1 : playersReadyStatusInLobby.keySet()) {
            if (!playersReadyStatusInLobby.get(username1) || playersFarmStatusInLobby.get(username1).equals("null")) {
                isAllReady = false;
                break;
            }
        }

        if (isAllReady) {
            //TODO
            // handle go to start game here (build map and ...)
            // should find lobby and send message to all members
            // remove id from 2 maps
        }
    }
}
