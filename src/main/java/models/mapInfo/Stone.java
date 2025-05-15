package models.mapInfo;

import models.ColorPrinter;
import models.Placeable;
import models.manuFactor.Ingredient;

import java.awt.*;

public class Stone implements Ingredient , Placeable {
    private final String colorCode = ColorPrinter.GRAY;
    private Rectangle bounds;

    public Stone() {

    }

    public Stone(int x, int y) {
        this.bounds = new Rectangle(x, y, 1, 1);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public char getSymbol() {
        return 'S';
    }

    @Override
    public int hashCode() {
        return 2;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Stone;
    }

    @Override
    public String getColor(){
        return colorCode;
    }
}
