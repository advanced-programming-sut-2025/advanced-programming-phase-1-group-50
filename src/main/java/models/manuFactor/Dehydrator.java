package models.manuFactor;

import models.Result;
import models.app.App;
import models.foraging.Crop;
import models.foraging.CropType;
import models.foraging.ForagingCrop;
import models.foraging.Fruit;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.manuFactor.artisanGoods.ArtisanGoodType;
import models.userInfo.Player;

public class Dehydrator extends ArtisanMachine {

    public Dehydrator() {
        super();
    }

    @Override
    public Result isReady() {
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
    public Result canUse(Player player, String product) {
        if (product.equals("Dried_Mushroom") || product.equals("dried_mushroom")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(ForagingCrop.CommonMushroom) ||
                        ingredient.equals(ForagingCrop.RedMushroom) ||
                        ingredient.equals(ForagingCrop.PurpleMushroom)) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {
                        player.getBackpack().removeIngredients(ingredient, 5);
                        producingGood = new ArtisanGood(ArtisanGoodType.DriedMushroom,
                                50,
                                (int) (7.5 * ((ForagingCrop)ingredient).getBaseSellPrice() + 25));
                        return new Result(true, "Your product is being made.Please wait.");
                    }
                    return new Result(false, "You don't have enough Ingredients!");
                }
            }
            return new Result(false, "You don't have enough Ingredients!");
        }
        else if (product.equals("Dried_Fruit") || product.equals("dried_fruit")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Fruit || ingredient instanceof Crop && !(((Crop) ingredient).getType().equals(CropType.Grape))) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {
                        player.getBackpack().removeIngredients(ingredient, 5);
                        if (ingredient instanceof Fruit)
                            producingGood = new ArtisanGood(ArtisanGoodType.DriedFruit,
                                75,
                                (int) (7.5 * ((Fruit)ingredient).getBaseSellPrice() + 25));
                        else
                            producingGood = new ArtisanGood(ArtisanGoodType.DriedFruit,
                                    75,
                                    (int) (7.5 * ((Crop)ingredient).getType().getBaseSellPrice() + 25));
                        return new Result(true, "Your product is being made.Please wait.");
                    }
                    return new Result(false, "You don't have enough Ingredients!");
                }
            }
            return new Result(false, "You don't have enough Ingredients!");
        }
        else if (product.equals("Raisins") || product.equals("raisins")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Crop && ((Crop) ingredient).getType().equals(CropType.Grape)) {
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
        return new Result(false, "This Machine can't make this Item!!");
    }
}
