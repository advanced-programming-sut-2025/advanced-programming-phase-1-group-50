package models.foraging;

import models.Placeable;
import models.app.App;
import models.date.Time;
import models.manuFactor.Ingredient;
import models.mapInfo.Position;

import java.awt.*;

public class Crop implements Ingredient, Growable , Placeable {
    private final CropType type;
    private int levelOfGrowth;
    private Time lastGrowthTime;
    private Time lastHarvestTime;
    private Time lastWaterTime;
    private final Fertilizer fertilizer;
    private final int numberOfDaysCanBeAliveWithoutWater;
    private final Rectangle bounds;



    public Crop(CropType type, Time timeOfPlanting, Fertilizer fertilizer , int x ,  int y) {
        this.type = type;
        this.lastGrowthTime = timeOfPlanting.clone();
        this.lastWaterTime = timeOfPlanting.clone();
        this.fertilizer = fertilizer;
        if (fertilizer == null) {
            levelOfGrowth = 0;
            numberOfDaysCanBeAliveWithoutWater = 2;
        }
        else {
            if (fertilizer.equals(Fertilizer.WaterFertilizer)) {
                levelOfGrowth = 0;
                numberOfDaysCanBeAliveWithoutWater = 3;
            } else {
                levelOfGrowth = 1;
                numberOfDaysCanBeAliveWithoutWater = 2;
            }
        }
        this.bounds = new Rectangle(x, y,1, 1);
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

    public int getNumberOfDaysToComplete() {
        int passedDays = 0;
        for (int i = 0; i < levelOfGrowth; i++) {
            passedDays += type.getTimeForGrow(i);
        }
        passedDays += App.getGame().getTime().getDate() - lastGrowthTime.getDate();
        return type.getTotalHarvestTime() - passedDays;
    }

    public int getCurrentStage() {
        return levelOfGrowth;
    }

    public boolean hasWateredToday() {
        return App.getGame().getTime().getDate() == lastWaterTime.getDate();
    }

    public boolean hasFertilized() {
        return fertilizer != null;
    }

    public Fertilizer getFertilizer() {
        return fertilizer;
    }

    public String getNameOfProduct() {
        return type.getName();
    }

    public String getName() {
        return type.name();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public char getSymbol() {
        return '*';
    }
}
