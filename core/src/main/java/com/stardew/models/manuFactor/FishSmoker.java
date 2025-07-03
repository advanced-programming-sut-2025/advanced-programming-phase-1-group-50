package com.stardew.models.manuFactor;

import com.stardew.models.Result;
import com.stardew.models.animals.Fish;
import com.stardew.models.animals.FishType;
import com.stardew.models.app.App;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGoodType;
import com.stardew.models.userInfo.Player;

public class FishSmoker extends ArtisanMachine {

    @Override
    public Result isReady() {
        if (timeOfRequest == null)
            return new Result(false, "You don't have any artisan goods in machine yet!!");
        int todayDate = App.getGame().getTime().getDate();
        if (App.getGame().getTime().getSeason() != timeOfRequest.getSeason())
            todayDate += 28;
        if (timeOfRequest.getDate() < todayDate ||
                timeOfRequest.getHour() + 1 <= App.getGame().getTime().getHour())
            return new Result(true, "Your product is Ready.");
        return new Result(false, "Your product is Not Ready.");
    }

    @Override
    public int getTotalProcessingTime() {
        return 1;
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
