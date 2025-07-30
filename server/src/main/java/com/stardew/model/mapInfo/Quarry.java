package com.stardew.model.mapInfo;

import com.badlogic.gdx.graphics.Color;
//import com.stardew.model.BackgroundColors;
//import com.stardew.model.ColorPrinter;
import com.stardew.model.TextureID;
import com.stardew.model.mapInfo.foraging.ForagingMineral;

import java.awt.*;
import java.util.ArrayList;

public class Quarry implements Placeable {
//    private final String backgroundCode = BackgroundColors.BRIGHT_CYAN;
//    private final String colorCode = ColorPrinter.BRIGHT_BLACK;
    private final Rectangle bounds;
    private final ArrayList<ForagingMineral> foragingMinerals = new ArrayList<>();

    public Quarry(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);

    }

    public Rectangle getBounds() {
        return bounds;
    }

    public char getSymbol() {
        return 'Q';
    }

    public ArrayList<ForagingMineral> getForagingMinerals() {
        return foragingMinerals;
    }

    public void addForagingMineral(ForagingMineral foragingMineral) {
        foragingMinerals.add(foragingMineral);
    }

    @Override
    public TextureID getTexture() {
        return null;
    }

}
