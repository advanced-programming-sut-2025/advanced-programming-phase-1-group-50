package models.foraging;

import models.manuFactor.Ingredient;

public class Crop implements Ingredient {
    private ForagingQuality quality;
    private CropType type;


    public int calculatePrice() {
        return 0;
    }
}
