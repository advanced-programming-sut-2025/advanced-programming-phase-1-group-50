package com.stardew.models.stores;

import com.stardew.models.cooking.Food;

public class StardopSaloonFoodItem extends ShopItem {
    private final Food food;

    public StardopSaloonFoodItem(String name, Food food ,int price, int dailyLimit) {
        super(name, price, dailyLimit);
        this.food = food;
    }

    public Food getFood() {
        return food;
    }
}
