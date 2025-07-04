package com.stardew.models.manuFactor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.animals.AnimalGood;
import com.stardew.models.animals.AnimalGoodType;
import com.stardew.models.date.TimeInterval;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGoodType;
import com.stardew.models.userInfo.Player;

public class CheesePress extends ArtisanMachine {

    public CheesePress() {
        super();
        image = new Image(GamePictureManager.cheesePressNormal);
        processingTimes.put(new ArtisanGood(ArtisanGoodType.CheeseByMilk), new TimeInterval(0, 3));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.CheeseByLargeMilk), new TimeInterval(0, 3));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.GoatCheeseByMilk), new TimeInterval(0, 3));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.GoatCheeseByLargeMilk), new TimeInterval(0, 3));

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
        else if (product.equals("Goat_Cheese") || product.equals("goat_cheese")) {
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
