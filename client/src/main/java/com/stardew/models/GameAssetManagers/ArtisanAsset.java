package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.models.manuFactor.*;

import java.util.*;

public enum ArtisanAsset {
    BeeHouse(GamePictureManager.beeHouseNormal,
        List.of(
            ArtisanGoodAsset.Honey)),
    CharcoalKiln(GamePictureManager.charcoalKilnNormal,
        List.of(
            ArtisanGoodAsset.Coal)),
    CheesePress(GamePictureManager.cheesePressNormal,
        Arrays.asList(
            ArtisanGoodAsset.Cheese,
            ArtisanGoodAsset.GoatCheese)),
    Dehydrator(GamePictureManager.dehydratorNormal,
        Arrays.asList(
            ArtisanGoodAsset.DriedMushroom,
            ArtisanGoodAsset.DriedFruit,
            ArtisanGoodAsset.Raisins)),
    FishSmoker(GamePictureManager.fishSmokerNormal,
        List.of(
            ArtisanGoodAsset.SmokedFish)),
    Furnace(GamePictureManager.furnaceNormal,
        Arrays.asList(
            ArtisanGoodAsset.IronBar,
            ArtisanGoodAsset.IridiumBar,
            ArtisanGoodAsset.CopperBar,
            ArtisanGoodAsset.GoldBar)),
    Keg(GamePictureManager.kegNormal,
        Arrays.asList(
            ArtisanGoodAsset.Beer,
            ArtisanGoodAsset.Vinegar,
            ArtisanGoodAsset.Coffee,
            ArtisanGoodAsset.Juice,
            ArtisanGoodAsset.Mead,
            ArtisanGoodAsset.PaleAle,
            ArtisanGoodAsset.Wine)),
    Loom(GamePictureManager.loomNormal,
        List.of(
            ArtisanGoodAsset.Cloth)),
    MayonnaiseMachine(GamePictureManager.mayonnaiseMachineNormal,
        Arrays.asList(
            ArtisanGoodAsset.Mayonnaise,
            ArtisanGoodAsset.DuckMayonnaise,
            ArtisanGoodAsset.DinosaurMayonnaise)),
    OilMaker(GamePictureManager.oilMakerNormal,
        Arrays.asList(
            ArtisanGoodAsset.Oil,
            ArtisanGoodAsset.TruffleOil)),
    PreservesJar(GamePictureManager.preservesJarNormal,
        Arrays.asList(
            ArtisanGoodAsset.Pickles,
            ArtisanGoodAsset.Jelly));

    private final TextureRegionDrawable drawable;
    private final ArrayList<ArtisanGoodAsset> products;
    private final String description;
    private static final Map<String, ArtisanAsset> stringToArtisanAssetMap = new HashMap<>();

    static {
        for (ArtisanAsset asset : ArtisanAsset.values()) {
            stringToArtisanAssetMap.put(asset.name().toLowerCase(), asset);
        }
    }

    ArtisanAsset(TextureRegionDrawable drawable, List<ArtisanGoodAsset> products) {
        this.drawable = drawable;
        this.products = new ArrayList<>(products);

        StringBuilder des = new StringBuilder();
        des.append("\n<").append(this.name()).append(">\n");
        for (ArtisanGoodAsset product : products) {
            des.append("---------------------------------------").append(product.getDescription());
        }

        description = des.toString();
    }

    public TextureRegionDrawable getDrawable() {
        return drawable;
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
        if (name == null) return null;
        return stringToArtisanAssetMap.get(name.toLowerCase());
    }
}
