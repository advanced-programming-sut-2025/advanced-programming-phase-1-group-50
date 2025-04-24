package models.manuFactor.artisanGoods;

import models.manuFactor.Ingredient;

public record DriedFruit(int energy, int sellPrice) implements Ingredient, ArtisanGood {
}
