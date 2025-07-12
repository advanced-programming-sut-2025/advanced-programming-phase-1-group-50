package com.stardew.view.InventoryWindows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.InventoryItem;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.Player;

public class HotBarActor extends Actor {
    private Player currentPlayer = App.getGame().getCurrentPlayingPlayer();
    private final int itemCount = 10;
    private final float itemSize = 40f;
    private ItemCell[] cells = new ItemCell[itemCount];
    private TextureRegion normalTexture = GamePictureManager.emptyTile;
    private TextureRegion selectedTexture = GamePictureManager.selectedTile;
    private int selectedIndex = -1;

    public HotBarActor() {
        initialize();

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                int index = (int) (x / itemSize);
                if (index >= 0 && index < itemCount) {
                    selectedIndex = index;
                    currentPlayer.setCurrentInventoryItem(currentPlayer.getHotBar()[index]);

                }
                return true;
            }
        });
    }

    public void initialize() {
        setSize(itemSize * itemCount, itemSize);
        setPosition(Gdx.graphics.getWidth() / 2f - getWidth() / 2f, 70);
        InventoryItem[] items = currentPlayer.getHotBar();

        for (int i = 0; i < itemCount; i++) {
            cells[i] = new ItemCell();

            if (items[i] != null && items[i].getInventoryTexture() != null) {
                cells[i].textureRegion = items[i].getInventoryTexture();
            } else {
                cells[i].textureRegion = GamePictureManager.emptyTile;
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (cells == null) {
            return;
        }

        for (int i = 0; i < itemCount; i++) {
            float drawX = i * itemSize;
            float drawY = 0f;

            TextureRegion base = (i == selectedIndex) ? selectedTexture : normalTexture;
            batch.draw(base, getX() + drawX, getY() + drawY , itemSize , itemSize);

            ItemCell item = cells[i];
            if (item.textureRegion != null) {
                batch.draw(item.textureRegion, getX() + drawX, getY() + drawY , itemSize , itemSize);
            }
        }
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        currentPlayer.setCurrentInventoryItem(currentPlayer.getHotBar()[selectedIndex]);
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public int getItemCount() {
        return itemCount;
    }
}

