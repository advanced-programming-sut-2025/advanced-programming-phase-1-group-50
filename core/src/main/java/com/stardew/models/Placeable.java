package com.stardew.models;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public interface Placeable {
    Rectangle getBounds();
    char getSymbol();
    String getColor();
    String getBackground();
    Texture getTexture();

}
