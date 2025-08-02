package com.stardew.view.GridMap;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.stardew.model.PlaceableDTO;
import com.stardew.model.TileDTO;
import com.stardew.models.GameAssetManagers.GameAssetIDManager;
import com.stardew.models.GameAssetManagers.GamePictureManager;

import java.util.ArrayList;

public class GridMapActor extends Actor {
    private final int cols = 100;
    private final int rows = 75;
    private final float cellSize = 24;
    private CellInfo[][] grid;
    private final ArrayList<TileDTO> tiles;
    private final ArrayList<PlaceableDTO> placeables;
    private int selectedX = -1;  //selected index X in cells in this Actor
    private int selectedY = -1;  //selected index Y in cells in this Actor
    private final int selectionWidth;
    private final int selectionHeight;
    private final TextureRegion normalTexture = GamePictureManager.emptyTile;
    private final TextureRegion selectedTexture = GamePictureManager.selectedTile;

    public GridMapActor(int selectionWidth, int selectionHeight, ArrayList<TileDTO> tiles, ArrayList<PlaceableDTO> placeables) {
        this.selectionWidth = selectionWidth;
        this.selectionHeight = selectionHeight;
        this.tiles = tiles;
        this.placeables = placeables;

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

        grid = new CellInfo[cols][rows];

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                grid[x][y] = new CellInfo();
            }
        }

        for (PlaceableDTO placeable : placeables) {
            int x = placeable.getX();
            int y = placeable.getY();
            for (int i = 0; i < tiles.size(); i++) {
                if (tiles.get(i).getX() == x && tiles.get(i).getY() == y) {
                    grid[i / rows][i % rows].occupied = true;
                    grid[i / rows][i % rows].contentTexture = GameAssetIDManager.getTextureRegion(placeable.getTextureID());
                    break;
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


    public int getSelectedX() {
        int index = selectedX * rows + selectedY;
        return tiles.get(index).getX();
    }

    public int getSelectedY() {
        int index = selectedX * rows + selectedY;
        return tiles.get(index).getY();
    }


}
