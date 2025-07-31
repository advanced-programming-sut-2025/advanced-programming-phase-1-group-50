package com.stardew.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.stardew.controller.TimeManager;
import com.stardew.model.PlaceableDTO;
import com.stardew.model.PlayerDTO;
import com.stardew.model.TileDTO;
import com.stardew.model.TimeDTO;
import com.stardew.models.GameAssetManagers.GamePictureManager;

import java.util.ArrayList;


public class GameModel {
    private final OrthographicCamera camera;
    private ArrayList<TileDTO> tiles;
    private ArrayList<PlaceableDTO> placeables;
    private PlayerDTO player;
    private TimeDTO time;
    private final int mapWidth, mapHeight;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private final Object lock = new Object();
    private final Object boundsLock = new Object();

//    private final Map map;
//    private PlayerController playerController;
//    private Stage stage;
//    private final AnimalsManager animalsManager;
//    private final ArtisanMachinesManager artisanMachinesManager;
//    private final HotBarActor hotBarActor;
//    private final ToolController toolController;
//    private final ForagingController foragingController;


//    public GameModel(Map map  , int mapWidth , int mapHeight, HotBarActor hotBarActor) {
    public GameModel(int mapWidth , int mapHeight) {
//        this.map = map;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);

//        Player p = App.getGame().getCurrentPlayingPlayer();
//        camera.position.set(p.getPlayerPosition().getFirst() , p.getPlayerPosition().getSecond() , 0);

//        animalsManager = new AnimalsManager();
//        artisanMachinesManager = new ArtisanMachinesManager();
//        this.hotBarActor = hotBarActor;
//        toolController = new ToolController();
//        foragingController = new ForagingController();

    }



//    public void setPlayerController(PlayerController playerController) {
//        this.playerController = playerController;
//    }

    public void updateCamera() {
        float playerX = player.getX() * GamePictureManager.TILE_SIZE;
        float playerY = player.getY() * GamePictureManager.TILE_SIZE;

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

//        animalsManager.update(delta);
//        artisanMachinesManager.update();
//        hotBarActor.update();


    }


    public void updateVisibleTilesBounds() {
        float camX = camera.position.x;
        float camY = camera.position.y;
        float viewportWidth = camera.viewportWidth;
        float viewportHeight = camera.viewportHeight;

        int tileSize = GamePictureManager.TILE_SIZE;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        synchronized (boundsLock) {
            startX = Math.max(0, (int) (cameraLeft / tileSize) - 3);
            startY = Math.max(0, (int) (cameraBottom / tileSize) - 3);
            endX = Math.min(mapWidth, (int) ((camX + viewportWidth / 2) / tileSize) + 4);
            endY = Math.min(mapHeight, (int) ((camY + viewportHeight / 2) / tileSize) + 4);
        }

    }


    public void handleClickTile(int indexTileX, int indexTileY) {
//        Tile[][] tiles = map.getTiles();
//        if (indexTileX < 0 || indexTileX >= tiles.length || indexTileY < 0 || indexTileY >= tiles[0].length)
//            return;
//
//        Tile selectedTile = tiles[indexTileX][indexTileY];
//
//        if (openInPersonFriendshipMenu(indexTileX,indexTileY)) {
//            return;
//        }
//
//        InventoryItem currentItem = App.getGame().getCurrentPlayingPlayer().getCurrentInventoryItem();
//
//        Result result = null;
//
//        if (currentItem instanceof Tool) {
//            result = toolController.useTool(selectedTile, stage);
//        }
//        else if (currentItem instanceof Fertilizer fertilizer) {
//            result = foragingController.fertilize(fertilizer, selectedTile);
//        }
//        else if (currentItem instanceof Seeds || currentItem instanceof TreeSource) {
//            result = foragingController.plant(currentItem, selectedTile);
//        }
//
//        if (result != null) {
//            SmartTooltip.getInstance().show("  " + result.getMessage() + "  ");
//            Timer.schedule(new Timer.Task() {
//                @Override
//                public void run() {
//                    SmartTooltip.getInstance().hide();
//                }
//            }, 3f);
//        }
    }

//    private boolean openInPersonFriendshipMenu(int indexTileX, int indexTileY) {
//
//        for (Player player : App.getGame().getPlayers()) {
//
//            if (player.equals(App.getGame().getCurrentPlayingPlayer())) {
//                continue;
//            }
//
//            float deltaX = (player.getPlayerPosition().getFirst()) - indexTileX;
//            float deltaY = (player.getPlayerPosition().getSecond()) - indexTileY;
//
//            if ((-0.5 < deltaX && 0.8 > deltaX) && (-0.7 < deltaY && 0.7 > deltaY)) {
//                stage.addActor(new InPersonFriendshipWindow(stage,player));
//                return true;
//            }
//
//        }
//
//        return false;
//    }


    public OrthographicCamera getCamera() {
        return camera;
    }

    public void updateTiles(ArrayList<TileDTO> tiles) {
        synchronized (lock) {
            this.tiles = tiles;
        }
    }

    public void updatePlayer(PlayerDTO player) {
        synchronized (lock) {
            this.player = player;
        }
    }

    public void updatePlaceables(ArrayList<PlaceableDTO> placeables) {
        synchronized (lock) {
            this.placeables = placeables;
        }
    }

    public void updateTime(TimeDTO time) {
        this.time = time;
    }

    public ArrayList<TileDTO> getTiles() {
        synchronized (lock) {
            return tiles;
        }
    }

    public PlayerDTO getPlayer() {
        synchronized (lock) {
            return player;
        }
    }

    public ArrayList<PlaceableDTO> getPlaceables() {
        synchronized (lock) {
            return placeables;
        }
    }

    public TimeDTO getTime() {
        return time;
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

    //    public Map getMap() {
//        return map;
//    }

//    public void setStage(Stage stage) {
//        this.stage = stage;
//    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

//    public PlayerController getPlayerController() {
//        return playerController;
//    }

//    public AnimalsManager getAnimalsManager() {
//        return animalsManager;
//    }

}
