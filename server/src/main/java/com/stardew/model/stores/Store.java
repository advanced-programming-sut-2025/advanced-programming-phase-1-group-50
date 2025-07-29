package com.stardew.model.stores;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.model.Result;
import com.stardew.model.mapInfo.Placeable;

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
        //TODO
        return true;
    }

    public Image getStoreImage() {
       //TODO
        return storeImage;
    }

    public void createStoreWindow() {
        //TODO
    }

//    @Override
//    public TextureRegion getTexture() {return texture;}
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
