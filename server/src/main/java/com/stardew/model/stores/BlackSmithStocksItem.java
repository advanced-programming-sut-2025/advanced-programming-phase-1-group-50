package com.stardew.model.stores;

import com.stardew.model.mapInfo.foraging.ForagingMineral;

public class BlackSmithStocksItem extends ShopItem{

    private final ForagingMineral type;

    public BlackSmithStocksItem(String name, ForagingMineral type, int price, Integer dailyLimit) {
        super(name, price, dailyLimit);
        this.type = type;
    }

    public ForagingMineral getType() {
        return type;
    }
}
