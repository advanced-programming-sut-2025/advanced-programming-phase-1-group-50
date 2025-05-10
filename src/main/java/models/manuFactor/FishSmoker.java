package models.manuFactor;

import models.Result;
import models.animals.Fish;
import models.animals.FishType;
import models.app.App;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.manuFactor.artisanGoods.ArtisanGoodType;
import models.userInfo.Player;

public class FishSmoker extends ArtisanMachine {

    @Override
    public Result isReady() {
        if (timeOfRequest == null)
            return new Result(false, "You don't have any artisan goods in machine yet!!");
        if (timeOfRequest.getHour() + 1 <= App.getGame().getTime().getHour())
            return new Result(true, "Your product is Ready.");
        return new Result(false, "Your product is Not Ready.");
    }

    @Override
    public Result canUse(Player player, String product) {
        FishType fishType = FishType.getFishTypeByName(product);
        if (fishType != null) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {

                if (ingredient instanceof Fish fish && fish.getType().equals(fishType)) {
                    for (Ingredient ingredient1 : player.getBackpack().getIngredientQuantity().keySet()) {
                        if (ingredient1.equals(new ArtisanGood(ArtisanGoodType.Coal))) {

                            player.getBackpack().removeIngredients(ingredient, 1);
                            player.getBackpack().removeIngredients(ingredient1, 1);

                            producingGood = new ArtisanGood(ArtisanGoodType.SmokedFish,
                                    (int) (1.5 * fish.getType().getPrice()),
                                    2 * fish.getType().getPrice());
                            return new Result(true, "Your product is being made.Please wait.");
                        }
                    }
                }
            }
            return new Result(false, "You don't have enough Ingredients!");
        }
        return new Result(false, "This Machine can't make this Item!!");
    }
}
