package com.stardew.view.GridMap;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.mapInfo.Tile;
import com.stardew.models.userInfo.Player;

public class GridMapActor extends Actor {
    private int cols = 100; //TODO
    private int rows = 75; //TODO
    private float cellSize = 24; //TODO
    private CellInfo[][] grid;
    private int selectedX = -1;
    private int selectedY = -1;
    private TextureRegion normalTexture = GamePictureManager.emptyTile;  //TODO
    private TextureRegion selectedTexture = GamePictureManager.selectedTile;  //TODO

    public GridMapActor() {
        initializeGrid();

        setSize(cols * cellSize, rows * cellSize);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                int cellX = (int)(x / cellSize);
                int cellY = (int)(y / cellSize);
                if (cellX >= 0 && cellX < cols && cellY >= 0 && cellY < rows) {
                    selectedX = cellX;
                    selectedY = cellY;
                }
                return true;
            }
        });
    }

    public void initializeGrid() {
        Tile[][] tiles = App.getGame().getMap().getTiles();
        int startX, startY; //TODO according to player

        int[] startXForMap = {0, 150, 0, 150};
        int[] startYForMap = {0, 0, 125, 125};
        Player currentPlayer = App.getGame().getCurrentPlayingPlayer();
        int index;
        for (index = 0; index < App.getGame().getPlayers().size(); index++) {
            if (App.getGame().getPlayers().get(index).getUsername().equals(currentPlayer.getUsername())) break;
        }

        startX = startXForMap[index];
        startY = startYForMap[index];

        grid = new CellInfo[cols][rows];
        for (int x = startX; x < startX + cols; x++) {
            for (int y = startY; y < startY + rows; y++) {
                grid[x][y] = new CellInfo();
                grid[x][y].occupied = tiles[x][y].getPlaceable() != null;
                if (tiles[x][y].getPlaceable() != null && tiles[x][y].getPlaceable().getTexture() != null) {
                    grid[x][y].contentTexture = tiles[x][y].getPlaceable().getTexture();
                } else {
                    grid[x][y].contentTexture = null;
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                float drawX = x * cellSize;
                float drawY = y * cellSize;

                TextureRegion base = (x == selectedX && y == selectedY) ? selectedTexture : normalTexture;
                batch.draw(base, getX() + drawX, getY() + drawY, cellSize, cellSize);

                CellInfo cell = grid[x][y];
                if (cell.occupied && cell.contentTexture != null) {
                    batch.draw(cell.contentTexture, getX() + drawX, getY() + drawY, cellSize, cellSize);
                }
            }
        }
    }


}
