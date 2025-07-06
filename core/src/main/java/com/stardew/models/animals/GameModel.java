package com.stardew.models.animals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.mapInfo.Map;
import com.stardew.models.mapInfo.Pair;
import com.stardew.models.userInfo.Player;


public class GameModel {
    private OrthographicCamera camera;
    private Map map;
    private final int mapWidth, mapHeight;

    public GameModel(Map map  , int mapWidth , int mapHeight) {
        this.map = map;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Player p = App.getGame().getCurrentPlayingPlayer();
        camera.position.set(p.getPlayerPosition().getFirst() , p.getPlayerPosition().getSecond() , 0);


    }

    public void update(float delta) {
        Pair<Float , Float> playerPos = App.getGame().getCurrentPlayingPlayer().getPlayerPosition();
        float playerX = playerPos.getFirst() * GamePictureManager.TILE_SIZE;
        float playerY = playerPos.getSecond() * GamePictureManager.TILE_SIZE;

        float cameraX = camera.position.x;
        float cameraY = camera.position.y;

        float viewHalfWidth = camera.viewportWidth / 2;
        float viewHalfHeight = camera.viewportHeight / 2;

        float border = GamePictureManager.TILE_SIZE * 2;

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

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Map getMap() {
        return map;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }
}
