package com.stardew.model.userInfo;

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
}
