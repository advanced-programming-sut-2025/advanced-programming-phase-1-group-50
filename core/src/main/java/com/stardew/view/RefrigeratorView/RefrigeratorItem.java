package com.stardew.view.RefrigeratorView;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.cooking.Eatable;

public class RefrigeratorItem {
    boolean occupied;
    TextureRegion itemTexture;
    Eatable eatableItem;
    int quantity;

    public boolean isOccupied() {
        return occupied;
    }

    public Eatable getEatableItem() {
        return eatableItem;
    }
}
