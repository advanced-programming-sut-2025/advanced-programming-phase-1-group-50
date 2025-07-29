package com.stardew.model.mapInfo.manuFactor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.model.Result;
import com.stardew.model.animals.AnimalGood;
import com.stardew.model.animals.AnimalGoodType;
import com.stardew.model.gameApp.date.TimeInterval;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.foraging.Crop;
import com.stardew.model.mapInfo.foraging.CropType;
import com.stardew.model.mapInfo.foraging.Seeds;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.userInfo.Player;

public class OilMaker extends ArtisanMachine{
    public OilMaker() {
        super();
        image = new Image(GamePictureManager.oilMakerNormal);
        processingTimes.put(new ArtisanGood(ArtisanGoodType.TruffleOil), new TimeInterval(0, 6));
        //for oil, it will calculate dynamically
    }

    @Override
    public Result canUse(Player player, String product) {
        if (product.equalsIgnoreCase("TruffleOil")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof AnimalGood animalGood && animalGood.getType().equals(AnimalGoodType.Truffle)) {

                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {

                        player.getBackpack().removeIngredients(ingredient, 1);

                        producingGood = new ArtisanGood(ArtisanGoodType.TruffleOil);
                        return new Result(true, "Your product is being made.Please wait.");
                    }
                    return new Result(false, "You don't have enough Ingredients!");
                }
            }
            return new Result(false, "You don't have enough Ingredients!");
        }
        else if (product.equalsIgnoreCase("Oil")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Crop crop && crop.getType().equals(CropType.Corn)) {
                    player.getBackpack().removeIngredients(ingredient, 1);
                    producingGood = new ArtisanGood(ArtisanGoodType.Oil);
                    processingTimes.put(new ArtisanGood(ArtisanGoodType.Oil), new TimeInterval(0, 6));
                    return new Result(true, "Your product is being made.Please wait.");
                }
                if (ingredient.equals(Seeds.SunflowerSeeds)) {
                    player.getBackpack().removeIngredients(ingredient, 1);
                    producingGood = new ArtisanGood(ArtisanGoodType.Oil);
                    processingTimes.put(new ArtisanGood(ArtisanGoodType.Oil), new TimeInterval(2, 0));
                    return new Result(true, "Your product is being made.Please wait.");
                }
                if (ingredient instanceof Crop crop && crop.getType().equals(CropType.Sunflower)) {
                    player.getBackpack().removeIngredients(ingredient, 1);
                    producingGood = new ArtisanGood(ArtisanGoodType.Oil);
                    processingTimes.put(new ArtisanGood(ArtisanGoodType.Oil), new TimeInterval(0, 1));
                    return new Result(true, "Your product is being made.Please wait.");
                }
            }
            return new Result(false, "You don't have enough Ingredients!");
        }
        return new Result(false, "This Machine can't make this Item!!");
    }
}
