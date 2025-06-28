package com.stardew.models.manuFactor;

import com.stardew.models.Result;
import com.stardew.models.date.TimeInterval;
import com.stardew.models.foraging.Crop;
import com.stardew.models.foraging.Fruit;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGoodType;
import com.stardew.models.userInfo.Player;

public class PreservesJar extends ArtisanMachine {

    public PreservesJar() {
        super();
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
