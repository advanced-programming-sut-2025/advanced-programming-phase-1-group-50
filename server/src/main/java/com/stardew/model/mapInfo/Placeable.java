package com.stardew.model.mapInfo;

import com.stardew.model.PlaceableDTO;
import com.stardew.model.TextureID;

import java.awt.*;

public interface Placeable {
    Rectangle getBounds();
    char getSymbol();
    TextureID getTexture();


    default PlaceableDTO toPlaceableDTO() {
        return new PlaceableDTO(getBounds().x, getBounds().y, getBounds().width, getBounds().height, getTexture());
    }
}
