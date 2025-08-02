package com.stardew.controller;

import com.stardew.controller.CookingCraftingControllers.CookingController;
import com.stardew.controller.CookingCraftingControllers.CookingCraftingInfoController;
import com.stardew.controller.CookingCraftingControllers.CraftingController;
import com.stardew.model.gameApp.Game;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.ArrayList;
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
            game.startHotBar();
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
                Player player = game.getPlayer(connection);
                PlayerController.getInstance().handleMovement(player, game.getMap().getTiles(), message);
            }
            case ShowInventory -> {
                Player p = game.getPlayer(connection);
                InventoryController.getInstance().handleSendInventoryList(p , connection , message.getRequestID());
            }
            case RemoveItem -> {
                Player p = game.getPlayer(connection);
                InventoryController.getInstance().handleRemoveItem(p , message , connection , message.getRequestID());

            }
            case GetCookingOrCraftingInfo -> {
                Player player = game.getPlayer(connection);
                CookingCraftingInfoController.getInstance().handleGetInfo(message, player, connection);
            }
            case CookingFood -> {
                Player player = game.getPlayer(connection);
                CookingController.getInstance().cookingPrepare(message, player, connection);
            }
            case GetMyFarmInfo -> {
                Player player = game.getPlayer(connection);
                CookingCraftingInfoController.getInstance().handleGetFarmInfo(message, player, game.getMap(), connection);
            }
            case CraftingMachine -> {
                Player player = game.getPlayer(connection);
                CraftingController.getInstance().craftingCraft(message, player, game.getMap(), connection);
            }

            case GetSkillInfo -> {
                Player player = game.getPlayer(connection);
                InventoryController.getInstance().handleSendSkillInfo(player , connection , message.getRequestID());

            }

            case GetRelationWithNPCInfo -> {
                Player player = game.getPlayer(connection);
                InventoryController.getInstance().handleSendRelationWithNPCInfo(player, connection , message.getRequestID());
            }

            case GetMapInfo -> {
                ArrayList<Player> players = game.getAllPlayers();
                InventoryController.getInstance().handleSendMapInfo(players , game , connection , message.getRequestID());

            }

            case ShuffleInventory -> {
                Player player = game.getPlayer(connection);
                InventoryController.getInstance().handleShuffleInventory(player, connection , message.getRequestID());

            }

            case SetCurrentItem -> {
                Player player = game.getPlayer(connection);
                InventoryController.getInstance().handleSetCurrentItem(player , message);

            }

            case CLickTile -> {
                Player player = game.getPlayer(connection);
                InventoryController.getInstance().handleClickTile(player, game , message , connection , message.getRequestID());
            }
        }

    }
}
