package com.stardew.model.mapInfo.manuFactor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.date.TimeInterval;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.userInfo.Player;

public class BeeHouse extends ArtisanMachine{
    public BeeHouse() {
        super();
//        image = new Image(GamePictureManager.beeHouseNormal);
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Honey), new TimeInterval(4, 0));
    }

//    @Override
//    public Result canUse(Player player, String product) {
//        producingGood = new ArtisanGood(ArtisanGoodType.Honey);
//        return new Result(true, "Your product is being made.Please wait.");
//    }

}
