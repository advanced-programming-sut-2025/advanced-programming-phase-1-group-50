package models.manuFactor;

import models.animals.AnimalGoodType;
import models.date.TimeInterval;
import models.foraging.Crop;
import models.foraging.CropType;
import models.foraging.Seeds;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.manuFactor.artisanGoods.ArtisanGoodType;
import models.userInfo.Player;

public class OilMaker extends ArtisanMachine {

    public OilMaker(Player player) {
        super();
        processingTimes.put(new ArtisanGood(ArtisanGoodType.TruffleOil), new TimeInterval(0, 6));
        //for oil, it will calculate dynamically
    }

    @Override
    public boolean canUse(Player player, String product) {
        if (product.equals("Truffle_Oil") || product.equals("truffle_oil")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient.equals(AnimalGoodType.Truffle)) {
                    if (player.getBackpack().getIngredientQuantity().get(ingredient) >= 1) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        producingGood = new ArtisanGood(ArtisanGoodType.TruffleOil);
                        return true;
                    }
                    return false;
                }
            }
        } if (product.equals("Oil") || product.equals("oil")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Crop && ((Crop) ingredient).getType().equals(CropType.Corn)) {
                    player.getBackpack().removeIngredients(ingredient, 1);
                    producingGood = new ArtisanGood(ArtisanGoodType.Oil);
                    processingTimes.put(new ArtisanGood(ArtisanGoodType.Oil), new TimeInterval(0, 6));
                    return true;
                }
                if (ingredient.equals(Seeds.SunflowerSeeds)) {
                    player.getBackpack().removeIngredients(ingredient, 1);
                    producingGood = new ArtisanGood(ArtisanGoodType.Oil);
                    processingTimes.put(new ArtisanGood(ArtisanGoodType.Oil), new TimeInterval(2, 0));
                    return true;
                }
                if (ingredient instanceof Crop && ((Crop) ingredient).getType().equals(CropType.Sunflower)) {
                    player.getBackpack().removeIngredients(ingredient, 1);
                    producingGood = new ArtisanGood(ArtisanGoodType.Oil);
                    processingTimes.put(new ArtisanGood(ArtisanGoodType.Oil), new TimeInterval(0, 1));
                    return true;
                }
            }
        }
        return false;
    }
}
