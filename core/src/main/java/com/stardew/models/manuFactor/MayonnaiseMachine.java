package com.stardew.models.manuFactor;

import com.stardew.models.Result;
import com.stardew.models.animals.AnimalGood;
import com.stardew.models.animals.AnimalGoodType;
import com.stardew.models.date.TimeInterval;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGoodType;
import com.stardew.models.userInfo.Player;

public class MayonnaiseMachine extends ArtisanMachine {

    public MayonnaiseMachine() {
        super();
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Mayonnaise), new TimeInterval(0, 3));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.DuckMayonnaise), new TimeInterval(0, 3));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.DinosaurMayonnaise), new TimeInterval(0, 3));
    }

    @Override
    public Result canUse(Player player, String product) {
        switch (product) {
            case "Mayonnaise", "mayonnaise" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                    if (ingredient instanceof AnimalGood animalGood &&
                            (animalGood.getType().equals(AnimalGoodType.Egg) ||
                                    animalGood.getType().equals(AnimalGoodType.LargeEgg))) {

                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {

                            player.getBackpack().removeIngredients(ingredient, 1);

                            producingGood = new ArtisanGood(ArtisanGoodType.Mayonnaise);
                            return new Result(true, "Your product is being made.Please wait.");
                        }
                        return new Result(false, "You don't have enough Ingredients!");
                    }
                }
                return new Result(false, "You don't have enough Ingredients!");
            }
            case "Duck_Mayonnaise", "duck_mayonnaise" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                    if (ingredient instanceof AnimalGood animalGood && animalGood.getType().equals(AnimalGoodType.DuckEgg)) {

                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {

                            player.getBackpack().removeIngredients(ingredient, 1);

                            producingGood = new ArtisanGood(ArtisanGoodType.DuckMayonnaise);
                            return new Result(true, "Your product is being made.Please wait.");
                        }
                        return new Result(false, "You don't have enough Ingredients!");
                    }
                }
                return new Result(false, "You don't have enough Ingredients!");
            }
            case "Dinosaur_Mayonnaise", "dinosaur_mayonnaise" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                    if (ingredient instanceof AnimalGood animalGood && animalGood.getType().equals(AnimalGoodType.DinosaurEgg)) {

                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {

                            player.getBackpack().removeIngredients(ingredient, 1);

                            producingGood = new ArtisanGood(ArtisanGoodType.DinosaurMayonnaise);
                            return new Result(true, "Your product is being made.Please wait.");
                        }
                        return new Result(false, "You don't have enough Ingredients!");
                    }
                }
                return new Result(false, "You don't have enough Ingredients!");
            }
        }
        return new Result(false, "This Machine can't make this Item!!");
    }
}
