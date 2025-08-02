package com.stardew.controller;

import com.badlogic.gdx.Gdx;
import com.google.gson.reflect.TypeToken;
import com.stardew.Main;
import com.stardew.model.*;
import com.stardew.models.GameAssetManagers.GameAssetIDManager;
import com.stardew.models.GameModel;
import com.stardew.network.Message;
import com.stardew.view.InventoryWindows.HotBarActor;
import com.stardew.view.InventoryWindows.InventoryWindow;
import com.stardew.view.ReactionWindows.ReactionTable;

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
        gameState.updateEnergy(player.getEnergy());
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

    public void handleUpdateTime(Message message) {
        if (message == null) return;
        TimeDTO dto = message.getFromBody("timeDTO", TimeDTO.class);
        gameState.updateTime(dto);
    }

    public void updateHotBar(Message message) {
        if (message == null) return;
        InventoryItemDTO[] items = message.getFromBody("hotBar", InventoryItemDTO[].class);
        gameState.updateHotBar(items);
        Gdx.app.postRunnable(() -> {
            if (HotBarActor.isOpen()) {
                HotBarActor.getCurrentInstance().update();
            }
        });
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

    public void handleUpdateInventoryList(Message message) {
        ArrayList<InventoryItemDTO> dto = message.getFromBody("inventory", new TypeToken<ArrayList<InventoryItemDTO>>(){}.getType());

        Gdx.app.postRunnable(() -> {

                InventoryWindow.getInstance().updateDTO(dto);

        });


    }

    public void handleUpdateReaction(Message message) {
        if (message == null) return;
        TextureID emoji = message.getFromBody("emoji", TextureID.class);
        String username = message.getFromBody("username");
        Gdx.app.postRunnable(() -> {
            ReactionTable.getInstance().update(GameAssetIDManager.getTextureRegion(emoji), username);
        });
    }
}
