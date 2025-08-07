package com.stardew.model.stores;

import com.stardew.controller.GameSessionController;
import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.TimeProvider;
import com.stardew.model.mapInfo.Placeable;
import com.stardew.model.userInfo.Player;

import java.awt.*;
import java.util.ArrayList;

public abstract class Store implements Placeable {
    protected final Rectangle bounds;
    protected final String shopAssistantName;
    protected final int startHour;
    protected final int endHour;
    protected final TextureID texture;
    protected final TimeProvider timeProvider;

    public Store(TimeProvider timeProvider,TextureID texture,Rectangle bounds, String shopAssistantName, int startHour, int endHour) {
        this.texture = texture;
        this.bounds = bounds;
        this.shopAssistantName = shopAssistantName;
        this.startHour = startHour;
        this.endHour = endHour;
        this.timeProvider = timeProvider;
        loadInventory();
    }


    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isOpen() {
        return timeProvider.getTime().getHour() >= startHour && timeProvider.getTime().getHour() < endHour;
    }

    public TextureID getTexture() {
        return texture;
    }
    public abstract char getSymbol();
    public void loadInventory() {}
    public abstract ArrayList<ShopItem> getAllProducts();
    public abstract ArrayList<ShopItem> getAvailableProducts();
    public abstract Result purchaseProduct(Player player, String productName, int value);
    public abstract void ResetQuantityEveryNight();

}
