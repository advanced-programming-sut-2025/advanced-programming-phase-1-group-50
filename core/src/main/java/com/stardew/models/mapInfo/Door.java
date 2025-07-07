package com.stardew.models.mapInfo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.BackgroundColors;
import com.stardew.models.ColorPrinter;
import com.stardew.models.Placeable;

import java.awt.*;

public class Door implements Placeable {
    private final String backgroundCode = BackgroundColors.WHITE;
    private final String colorCode = ColorPrinter.GRAY;
    private final Rectangle doorPosition;
    public Door(int x, int y , int width, int height) {
        this.doorPosition = new Rectangle(x, y,width, height);
    }
    public Rectangle getBounds() {
        return doorPosition;
    }
    @Override
    public char getSymbol() {
        return 'd';
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
        return null;
    }

}
