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
    private final int cols = 100; //TODO
    private final int rows = 75; //TODO
    private final float cellSize = 24; //TODO
    private CellInfo[][] grid;
    private Tile[][] tiles;
    private int selectedX = -1;
    private int selectedY = -1;
    private int selectedTileX = -1;
    private int selectedTileY = -1;
    private final TextureRegion normalTexture = GamePictureManager.emptyTile;  //TODO
    private final TextureRegion selectedTexture = GamePictureManager.selectedTile;  //TODO
    private int startX;
    private int startY;

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
                    selectedTileX = cellX + startX;
                    selectedTileY = cellY + startY;
                }
                return true;
            }
        });
    }

    public void initializeGrid() {
        tiles = App.getGame().getMap().getTiles();

        int[] startXForMap = {0, 150, 0, 150};
        int[] startYForMap = {0, 0, 125, 125};
        Player currentPlayer = App.getGame().getCurrentPlayingPlayer();

        int playerIndex;
        for (playerIndex = 0; playerIndex < App.getGame().getPlayers().size(); playerIndex++) {
            if (App.getGame().getPlayers().get(playerIndex).getUsername().equals(currentPlayer.getUsername())) break;
        }

        startX = startXForMap[playerIndex];
        startY = startYForMap[playerIndex];

        grid = new CellInfo[cols][rows];
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                grid[x][y] = new CellInfo();
                if (tiles[x + startX][y + startY].getPlaceable() == null) {
                    grid[x][y].occupied = false;
                    grid[x][y].contentTexture = null;
                } else {
                    grid[x][y].occupied = true;
                    grid[x][y].contentTexture = tiles[x + startX][y + startY].getPlaceable().getTexture();
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

    public Tile getSelectedTile() {
        if (selectedTileX == -1 || selectedTileY == -1)
            return null;
        return tiles[selectedTileX][selectedTileY];
    }


}
