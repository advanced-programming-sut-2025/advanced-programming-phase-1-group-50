package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ArtisanAsset {
    BeeHouse(List.of(
        ArtisanGoodAsset.Honey)),
    CharcoalKiln(List.of(
        ArtisanGoodAsset.Coal)),
    CheesePress(Arrays.asList(
        ArtisanGoodAsset.Cheese,
        ArtisanGoodAsset.GoatCheese)),
    Dehydrator(Arrays.asList(
        ArtisanGoodAsset.DriedMushroom,
        ArtisanGoodAsset.DriedFruit,
        ArtisanGoodAsset.Raisins)),
    FishSmoker(List.of(
        ArtisanGoodAsset.SmokedFish)),
    Furnace(Arrays.asList(
        ArtisanGoodAsset.IronBar,
        ArtisanGoodAsset.IridiumBar,
        ArtisanGoodAsset.CopperBar,
        ArtisanGoodAsset.GoldBar)),
    Keg(Arrays.asList(
        ArtisanGoodAsset.Beer,
        ArtisanGoodAsset.Vinegar,
        ArtisanGoodAsset.Coffee,
        ArtisanGoodAsset.Juice,
        ArtisanGoodAsset.Mead,
        ArtisanGoodAsset.PaleAle,
        ArtisanGoodAsset.Wine)),
    Loom(List.of(
        ArtisanGoodAsset.Cloth)),
    MayonnaiseMachine(Arrays.asList(
        ArtisanGoodAsset.Mayonnaise,
        ArtisanGoodAsset.DuckMayonnaise,
        ArtisanGoodAsset.DinosaurMayonnaise)),
    OilMaker(Arrays.asList(
        ArtisanGoodAsset.Oil,
        ArtisanGoodAsset.TruffleOil)),
    PreservesJar(Arrays.asList(
        ArtisanGoodAsset.Pickles,
        ArtisanGoodAsset.Jelly));

    private final ArrayList<ArtisanGoodAsset> products;

    ArtisanAsset(List<ArtisanGoodAsset> products) {
        this.products = new ArrayList<>(products);
    }

    public ArrayList<ArtisanGoodAsset> getProducts() {
        return new ArrayList<>(products);
    }
}
