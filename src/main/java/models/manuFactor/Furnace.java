package models.manuFactor;

import models.Result;
import models.app.App;
import models.foraging.ForagingMineral;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.manuFactor.artisanGoods.ArtisanGoodType;
import models.userInfo.Player;

public class Furnace extends ArtisanMachine {

    @Override
    public Result isReady() {
        if (timeOfRequest == null)
            return new Result(false, "You don't have any artisan goods in machine yet!!");
        if (timeOfRequest.getHour() + 4 <= App.getGame().getTime().getHour())
            return new Result(true, "Your product is Ready.");
        return new Result(false, "Your product is Not Ready.");
    }

    @Override
    public Result canUse(Player player, String product) {
        switch (product) {
            case "Iron_Bar", "iron_bar" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                    if (ingredient.equals(ForagingMineral.Iron) &&
                            player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {

                        for (Ingredient ingredient1 : player.getBackpack().getIngredientQuantity().keySet()) {
                            if (ingredient1.equals(new ArtisanGood(ArtisanGoodType.Coal)) ||
                                    ingredient1.equals(ForagingMineral.Coal)) {

                                player.getBackpack().removeIngredients(ingredient, 5);
                                player.getBackpack().removeIngredients(ingredient1, 1);

                                producingGood = new ArtisanGood(ArtisanGoodType.IronBar,
                                        0,
                                        10 * ForagingMineral.Iron.getSellPrice());
                                return new Result(true, "Your product is being made.Please wait.");
                            }
                        }
                        return new Result(false, "You don't have enough Ingredients!");
                    }
                }
                return new Result(false, "You don't have enough Ingredients!");
            }
            case "Iridium_Bar", "iridium_bar" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                    if (ingredient.equals(ForagingMineral.Iridium) &&
                            player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {

                        for (Ingredient ingredient1 : player.getBackpack().getIngredientQuantity().keySet()) {
                            if (ingredient1.equals(new ArtisanGood(ArtisanGoodType.Coal)) ||
                                    ingredient1.equals(ForagingMineral.Coal)) {

                                player.getBackpack().removeIngredients(ingredient, 5);
                                player.getBackpack().removeIngredients(ingredient1, 1);

                                producingGood = new ArtisanGood(ArtisanGoodType.IridiumBar,
                                        0,
                                        10 * ForagingMineral.Iridium.getSellPrice());
                                return new Result(true, "Your product is being made.Please wait.");
                            }
                        }
                        return new Result(false, "You don't have enough Ingredients!");
                    }
                }
                return new Result(false, "You don't have enough Ingredients!");
            }
            case "Copper_Bar", "copper_bar" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                    if (ingredient.equals(ForagingMineral.Copper) &&
                            player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {

                        for (Ingredient ingredient1 : player.getBackpack().getIngredientQuantity().keySet()) {
                            if (ingredient1.equals(new ArtisanGood(ArtisanGoodType.Coal)) ||
                                    ingredient1.equals(ForagingMineral.Coal)) {

                                player.getBackpack().removeIngredients(ingredient, 5);
                                player.getBackpack().removeIngredients(ingredient1, 1);

                                producingGood = new ArtisanGood(ArtisanGoodType.CopperBar,
                                        0,
                                        10 * ForagingMineral.Copper.getSellPrice());
                                return new Result(true, "Your product is being made.Please wait.");
                            }
                        }
                        return new Result(false, "You don't have enough Ingredients!");
                    }
                }
                return new Result(false, "You don't have enough Ingredients!");
            }
            case "Gold_Bar", "gold_bar" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                    if (ingredient.equals(ForagingMineral.Gold) &&
                            player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {

                        for (Ingredient ingredient1 : player.getBackpack().getIngredientQuantity().keySet()) {
                            if (ingredient1.equals(new ArtisanGood(ArtisanGoodType.Coal)) ||
                                    ingredient1.equals(ForagingMineral.Coal)) {

                                player.getBackpack().removeIngredients(ingredient, 5);
                                player.getBackpack().removeIngredients(ingredient1, 1);

                                producingGood = new ArtisanGood(ArtisanGoodType.GoldBar,
                                        0,
                                        10 * ForagingMineral.Gold.getSellPrice());
                                return new Result(true, "Your product is being made.Please wait.");
                            }
                        }
                        return new Result(false, "You don't have enough Ingredients!");
                    }
                }
                return new Result(false, "You don't have enough Ingredients!");
            }
        }
        return new Result(false, "This Machine can't make this Item!!");
    }
}
