package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.models.manuFactor.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ArtisanAsset {
    BeeHouse(new Image(GamePictureManager.beeHouseNormal),
        List.of(
            ArtisanGoodAsset.Honey)),
    CharcoalKiln(new Image(GamePictureManager.charcoalKilnNormal),
        List.of(
            ArtisanGoodAsset.Coal)),
    CheesePress(new Image(GamePictureManager.cheesePressNormal),
        Arrays.asList(
            ArtisanGoodAsset.Cheese,
            ArtisanGoodAsset.GoatCheese)),
    Dehydrator(new Image(GamePictureManager.dehydratorNormal),
        Arrays.asList(
            ArtisanGoodAsset.DriedMushroom,
            ArtisanGoodAsset.DriedFruit,
            ArtisanGoodAsset.Raisins)),
    FishSmoker(new Image(GamePictureManager.fishSmokerNormal),
        List.of(
            ArtisanGoodAsset.SmokedFish)),
    Furnace(new Image(GamePictureManager.furnaceNormal),
        Arrays.asList(
            ArtisanGoodAsset.IronBar,
            ArtisanGoodAsset.IridiumBar,
            ArtisanGoodAsset.CopperBar,
            ArtisanGoodAsset.GoldBar)),
    Keg(new Image(GamePictureManager.kegNormal),
        Arrays.asList(
            ArtisanGoodAsset.Beer,
            ArtisanGoodAsset.Vinegar,
            ArtisanGoodAsset.Coffee,
            ArtisanGoodAsset.Juice,
            ArtisanGoodAsset.Mead,
            ArtisanGoodAsset.PaleAle,
            ArtisanGoodAsset.Wine)),
    Loom(new Image(GamePictureManager.loomNormal),
        List.of(
            ArtisanGoodAsset.Cloth)),
    MayonnaiseMachine(new Image(GamePictureManager.mayonnaiseMachineNormal),
        Arrays.asList(
            ArtisanGoodAsset.Mayonnaise,
            ArtisanGoodAsset.DuckMayonnaise,
            ArtisanGoodAsset.DinosaurMayonnaise)),
    OilMaker(new Image(GamePictureManager.oilMakerNormal),
        Arrays.asList(
            ArtisanGoodAsset.Oil,
            ArtisanGoodAsset.TruffleOil)),
    PreservesJar(new Image(GamePictureManager.preservesJarNormal),
        Arrays.asList(
            ArtisanGoodAsset.Pickles,
            ArtisanGoodAsset.Jelly));

    private final Image image;
    private final ArrayList<ArtisanGoodAsset> products;
    private final String description;

    ArtisanAsset(Image image, List<ArtisanGoodAsset> products) {
        this.image = image;
        this.products = new ArrayList<>(products);

        StringBuilder des = new StringBuilder();
        des.append("\n<").append(this.name()).append(">\n");
        for (ArtisanGoodAsset product : products) {
            des.append("---------------------------------------").append(product.getDescription());
        }

        description = des.toString();
    }

    public Image getImage() {
        return image;
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

    public static ArtisanAsset getArtisanAssetByName(String name) {
        return ArtisanAsset.valueOf(name);
    }
}
