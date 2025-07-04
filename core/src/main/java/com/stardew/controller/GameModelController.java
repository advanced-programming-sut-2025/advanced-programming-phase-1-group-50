package com.stardew.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.stardew.models.app.App;
import com.stardew.models.mapInfo.Map;
import com.stardew.models.mapInfo.Tile;
import com.stardew.models.userInfo.Player;

public class GameModelController {
    private Tile[][] tiles;
    private Map map;
    private Player currentPlayingPlayer;
    private final int mapWidth;
    private final int mapHeight;
    private OrthographicCamera camera;


    public GameModelController(int mapWidth, int mapHeight ,Map map ) {
        this.map = map;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.tiles = map.getTiles();

    }
}
