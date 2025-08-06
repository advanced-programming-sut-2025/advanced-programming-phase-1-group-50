package com.stardew.model.mapInfo.manuFactor;

import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.TimeProvider;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.foraging.ForagingMineral;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.userInfo.Player;
import com.stardew.model.animals.Fish;

public class FishSmoker extends ArtisanMachine{

    public FishSmoker(TimeProvider timeProvider, int x, int y) {
        super(timeProvider, x, y);
    }

    @Override
    public TextureID getTexture() {
        return TextureID.fishSmoker;
    }

    @Override
    public Result isReady() {
        if (cheatReady)
            return new Result(true, "Your product is Ready.");
        if (timeOfRequest == null)
            return new Result(false, "You don't have any artisan goods in machine yet!!");
        int todayDate = timeProvider.getTime().getDate();
        if (timeProvider.getTime().getSeason() != timeOfRequest.getSeason())
            todayDate += 28;
        if (timeOfRequest.getDate() < todayDate ||
            timeOfRequest.getHour() + 1 <= timeProvider.getTime().getHour())
            return new Result(true, "Your product is Ready.");
        return new Result(false, "Your product is Not Ready.");
    }

    @Override
    public int getTotalProcessingTime() {
        return 1;
    }

    @Override
    public Result canUse(Player player, String product) {

        if (product.equalsIgnoreCase("SmokedFish")) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {

                if (ingredient instanceof Fish fish) {
                    for (Ingredient ingredient1 : player.getBackpack().getIngredientQuantity().keySet()) {
                        if (ingredient1.equals(new ArtisanGood(ArtisanGoodType.Coal)) ||
                            ingredient1.equals(ForagingMineral.Coal)) {

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
