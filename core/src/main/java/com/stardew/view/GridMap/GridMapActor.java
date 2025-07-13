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
    private int selectedX = -1;  //selected index X in cells in this Actor
    private int selectedY = -1;  //selected index Y in cells in this Actor
    private final int selectionWidth;
    private final int selectionHeight;
    private final TextureRegion normalTexture = GamePictureManager.emptyTile;  //TODO
    private final TextureRegion selectedTexture = GamePictureManager.selectedTile;  //TODO
    private int startX;  //the X index of tiles in map for current player
    private int startY;  //the Y index of tiles in map for current player

    public GridMapActor(int selectionWidth, int selectionHeight) {
        this.selectionWidth = selectionWidth;
        this.selectionHeight = selectionHeight;

        initializeGrid();

        setSize(cols * cellSize, rows * cellSize);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                int cellX = (int)(x / cellSize);
                int cellY = (int)(y / cellSize);
                if (cellX >= 0 && cellX < cols && cellY >= 0 && cellY < rows &&
                    cellX + selectionWidth <= cols && cellY + selectionHeight <= rows) {
                    selectedX = cellX;
                    selectedY = cellY;
                }
                return true;
            }
        });
    }

    public void initializeGrid() {
        tiles = App.getGame().getMap().getTiles();

        Player currentPlayer = App.getGame().getCurrentPlayingPlayer();

        startX = App.getGame().getMap().getFarmStartX(currentPlayer);
        startY = App.getGame().getMap().getFarmStartY(currentPlayer);

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

                boolean inSelection = selectedX != -1 && selectedY != -1 &&
                                      x >= selectedX && x < selectedX + selectionWidth &&
                                      y >= selectedY && y < selectedY + selectionHeight;

                TextureRegion base = inSelection ? selectedTexture : normalTexture;
                batch.draw(base, getX() + drawX, getY() + drawY, cellSize, cellSize);

                CellInfo cell = grid[x][y];
                if (cell.occupied && cell.contentTexture != null) {
                    batch.draw(cell.contentTexture, getX() + drawX, getY() + drawY, cellSize, cellSize);
                }
            }
        }
    }

    public Tile getSelectedTile() {
        if (selectedX == -1 || selectedY == -1)
            return null;
        return tiles[selectedX + startX][selectedY + startY];
    }


}
