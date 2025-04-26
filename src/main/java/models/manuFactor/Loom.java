package models.manuFactor;

import models.animals.AnimalGood;
import models.date.TimeInterval;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.manuFactor.artisanGoods.ArtisanGoodType;
import models.userInfo.Player;

public class Loom extends ArtisanMachine {

    public Loom() {
        super();
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Cloth), new TimeInterval(0, 4));
    }

    @Override
    public boolean canUse(Player player, String product) {
        if (product.equals("Cloth") || product.equals("cloth")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(AnimalGood.Wool)) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        producingGood = new ArtisanGood(ArtisanGoodType.Cloth);
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }
}
