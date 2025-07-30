package com.stardew.model.mapInfo.foraging;

import com.stardew.model.TextureID;
import com.stardew.model.gameApp.TimeProvider;
import com.stardew.model.gameApp.date.Time;
import com.stardew.model.gameApp.date.Weather;
import com.stardew.model.mapInfo.Eatable;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.Placeable;
import com.stardew.model.stores.Sellable;

import java.awt.*;
import java.util.Objects;

public class Crop implements Placeable , Ingredient , Sellable , Growable , Eatable {

    private final CropType type;
    private int levelOfGrowth;
    private Time lastGrowthTime;
    private Time lastHarvestTime;
    private Time lastWaterTime;
    private final Fertilizer fertilizer;
    private final int numberOfDaysCanBeAliveWithoutWater;
    private final Rectangle bounds;
    private boolean isGeneratedRandomly = false;
    private TimeProvider timeProvider;



    public Crop(CropType type, TimeProvider timeOfPlanting, Fertilizer fertilizer , int x , int y) {
        this.type = type;
        this.timeProvider = timeOfPlanting;
        this.lastGrowthTime = timeOfPlanting.getTime().clone();
        this.lastWaterTime = timeOfPlanting.getTime().clone();
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
        if (isComplete()) {
            if (isCompleteAgain())
                levelOfGrowth = type.getNumberOfStages();
            return;
        }

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

    public boolean isCompleteAgain() {
        if (!isComplete() || type.isOneTime())
            return false;

        if (lastHarvestTime == null) {
            return true;
        }

        Time today = timeProvider.getTime().clone();
        int timeForGrow = type.getRegrowthTime();

        int todayDate = today.getDate();
        if (today.getSeason() != lastHarvestTime.getSeason())
            todayDate += Math.abs(lastHarvestTime.getSeason().ordinal() - today.getSeason().ordinal()) * 28;

        return lastHarvestTime.getDate() + timeForGrow <= todayDate;
    }

    public void doAgainHarvesting() {
        lastHarvestTime = timeProvider.getTime().clone();
        levelOfGrowth = type.getNumberOfStages() + 1;
    }

    public boolean isComplete() {
        return levelOfGrowth >= type.getNumberOfStages();
    }





    @Override
    public void watering() {
        lastWaterTime = timeProvider.getTime().clone();
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
//
    public int getNumberOfDaysToComplete() {
        if (isComplete())
            return 0;
        int passedDays = 0;
        for (int i = 0; i < levelOfGrowth; i++) {
            passedDays += type.getTimeForGrow(i);
        }
        Time today = timeProvider.getTime().clone();
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
        return timeProvider.getTime().getDate() == lastWaterTime.getDate();
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
    public String toString() {
        return type.toString();
    }



    @Override
    public TextureID getTexture() {
        if(isGeneratedRandomly){
            return type.getLevelsTextures()[type.getNumberOfStages()];
        }
        return type.getLevelsTextures()[levelOfGrowth];
    }



    @Override
    public int getEnergy() {
        return type.getEnergy();
    }

    public boolean isGeneratedRandomly() {
        return isGeneratedRandomly;
    }

    public void setGeneratedRandomly(boolean isGeneratedRandomly) {
        this.isGeneratedRandomly = isGeneratedRandomly;
    }
//
//    public void render(Batch batch) {
//        batch.draw(getTexture(), bounds.x * GamePictureManager.TILE_SIZE, bounds.y * GamePictureManager.TILE_SIZE);
//    }

    @Override
    public TextureID getInventoryTexture() {
        return type.getMainTexture();
    }
}
