package models.mapInfo;

import models.Placeable;
import models.app.App;
import models.foraging.Crop;
import models.foraging.Growable;
import models.foraging.Tree;
import models.manuFactor.Ingredient;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GreenHouse implements Placeable {
    private boolean isBroken;
    private final Rectangle bounds;
    private ArrayList<Growable> growables = new ArrayList<>();
    public GreenHouse(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
        this.isBroken = true;
    }
    public boolean isBroken() {
        return isBroken;
    }
    public Rectangle getBounds() {
        return bounds;
    }
    public void setBroken(boolean broken) {
        isBroken = broken;
    }
    public char getSymbol() {
        return 'G';
    }



    public ArrayList<Growable> getGrowables() {
        return growables;
    }

    public void addGrowable(Growable growable) {
        growables.add(growable);
    }



    public void watering(){
        if(!isBroken()) {
            for (Growable growable : growables) {
                growable.watering();

            }
        }
    }
    public void harvestGrowable(){
        if(!isBroken()) {
            Iterator<Growable> iterator = growables.iterator();
            while (iterator.hasNext()) {
                Growable growable = iterator.next();
                if (growable.harvest()) {
                    growables.removeIf(c -> c == growable);
                    if (growable instanceof Tree tree) {
                        int numberOfWoods = tree.getCurrentStage();
                        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Wood(), numberOfWoods);
                    } else if (growable instanceof Crop crop) {
                        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(crop, 1);
                    }
                }
            }
        }
    }
}
