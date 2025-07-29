package com.stardew.model.mapInfo.manuFactor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.model.Result;
import com.stardew.model.gameApp.date.TimeInterval;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.foraging.Crop;
import com.stardew.model.mapInfo.foraging.CropType;
import com.stardew.model.mapInfo.foraging.Fruit;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.userInfo.Player;

public class Keg extends ArtisanMachine {
    public Keg() {
        super();
        image = new Image(GamePictureManager.kegNormal);
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Beer), new TimeInterval(1, 0));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Vinegar), new TimeInterval(0, 10));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Coffee), new TimeInterval(0, 2));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Juice), new TimeInterval(4, 0));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Mead), new TimeInterval(0, 10));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.PaleAle), new TimeInterval(3, 0));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Wine) , new TimeInterval(7, 0));
    }

    @Override
    public Result canUse(Player player, String product) {
        switch (product) {
            case "Beer", "beer" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                    if (ingredient instanceof Crop crop && crop.getType().equals(CropType.Wheat)) {

                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {

                            player.getBackpack().removeIngredients(ingredient, 1);

                            producingGood = new ArtisanGood(ArtisanGoodType.Beer);
                            return new Result(true, "Your product is being made.Please wait.");
                        }
                        return new Result(false, "You don't have enough Ingredients!");
                    }
                }
                return new Result(false, "You don't have enough Ingredients!");
            }
            case "Vinegar", "vinegar" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                    if (ingredient instanceof Crop crop && crop.getType().equals(CropType.UnMilledRice)) {

                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {

                            player.getBackpack().removeIngredients(ingredient, 1);

                            producingGood = new ArtisanGood(ArtisanGoodType.Vinegar);
                            return new Result(true, "Your product is being made.Please wait.");
                        }
                        return new Result(false, "You don't have enough Ingredients!");
                    }
                }
                return new Result(false, "You don't have enough Ingredients!");
            }
            case "Coffee", "coffee" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                    if (ingredient instanceof Crop crop && crop.getType().equals(CropType.CoffeeBean)) {

                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {

                            player.getBackpack().removeIngredients(ingredient, 5);

                            producingGood = new ArtisanGood(ArtisanGoodType.Coffee);
                            return new Result(true, "Your product is being made.Please wait.");
                        }
                        return new Result(false, "You don't have enough Ingredients!");
                    }
                }
                return new Result(false, "You don't have enough Ingredients!");
            }
            case "Juice", "juice" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                    if (ingredient instanceof Crop crop) {

                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {

                            player.getBackpack().removeIngredients(ingredient, 1);

                            producingGood = new ArtisanGood(ArtisanGoodType.Juice,
                                2 * crop.getType().getEnergy(),
                                (int) (2.25 * crop.getType().getBaseSellPrice()));
                            return new Result(true, "Your product is being made.Please wait.");
                        }
                        return new Result(false, "You don't have enough Ingredients!");
                    }
                }
                return new Result(false, "You don't have enough Ingredients!");
            }
            case "Mead", "mead" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                    if (ingredient instanceof ArtisanGood artisanGood && artisanGood.getType().equals(ArtisanGoodType.Honey)) {

                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {

                            player.getBackpack().removeIngredients(ingredient, 1);

                            producingGood = new ArtisanGood(ArtisanGoodType.Mead);
                            return new Result(true, "Your product is being made.Please wait.");
                        }
                        return new Result(false, "You don't have enough Ingredients!");
                    }
                }
                return new Result(false, "You don't have enough Ingredients!");
            }
            case "PaleAle", "pale_ale" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                    if (ingredient instanceof Crop crop && crop.getType().equals(CropType.Hops)) {

                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {

                            player.getBackpack().removeIngredients(ingredient, 1);

                            producingGood = new ArtisanGood(ArtisanGoodType.PaleAle);
                            return new Result(true, "Your product is being made.Please wait.");
                        }
                        return new Result(false, "You don't have enough Ingredients!");
                    }
                }
                return new Result(false, "You don't have enough Ingredients!");
            }
            case "Wine", "wine" -> {
                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                    if (ingredient instanceof Fruit) {

                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {

                            player.getBackpack().removeIngredients(ingredient, 1);

                            producingGood = new ArtisanGood(ArtisanGoodType.Wine,
                                (int) (1.75 * ((Fruit) ingredient).getEnergy()),
                                3 * ((Fruit) ingredient).getSellPrice());
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
