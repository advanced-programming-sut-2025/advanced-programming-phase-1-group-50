package com.stardew.model.stores;

import com.stardew.model.gameApp.date.Season;
import com.stardew.model.mapInfo.foraging.Seeds;

public class JojaMartSeasonsStock extends ShopItem {
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
