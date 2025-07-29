package com.stardew.model.stores;

import com.stardew.model.mapInfo.foraging.TreeSource;

public class PierreGeneralStoreSaplingItem extends ShopItem{
    private final TreeSource source;


    public PierreGeneralStoreSaplingItem(String name, TreeSource source,int price, int dailyLimit) {
        super(name, price, dailyLimit);
        this.source = source;
    }

    public TreeSource getSource() {
        return source;
    }
}
