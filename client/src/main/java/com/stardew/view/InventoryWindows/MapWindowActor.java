package com.stardew.view.InventoryWindows;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.stardew.model.TextureID;
import com.stardew.models.GameAssetManagers.GameAssetIDManager;
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
    private TextureID[][] tiles;
    private final TextureRegion normalTexture = GamePictureManager.emptyTile;
    private final TextureRegion selectedTexture = GamePictureManager.selectedTile;


    public MapWindowActor(TextureID[][] tiles) {
        this.tiles = tiles;
        init();
        setSize(cols * cellSize, rows * cellSize);

    }

    public void init() {


        grid = new CellInfo[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = new CellInfo();
                if(tiles[i][j] != null) {
                    grid[i][j].contentTexture = GameAssetIDManager.getTextureRegion(tiles[i][j]);

                }
                else {
                    grid[i][j].contentTexture = normalTexture;
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

                TextureRegion tex = grid[i][j].contentTexture != null ? grid[i][j].contentTexture : normalTexture;
                batch.draw(tex, getX() + drawX, getY() + drawY, cellSize, cellSize);
            }
        }
    }
}
