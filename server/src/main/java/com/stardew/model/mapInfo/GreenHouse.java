package com.stardew.model.mapInfo;

import com.stardew.model.TextureID;
import com.stardew.model.mapInfo.foraging.Growable;

import java.awt.*;
import java.util.ArrayList;

public class GreenHouse implements Placeable{


    private boolean isBroken;
    private final Rectangle bounds;
    private ArrayList<Growable> growables = new ArrayList<>();
    private TextureID texture = TextureID.greenHouseRegion;
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

    @Override
    public TextureID getTexture() {
        return texture;
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
//    public void harvestGrowable(){
//        if(!isBroken()) {
//            Iterator<Growable> iterator = growables.iterator();
//            while (iterator.hasNext()) {
//                Growable growable = iterator.next();
//                if (growable.isCompleteAgain()) {
//                    iterator.remove();
//                    growable.doAgainHarvesting();
//                    if (growable instanceof Tree tree) {
//                        int numberOfWoods = tree.getCurrentStage();
//                        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Wood(), numberOfWoods);
//                    } else if (growable instanceof Crop crop) {
//                        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(crop, 1);
//                    }
//                }
//            }
//        }
//    }









}
