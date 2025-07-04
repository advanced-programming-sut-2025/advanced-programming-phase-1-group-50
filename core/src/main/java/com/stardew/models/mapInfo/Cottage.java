package com.stardew.models.mapInfo;

import com.badlogic.gdx.graphics.Texture;
import com.stardew.models.BackgroundColors;
import com.stardew.models.ColorPrinter;
import com.stardew.models.Placeable;

import java.awt.*;

public class Cottage implements Placeable {
    private final String backgroundCode = BackgroundColors.WHITE;
    private final String colorCode = ColorPrinter.BRIGHT_BLACK;
    private final Rectangle bounds;
    private  Texture texture ;
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

    @Override
    public String getBackground(){
        return backgroundCode;
    }

    @Override
    public Texture getTexture(){
        return null;
    }


}
