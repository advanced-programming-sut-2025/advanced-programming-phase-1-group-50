package com.stardew.model;

import com.stardew.model.mapInfo.Ingredient;

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
    public TextureID getInventoryTexture() {
        return null;
    }

    @Override
    public InventoryItemDTO toDTO() {
        return null;
    }

    @Override
    public String getId() {
        return "";
    }
}
