package com.stardew.model.stores;

import com.stardew.model.animals.AnimalType;

public class MarnieRanchLiveStockItem extends ShopItem {
    private final AnimalType animalType;


    public MarnieRanchLiveStockItem(String name, AnimalType animalType, int price, int dailyLimit) {

        super(name, price, dailyLimit);
        this.animalType = animalType;

    }

    public AnimalType getType() {
        return animalType;
    }
}
