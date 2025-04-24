package models.manuFactor;

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
        timeOfRequest = App.getToday().clone();
    }

    public ArtisanGood get() {
        if (isReady())
            return producingGood;
        return null;
    }

    public abstract boolean canUse(Player player, String product);

    public void reset() {
        timeOfRequest = null;
        producingGood = null;
    }

    public boolean isReady() {
        if (timeOfRequest == null)
            return false;
        int todayDate = App.getToday().getDate();
        int todayHour = App.getToday().getHour();
        if (App.getToday().getSeason() != timeOfRequest.getSeason()) {
            todayDate += 28;
            if (timeOfRequest.getDate() + processingTimes.get(producingGood).getDays() <= todayDate &&
                    timeOfRequest.getHour() + processingTimes.get(producingGood).getHours() <= todayHour) {
                return true;
            }
        }
        return false;
    }
}
