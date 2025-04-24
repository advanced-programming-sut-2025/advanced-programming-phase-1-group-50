package models.manuFactor;

import models.foraging.ForagingMineral;
import models.manuFactor.artisanGoods.ArtisanGoodItem;
import models.manuFactor.artisanGoods.BarType;
import models.manuFactor.artisanGoods.MetalBar;
import models.userInfo.Player;

public class Furnace extends ArtisanMachine {

    @Override
    public boolean isReady() {
        if (timeOfRequest == null)
            return false;
        return timeOfRequest.getHour() + 4 <= App.getToday().getHour();
    }

    @Override
    public boolean canUse(Player player, String product) {
        if (product.equals("Iron_Bar") || product.equals("iron_bar")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(ForagingMineral.Iron) &&
                        player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {
                    for (Ingredient ingredient1 : player.getBackpack().getIngredientQuantity().keySet()) {
                        if (ingredient1.equals(ArtisanGoodItem.Coal)) {
                            player.getBackpack().removeIngredients(ingredient, 5);
                            player.getBackpack().removeIngredients(ingredient1, 1);
                            producingGood = new MetalBar(
                                    BarType.Iron,
                                    10 * ForagingMineral.Iron.getSellPrice());
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        if (product.equals("Iridium_Bar") || product.equals("iridium_bar")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(ForagingMineral.Iridium) &&
                        player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {
                    for (Ingredient ingredient1 : player.getBackpack().getIngredientQuantity().keySet()) {
                        if (ingredient1.equals(ArtisanGoodItem.Coal)) {
                            player.getBackpack().removeIngredients(ingredient, 5);
                            player.getBackpack().removeIngredients(ingredient1, 1);
                            producingGood = new MetalBar(
                                    BarType.Iridium,
                                    10 * ForagingMineral.Iridium.getSellPrice());
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        if (product.equals("Copper_Bar") || product.equals("copper_bar")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(ForagingMineral.Copper) &&
                        player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {
                    for (Ingredient ingredient1 : player.getBackpack().getIngredientQuantity().keySet()) {
                        if (ingredient1.equals(ArtisanGoodItem.Coal)) {
                            player.getBackpack().removeIngredients(ingredient, 5);
                            player.getBackpack().removeIngredients(ingredient1, 1);
                            producingGood = new MetalBar(
                                    BarType.Copper,
                                    10 * ForagingMineral.Copper.getSellPrice());
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        if (product.equals("Gold_Bar") || product.equals("gold_bar")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(ForagingMineral.Gold) &&
                        player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {
                    for (Ingredient ingredient1 : player.getBackpack().getIngredientQuantity().keySet()) {
                        if (ingredient1.equals(ArtisanGoodItem.Coal)) {
                            player.getBackpack().removeIngredients(ingredient, 5);
                            player.getBackpack().removeIngredients(ingredient1, 1);
                            producingGood = new MetalBar(
                                    BarType.Gold,
                                    10 * ForagingMineral.Gold.getSellPrice());
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }
}
