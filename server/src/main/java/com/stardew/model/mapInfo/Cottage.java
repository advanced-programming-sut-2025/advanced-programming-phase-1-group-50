package com.stardew.model.mapInfo;

import com.stardew.model.TextureID;

import java.awt.*;

public class Cottage implements Placeable{

    private final Rectangle bounds;
    private TextureID texture;
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
    public TextureID getTexture() {
        return texture;
    }



}
