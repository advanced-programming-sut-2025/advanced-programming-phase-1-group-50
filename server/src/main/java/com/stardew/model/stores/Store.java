package com.stardew.model.stores;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.mapInfo.Placeable;

import java.awt.*;
import java.util.ArrayList;

public abstract class Store implements Placeable {
    protected final Rectangle bounds;
    protected final String shopAssistantName;
    protected final int startHour;
    protected final int endHour;
    protected final TextureID texture;
    protected Image storeImage;

    public Store(TextureID texture,Rectangle bounds, String shopAssistantName, int startHour, int endHour) {
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

    public TextureID getTexture() {
        return texture;
    }
    public abstract char getSymbol();
    public void loadInventory() {}
    public abstract ArrayList<ShopItem> showAllProducts();
    public abstract ArrayList<ShopItem> showAvailableProducts();
    public abstract Result purchaseProduct(int value, String productName);
    public abstract void ResetQuantityEveryNight();

}
