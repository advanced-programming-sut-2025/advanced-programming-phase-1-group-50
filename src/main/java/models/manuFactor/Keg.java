package models.manuFactor;

import models.date.TimeInterval;
import models.foraging.Crop;
import models.foraging.CropType;
import models.foraging.Fruit;
import models.manuFactor.artisanGoods.ArtisanGoodItem;
import models.manuFactor.artisanGoods.Juice;
import models.manuFactor.artisanGoods.Wine;
import models.userInfo.Player;

public class Keg extends ArtisanMachine {

    public Keg() {
        super();
        processingTimes.put(ArtisanGoodItem.Beer, new TimeInterval(1, 0));
        processingTimes.put(ArtisanGoodItem.Vinegar, new TimeInterval(0, 10));
        processingTimes.put(ArtisanGoodItem.Coffee, new TimeInterval(0, 2));
        processingTimes.put(new Juice(0, 0), new TimeInterval(4, 0));
        processingTimes.put(ArtisanGoodItem.Mead, new TimeInterval(0, 10));
        processingTimes.put(ArtisanGoodItem.PaleAle, new TimeInterval(3, 0));
        processingTimes.put(new Wine(0, 0) , new TimeInterval(3, 0));
    }

    @Override
    public boolean canUse(Player player, String product) {
        if (product.equals("Beer") || product.equals("beer")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Crop && ((Crop)ingredient).getType().equals(CropType.Wheat)) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        producingGood = ArtisanGoodItem.Beer;
                        return true;
                    }
                    return false;
                }
            }
        } if (product.equals("Vinegar") || product.equals("vinegar")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Crop && ((Crop)ingredient).getType().equals(CropType.UnMilledRice)) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        producingGood = ArtisanGoodItem.Vinegar;
                        return true;
                    }
                    return false;
                }
            }
        } if (product.equals("Coffee") || product.equals("coffee")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Crop && ((Crop)ingredient).getType().equals(CropType.CoffeeBean)) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {
                        player.getBackpack().removeIngredients(ingredient, 5);
                        producingGood = ArtisanGoodItem.Coffee;
                        return true;
                    }
                    return false;
                }
            }
        } if (product.equals("Juice") || product.equals("juice")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Crop) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        producingGood = new Juice(
                                2 * ((Crop) ingredient).getType().getEnergy(),
                                (int) (2.25 * ((Crop) ingredient).getType().getBaseSalePrice()));
                        return true;
                    }
                    return false;
                }
            }
        } if (product.equals("Mead") || product.equals("mead")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(ArtisanGoodItem.Honey)) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        producingGood = ArtisanGoodItem.Mead;
                        return true;
                    }
                    return false;
                }
            }
        } if (product.equals("Pale_Ale") || product.equals("pale_ale")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Crop && ((Crop)ingredient).getType().equals(CropType.Hops)) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        producingGood = ArtisanGoodItem.PaleAle;
                        return true;
                    }
                    return false;
                }
            }
        } if (product.equals("Wine") || product.equals("wine")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Fruit) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        producingGood = new Wine(
                                (int) (1.75 * ((Fruit) ingredient).getEnergy()),
                                3 * ((Fruit) ingredient).getBaseSellPrice());
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }
}
