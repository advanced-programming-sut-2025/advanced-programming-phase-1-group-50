package com.stardew.models.mapInfo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.BackgroundColors;
import com.stardew.models.ColorPrinter;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Placeable;
import com.stardew.models.app.App;
import com.stardew.models.foraging.Crop;
import com.stardew.models.foraging.Growable;
import com.stardew.models.foraging.Tree;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GreenHouse implements Placeable {
    private final String backgroundCode = BackgroundColors.GREEN;
    private final String colorCode = ColorPrinter.BRIGHT_YELLOW;
    private boolean isBroken;
    private final Rectangle bounds;
    private ArrayList<Growable> growables = new ArrayList<>();
    private TextureRegion texture = new TextureRegion(GamePictureManager.greenHouseTexture);
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
                if (growable.isCompleteAgain()) {
                    iterator.remove();
                    growable.doAgainHarvesting();
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


    @Override
    public String getColor(){
        return colorCode;
    }

    @Override
    public String getBackground(){
        return backgroundCode;
    }

    @Override
    public TextureRegion getTexture() {
        return texture;
    }

    @Override
    public Color getMiniMapColor() {
        return Color.MAGENTA;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

}
