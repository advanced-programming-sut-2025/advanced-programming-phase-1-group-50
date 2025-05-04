package models.animals;

import models.manuFactor.Ingredient;

public class AnimalGood implements Ingredient {
    private final AnimalGoodType type;
    private final Quality quality;
    private final int sellPrice;

    public AnimalGood(AnimalGoodType type, Quality quality) {
        this.type = type;
        this.quality = quality;
        this.sellPrice = (int) (type.getPrice() * quality.getRatio());
    }

    public AnimalGoodType getType() {
        return type;
    }

    public Quality getQuality() {
        return quality;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}
