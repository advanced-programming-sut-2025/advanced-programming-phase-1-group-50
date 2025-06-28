package com.stardew.models.manuFactor;

import com.stardew.models.Result;
import com.stardew.models.date.TimeInterval;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGoodType;
import com.stardew.models.userInfo.Player;

public class BeeHouse extends ArtisanMachine {

    public BeeHouse() {
        super();
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Honey), new TimeInterval(4, 0));
    }

    @Override
    public Result canUse(Player player, String product) {
        producingGood = new ArtisanGood(ArtisanGoodType.Honey);
        return new Result(true, "Your product is being made.Please wait.");
    }
}
