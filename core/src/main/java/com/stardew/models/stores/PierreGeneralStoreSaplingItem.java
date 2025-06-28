package com.stardew.models.stores;

import com.stardew.models.foraging.TreeSource;
import com.stardew.models.foraging.TreeType;

public class PierreGeneralStoreSaplingItem extends ShopItem {

    private final TreeSource source;


    public PierreGeneralStoreSaplingItem(String name, TreeSource source,int price, int dailyLimit) {
        super(name, price, dailyLimit);
        this.source = source;
    }

   public TreeSource getSource() {
        return source;
   }
}
