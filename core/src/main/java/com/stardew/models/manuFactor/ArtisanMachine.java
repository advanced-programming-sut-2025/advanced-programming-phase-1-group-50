package com.stardew.models.manuFactor;

import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.date.Time;
import com.stardew.models.date.TimeInterval;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.recipes.CraftingRecipes;
import com.stardew.models.userInfo.Player;

import java.util.HashMap;

public abstract class ArtisanMachine {
    protected Time timeOfRequest;
    protected HashMap<ArtisanGood, TimeInterval> processingTimes;
    protected ArtisanGood producingGood;

    public ArtisanMachine() {
        processingTimes = new HashMap<>();
        timeOfRequest = null;
        producingGood = null;
    }

    public void use() {
        timeOfRequest = App.getGame().getTime().clone();
    }

    public ArtisanGood get() {
        if (isReady().getSuccessful())
            return producingGood;
        return null;
    }

    public abstract Result canUse(Player player, String product);

    public void reset() {
        timeOfRequest = null;
        producingGood = null;
    }

    public Result isReady() {
        if (timeOfRequest == null)
            return new Result(false, "You don't have any artisan goods in machine yet!!");
        int todayDate = App.getGame().getTime().getDate();
        int todayHour = App.getGame().getTime().getHour();
        if (App.getGame().getTime().getSeason() != timeOfRequest.getSeason())
            todayDate += 28;
        if(timeOfRequest.getDate() + processingTimes.get(producingGood).getDays() < todayDate ||
                timeOfRequest.getDate() + processingTimes.get(producingGood).getDays() == todayDate &&
                timeOfRequest.getHour() + processingTimes.get(producingGood).getHours() <= todayHour)
            return new Result(true, "Your product is Ready.");
        return new Result(false, "Your product is Not Ready.");
    }

    public static ArtisanMachine getArtisanMachineByRecipe(CraftingRecipes recipe) {
        if (recipe == null)
            return null;
        return switch (recipe) {
            case CharcoalKiln -> new CharcoalKiln();
            case Furnace -> new Furnace();
            case BeeHouse -> new BeeHouse();
            case CheesePress -> new CheesePress();
            case Keg -> new Keg();
            case Loom -> new Loom();
            case MayonnaiseMachine -> new MayonnaiseMachine();
            case OilMaker -> new OilMaker();
            case PreservesJar -> new PreservesJar();
            case Dehydrator -> new Dehydrator();
            case FishSmoker -> new FishSmoker();
            default -> null;
        };
    }
}
