package com.stardew.models.animals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.stardew.models.app.App;
import com.stardew.models.mapInfo.Map;
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
}
