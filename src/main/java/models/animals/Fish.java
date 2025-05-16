package models.animals;

import models.manuFactor.Ingredient;
import models.stores.Sellable;

import java.util.Objects;

public class Fish implements Ingredient, Sellable {
    private final FishType type;
    private final int sellPrice;
    private final Quality quality;

    public Fish(FishType type, Quality quality) {
        this.type = type;
        this.quality = quality;
        this.sellPrice = (int) (type.getPrice() * quality.getRatio());
    }

    public FishType getType() {
        return type;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public Quality getQuality() {
        return quality;
    }

    public String getInfo() {
        return "Fish -> " +
                "type: " + type +
                ", quality: " + quality;
    }

    @Override
    public String toString() {
        return quality + " " + type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fish fish = (Fish) o;
        return type == fish.type && quality == fish.quality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, quality);
    }
}
