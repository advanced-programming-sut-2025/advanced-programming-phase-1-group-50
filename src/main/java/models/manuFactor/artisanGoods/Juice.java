package models.manuFactor.artisanGoods;

import models.manuFactor.Ingredient;

public record Juice(int energy, int sellPrice) implements Ingredient, ArtisanGood {
    @Override
    public int hashCode() {
        return 2;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Juice;
    }
}
