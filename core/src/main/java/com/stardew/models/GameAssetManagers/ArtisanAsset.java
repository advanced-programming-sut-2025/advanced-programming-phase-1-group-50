package com.stardew.models.GameAssetManagers;

import com.stardew.models.manuFactor.*;

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
    private final String description;

    ArtisanAsset(List<ArtisanGoodAsset> products) {
        this.products = new ArrayList<>(products);

        StringBuilder des = new StringBuilder();
        des.append("\n<").append(this.name()).append(">\n");
        for (ArtisanGoodAsset product : products) {
            des.append("---------------------------------------").append(product.getDescription());
        }

        description = des.toString();
    }

    public ArrayList<ArtisanGoodAsset> getProducts() {
        return new ArrayList<>(products);
    }

    public String getDescription() {
        return description;
    }

    public static ArtisanAsset getArtisanAssetByInstance(ArtisanMachine artisanMachine) {
        if (artisanMachine instanceof BeeHouse)
            return BeeHouse;
        if (artisanMachine instanceof CharcoalKiln)
            return CharcoalKiln;
        if (artisanMachine instanceof CheesePress)
            return CheesePress;
        if (artisanMachine instanceof Dehydrator)
            return Dehydrator;
        if (artisanMachine instanceof FishSmoker)
            return FishSmoker;
        if (artisanMachine instanceof Furnace)
            return Furnace;
        if (artisanMachine instanceof Keg)
            return Keg;
        if (artisanMachine instanceof Loom)
            return Loom;
        if (artisanMachine instanceof MayonnaiseMachine)
            return MayonnaiseMachine;
        if (artisanMachine instanceof OilMaker)
            return OilMaker;
        if (artisanMachine instanceof PreservesJar)
            return PreservesJar;
        else
            return null;
    }
}
