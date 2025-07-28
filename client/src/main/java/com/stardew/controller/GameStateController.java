package com.stardew.controller;

import com.google.gson.reflect.TypeToken;
import com.stardew.model.PlayerDTO;
import com.stardew.model.TileDTO;
import com.stardew.models.GameModel;
import com.stardew.network.Message;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GameStateController {
    private static GameStateController instance;
    private GameModel gameState;
    private boolean firstUpdate = true;



    private GameStateController() {}

    public static GameStateController getInstance() {
        if (instance == null) {
            instance = new GameStateController();
        }
        return instance;
    }


    public void handleUpdate(Message message) {
        if (message == null) return;

        Type tileDTOType = new TypeToken<ArrayList<TileDTO>>(){}.getType();

        if (firstUpdate) {
            int mapWidth = message.getIntFromBody("mapWidth");
            int mapHeight = message.getIntFromBody("mapHeight");
            gameState = new GameModel(mapWidth, mapHeight);
            firstUpdate = false;
        }

        ArrayList<TileDTO> tileDTOs = message.getFromBody("tileDTOs", tileDTOType);
        PlayerDTO player = message.getFromBody("player", PlayerDTO.class);

        gameState.updateTiles(tileDTOs);
        gameState.updatePlayer(player);
        gameState.updateCamera();
        gameState.updateIndexes();
    }


    public GameModel getGameState() {
        return gameState;
    }


    public boolean isFirstUpdate() {
        return firstUpdate;
    }


    public void restartGame() {
        instance = null;
        firstUpdate = true;
    }
}
