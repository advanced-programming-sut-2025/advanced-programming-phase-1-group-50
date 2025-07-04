package com.stardew.models.manuFactor;

import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.foraging.Crop;
import com.stardew.models.foraging.CropType;
import com.stardew.models.foraging.ForagingCrop;
import com.stardew.models.foraging.Fruit;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGoodType;
import com.stardew.models.userInfo.Player;

public class Dehydrator extends ArtisanMachine {

    public Dehydrator() {
        super();
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

        //Next morning
        if (todayDate > timeOfRequest.getDate())
            return new Result(true, "Your product is Ready.");
        else
            return new Result(false, "Your product is Not Ready.");
    }

    @Override
    public int getTotalProcessingTime() {
        return 22 - timeOfRequest.getHour();
    }

    @Override
    public Result canUse(Player player, String product) {
        switch (product) {
            case "Dried_Mushroom", "dried_mushroom" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {

                    if (ingredient.equals(ForagingCrop.CommonMushroom) ||
                            ingredient.equals(ForagingCrop.RedMushroom) ||
                            ingredient.equals(ForagingCrop.PurpleMushroom)) {

                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {

                            player.getBackpack().removeIngredients(ingredient, 5);

                            producingGood = new ArtisanGood(ArtisanGoodType.DriedMushroom,
                                    50,
                                    (int) (7.5 * ((ForagingCrop) ingredient).getBaseSellPrice() + 25));
                            return new Result(true, "Your product is being made.Please wait.");
                        }
                        return new Result(false, "You don't have enough Ingredients!");
                    }
                }
                return new Result(false, "You don't have enough Ingredients!");
            }
            case "Dried_Fruit", "dried_fruit" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {

                    if (ingredient instanceof Fruit ||
                            ingredient instanceof Crop crop && !(crop.getType().equals(CropType.Grape))) {

                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {

                            player.getBackpack().removeIngredients(ingredient, 5);

                            if (ingredient instanceof Fruit)
                                producingGood = new ArtisanGood(ArtisanGoodType.DriedFruit,
                                        75,
                                        (int) (7.5 * ((Fruit) ingredient).getSellPrice() + 25));
                            else
                                producingGood = new ArtisanGood(ArtisanGoodType.DriedFruit,
                                        75,
                                        (int) (7.5 * ((Crop) ingredient).getType().getBaseSellPrice() + 25));
                            return new Result(true, "Your product is being made.Please wait.");
                        }
                        return new Result(false, "You don't have enough Ingredients!");
                    }
                }
                return new Result(false, "You don't have enough Ingredients!");
            }
            case "Raisins", "raisins" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {

                    if (ingredient instanceof Crop crop && crop.getType().equals(CropType.Grape)) {

                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {

                            player.getBackpack().removeIngredients(ingredient, 5);

                            producingGood = new ArtisanGood(ArtisanGoodType.Raisins);
                            return new Result(true, "Your product is being made.Please wait.");
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
