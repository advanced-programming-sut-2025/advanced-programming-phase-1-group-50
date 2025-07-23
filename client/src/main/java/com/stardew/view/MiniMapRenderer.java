package com.stardew.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.stardew.models.GameModel;
import com.stardew.models.mapInfo.Tile;

public class MiniMapRenderer {
    private final GameModel gameModel;

    private int mapWidth;
    private int mapHeight;
    public MiniMapRenderer(GameModel gameModel , int mapWidth , int mapHeight){
        this.gameModel = gameModel;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public Texture getMiniMapTexture(){
        Tile[][] tiles = gameModel.getMap().getTiles();
        int tileSizeMiniMap = 4;
        int miniMapWidth = mapWidth * tileSizeMiniMap;
        int miniMapHeight = mapHeight * tileSizeMiniMap;

        Pixmap miniMapPixmap = new Pixmap(miniMapWidth, miniMapHeight, Pixmap.Format.RGBA8888);

        for(int x = 0; x < mapWidth; x++){
            for(int y = 0; y < mapHeight; y++){
                Tile tile = tiles[x][y];
                Color c;

                if(tile.getPlaceable() == null){
                    c = new Color(0.3f, 0.3f, 0.3f, 1f);

                } else {
                    c = tile.getPlaceable().getMiniMapColor();
                }

                miniMapPixmap.setColor(c);
                miniMapPixmap.fillRectangle(x * tileSizeMiniMap, y * tileSizeMiniMap,
                    tileSizeMiniMap, tileSizeMiniMap);
            }
        }





        Texture texture = new Texture(miniMapPixmap);
        miniMapPixmap.dispose();
        return texture;

    }
}
