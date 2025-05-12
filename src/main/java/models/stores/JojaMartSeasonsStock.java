package models.stores;

import models.foraging.Seeds;

public class JojaMartSeasonsStock extends ShopItem{

    private final Seeds seedType;

    public JojaMartSeasonsStock(String name, Seeds seedType ,int price, int dailyLimit) {
        super(name, price, dailyLimit);
        this.seedType = seedType;
    }

    public Seeds getSeedType() {
        return seedType;
    }
}
