package com.stardew.model.mapInfo.manuFactor;

import com.stardew.model.Result;
import com.stardew.model.gameApp.TimeProvider;
import com.stardew.model.gameApp.date.Time;
import com.stardew.model.gameApp.date.TimeInterval;
import com.stardew.model.mapInfo.Placeable;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.recipes.CraftingRecipes;
import com.stardew.model.userInfo.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public abstract class ArtisanMachine implements Placeable {
    protected Time timeOfRequest;
    protected HashMap<ArtisanGood, TimeInterval> processingTimes;
    protected ArtisanGood producingGood;
    protected boolean cheatReady = false;
    protected final String id;
    protected final TimeProvider timeProvider;
    protected final int x, y;


    public ArtisanMachine(TimeProvider timeProvider, int x, int y) {
        this.timeProvider = timeProvider;
        this.x = x;
        this.y = y;
        processingTimes = new HashMap<>();
        timeOfRequest = null;
        producingGood = null;
        this.id = UUID.randomUUID().toString();
    }


    public void use() {
        cheatReady = false;
        timeOfRequest = timeProvider.getTime().clone();
        //updateMachine();
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
        cheatReady = false;
        //updateMachine();
    }

    public Result isReady() {
        if (cheatReady)
            return new Result(true, "Your product is Ready.");
        if (timeOfRequest == null)
            return new Result(false, "You don't have any artisan goods in machine yet!!");
        int todayDate = timeProvider.getTime().getDate();
        int todayHour = timeProvider.getTime().getHour();
        if (timeProvider.getTime().getSeason() != timeOfRequest.getSeason())
            todayDate += 28;

        int exactDays = timeOfRequest.getDate() + processingTimes.get(producingGood).getDays();
        int exactHours = timeOfRequest.getHour() + processingTimes.get(producingGood).getHours();
        if (exactHours > 22) {
            exactHours -= 22 - 9;
            exactDays += 1;
        }
        if(exactDays < todayDate ||
            exactDays == todayDate && exactHours <= todayHour)
            return new Result(true, "Your product is Ready.");
        return new Result(false, "Your product is Not Ready.");
    }

    public int getPassedTime() {
        if (timeOfRequest == null)
            return 0;
        if (cheatReady)
            return getTotalProcessingTime();
        int todayDate = timeProvider.getTime().getDate();
        int todayHour = timeProvider.getTime().getHour();
        if (timeProvider.getTime().getSeason() != timeOfRequest.getSeason())
            todayDate += 28;
        int passedDays;
        int passedHours;
        if (todayDate > timeOfRequest.getDate()) {
            passedDays = todayDate - timeOfRequest.getDate() - 1;
            passedHours = (22 - timeOfRequest.getHour()) + (todayHour - 9);
        }
        else {
            passedDays = 0;
            passedHours = todayHour - timeOfRequest.getHour();
        }
        return passedDays * (22 - 9) + passedHours;
    }

    public int getTotalProcessingTime() {
        return processingTimes.get(producingGood).getDays() * (22 - 9) + processingTimes.get(producingGood).getHours();
    }

    public void setCheatReady(boolean cheatReady) {
        this.cheatReady = cheatReady;
        //updateMachine();
    }

    public boolean isAnyProducing() {
        return timeOfRequest != null;
    }

    public String getId() {
        return id;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 1, 1);
    }

    @Override
    public char getSymbol() {
        return 0;
    }




    public static ArtisanMachine CreateArtisanMachineByRecipe(CraftingRecipes recipe, TimeProvider timeProvider, int x, int y) {
        if (recipe == null)
            return null;
        return switch (recipe) {
            case CharcoalKiln -> new CharcoalKiln(timeProvider, x, y);
            case Furnace -> new Furnace(timeProvider, x, y);
            case BeeHouse -> new BeeHouse(timeProvider, x, y);
            case CheesePress -> new CheesePress(timeProvider, x, y);
            case Keg -> new Keg(timeProvider, x, y);
            case Loom -> new Loom(timeProvider, x, y);
            case MayonnaiseMachine -> new MayonnaiseMachine(timeProvider, x, y);
            case OilMaker -> new OilMaker(timeProvider, x, y);
            case PreservesJar -> new PreservesJar(timeProvider, x, y);
            case Dehydrator -> new Dehydrator(timeProvider, x, y);
            case FishSmoker -> new FishSmoker(timeProvider, x, y);
            default -> null;
        };
    }
}
