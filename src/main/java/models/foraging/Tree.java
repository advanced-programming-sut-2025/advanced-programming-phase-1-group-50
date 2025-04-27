package models.foraging;

import models.Placeable;
import models.app.App;
import models.date.Time;

import java.awt.*;

public class Tree implements Growable, Placeable {
    private final TreeType type;
    private int levelOfGrowth;
    private Time lastGrowthTime;
    private Time lastHarvestTime;
    private Time lastWaterTime;
    private Rectangle bounds;
    private static final int numberOfDaysCanBeAliveWithoutWater = 2;

    public Tree(TreeType type, Time timeOfPlanting, int x, int y, int width, int height){
        this.type = type;
        this.lastGrowthTime = timeOfPlanting.clone();
        this.lastWaterTime = timeOfPlanting.clone();
        this.levelOfGrowth = 0;
        this.bounds = new Rectangle(x, y, width, height);
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

        if (lastGrowthTime.getDate() + timeForGrow == today.getDate()) {
            levelOfGrowth++;
            lastGrowthTime = today;
        }

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
            lastHarvestTime = today;
            return true;
        }
        return false;
    }

    public void watering() {
        lastWaterTime = App.getGame().getTime().clone();
    }

    public boolean canBeAlive(Time today) {
        return today.getDate() <= lastWaterTime.getDate() + numberOfDaysCanBeAliveWithoutWater;
    }
}
