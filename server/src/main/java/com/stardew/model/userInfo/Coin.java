package com.stardew.model.userInfo;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.ItemInventoryType;
import com.stardew.model.TextureID;
import com.stardew.model.mapInfo.Ingredient;

public class Coin implements Ingredient {
    @Override
    public int hashCode() {
        return 3;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Coin;
    }

    @Override
    public String toString() {
        return "Coin";
    }

    @Override
    public TextureID getInventoryTexture() {
        return TextureID.coinTexture;
    }

    @Override
    public InventoryItemDTO toDTO() {
        return new InventoryItemDTO(getInventoryTexture() , false , 1 , ItemInventoryType.coin , "coin");
    }


    @Override
    public String getId() {
        return "coin";
    }
}
