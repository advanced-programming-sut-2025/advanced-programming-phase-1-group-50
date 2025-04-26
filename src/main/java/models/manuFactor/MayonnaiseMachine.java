package models.manuFactor;

import models.animals.AnimalGood;
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
    public boolean canUse(Player player, String product) {
        if (product.equals("Mayonnaise") || product.equals("mayonnaise")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(AnimalGood.Egg) || ingredient.equals(AnimalGood.LargeEgg)) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        producingGood = new ArtisanGood(ArtisanGoodType.Mayonnaise);
                        return true;
                    }
                    return false;
                }
            }
        } if (product.equals("Duck_Mayonnaise") || product.equals("duck_mayonnaise")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(AnimalGood.DuckEgg)) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        producingGood = new ArtisanGood(ArtisanGoodType.DuckMayonnaise);
                        return true;
                    }
                    return false;
                }
            }
        } if (product.equals("Dinosaur_Mayonnaise") || product.equals("dinosaur_mayonnaise")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(AnimalGood.DinosaurEgg)) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        producingGood = new ArtisanGood(ArtisanGoodType.DinosaurMayonnaise);
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }
}
