package models.manuFactor;

import models.date.TimeInterval;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.manuFactor.artisanGoods.ArtisanGoodType;
import models.userInfo.Player;

public class BeeHouse extends ArtisanMachine {

    public BeeHouse() {
        super();
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Honey), new TimeInterval(4, 0));
    }

    @Override
    public boolean canUse(Player player, String product) {
        producingGood = new ArtisanGood(ArtisanGoodType.Honey);
        return true;
    }
}
