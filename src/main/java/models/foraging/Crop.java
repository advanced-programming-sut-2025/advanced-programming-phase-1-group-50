package models.foraging;

import models.app.App;
import models.date.Time;
import models.manuFactor.Ingredient;

public class Crop implements Ingredient {
    private ForagingQuality quality;
    private final CropType type;
    private int levelOfGrowth;
    private Time lastGrowthTime;
    private Time lastHarvestTime;

    public Crop(CropType type, Time timeOfPlanting) {
        this.type = type;
        this.lastGrowthTime = timeOfPlanting.clone();
        levelOfGrowth = 0;
    }

    public int calculatePrice() {
        return 0;
    }

    public CropType getType() {
        return type;
    }

    public void grow() {
        if (isComplete())
            return;

        Time today = App.getGame().getTime().clone();
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

    public void harvest() {
        if (!isComplete() || type.isOneTime())
            return;

        Time today = App.getGame().getTime().clone();
        int timeForGrow = type.getRegrowthTime();

        if (lastHarvestTime == null || lastHarvestTime.getDate() + timeForGrow <= today.getDate()) {
            lastHarvestTime = today;
        }
    }

    public boolean isComplete() {
        return levelOfGrowth >= type.getNumberOfStages();
    }
}
