package com.stardew.model.mapInfo.manuFactor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.model.Result;
import com.stardew.model.animals.AnimalGood;
import com.stardew.model.gameApp.date.TimeInterval;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.userInfo.Player;

public class Loom extends ArtisanMachine {
    public Loom() {
        super();
        image = new Image(GamePictureManager.loomNormal);
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Cloth), new TimeInterval(0, 4));
    }

    @Override
    public Result canUse(Player player, String product) {
        if (product.equals("Cloth") || product.equals("cloth")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof AnimalGood animalGood && animalGood.getType().equals(AnimalGoodType.Wool)) {

                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {

                        player.getBackpack().removeIngredients(ingredient, 1);

                        producingGood = new ArtisanGood(ArtisanGoodType.Cloth);
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
