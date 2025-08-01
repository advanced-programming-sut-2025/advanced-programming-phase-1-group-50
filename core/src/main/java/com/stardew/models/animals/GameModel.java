package com.stardew.models.animals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.stardew.controller.ForagingControllers.ForagingController;
import com.stardew.controller.PlayerController;
import com.stardew.controller.ToolsControllers.ToolController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.InventoryItem;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.foraging.Fertilizer;
import com.stardew.models.foraging.Growable;
import com.stardew.models.foraging.Seeds;
import com.stardew.models.foraging.TreeSource;
import com.stardew.models.mapInfo.Map;
import com.stardew.models.mapInfo.Pair;
import com.stardew.models.mapInfo.Position;
import com.stardew.models.mapInfo.Tile;
import com.stardew.models.tools.Tool;
import com.stardew.models.userInfo.Player;
import com.stardew.view.InPersonPlayersRelationsWindows.InPersonFriendshipWindow;
import com.stardew.view.InventoryWindows.HotBarActor;
import com.stardew.view.modelsManager.AnimalsManager;
import com.stardew.view.modelsManager.ArtisanMachinesManager;
import com.stardew.view.windows.SmartTooltip;


public class GameModel {
    private final OrthographicCamera camera;
    private final Map map;
    private final int mapWidth, mapHeight;
    private PlayerController playerController;
    private Stage stage;
    private final AnimalsManager animalsManager;
    private final ArtisanMachinesManager artisanMachinesManager;
    private final HotBarActor hotBarActor;
    private final ToolController toolController;
    private final ForagingController foragingController;


    public GameModel(Map map  , int mapWidth , int mapHeight, HotBarActor hotBarActor) {
        this.map = map;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Player p = App.getGame().getCurrentPlayingPlayer();
        camera.position.set(p.getPlayerPosition().getFirst() , p.getPlayerPosition().getSecond() , 0);

        animalsManager = new AnimalsManager();
        artisanMachinesManager = new ArtisanMachinesManager();
        this.hotBarActor = hotBarActor;
        toolController = new ToolController();
        foragingController = new ForagingController();

    }



    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public void update(float delta) {
        Pair<Float , Float> playerPos = App.getGame().getCurrentPlayingPlayer().getPlayerPosition();
        float playerX = playerPos.getFirst() * GamePictureManager.TILE_SIZE;
        float playerY = playerPos.getSecond() * GamePictureManager.TILE_SIZE;

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

        animalsManager.update(delta);
        artisanMachinesManager.update();
        hotBarActor.update();


    }

    public void handleClickTile(int indexTileX, int indexTileY) {
        Tile[][] tiles = map.getTiles();
        if (indexTileX < 0 || indexTileX >= tiles.length || indexTileY < 0 || indexTileY >= tiles[0].length)
            return;

        Tile selectedTile = tiles[indexTileX][indexTileY];

        if (openInPersonFriendshipMenu(indexTileX,indexTileY)) {
            return;
        }

        InventoryItem currentItem = App.getGame().getCurrentPlayingPlayer().getCurrentInventoryItem();

        Result result = null;

        if (currentItem instanceof Tool) {
            result = toolController.useTool(selectedTile, stage);
        }
        else if (currentItem instanceof Fertilizer fertilizer) {
            result = foragingController.fertilize(fertilizer, selectedTile);
        }
        else if (currentItem instanceof Seeds || currentItem instanceof TreeSource) {
            result = foragingController.plant(currentItem, selectedTile);
        }

        if (result != null) {
            SmartTooltip.getInstance().show("  " + result.getMessage() + "  ");
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    SmartTooltip.getInstance().hide();
                }
            }, 3f);
        }
    }

    private boolean openInPersonFriendshipMenu(int indexTileX, int indexTileY) {

        for (Player player : App.getGame().getPlayers()) {

            if (player.equals(App.getGame().getCurrentPlayingPlayer())) {
                continue;
            }

            float deltaX = (player.getPlayerPosition().getFirst()) - indexTileX;
            float deltaY = (player.getPlayerPosition().getSecond()) - indexTileY;

            if ((-0.5 < deltaX && 0.8 > deltaX) && (-0.7 < deltaY && 0.7 > deltaY)) {
                stage.addActor(new InPersonFriendshipWindow(stage,player));
                return true;
            }

        }

        return false;
    }


    public OrthographicCamera getCamera() {
        return camera;
    }

    public Map getMap() {
        return map;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public AnimalsManager getAnimalsManager() {
        return animalsManager;
    }

}
