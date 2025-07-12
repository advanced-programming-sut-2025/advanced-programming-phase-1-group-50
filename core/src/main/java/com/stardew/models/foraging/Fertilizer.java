package com.stardew.models.foraging;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.manuFactor.Ingredient;

public enum Fertilizer implements Ingredient {
    GrowthFertilizer,
    WaterFertilizer;

    public static Fertilizer getFertilizerByName(String fertilizerName) {
        if (fertilizerName.equalsIgnoreCase("GrowthFertilizer"))
            return GrowthFertilizer;
        else if (fertilizerName.equalsIgnoreCase("WaterFertilizer"))
            return WaterFertilizer;
        return null;
    }

    @Override
    public TextureRegion getInventoryTexture() {
        return null;
    }
}
