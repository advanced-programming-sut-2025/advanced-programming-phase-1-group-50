package com.stardew.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.manuFactor.Ingredient;

public class Bouquet implements Ingredient {

    @Override
    public int hashCode() {
        return 4;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Bouquet;
    }

    @Override
    public String toString() {
        return "Bouquet";
    }


    @Override
    public TextureRegion getInventoryTexture() {
        return null;
    }
}
