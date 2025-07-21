package com.stardew.view.RefrigeratorView;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.cooking.Eatable;
import com.stardew.models.cooking.Refrigerator;
import com.stardew.view.windows.SmartTooltip;

import java.util.ArrayList;
import java.util.HashMap;

public class RefrigeratorGridActor extends Actor {
    private final int cols = 12;
    private final int rows = 3;
    private final int cellSize = 48;
    private RefrigeratorItem[][] items;
    private int selectedX = -1;
    private int selectedY = -1;
    private final TextureRegion normalTexture = GamePictureManager.emptyTile;
    private final TextureRegion selectedTexture = GamePictureManager.selectedTile;
    private final BitmapFont smallFont = GamePictureManager.smallFont;
    private final GlyphLayout layout = new GlyphLayout();

    private int lastVisitedCellX = -1;
    private int lastVisitedCellY = -1;


    public RefrigeratorGridActor() {
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

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                SmartTooltip.getInstance().hide();
                lastVisitedCellX = -1;
                lastVisitedCellY = -1;
            }

            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                int cellX = (int)(x / cellSize);
                int cellY = (int)(y / cellSize);
                if (cellX != lastVisitedCellX || cellY != lastVisitedCellY) {
                    lastVisitedCellX = cellX;
                    lastVisitedCellY = cellY;
                    SmartTooltip.getInstance().hide();

                    String info = "";
                    if (cellX >= 0 && cellX < cols && cellY >= 0 && cellY < rows) {
                        if (items[cellX][cellY].occupied) {
                            info = "  " + items[cellX][cellY].eatableItem.toString() + "  ";
                        }
                    }
                    if (!info.isEmpty())
                        SmartTooltip.getInstance().show(info);
                }
                return true;
            }

        });
    }

    private void initializeGrid() {
        Refrigerator refrigerator = App.getGame().getCurrentPlayingPlayer().getBackpack().getRefrigerator();
        HashMap<Eatable, Integer> itemQuantity = refrigerator.getItemsQuantity();
        ArrayList<Eatable> itemsInRefrigerator = new ArrayList<>(itemQuantity.keySet());
        int size = itemsInRefrigerator.size();
        int index = 0;

        items = new RefrigeratorItem[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                items[i][j] = new RefrigeratorItem();
                if (index >= 0 && index < size) {
                    items[i][j].occupied = true;
                    items[i][j].eatableItem = itemsInRefrigerator.get(index);
                    items[i][j].quantity = itemQuantity.get(itemsInRefrigerator.get(index));
                    items[i][j].itemTexture = itemsInRefrigerator.get(index).getInventoryTexture();
                }
                index++;
            }
        }
    }

    public void update() {
        Refrigerator refrigerator = App.getGame().getCurrentPlayingPlayer().getBackpack().getRefrigerator();
        HashMap<Eatable, Integer> itemQuantity = refrigerator.getItemsQuantity();
        ArrayList<Eatable> itemsInRefrigerator = new ArrayList<>(itemQuantity.keySet());
        int index = 0;
        int size = itemsInRefrigerator.size();

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (index >= 0 && index < size) {
                    items[i][j].occupied = true;
                    items[i][j].eatableItem = itemsInRefrigerator.get(index);
                    items[i][j].quantity = itemQuantity.get(itemsInRefrigerator.get(index));
                    items[i][j].itemTexture = itemsInRefrigerator.get(index).getInventoryTexture();
                }
                else {
                    items[i][j].occupied = false;
                    items[i][j].eatableItem = null;
                    items[i][j].quantity = 0;
                    items[i][j].itemTexture = null;
                }
                index++;
            }
        }
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                float drawX = x * cellSize;
                float drawY = y * cellSize;

                boolean isSelected = x == selectedX && y == selectedY;

                TextureRegion base = isSelected ? selectedTexture : normalTexture;
                batch.draw(base, getX() + drawX, getY() + drawY, cellSize, cellSize);

                RefrigeratorItem cell = items[x][y];
                if (cell.occupied && cell.itemTexture != null) {
                    batch.draw(cell.itemTexture, getX() + drawX, getY() + drawY, cellSize, cellSize);

                    String quantity = String.valueOf(cell.quantity);
                    layout.setText(smallFont, quantity);
                    float textX = getX() + drawX + cellSize - layout.width;
                    float textY = getY() + drawY + layout.height - 2;
                    smallFont.draw(batch, layout, textX, textY);
                }
            }
        }
    }

    public RefrigeratorItem[][] getItems() {
        return items;
    }

    public int getSelectedX() {
        return selectedX;
    }

    public int getSelectedY() {
        return selectedY;
    }
}
