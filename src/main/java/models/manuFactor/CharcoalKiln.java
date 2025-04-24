package models.manuFactor;

import models.animals.AnimalGood;
import models.date.TimeInterval;
import models.manuFactor.artisanGoods.ArtisanGoodItem;
import models.mapInfo.Wood;
import models.userInfo.Player;

public class CharcoalKiln extends ArtisanMachine {

    public CharcoalKiln() {
        super();
        processingTimes.put(ArtisanGoodItem.Coal, new TimeInterval(0, 1));
    }

    @Override
    public boolean canUse(Player player, String product) {
        if (product.equals("Coal") || product.equals("coal")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Wood) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 10) {
                        player.getBackpack().removeIngredients(ingredient, 10);
                        producingGood = ArtisanGoodItem.Coal;
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }
}
