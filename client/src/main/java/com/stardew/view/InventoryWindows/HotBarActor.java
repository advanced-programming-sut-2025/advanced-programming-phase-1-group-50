package com.stardew.view.InventoryWindows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.InventoryItem;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.Player;
import com.stardew.view.windows.SmartTooltip;

public class HotBarActor extends Actor {
//    private final Player currentPlayer = App.getGame().getCurrentPlayingPlayer();
    private final int itemCount = 10;
    private final float itemSize = 60f;
    private final ItemCell[] cells = new ItemCell[itemCount];
    private final TextureRegion normalTexture = GamePictureManager.emptyTile;
    private final TextureRegion selectedTexture = GamePictureManager.selectedTile;
    private int selectedIndex = -1;
    private int lastVisitedCellX = -1;
    private final BitmapFont smallFont = GamePictureManager.smallFont;
    private final GlyphLayout layout = new GlyphLayout();



    public HotBarActor() {
//        initialize();

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                int index = (int) (x / itemSize);
                if (index >= 0 && index < itemCount) {
                    selectedIndex = index;
//                    currentPlayer.setCurrentInventoryItem(currentPlayer.getHotBar()[index]);

                }
                return true;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                SmartTooltip.getInstance().hide();
                lastVisitedCellX = -1;
            }

            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                int index = (int)(x / itemSize);
                if (index != lastVisitedCellX) {
                    lastVisitedCellX = index;
                    SmartTooltip.getInstance().hide();

                    String info = "";
                    if (index >= 0 && index < cells.length) {
                        if (cells[index].textureRegion != null) {
                            info = "  " + cells[index].inventoryItem.toString() + "  ";
                        }
                    }

                    if (!info.isEmpty())
                        SmartTooltip.getInstance().show(info);

                }
                return true;
            }
        });
    }

    public void initialize() {
        setSize(itemSize * itemCount, itemSize);
        setPosition(Gdx.graphics.getWidth() / 2f - getWidth() / 2f, 70);
//        InventoryItem[] items = currentPlayer.getHotBar();

        for (int i = 0; i < itemCount; i++) {
            cells[i] = new ItemCell();

//            if (items[i] != null && items[i].getInventoryTexture() != null) {
//                cells[i].textureRegion = items[i].getInventoryTexture();
//                cells[i].inventoryItem = items[i];
//                cells[i].quantity = App.getGame().getCurrentPlayingPlayer().getQuantityOfIngredient(items[i]);
//            } else {
//                cells[i].textureRegion = null;
//                cells[i].inventoryItem = null;
//                cells[i].quantity = 0;
//            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        for (int i = 0; i < itemCount; i++) {
            float drawX = i * itemSize;
            float drawY = 0f;

            TextureRegion base = (i == selectedIndex) ? selectedTexture : normalTexture;
            batch.draw(base, getX() + drawX, getY() + drawY , itemSize , itemSize);

            ItemCell item = cells[i];
            if (item.textureRegion != null) {
                batch.draw(item.textureRegion, getX() + drawX, getY() + drawY , itemSize , itemSize);

                String quantity = String.valueOf(item.quantity);
                layout.setText(smallFont, quantity);
                float textX = getX() + drawX + itemSize - layout.width;
                float textY = getY() + drawY + layout.height - 2;
                smallFont.draw(batch, layout, textX, textY);
            }
        }
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
//        currentPlayer.setCurrentInventoryItem(currentPlayer.getHotBar()[selectedIndex]);
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void update(){
        initialize();
    }
}

