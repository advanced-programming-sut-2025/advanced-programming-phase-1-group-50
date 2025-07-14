package com.stardew.view.RefrigeratorView;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.cooking.Eatable;
import com.stardew.models.cooking.Food;

public class RefrigeratorItem {
    boolean occupied;
    TextureRegion itemTexture;
    Food eatableItem;
    int quantity;

    public boolean isOccupied() {
        return occupied;
    }

    public Food getEatableItem() {
        return eatableItem;
    }
}
