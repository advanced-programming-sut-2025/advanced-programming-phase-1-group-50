package models.manuFactor;

import models.animals.AnimalGood;
import models.date.TimeInterval;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.manuFactor.artisanGoods.ArtisanGoodType;
import models.userInfo.Player;

public class CheesePress extends ArtisanMachine {

    public CheesePress() {
        super();
        processingTimes.put(new ArtisanGood(ArtisanGoodType.CheeseByMilk), new TimeInterval(0, 3));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.CheeseByLargeMilk), new TimeInterval(0, 3));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.GoatCheeseByMilk), new TimeInterval(0, 3));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.GoatCheeseByLargeMilk), new TimeInterval(0, 3));

    }

    @Override
    public boolean canUse(Player player, String product) {
        if (product.equals("Cheese") || product.equals("cheese")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(AnimalGood.Milk) || ingredient.equals(AnimalGood.LargeMilk)) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        producingGood = ingredient.equals(AnimalGood.Milk) ?
                                new ArtisanGood(ArtisanGoodType.CheeseByMilk) : new ArtisanGood(ArtisanGoodType.CheeseByLargeMilk);
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
                                new ArtisanGood(ArtisanGoodType.GoatCheeseByMilk) :
                                new ArtisanGood(ArtisanGoodType.GoatCheeseByLargeMilk);
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }
}
