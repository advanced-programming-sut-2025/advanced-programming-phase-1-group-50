package com.stardew.controller;

import com.stardew.model.gameApp.Game;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameSessionController {
    private static GameSessionController instance;
    private final Map<Integer, Game> games = new ConcurrentHashMap<>();

    public static synchronized GameSessionController getInstance() {
        if (instance == null) {
            instance = new GameSessionController();
        }
        return instance;
    }

    public Game getGame(int id) {
        return games.get(id);
    }

    public void addGame(int id, Game game) {
        games.put(id, game);
    }

    public void removeGame(int id) {
        games.remove(id);
    }


    public void handleUpdateGameState(Message message, ClientConnectionThread connection) {
        if (message == null) return;

        int id = message.getIntFromBody("id");
        Game game = games.get(id);

        if (game == null) return;

        if(!game.isStarted()) {
            game.setStarted(true);
            game.startTime();
        }


        int startX = message.getIntFromBody("startX");
        int startY = message.getIntFromBody("startY");
        int endX = message.getIntFromBody("endX");
        int endY = message.getIntFromBody("endY");

        HashMap<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("gameState", game.getGameState(startX, startY, endX, endY, connection));
        Message response = new Message(body, MessageType.UPDATE_GAME_RESULT);
        connection.sendMessage(response);
    }

    public void handleMapRequest(Message message, ClientConnectionThread connection) {
        if (message == null) return;
        int id = message.getIntFromBody("id");
        Game game = games.get(id);
        if (game == null) return;

        HashMap<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("mapWidth", game.getMap().getWidth());
        body.put("mapHeight", game.getMap().getHeight());
        Message response = new Message(body, MessageType.MAP_REQUEST_RESULT);

        connection.sendMessage(response);
    }

    public void handleEventInGame(Message message, ClientConnectionThread connection) {
        if (message == null) return;
        int id = message.getIntFromBody("id");
        Game game = games.get(id);
        if (game == null) return;

        Event event = message.getFromBody("event", Event.class);
        if (event == null) return;

        switch (event) {
            case Moving -> {
                Float vx = message.getFromBody("vx", Float.class);
                Float vy = message.getFromBody("vy", Float.class);
                int dir = message.getIntFromBody("dir");
                PlayerController.getInstance().handleMovement(game.getPlayer(connection), game.getMap().getTiles(), vx, vy, dir);
            }
        }

    }
}
