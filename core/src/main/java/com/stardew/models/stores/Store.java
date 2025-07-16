package com.stardew.models.stores;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.Main;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Placeable;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.view.GameScreenMenu;

import java.awt.*;
import java.util.ArrayList;

public abstract class Store implements Placeable {

    protected final Rectangle bounds;
    protected final String shopAssistantName;
    protected final int startHour;
    protected final int endHour;
    protected final TextureRegion[][] regions;
    protected final TextureRegion texture;
    protected Image storeImage;

    public Store(TextureRegion[][] regions, TextureRegion texture,Rectangle bounds, String shopAssistantName, int startHour, int endHour) {
        this.regions = regions;
        this.texture = texture;
        this.bounds = bounds;
        this.shopAssistantName = shopAssistantName;
        this.startHour = startHour;
        this.endHour = endHour;
        loadInventory();
    }

    public String getShopAssistantName() {
        return shopAssistantName;
    }

    public Rectangle getBounds() {
        return bounds;
    }


    public boolean isOpen() {
        return App.getGame().getTime().getHour() >= startHour && App.getGame().getTime().getHour() <= endHour;
    }

    public Image getStoreImage() {
        if (storeImage == null) {
            storeImage = new Image(new TextureRegionDrawable(getTexture()));
            storeImage.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    createStoreWindow();
                    return true;
                }
            });
        storeImage.setPosition(bounds.x * GamePictureManager.TILE_SIZE, bounds.y * GamePictureManager.TILE_SIZE);
        }
        return storeImage;
    }

    private void createStoreWindow() {
        if (isOpen()) {
            ((GameScreenMenu) Main.getMain().getScreen()).getGameMenuInputAdapter().createStoreWindow(this);
        } else {
            ((GameScreenMenu) Main.getMain().getScreen()).getGameMenuInputAdapter().showClosedStoreMessage();
        }
    }



    @Override
    public TextureRegion getTexture() {
        return texture;
    }

    public TextureRegion[][] getRegions() {
        return regions;
    }

    public abstract char getSymbol();
    public void loadInventory() {}
    public abstract ArrayList<ShopItem> showAllProducts();
    public abstract ArrayList<ShopItem> showAvailableProducts();
    public abstract Result purchaseProduct(int value, String productName);
    public abstract void ResetQuantityEveryNight();


}
