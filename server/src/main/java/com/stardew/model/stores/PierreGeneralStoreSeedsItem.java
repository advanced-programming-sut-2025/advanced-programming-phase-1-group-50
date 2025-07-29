package com.stardew.model.stores;

import com.stardew.model.gameApp.date.Season;
import com.stardew.model.mapInfo.foraging.Seeds;

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

//    @Override
//    public int getPrice() {
//        if (season.equals(App.getGame().getTime().getSeason())) {
//            return (price * 2)/3;
//        }
//        return price;
//    }
}
