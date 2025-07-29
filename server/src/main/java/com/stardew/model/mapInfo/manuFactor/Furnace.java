package com.stardew.model.mapInfo.manuFactor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.model.Result;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.foraging.ForagingMineral;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.userInfo.Player;

public class Furnace extends ArtisanMachine{
    public Furnace() {
        super();
        image = new Image(GamePictureManager.furnaceNormal);
    }

    @Override
    public Result isReady() {
        if (cheatReady)
            return new Result(true, "Your product is Ready.");
        if (timeOfRequest == null)
            return new Result(false, "You don't have any artisan goods in machine yet!!");
        int todayDate = App.getGame().getTime().getDate();
        if (App.getGame().getTime().getSeason() != timeOfRequest.getSeason())
            todayDate += 28;
        if (timeOfRequest.getDate() < todayDate ||
            timeOfRequest.getHour() + 4 <= App.getGame().getTime().getHour())
            return new Result(true, "Your product is Ready.");
        return new Result(false, "Your product is Not Ready.");
    }

    @Override
    public int getTotalProcessingTime() {
        return 4;
    }

    @Override
    public Result canUse(Player player, String product) {
        switch (product) {
            case "IronBar" -> {
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
            case "IridiumBar" -> {
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
            case "CopperBar" -> {
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
            case "GoldBar" -> {
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
