package com.stardew.controller;

import com.badlogic.gdx.Gdx;
import com.google.gson.reflect.TypeToken;
import com.stardew.model.*;
import com.stardew.models.GameAssetManagers.GameAssetIDManager;
import com.stardew.models.GameModel;
import com.stardew.network.Message;
import com.stardew.view.InventoryWindows.HotBarActor;
import com.stardew.view.InventoryWindows.InventoryWindow;
import com.stardew.view.ReactionWindows.ReactionTable;

import java.util.ArrayList;

public class GameModelController {
    private static GameModelController instance;
    private GameModel gameModel;
    private boolean firstUpdate = true;



    private GameModelController() {}

    public static GameModelController getInstance() {
        if (instance == null) {
            instance = new GameModelController();
        }
        return instance;
    }


    public void handleUpdateTiles(Message message) {
        if (message == null) return;

        ArrayList<TileDTO> tileDTOs = message.getFromBody(
            "tiles", new TypeToken<ArrayList<TileDTO>>(){}.getType());
        ArrayList<PlaceableDTO> placeables = message.getFromBody(
            "placeables", new TypeToken<ArrayList<PlaceableDTO>>(){}.getType());

        gameModel.updateTiles(tileDTOs);
        gameModel.updatePlaceables(placeables);
    }

    public void handleUpdatePlayers(Message message) {
        if (message == null) return;

        PlayerDTO mainPlayer = message.getFromBody("main_player", PlayerDTO.class);
        ArrayList<PlayerDTO> otherPlayers = message.getFromBody(
            "other_players", new TypeToken<ArrayList<PlayerDTO>>(){}.getType());

        gameModel.updateMainPlayer(mainPlayer);
        gameModel.updateOtherPlayers(otherPlayers);
        gameModel.updateCamera();
        gameModel.updateVisibleTilesBounds();
    }

    public void handleRequestMap(Message message) {
        if (message == null) return;

        if (firstUpdate) {
            int mapWidth = message.getIntFromBody("mapWidth");
            int mapHeight = message.getIntFromBody("mapHeight");
            gameModel = new GameModel(mapWidth, mapHeight);
            firstUpdate = false;
        }
    }

    public void handleUpdateTime(Message message) {
        if (message == null) return;
        TimeDTO dto = message.getFromBody("timeDTO", TimeDTO.class);
        gameModel.updateTime(dto);
    }

    public void handleUpdateAnimals(Message message) {
        if (message == null) return;
        ArrayList<AnimalDTO> animals = message.getFromBody("animals", new TypeToken<ArrayList<AnimalDTO>>(){}.getType());
        gameModel.updateAnimals(animals);
    }

    public void updateHotBar(Message message) {
        if (message == null) return;
        InventoryItemDTO[] items = message.getFromBody("hotBar", InventoryItemDTO[].class);
        gameModel.updateHotBar(items);
        Gdx.app.postRunnable(() -> {
            if (HotBarActor.isOpen()) {
                HotBarActor.getCurrentInstance().update();
            }
        });
    }


    public GameModel getGameModel() {
        return gameModel;
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

    public void handleUpdateCoin(Message message) {
        if (message == null) return;
        int coin = message.getIntFromBody("coin");
        Gdx.app.postRunnable(() -> {
            gameModel.updateCoin(coin);
        });
    }


    public void handleUpdateScoreBoard(Message message) {
        if (message == null) return;
        ArrayList<ScoreBoardDTO> dtoS = message.getFromBody("scoreBoard", new TypeToken<ArrayList<ScoreBoardDTO>>(){}.getType());
        Gdx.app.postRunnable(() -> {
            gameModel.updateScoreBoard(dtoS);
        });
    }

    public void handleFadeOut(Message message) {
        if (message == null) return;
        Gdx.app.postRunnable(() -> {
            TimeManager.getInstance().setFade(false);
        });


    }
}
