package models.waterBodies;

import models.ColorPrinter;
import models.Placeable;
import models.stores.CarpenterShop;

import java.awt.*;

public class Lake extends WaterBody implements Placeable {
    private final String colorCode = ColorPrinter.BLUE;
    private final Rectangle bounds;
    public Lake(int x, int y, int width, int height) {
        this.bounds = new Rectangle(x, y, width, height);

    }
    public Rectangle getBounds() {
        return bounds;
    }
    public char getSymbol() {
        return 'L';
    }

    @Override
    public String getColor(){
        return colorCode;
    }
}
