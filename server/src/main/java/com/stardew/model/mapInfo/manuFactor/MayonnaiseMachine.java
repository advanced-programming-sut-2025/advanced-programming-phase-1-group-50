package com.stardew.model.mapInfo.manuFactor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.model.Result;
import com.stardew.model.animals.AnimalGood;
import com.stardew.model.animals.AnimalGoodType;
import com.stardew.model.gameApp.date.TimeInterval;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.userInfo.Player;

public class MayonnaiseMachine extends ArtisanMachine{
    public MayonnaiseMachine() {
        super();
//        image = new Image(GamePictureManager.mayonnaiseMachineNormal);
        processingTimes.put(new ArtisanGood(ArtisanGoodType.Mayonnaise), new TimeInterval(0, 3));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.DuckMayonnaise), new TimeInterval(0, 3));
        processingTimes.put(new ArtisanGood(ArtisanGoodType.DinosaurMayonnaise), new TimeInterval(0, 3));
    }

//    @Override
//    public Result canUse(Player player, String product) {
//        switch (product) {
//            case "Mayonnaise", "mayonnaise" -> {
//                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
//                    if (ingredient instanceof AnimalGood animalGood &&
//                        (animalGood.getType().equals(AnimalGoodType.Egg) ||
//                            animalGood.getType().equals(AnimalGoodType.LargeEgg))) {
//
//                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
//
//                            player.getBackpack().removeIngredients(ingredient, 1);
//
//                            producingGood = new ArtisanGood(ArtisanGoodType.Mayonnaise);
//                            return new Result(true, "Your product is being made.Please wait.");
//                        }
//                        return new Result(false, "You don't have enough Ingredients!");
//                    }
//                }
//                return new Result(false, "You don't have enough Ingredients!");
//            }
//            case "DuckMayonnaise" -> {
//                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
//                    if (ingredient instanceof AnimalGood animalGood && animalGood.getType().equals(AnimalGoodType.DuckEgg)) {
//
//                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
//
//                            player.getBackpack().removeIngredients(ingredient, 1);
//
//                            producingGood = new ArtisanGood(ArtisanGoodType.DuckMayonnaise);
//                            return new Result(true, "Your product is being made.Please wait.");
//                        }
//                        return new Result(false, "You don't have enough Ingredients!");
//                    }
//                }
//                return new Result(false, "You don't have enough Ingredients!");
//            }
//            case "DinosaurMayonnaise" -> {
//                for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
//                    if (ingredient instanceof AnimalGood animalGood && animalGood.getType().equals(AnimalGoodType.DinosaurEgg)) {
//
//                        if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
//
//                            player.getBackpack().removeIngredients(ingredient, 1);
//
//                            producingGood = new ArtisanGood(ArtisanGoodType.DinosaurMayonnaise);
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
