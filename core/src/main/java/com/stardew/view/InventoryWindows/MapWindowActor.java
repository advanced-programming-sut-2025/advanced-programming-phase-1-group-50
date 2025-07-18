package com.stardew.view.InventoryWindows;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.mapInfo.Tile;
import com.stardew.models.userInfo.Player;
import com.stardew.view.GridMap.CellInfo;

public class MapWindowActor extends Actor {
    private final int cols = 250;
    private final int rows = 200;
    private final float cellSize = 24;
    private CellInfo[][] grid;
    private Tile[][] tiles;
    private final TextureRegion normalTexture = GamePictureManager.emptyTile;
    private final TextureRegion selectedTexture = GamePictureManager.selectedTile;


    public MapWindowActor() {

        init();
        setSize(cols * cellSize, rows * cellSize);

    }

    public void init() {
        tiles = App.getGame().getMap().getTiles();
        Player p = App.getGame().getCurrentPlayingPlayer();
        int x = (int) Math.floor(p.getPlayerPosition().getFirst());
        int y =(int) Math.floor(p.getPlayerPosition().getSecond());

        grid = new CellInfo[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = new CellInfo();
                if(tiles[i][j].getPlaceable() == null){
                    if(i == x && j == y){
                        grid[i][j].occupied = true;
                        grid[i][j].contentTexture = GamePictureManager.skillsTextureDrawable.getRegion();
                    }
                    else {
                        grid[i][j].occupied = false;
                        grid[i][j].contentTexture = null;
                    }

                }
                else {
                    grid[i][j].occupied = true;
                    grid[i][j].contentTexture = tiles[i][j].getPlaceable().getTexture();
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                float drawX = i * cellSize;
                float drawY = j * cellSize;

                TextureRegion base = !grid[i][j].occupied ? selectedTexture : normalTexture;
                batch.draw(base, getX() + drawX, getY() + drawY, cellSize, cellSize);

                CellInfo cell = grid[i][j];
                if (cell.occupied && cell.contentTexture != null) {
                    batch.draw(cell.contentTexture, getX() + drawX, getY() + drawY, cellSize, cellSize);
                }
            }
        }
    }
}
