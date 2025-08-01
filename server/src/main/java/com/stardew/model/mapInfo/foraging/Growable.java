package com.stardew.model.mapInfo.foraging;

import com.stardew.model.gameApp.date.Time;

public interface Growable {
    void grow(Time today);
    boolean canGrowAgain();
    boolean isComplete();
    boolean isCompleteAgain();
    void doAgainHarvesting();
    void watering();
    boolean canBeAlive(Time today);
    int getNumberOfDaysToComplete();
    int getCurrentStage();
    boolean hasWateredToday();
    boolean hasFertilized();
    Fertilizer getFertilizer();
    String getName();
    String getNameOfProduct();
}
