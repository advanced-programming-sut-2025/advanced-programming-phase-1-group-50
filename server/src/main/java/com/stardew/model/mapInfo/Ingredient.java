package com.stardew.model.mapInfo;

import com.stardew.model.InventoryItemDTO;

public interface Ingredient extends InventoryItem{
    default InventoryItemDTO toDTO() {
        return new InventoryItemDTO(getInventoryTexture(), false , 1);
    }
}
