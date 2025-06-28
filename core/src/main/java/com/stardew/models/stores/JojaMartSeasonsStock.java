package com.stardew.models.stores;

import com.stardew.models.date.Season;
import com.stardew.models.foraging.Seeds;

public class JojaMartSeasonsStock extends ShopItem{

    private final Seeds seedType;
    private final Season season;

    public JojaMartSeasonsStock(String name, Seeds seedType , Season season, int price, int dailyLimit) {
        super(name, price, dailyLimit);
        this.seedType = seedType;
        this.season = season;
    }

    public Seeds getSeedType() {
        return seedType;
    }

    public Season getSeason() {
        return season;
    }
}
