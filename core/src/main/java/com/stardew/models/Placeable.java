package com.stardew.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;

public interface Placeable {
    Rectangle getBounds();
    char getSymbol();
    String getColor();
    String getBackground();
    TextureRegion getTexture();

}
