package com.stardew.models.manuFactor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.date.TimeInterval;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGoodType;
import com.stardew.models.mapInfo.Wood;
import com.stardew.models.userInfo.Player;

public class CharcoalKiln extends ArtisanMachine {

    public CharcoalKiln() {
        super();
        image = new Image(GamePictureManager.charcoalKilnNormal);
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Coal), new TimeInterval(0, 1));
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
