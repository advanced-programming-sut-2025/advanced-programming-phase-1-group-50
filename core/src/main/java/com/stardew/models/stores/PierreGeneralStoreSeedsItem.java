package com.stardew.models.stores;

import com.stardew.models.date.Season;
import com.stardew.models.foraging.Seeds;

public class PierreGeneralStoreSeedsItem extends ShopItem {

    private final Seeds seeds;
    private final Season season;


    public PierreGeneralStoreSeedsItem(String name, Seeds seed , Season season,int price, int dailyLimit) {
        super(name, price, dailyLimit);
        this.seeds = seed;
        this.season = season;
    }

    public Seeds getSeeds() {
        return seeds;
    }

    public Season getSeason() {
        return season;
    }
}
