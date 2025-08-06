package com.stardew.model.mapInfo.manuFactor;

import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.animals.AnimalGood;
import com.stardew.model.animals.AnimalGoodType;
import com.stardew.model.gameApp.TimeProvider;
import com.stardew.model.gameApp.date.TimeInterval;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.userInfo.Player;

public class CheesePress extends ArtisanMachine {

    public CheesePress(TimeProvider timeProvider, int x, int y) {
        super(timeProvider, x, y);
        processingTimes.put(new ArtisanGood(ArtisanGoodType.CheeseByMilk), new TimeInterval(0, 3));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.CheeseByLargeMilk), new TimeInterval(0, 3));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.GoatCheeseByMilk), new TimeInterval(0, 3));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.GoatCheeseByLargeMilk), new TimeInterval(0, 3));

    }

    @Override
    public TextureID getTexture() {
        return TextureID.cheesePress;
    }

    @Override
    public Result canUse(Player player, String product) {
        if (product.equals("Cheese") || product.equals("cheese")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {

                if (ingredient instanceof AnimalGood animalGood &&
                    (animalGood.getType().equals(AnimalGoodType.Milk) ||
                        animalGood.getType().equals(AnimalGoodType.LargeMilk))) {

                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {

                        player.getBackpack().removeIngredients(ingredient, 1);

                        producingGood = animalGood.getType().equals(AnimalGoodType.Milk) ?
                            new ArtisanGood(ArtisanGoodType.CheeseByMilk) : new ArtisanGood(ArtisanGoodType.CheeseByLargeMilk);
                        return new Result(true, "Your product is being made.Please wait.");
                    }
                    return new Result(false, "You don't have enough Ingredients!");
                }
            }
            return new Result(false, "You don't have enough Ingredients!");
        }
        else if (product.equalsIgnoreCase("GoatCheese") || product.equalsIgnoreCase("goat_cheese")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {

                if (ingredient instanceof AnimalGood animalGood &&
                    (animalGood.getType().equals(AnimalGoodType.GoatMilk) ||
                        animalGood.getType().equals(AnimalGoodType.LargeGoatMilk))) {

                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {

                        player.getBackpack().removeIngredients(ingredient, 1);

                        producingGood = animalGood.getType().equals(AnimalGoodType.GoatMilk) ?
                            new ArtisanGood(ArtisanGoodType.GoatCheeseByMilk) :
                            new ArtisanGood(ArtisanGoodType.GoatCheeseByLargeMilk);
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
