package models.manuFactor;

import models.Result;
import models.app.App;
import models.date.Time;
import models.date.TimeInterval;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.userInfo.Player;

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
        if (App.getGame().getTime().getSeason() != timeOfRequest.getSeason()) {
            todayDate += 28;
            if(timeOfRequest.getDate() + processingTimes.get(producingGood).getDays() <= todayDate &&
                    timeOfRequest.getHour() + processingTimes.get(producingGood).getHours() <= todayHour)
                return new Result(true, "Your product is Ready.");
        }
        return new Result(false, "Your product is Not Ready.");
    }
}
