package com.stardew.controller.CookingAndCraftingControllers;

import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.mapInfo.Tile;

public class TileSelectionController {
    private Tile selectedTile;
    private Tile[][] tiles;
    private final int width;
    private final int height;


    public TileSelectionController(int width, int height) {
        tiles = App.getGame().getMap().getTiles();
        this.width = width;
        this.height = height;
    }


    public Result checkTileSelection(Tile startTile) {

        if (startTile == null)
            return new Result(false, "You must select a startTile!");

        int x = startTile.getPosition().getX();
        int y = startTile.getPosition().getY();

        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                if (tiles[i][j].getPlaceable() != null)
                    return new Result(false, "You must select a free startTile!");
            }
        }

        selectedTile = startTile;
        return new Result(true, "");

    }

    public Tile getSelectedTile() {
        return selectedTile;
    }
}
