package models.stores;

import models.manuFactor.Ingredient;

public class FishShopStock extends ShopItem implements Ingredient {

    public FishShopStock(String name, int price, int dailyLimit) {
        super(name, price, dailyLimit);
    }

}
