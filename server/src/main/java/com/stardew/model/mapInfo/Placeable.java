package com.stardew.model.mapInfo;

import com.stardew.model.TextureID;

import java.awt.*;

public interface Placeable {
    Rectangle getBounds();
    char getSymbol();
    TextureID getTexture();


}
