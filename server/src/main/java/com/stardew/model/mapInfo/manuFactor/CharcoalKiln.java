package com.stardew.model.mapInfo.manuFactor;

import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.TimeProvider;
import com.stardew.model.gameApp.date.TimeInterval;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.Wood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.userInfo.Player;

public class CharcoalKiln extends ArtisanMachine {

    public CharcoalKiln(TimeProvider timeProvider, int x, int y) {
        super(timeProvider, x, y);
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Coal), new TimeInterval(0, 1));
    }

    @Override
    public TextureID getTexture() {
        return TextureID.charcoalKiln;
    }

    @Override
    public Result canUse(Player player, String product) {
        if (product.equals("Coal") || product.equals("coal")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {

                if (ingredient instanceof Wood) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 10) {

                        player.getBackpack().removeIngredients(ingredient, 10);

                        producingGood = new ArtisanGood(ArtisanGoodType.Coal);
                        return new Result(true, "Your product is being made.Please wait.");
                    }
                    return new Result(false, "You don't have enough Ingredients!");
                }
            }
            return new Result(false, "You don't have enough Ingredients!");
        }
        return new Result(false, "This Machine can't make this Item!!");
    }

}
