package models.manuFactor.artisanGoods;

import models.manuFactor.Ingredient;

public record SmokedFish(int energy, int sellPrice) implements Ingredient, ArtisanGood {
}
