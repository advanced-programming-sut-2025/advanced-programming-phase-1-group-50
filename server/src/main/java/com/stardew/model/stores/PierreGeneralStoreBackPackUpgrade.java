package com.stardew.model.stores;

import com.stardew.model.userInfo.BackpackType;

public class PierreGeneralStoreBackPackUpgrade extends ShopItem{
    private final BackpackType backpackType;


    public PierreGeneralStoreBackPackUpgrade(String name, BackpackType type ,int price, int dailyLimit) {
        super(name, price, dailyLimit);
        this.backpackType = type;
    }

    public BackpackType getBackpackType() {
        return backpackType;
    }
}
