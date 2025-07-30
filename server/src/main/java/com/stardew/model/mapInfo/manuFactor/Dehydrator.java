package com.stardew.model.mapInfo.manuFactor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.model.Result;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.foraging.Crop;
import com.stardew.model.mapInfo.foraging.CropType;
import com.stardew.model.mapInfo.foraging.ForagingCrop;
import com.stardew.model.mapInfo.foraging.Fruit;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.userInfo.Player;

public class Dehydrator extends ArtisanMachine{
    public Dehydrator() {
        super();
//        image = new Image(GamePictureManager.dehydratorNormal);
    }

//    @Override
//    public Result isReady() {
//        if (cheatReady)
//            return new Result(true, "Your product is Ready.");
//        if (timeOfRequest == null)
//            return new Result(false, "You don't have any artisan goods in machine yet!!");
//        int todayDate = App.getGame().getTime().getDate();
//        if (App.getGame().getTime().getSeason() != timeOfRequest.getSeason())
//            todayDate += 28;
//
//        //Next morning
//        if (todayDate > timeOfRequest.getDate())
//            return new Result(true, "Your product is Ready.");
//        else
//            return new Result(false, "Your product is Not Ready.");
//    }

//    @Override
//    public int getTotalProcessingTime() {
//        return 22 - timeOfRequest.getHour();
//    }
//
//    @Override
//    public Result canUse(Player player, String product) {
//        switch (product) {
//            case "DriedMushroom" -> {
//                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
//
//                    if (ingredient.equals(ForagingCrop.CommonMushroom) ||
//                        ingredient.equals(Fruit.CommonMushroom) ||
//                        ingredient.equals(ForagingCrop.RedMushroom) ||
//                        ingredient.equals(ForagingCrop.PurpleMushroom)) {
//
//                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {
//
//                            player.getBackpack().removeIngredients(ingredient, 5);
//
//                            producingGood = new ArtisanGood(ArtisanGoodType.DriedMushroom,
//                                50,
//                                (int) (7.5 * ((ForagingCrop) ingredient).getBaseSellPrice() + 25));
//                            return new Result(true, "Your product is being made.Please wait.");
//                        }
//                        return new Result(false, "You don't have enough Ingredients!");
//                    }
//                }
//                return new Result(false, "You don't have enough Ingredients!");
//            }
//            case "DriedFruit" -> {
//                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
//
//                    if (ingredient instanceof Fruit ||
//                        ingredient instanceof Crop crop && !(crop.getType().equals(CropType.Grape))) {
//
//                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {
//
//                            player.getBackpack().removeIngredients(ingredient, 5);
//
//                            if (ingredient instanceof Fruit)
//                                producingGood = new ArtisanGood(ArtisanGoodType.DriedFruit,
//                                    75,
//                                    (int) (7.5 * ((Fruit) ingredient).getSellPrice() + 25));
//                            else
//                                producingGood = new ArtisanGood(ArtisanGoodType.DriedFruit,
//                                    75,
//                                    (int) (7.5 * ((Crop) ingredient).getType().getBaseSellPrice() + 25));
//                            return new Result(true, "Your product is being made.Please wait.");
//                        }
//                        return new Result(false, "You don't have enough Ingredients!");
//                    }
//                }
//                return new Result(false, "You don't have enough Ingredients!");
//            }
//            case "Raisins" -> {
//                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
//
//                    if (ingredient instanceof Crop crop && crop.getType().equals(CropType.Grape)) {
//
//                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 5) {
//
//                            player.getBackpack().removeIngredients(ingredient, 5);
//
//                            producingGood = new ArtisanGood(ArtisanGoodType.Raisins);
//                            return new Result(true, "Your product is being made.Please wait.");
//                        }
//                        return new Result(false, "You don't have enough Ingredients!");
//                    }
//                }
//                return new Result(false, "You don't have enough Ingredients!");
//            }
//        }
//        return new Result(false, "This Machine can't make this Item!!");
//    }
}
