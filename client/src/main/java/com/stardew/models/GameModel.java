package com.stardew.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.model.*;
import com.stardew.models.GameAssetManagers.GamePictureManager;

import java.util.ArrayList;


public class GameModel {
    private final OrthographicCamera camera;
    private ArrayList<TileDTO> tiles = new ArrayList<>();
    private ArrayList<PlaceableDTO> placeables = new ArrayList<>();
    private ArrayList<PlayerDTO> otherPlayers = new ArrayList<>();
    private PlayerDTO mainPlayer;
    private TimeDTO time;
    private ArrayList<AnimalDTO> animals = new ArrayList<>();
    private final int mapWidth, mapHeight;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private final Object lock = new Object();
    private final Object boundsLock = new Object();
    private InventoryItemDTO[] hotBar;
    private int coin;
    private ArrayList<ScoreBoardDTO> scoreBoard;


    public GameModel(int mapWidth , int mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);
    }



    public void updateCamera() {
        float playerX = mainPlayer.getX() * GamePictureManager.TILE_SIZE;
        float playerY = mainPlayer.getY() * GamePictureManager.TILE_SIZE;

        float cameraX = camera.position.x;
        float cameraY = camera.position.y;

        float viewHalfWidth = camera.viewportWidth / 2;
        float viewHalfHeight = camera.viewportHeight / 2;

        float border = GamePictureManager.TILE_SIZE * 5;

        if(playerX < cameraX - viewHalfWidth + border){
            cameraX = playerX + viewHalfWidth - border;
        } else if (playerX > cameraX + viewHalfWidth - border) {
            cameraX = playerX - viewHalfWidth + border;
        }

        if (playerY < cameraY - viewHalfHeight + border) {
            cameraY = playerY + viewHalfHeight - border;
        } else if (playerY > cameraY + viewHalfHeight - border) {
            cameraY = playerY - viewHalfHeight + border;
        }

        cameraX = Math.max(viewHalfWidth, Math.min(cameraX, mapWidth * GamePictureManager.TILE_SIZE - viewHalfWidth));
        cameraY = Math.max(viewHalfHeight, Math.min(cameraY, mapHeight * GamePictureManager.TILE_SIZE - viewHalfHeight));

        camera.position.set(cameraX, cameraY, 0);
        camera.update();
    }


    public void updateVisibleTilesBounds() {
        float camX = camera.position.x;
        float camY = camera.position.y;
        float viewportWidth = camera.viewportWidth;
        float viewportHeight = camera.viewportHeight;

        int tileSize = GamePictureManager.TILE_SIZE;

        synchronized (boundsLock) {
            startX = Math.max(0, (int) ((camX - viewportWidth / 2) / tileSize) - 5);
            startY = Math.max(0, (int) ((camY - viewportHeight / 2) / tileSize) - 5);
            endX = Math.min(mapWidth, (int) ((camX + viewportWidth / 2) / tileSize) + 5);
            endY = Math.min(mapHeight, (int) ((camY + viewportHeight / 2) / tileSize) + 5);
        }

    }



    public OrthographicCamera getCamera() {
        return camera;
    }

    public void updateTiles(ArrayList<TileDTO> tiles) {
        synchronized (lock) {
            this.tiles = tiles;
        }
    }

    public void updateMainPlayer(PlayerDTO player) {
        synchronized (lock) {
            this.mainPlayer = player;
        }
    }

    public void updateOtherPlayers(ArrayList<PlayerDTO> otherPlayers) {
        this.otherPlayers = otherPlayers;
    }

    public void updatePlaceables(ArrayList<PlaceableDTO> placeables) {
        synchronized (lock) {
            this.placeables = placeables;
        }
    }

    public void updateTime(TimeDTO time) {
        this.time = time;
    }

    public void updateAnimals(ArrayList<AnimalDTO> animals) {
        this.animals = animals;
    }

    public ArrayList<TileDTO> getTiles() {
        synchronized (lock) {
            return tiles;
        }
    }

    public PlayerDTO getPlayer() {
        synchronized (lock) {
            return mainPlayer;
        }
    }

    public ArrayList<PlayerDTO> getOtherPlayers() {
        return otherPlayers;
    }

    public ArrayList<PlayerDTO> getAllPlayers() {
        ArrayList<PlayerDTO> players = new ArrayList<>(otherPlayers);
        players.add(mainPlayer);
        return players;
    }

    public ArrayList<PlaceableDTO> getPlaceables() {
        synchronized (lock) {
            return placeables;
        }
    }

    public TimeDTO getTime() {
        return time;
    }

    public ArrayList<AnimalDTO> getAnimals() {
        return animals;
    }

    public int getStartX() {
        synchronized (boundsLock) {
            return startX;
        }
    }

    public int getStartY() {
        synchronized (boundsLock) {
            return startY;
        }
    }

    public int getEndX() {
        synchronized (boundsLock) {
            return endX;
        }
    }

    public int getEndY() {
        synchronized (boundsLock) {
            return endY;
        }
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getPlayerEnergy() {
        return mainPlayer.getEnergy();
    }

    public void updateHotBar(InventoryItemDTO[] hotBar) {
        this.hotBar = hotBar;
    }

    public InventoryItemDTO[] getHotBar() {
        return hotBar;
    }

    public void updateCoin(int coin) {
        this.coin = coin;
    }

    public int getCoin() {
        return coin;
    }

    public void updateScoreBoard(ArrayList<ScoreBoardDTO> scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public ArrayList<ScoreBoardDTO> getScoreBoard() {
        return scoreBoard;
    }

}
