package com.stardew.models.foraging;

import com.stardew.models.ColorPrinter;
import com.stardew.models.Placeable;
import com.stardew.models.app.App;
import com.stardew.models.date.Time;
import com.stardew.models.date.Weather;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.mapInfo.Position;
import com.stardew.models.stores.Sellable;

import java.awt.*;
import java.util.Objects;

public class Crop implements Ingredient, Growable , Placeable, Sellable {
    private final String colorCode = ColorPrinter.BRIGHT_GREEN;
    private final String backgroundCode = ColorPrinter.BRIGHT_YELLOW;
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
        int todayDate = today.getDate();

        if (today.getSeason() != lastGrowthTime.getSeason())
            todayDate += Math.abs(lastGrowthTime.getSeason().ordinal() - today.getSeason().ordinal()) * 28;

        if (lastGrowthTime.getDate() + timeForGrow == todayDate) {
            levelOfGrowth++;
            lastGrowthTime = today.clone();
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
            lastHarvestTime = today.clone();
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
        if (today.getWeather().equals(Weather.Rainy)) {
            watering();
            return true;
        }
        int todayDate = today.getDate();
        if (today.getSeason() != lastGrowthTime.getSeason())
            todayDate += Math.abs(lastGrowthTime.getSeason().ordinal() - today.getSeason().ordinal()) * 28;
        return todayDate <= lastWaterTime.getDate() + numberOfDaysCanBeAliveWithoutWater;
    }

    public int getNumberOfDaysToComplete() {
        if (isComplete())
            return 0;
        int passedDays = 0;
        for (int i = 0; i < levelOfGrowth; i++) {
            passedDays += type.getTimeForGrow(i);
        }
        Time today = App.getGame().getTime().clone();
        int todayDate = today.getDate();
        if (today.getSeason() != lastGrowthTime.getSeason())
            todayDate += Math.abs(lastGrowthTime.getSeason().ordinal() - today.getSeason().ordinal()) * 28;
        passedDays += todayDate - lastGrowthTime.getDate();
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

    public int getSellPrice() {
        return type.getBaseSellPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Crop crop = (Crop) o;
        return type.equals(crop.type);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type);
    }

    @Override
    public String getColor(){
        return colorCode;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    @Override
    public String getBackground(){
        return backgroundCode;
    }

}
