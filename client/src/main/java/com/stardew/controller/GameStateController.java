package com.stardew.controller;

import com.stardew.model.GameState;
import com.stardew.model.PlaceableDTO;
import com.stardew.model.PlayerDTO;
import com.stardew.model.TileDTO;
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
        ArrayList<PlaceableDTO> placeables = state.getPlaceables();// TODO handle
        PlayerDTO player = state.getPlayer();

        gameState.updateTiles(tileDTOs);
        gameState.updatePlayer(player);
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
}
