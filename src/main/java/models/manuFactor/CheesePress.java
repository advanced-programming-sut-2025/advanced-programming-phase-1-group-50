package models.manuFactor;

import models.animals.AnimalGood;
import models.date.TimeInterval;
import models.manuFactor.artisanGoods.ArtisanGoodItem;
import models.userInfo.Player;

public class CheesePress extends ArtisanMachine {

    public CheesePress() {
        super();
        processingTimes.put(ArtisanGoodItem.CheeseByMilk, new TimeInterval(0, 3));
        processingTimes.put(ArtisanGoodItem.CheeseByLargeMilk, new TimeInterval(0, 3));
        processingTimes.put(ArtisanGoodItem.GoatCheeseByMilk, new TimeInterval(0, 3));
        processingTimes.put(ArtisanGoodItem.GoatCheeseByLargeMilk, new TimeInterval(0, 3));

    }

    @Override
    public boolean canUse(Player player, String product) {
        if (product.equals("Cheese") || product.equals("cheese")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(AnimalGood.Milk) || ingredient.equals(AnimalGood.LargeMilk)) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        producingGood = ingredient.equals(AnimalGood.Milk) ?
                                ArtisanGoodItem.CheeseByMilk : ArtisanGoodItem.CheeseByLargeMilk;
                        return true;
                    }
                    return false;
                }
            }
        }
        if (product.equals("Goat_Cheese") || product.equals("goat_cheese")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(AnimalGood.GoatMilk) || ingredient.equals(AnimalGood.LargeGoatMilk)) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        producingGood = ingredient.equals(AnimalGood.GoatMilk) ?
                                ArtisanGoodItem.GoatCheeseByMilk : ArtisanGoodItem.GoatCheeseByLargeMilk;
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }
}
