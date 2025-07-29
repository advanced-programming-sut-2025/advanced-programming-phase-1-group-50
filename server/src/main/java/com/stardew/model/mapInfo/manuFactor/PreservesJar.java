package com.stardew.model.mapInfo.manuFactor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.model.Result;
import com.stardew.model.gameApp.date.TimeInterval;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.foraging.Crop;
import com.stardew.model.mapInfo.foraging.Fruit;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.userInfo.Player;

public class PreservesJar extends ArtisanMachine{

    public PreservesJar() {
        super();
        image = new Image(GamePictureManager.preservesJarNormal);
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Pickles), new TimeInterval(0, 6));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Jelly), new TimeInterval(3, 0));
    }

    @Override
    public Result canUse(Player player, String product) {
        if (product.equals("Pickles") || product.equals("pickles")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Crop crop) {

                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {

                        player.getBackpack().removeIngredients(ingredient, 1);

                        producingGood = new ArtisanGood(ArtisanGoodType.Pickles,
                            (int) (1.75 * crop.getType().getEnergy()),
                            2 * crop.getType().getBaseSellPrice() + 50);
                        return new Result(true, "Your product is being made.Please wait.");
                    }
                    return new Result(false, "You don't have enough Ingredients!");
                }
            }
            return new Result(false, "You don't have enough Ingredients!");
        }
        else if (product.equals("Jelly") || product.equals("jelly")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Crop crop) {
                    player.getBackpack().removeIngredients(ingredient, 1);
                    producingGood = new ArtisanGood(ArtisanGoodType.Jelly,
                        2 * crop.getType().getEnergy(),
                        2 * crop.getType().getBaseSellPrice() + 50);
                    return new Result(true, "Your product is being made.Please wait.");
                }
                if (ingredient instanceof Fruit fruit) {
                    player.getBackpack().removeIngredients(ingredient, 1);
                    producingGood = new ArtisanGood(ArtisanGoodType.Jelly,
                        2 * fruit.getEnergy(),
                        2 * fruit.getSellPrice());
                    return new Result(true, "Your product is being made.Please wait.");
                }
            }
            return new Result(false, "You don't have enough Ingredients!");
        }
        return new Result(false, "This Machine can't make this Item!!");
    }
}
