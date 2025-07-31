package com.stardew.model.mapInfo;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.ItemInventoryType;
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

    @Override
    public InventoryItemDTO toDTO() {
        return new InventoryItemDTO(getInventoryTexture() , false , 1 , ItemInventoryType.wood , "wood");
    }

    @Override
    public String getId() {
        return "wood";
    }
}
