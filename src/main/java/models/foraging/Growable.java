package models.foraging;

import models.date.Time;

public interface Growable {
    void grow(Time today);
    boolean canGrowAgain();
    boolean isComplete();
    boolean harvest();
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
