package models.manuFactor.artisanGoods;

import models.manuFactor.Ingredient;

import java.util.Objects;

public class ArtisanGood implements Ingredient {
    private final ArtisanGoodType type;

    public ArtisanGood(ArtisanGoodType type) {
        this.type = type;
    }

    public ArtisanGood(ArtisanGoodType type, int energy, int sellPrice) {
        this.type = type;
        type.setSellPrice(sellPrice);
        type.setEnergy(energy);
    }

    public ArtisanGoodType getType() {
        return type;
    }

    public int getSellPrice() {
        return type.getSellPrice();
    }

    public int getEnergy() {
        return type.getEnergy();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ArtisanGood that)) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type);
    }
}
