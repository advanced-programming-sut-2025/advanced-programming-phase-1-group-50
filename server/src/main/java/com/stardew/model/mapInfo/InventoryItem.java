package com.stardew.model.mapInfo;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.TextureID;

public interface InventoryItem {
    TextureID getInventoryTexture();
    InventoryItemDTO toDTO();
    String getId();
}
