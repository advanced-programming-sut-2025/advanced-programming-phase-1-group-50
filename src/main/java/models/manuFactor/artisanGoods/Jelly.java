package models.manuFactor.artisanGoods;

import models.manuFactor.Ingredient;

public record Jelly(int energy, int sellPrice) implements Ingredient, ArtisanGood {
    @Override
    public int hashCode() {
        return 2;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Jelly;
    }
}
