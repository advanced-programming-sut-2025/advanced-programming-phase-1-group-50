package com.stardew.model.mapInfo;

import com.stardew.model.TextureID;
import com.stardew.model.gameApp.date.Time;
import com.stardew.model.mapInfo.foraging.*;

import java.awt.*;
import java.util.ArrayList;

public class GreenHouse implements Placeable{


    private boolean isBroken ;
    private final Rectangle bounds;
    private ArrayList<Growable> growables = new ArrayList<>();
    private TextureID texture = TextureID.initialGreenhouse;
    public GreenHouse(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
        this.isBroken = true;
        randomAddGrowable();
    }
    public boolean isBroken() {
        return isBroken;
    }
    public Rectangle getBounds() {
        return bounds;
    }
    public void setBroken(boolean broken) {
        isBroken = broken;
        if(!isBroken) {
            texture = TextureID.progressiveGreenhouse;
        }
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


    public void randomAddGrowable() {
        addGrowable(new Crop(CropType.Blueberry , new Time() , null , 0 ,0 ));
        addGrowable(new Crop(CropType.AncientFruit , new Time() , null , 0 ,0  ));
        addGrowable(new Tree(TreeType.AppleTree , new Time(), null , 0 , 0 , 1 , 1));
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
