package models.mapInfo;

import models.ColorPrinter;
import models.Placeable;

import java.awt.*;

public class Cottage implements Placeable {
    private final String colorCode = ColorPrinter.BRIGHT_BLACK;
    private final Rectangle bounds;
    public Cottage(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public char getSymbol() {
        return 'C';
    }

    @Override
    public String getColor(){
        return colorCode;
    }

}
