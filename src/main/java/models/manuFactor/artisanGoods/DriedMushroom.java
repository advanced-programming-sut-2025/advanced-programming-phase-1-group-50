package models.manuFactor.artisanGoods;

import models.manuFactor.Ingredient;

public record DriedMushroom(int energy, int sellPrice) implements Ingredient, ArtisanGood {
}
