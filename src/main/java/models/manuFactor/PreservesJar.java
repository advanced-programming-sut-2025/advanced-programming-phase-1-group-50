package models.manuFactor;

import models.date.TimeInterval;
import models.foraging.Crop;
import models.foraging.Fruit;
import models.manuFactor.artisanGoods.Jelly;
import models.manuFactor.artisanGoods.Pickles;
import models.userInfo.Player;

public class PreservesJar extends ArtisanMachine {

    public PreservesJar() {
        super();
        processingTimes.put(new Pickles(0, 0), new TimeInterval(0, 6));
        processingTimes.put(new Jelly(0, 0), new TimeInterval(3, 0));
    }

    @Override
    public boolean canUse(Player player, String product) {
        if (product.equals("Pickles") || product.equals("pickles")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Crop) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        producingGood = new Pickles(
                                (int) (1.75 * ((Crop) ingredient).getType().getEnergy()),
                                2 * ((Crop) ingredient).getType().getBaseSalePrice() + 50);
                        return true;
                    }
                    return false;
                }
            }
        } if (product.equals("Jelly") || product.equals("jelly")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Crop) {
                    player.getBackpack().removeIngredients(ingredient, 1);
                    producingGood = new Jelly(
                            2 * ((Crop) ingredient).getType().getEnergy(),
                            2 * ((Crop) ingredient).getType().getBaseSalePrice() + 50);
                    return true;
                }
                if (ingredient instanceof Fruit) {
                    player.getBackpack().removeIngredients(ingredient, 1);
                    producingGood = new Jelly(
                            2 * ((Fruit) ingredient).getEnergy(),
                            2 * ((Fruit) ingredient).getBaseSellPrice());
                    return true;
                }
            }
        }
        return false;
    }
}
