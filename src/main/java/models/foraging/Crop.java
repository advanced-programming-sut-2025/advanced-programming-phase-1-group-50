package models.foraging;

import models.app.App;
import models.date.Time;
import models.manuFactor.Ingredient;

public class Crop implements Ingredient, Growable {
    private ForagingQuality quality;
    private final CropType type;
    private int levelOfGrowth;
    private Time lastGrowthTime;
    private Time lastHarvestTime;
    private Time lastWaterTime;
    private static final int numberOfDaysCanBeAliveWithoutWater = 2;

    public Crop(CropType type, Time timeOfPlanting) {
        this.type = type;
        this.lastGrowthTime = timeOfPlanting.clone();
        this.lastWaterTime = timeOfPlanting.clone();
        levelOfGrowth = 0;
    }

    public int calculatePrice() {
        return 0;
    }

    public CropType getType() {
        return type;
    }

    public void grow(Time today) {
        if (isComplete())
            return;

        int timeForGrow = type.getTimeForGrow(levelOfGrowth);

        if (lastGrowthTime.getDate() + timeForGrow == today.getDate()) {
            levelOfGrowth++;
            lastGrowthTime = today;
        }

    }

    public boolean canGrowAgain() {
        return !type.isOneTime();
    }

    public boolean canHarvestAgain() {
        if (type.isOneTime())
            return false;

        if (lastHarvestTime == null)
            return true;

        Time today = App.getGame().getTime().clone();
        int timeForGrow = type.getRegrowthTime();

        return lastHarvestTime.getDate() + timeForGrow == today.getDate();
    }

    public boolean harvest() {
        if (!isComplete() || type.isOneTime())
            return false;

        Time today = App.getGame().getTime().clone();
        int timeForGrow = type.getRegrowthTime();

        if (lastHarvestTime == null || lastHarvestTime.getDate() + timeForGrow <= today.getDate()) {
            lastHarvestTime = today;
            return true;
        }
        return false;
    }

    public boolean isComplete() {
        return levelOfGrowth >= type.getNumberOfStages();
    }

    public void watering() {
        lastWaterTime = App.getGame().getTime().clone();
    }

    public boolean canBeAlive(Time today) {
        return today.getDate() <= lastWaterTime.getDate() + numberOfDaysCanBeAliveWithoutWater;
    }
}
