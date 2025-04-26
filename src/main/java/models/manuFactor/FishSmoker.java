package models.manuFactor;

import models.animals.Fish;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.manuFactor.artisanGoods.ArtisanGoodType;
import models.userInfo.Player;

public class FishSmoker extends ArtisanMachine {

    @Override
    public boolean isReady() {
        if (timeOfRequest == null)
            return false;
        return timeOfRequest.getHour() + 1 <= App.getToday().getHour();
    }

    @Override
    public boolean canUse(Player player, String product) {
        if (product.equals("Smoked_Fish") || product.equals("smoked_fish")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Fish) {
                    for (Ingredient ingredient1 : player.getBackpack().getIngredientQuantity().keySet()) {
                        if (ingredient1.equals(new ArtisanGood(ArtisanGoodType.Coal))) {
                            player.getBackpack().removeIngredients(ingredient, 1);
                            player.getBackpack().removeIngredients(ingredient1, 1);
                            producingGood = new ArtisanGood(ArtisanGoodType.SmokedFish,
                                    (int) (1.5 * ((Fish) ingredient).getPrice()),
                                    2 * ((Fish) ingredient).getPrice());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
