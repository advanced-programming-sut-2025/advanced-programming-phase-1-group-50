package com.stardew.model.stores;

import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;

public class StardopSaloonArtisanGoodItem extends ShopItem {
    private final ArtisanGoodType type;

    public StardopSaloonArtisanGoodItem(String name, ArtisanGoodType type ,int price, int dailyLimit) {
        super(name, price, dailyLimit);
        this.type = type;
    }

    public ArtisanGoodType getType() {
        return type;
    }
}
