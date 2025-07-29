package com.stardew.model.mapInfo;

import com.stardew.model.TextureID;

public class Wood implements Ingredient {
    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Wood;
    }

    @Override
    public String toString() {
        return "Wood";
    }

    @Override
    public TextureID getInventoryTexture() {
        return TextureID.woodTexture;
    }
}
