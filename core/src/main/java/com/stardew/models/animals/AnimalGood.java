package com.stardew.models.animals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.stores.Sellable;

import java.util.Objects;

public class AnimalGood implements Ingredient, Sellable {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AnimalGood that = (AnimalGood) o;
        return type == that.type && quality == that.quality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, quality);
    }

    @Override
    public String toString() {
        return quality + " " + type;
    }


    @Override
    public TextureRegion getInventoryTexture() {
        return type.getInventoryTexture();
    }
}
