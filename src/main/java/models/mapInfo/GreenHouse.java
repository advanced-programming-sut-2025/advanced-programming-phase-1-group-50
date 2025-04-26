package models.mapInfo;

import models.Placeable;

import java.awt.*;

public class GreenHouse implements Placeable {
    private boolean isBroken;
    private Rectangle bounds;
    public GreenHouse(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
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
}
