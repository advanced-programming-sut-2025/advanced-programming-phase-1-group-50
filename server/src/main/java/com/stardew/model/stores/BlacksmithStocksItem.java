package com.stardew.model.stores;

import com.stardew.model.mapInfo.foraging.ForagingMineral;

public class BlacksmithStocksItem extends ShopItem{

    private final ForagingMineral type;

    public BlacksmithStocksItem(String name, ForagingMineral type, int price, Integer dailyLimit) {
        super(name, price, dailyLimit);
        this.type = type;
    }

    public ForagingMineral getType() {
        return type;
    }
}
