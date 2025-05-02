package models.mapInfo;

import models.Placeable;
import models.manuFactor.Ingredient;

import java.awt.*;

public class Stone implements Ingredient , Placeable {
    private Rectangle bounds;
    public Stone(int x, int y) {
        this.bounds = new Rectangle(x, y, 1, 1);
    }
    public Rectangle getBounds() {
        return bounds;
    }
    public char getSymbol() {
        return 'S';
    }
}
