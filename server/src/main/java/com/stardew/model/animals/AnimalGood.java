package com.stardew.model.animals;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.TextureID;
import com.stardew.model.mapInfo.Eatable;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.stores.Sellable;

import java.util.Objects;

public class AnimalGood implements Ingredient , Sellable , Eatable {
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

    public int getEnergy() {
        return type.getEnergy();
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
    public TextureID getInventoryTexture() {
        return type.getInventoryTexture();
    }


}
