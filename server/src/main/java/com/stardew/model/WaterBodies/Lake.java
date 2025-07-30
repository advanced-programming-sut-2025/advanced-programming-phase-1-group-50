package com.stardew.model.WaterBodies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.model.TextureID;
import com.stardew.model.mapInfo.Placeable;

import java.awt.*;

public class Lake implements Placeable {
    private final Rectangle bounds;
//    private final TextureID texture ;
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
    public TextureID getTexture() {
        return null;
    }



}
