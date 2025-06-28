package com.stardew.models;

import java.awt.*;

public interface Placeable {
    Rectangle getBounds();
    char getSymbol();
    String getColor();
    String getBackground();

}
