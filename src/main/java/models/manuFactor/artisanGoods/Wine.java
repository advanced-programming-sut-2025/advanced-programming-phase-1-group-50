package models.manuFactor.artisanGoods;

import models.manuFactor.Ingredient;

public record Wine(int energy, int sellPrice) implements Ingredient, ArtisanGood {
    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Wine;
    }
}
