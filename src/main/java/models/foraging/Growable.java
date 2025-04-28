package models.foraging;

import models.date.Time;

public interface Growable {
    void grow(Time today);
    boolean isComplete();
    boolean harvest();
    void watering();
    boolean canBeAlive(Time today);
    int getNumberOfDaysToComplete();
}
