package com.stardew.models.stores;

import com.stardew.models.animals.AnimalType;
import com.stardew.models.animals.HabitatSize;
import com.stardew.models.animals.HabitatType;

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
