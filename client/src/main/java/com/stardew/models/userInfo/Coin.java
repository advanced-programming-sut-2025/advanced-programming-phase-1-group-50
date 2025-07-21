package com.stardew.models.userInfo;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.mapInfo.Wood;

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
    public TextureRegion getInventoryTexture() {
        return GamePictureManager.coinTexture;
    }
}
