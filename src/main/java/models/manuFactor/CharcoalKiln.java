package models.manuFactor;

import models.date.TimeInterval;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.manuFactor.artisanGoods.ArtisanGoodType;
import models.mapInfo.Wood;
import models.userInfo.Player;

public class CharcoalKiln extends ArtisanMachine {

    public CharcoalKiln() {
        super();
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Coal), new TimeInterval(0, 1));
    }

    @Override
    public boolean canUse(Player player, String product) {
        if (product.equals("Coal") || product.equals("coal")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Wood) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 10) {
                        player.getBackpack().removeIngredients(ingredient, 10);
                        producingGood = new ArtisanGood(ArtisanGoodType.Coal);
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }
}
