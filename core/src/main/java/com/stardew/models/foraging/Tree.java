package com.stardew.models.foraging;

import com.badlogic.gdx.graphics.Texture;
import com.stardew.models.BackgroundColors;
import com.stardew.models.ColorPrinter;
import com.stardew.models.Placeable;
import com.stardew.models.app.App;
import com.stardew.models.date.Time;
import com.stardew.models.date.Weather;

import java.awt.*;

public class Tree implements Growable, Placeable {
    private final String backgroundCode = BackgroundColors.BROWN;
    private final String colorCode = ColorPrinter.GREEN;
    private final TreeType type;
    private int levelOfGrowth;
    private Time lastGrowthTime;
    private Time lastHarvestTime;
    private Time lastWaterTime;
    private final Fertilizer fertilizer;
    private final int numberOfDaysCanBeAliveWithoutWater;
    private Rectangle bounds;
    private Texture texture ;

    public Tree(TreeType type, Time timeOfPlanting, Fertilizer fertilizer, int x, int y, int width, int height){
        this.type = type;
        this.lastGrowthTime = timeOfPlanting.clone();
        this.lastWaterTime = timeOfPlanting.clone();
        this.fertilizer = fertilizer;
        if (fertilizer == null) {
            this.levelOfGrowth = 0;
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
        this.bounds = new Rectangle(x, y, width, height);
        texture = type.getStageTextures().getFirst();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public char getSymbol() {
        return 'T';
    }

    public TreeType getType() {
        return type;
    }

    public void grow(Time today) {
        if (isComplete())
            return;

        int timeForGrow = type.getTimeForGrow(levelOfGrowth);

        int todayDate = today.getDate();
        texture = type.getStageTextureByLevel(levelOfGrowth);

        if (today.getSeason() != lastGrowthTime.getSeason())
            todayDate += Math.abs(lastGrowthTime.getSeason().ordinal() - today.getSeason().ordinal()) * 28;

        if (lastGrowthTime.getDate() + timeForGrow == todayDate) {
            levelOfGrowth++;
            lastGrowthTime = today.clone();
        }

    }

    public boolean canGrowAgain() {
        return true;
    }

    public boolean isComplete() {
        return levelOfGrowth >= type.getStages().size();
    }

    public boolean harvest() {
        if (!isComplete())
            return false;

        Time today = App.getGame().getTime().clone();
        int timeForGrow = type.getHarvestCycle();

        if (lastHarvestTime == null || lastHarvestTime.getDate() + timeForGrow <= today.getDate()) {
            lastHarvestTime = today.clone();
            return true;
        }
        return false;
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
        return type.getFruit().name();
    }

    public String getName() {
        return type.name();
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

    @Override
    public Texture getTexture(){
        return texture;
    }

}
