package com.stardew.model.mapInfo.foraging;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.TextureID;
import com.stardew.model.mapInfo.Ingredient;

public enum Fertilizer implements Ingredient {
    GrowthFertilizer(TextureID.growthFertilizer),
    WaterFertilizer(TextureID.waterFertilizer);

    private final TextureID texture;

    Fertilizer(TextureID texture) {
        this.texture = texture;
    }

    public static Fertilizer getFertilizerByName(String fertilizerName) {
        if (fertilizerName.equalsIgnoreCase("GrowthFertilizer"))
            return GrowthFertilizer;
        else if (fertilizerName.equalsIgnoreCase("WaterFertilizer"))
            return WaterFertilizer;
        return null;
    }


    public TextureID getInventoryTexture() {
        return texture;
    }


}
