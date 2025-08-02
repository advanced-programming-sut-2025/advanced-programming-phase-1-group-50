package com.stardew.view.InventoryWindows;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.stardew.model.InventoryItemDTO;
import com.stardew.models.GameAssetManagers.GameAssetIDManager;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.InventoryItem;
import com.stardew.models.app.App;
import com.stardew.view.windows.SmartTooltip;

import java.util.ArrayList;

public class BackpackGridActor extends Actor {
    private final float cellSize = 48f;
    private ItemCell[][] cells;
    private int selectedX = -1;
    private int selectedY = -1;
    private TextureRegion normalTexture = GamePictureManager.emptyTile;
    private TextureRegion selectedTexture = GamePictureManager.selectedTile;
    private final BitmapFont smallFont = GamePictureManager.smallFont;
    private final GlyphLayout layout = new GlyphLayout();
    private ArrayList<InventoryItemDTO> dto;

    private int lastVisitedCellX = -1;
    private int lastVisitedCellY = -1;

    public BackpackGridActor(ArrayList<InventoryItemDTO> dto) {
        this.dto = dto;
        initializeGrid();

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                int cellX = (int) (x / cellSize);
                int cellY = (int) (y / cellSize);
                if (cellY >= 0 && cellY < cells.length && cellX >= 0 && cellX < cells[0].length) {
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
                    if (cellY >= 0 && cellY < cells.length && cellX >= 0 && cellX < cells[0].length) {
                        if (cells[cellY][cellX].textureRegion != null) {
                            info = "  " + cells[cellY][cellX].inventoryItem.toString() + "  ";
                        }
                    }

                    if (!info.isEmpty())
                        SmartTooltip.getInstance().show(info);

                }
                return true;
            }
        });
    }

    public void initializeGrid() {
        InventoryItemDTO[][] it = getInventoryItems(dto);


        int size = dto.size();
        int index = 0;

        if (it.length == 0 || it[0].length == 0) return;

        cells = new ItemCell[it.length][it[0].length];

        for (int i = 0; i < it.length; i++) {
            for (int j = 0; j < it[i].length; j++) {
                cells[i][j] = new ItemCell();
                if(index >= 0 && index < size) {
                    cells[i][j].textureRegion = GameAssetIDManager.getTextureRegion(dto.get(index).getTextureID());
                    cells[i][j].inventoryItem = dto.get(index);
                    cells[i][j].quantity =dto.get(index).getQuantity();
                }
                else{
                    cells[i][j].textureRegion = null;
                    cells[i][j].inventoryItem = null;
                    cells[i][j].quantity = 0;
                }
                index++;
            }
        }
    }

    public InventoryItemDTO[][] getInventoryItems(ArrayList<InventoryItemDTO> items) {


        int total = items.size();
        int cols = 10;
        int rows = Math.max(5, (int)Math.ceil((double) total / cols));

        setSize(cols * cellSize, rows * cellSize);

        InventoryItemDTO[][] items1 = new InventoryItemDTO[rows][cols];

        for (int index = 0; index < items.size(); index++) {
            int row = index / cols;
            int col = index % cols;
            items1[row][col] = items.get(index);
        }

        return items1;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (cells == null) return;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                float drawX = j * cellSize;
                float drawY = i * cellSize;

                TextureRegion base = (i == selectedY && j == selectedX) ? selectedTexture : normalTexture;
                batch.draw(base, getX() + drawX, getY() + drawY, cellSize, cellSize);

                ItemCell item = cells[i][j];
                if (item.textureRegion != null) {
                    batch.draw(item.textureRegion, getX() + drawX, getY() + drawY, cellSize, cellSize);
                    String quantity = String.valueOf(item.quantity);
                    layout.setText(smallFont, quantity);
                    float textX = getX() + drawX + cellSize - layout.width;
                    float textY = getY() + drawY + layout.height - 2;
                    smallFont.draw(batch, layout, textX, textY);
                }
            }
        }
    }

    public int getSelectedX() {
        return selectedX;
    }

    public int getSelectedY(){
        return selectedY;
    }

    public InventoryItemDTO getInventoryItemByXAndY(int x, int y) {
        if (x == -1 || y == -1) return null;
        return cells[y][x].inventoryItem;
    }

    private void update(){
        initializeGrid();
    }

    public void updateDTO(ArrayList<InventoryItemDTO> dto) {
        this.dto = dto;
        update();
    }
}
