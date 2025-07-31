package com.stardew.controller;

import com.stardew.model.*;
import com.stardew.models.GameModel;
import com.stardew.network.Message;

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

        GameState state = message.getFromBody("gameState", GameState.class);

        ArrayList<TileDTO> tileDTOs = state.getTiles();
        ArrayList<PlaceableDTO> placeables = state.getPlaceables();
        PlayerDTO player = state.getPlayer();

        gameState.updateTiles(tileDTOs);
        gameState.updatePlayer(player);
        gameState.updatePlaceables(placeables);
        gameState.updateCamera();
        gameState.updateVisibleTilesBounds();
    }

    public void handleRequestMap(Message message) {
        if (message == null) return;

        if (firstUpdate) {
            int mapWidth = message.getIntFromBody("mapWidth");
            int mapHeight = message.getIntFromBody("mapHeight");
            gameState = new GameModel(mapWidth, mapHeight);
            firstUpdate = false;
        }
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

    public void updateTime(Message message) {
        if (message == null) return;
        TimeDTO dto = message.getFromBody("timeDTO", TimeDTO.class);
        gameState.getTimeManager().setTimeDTO(dto);
    }
}
