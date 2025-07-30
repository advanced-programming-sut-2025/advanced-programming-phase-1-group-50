package com.stardew.model.mapInfo;

import com.stardew.model.TextureID;

import java.awt.*;

public class Door implements Placeable{

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
    public TextureID getTexture() {
        return null;
    }


}
