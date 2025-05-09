package models.foraging;

import models.manuFactor.Ingredient;

public enum Fertilizer implements Ingredient {
    GrowthFertilizer,
    WaterFertilizer;

    public static Fertilizer getFertilizerByName(String fertilizerName) {
        if (fertilizerName.equalsIgnoreCase("GrowthFertilizer"))
            return GrowthFertilizer;
        else
            return WaterFertilizer;
    }
}
