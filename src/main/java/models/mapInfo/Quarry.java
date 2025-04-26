package models.mapInfo;

import models.Placeable;

import java.awt.*;

public class Quarry implements Placeable {
    private final Rectangle bounds;
    public Quarry(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);

    }
    public Rectangle getBounds() {
        return bounds;
    }
    public char getSymbol() {
        return 'Q';
    }
}
