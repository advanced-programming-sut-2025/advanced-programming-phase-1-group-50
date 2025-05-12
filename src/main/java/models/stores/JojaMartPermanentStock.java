package models.stores;

import models.manuFactor.Ingredient;

public class JojaMartPermanentStock extends ShopItem implements Ingredient {

    public JojaMartPermanentStock(String name, int price, int dailyLimit) {
        super(name, price, dailyLimit);
    }

}
