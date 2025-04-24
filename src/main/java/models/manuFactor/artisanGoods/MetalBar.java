package models.manuFactor.artisanGoods;

import models.manuFactor.Ingredient;

public record MetalBar(BarType barType, int sellPrice) implements Ingredient, ArtisanGood {
}
