package models.manuFactor;

import models.Result;
import models.animals.AnimalGoodType;
import models.date.TimeInterval;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.manuFactor.artisanGoods.ArtisanGoodType;
import models.userInfo.Player;

public class MayonnaiseMachine extends ArtisanMachine {

    public MayonnaiseMachine() {
        super();
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Mayonnaise), new TimeInterval(0, 3));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.DuckMayonnaise), new TimeInterval(0, 3));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.DinosaurMayonnaise), new TimeInterval(0, 3));
    }

    @Override
    public Result canUse(Player player, String product) {
        if (product.equals("Mayonnaise") || product.equals("mayonnaise")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(AnimalGoodType.Egg) || ingredient.equals(AnimalGoodType.LargeEgg)) {
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
        else if (product.equals("Duck_Mayonnaise") || product.equals("duck_mayonnaise")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(AnimalGoodType.DuckEgg)) {
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
        else if (product.equals("Dinosaur_Mayonnaise") || product.equals("dinosaur_mayonnaise")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(AnimalGoodType.DinosaurEgg)) {
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
        return new Result(false, "This Machine can't make this Item!!");
    }
}
