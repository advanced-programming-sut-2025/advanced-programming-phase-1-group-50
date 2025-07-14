package com.stardew.models.manuFactor.artisanGoods;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;

import java.util.HashMap;

public enum ArtisanGoodType {
    Honey(75, 350, GamePictureManager.honeyTexture),
    CheeseByMilk(100, 230, GamePictureManager.cheeseTexture),
    CheeseByLargeMilk(100, 345, GamePictureManager.cheeseByLargeMilkTexture),
    GoatCheeseByMilk(100, 400, GamePictureManager.goatCheeseTexture),
    GoatCheeseByLargeMilk(100, 600, GamePictureManager.goatCheeseByLargeMilkTexture),
    Beer(50, 200, GamePictureManager.beerTexture),
    Vinegar(13, 100, GamePictureManager.vinegarTexture),
    Coffee(75, 150, GamePictureManager.coffeeTexture),
    Mead(100, 300, GamePictureManager.meadTexture),
    PaleAle(50, 300, GamePictureManager.paleAleTexture),
    Raisins(125, 600, GamePictureManager.raisinsTexture),
    Coal(0, 50, GamePictureManager.coalTexture),
    Cloth(0, 470, GamePictureManager.clothTexture),
    Mayonnaise(50, 190, GamePictureManager.mayonnaiseTexture),
    DuckMayonnaise(75, 37, GamePictureManager.duckMayonnaiseTexture),
    DinosaurMayonnaise(125, 800, GamePictureManager.dinosaurMayonnaiseTexture),
    TruffleOil(38, 1065, GamePictureManager.truffleOilTexture),
    Oil(13, 100, GamePictureManager.oilTexture),
    DriedMushroom(50, 100, GamePictureManager.driedMushroomsTexture),
    DriedFruit(45, 150, GamePictureManager.driedFruitTexture),
    Jelly(23, 85, GamePictureManager.jellyTexture),
    Juice(65, 220, GamePictureManager.juiceTexture),
    Pickles(43, 120, GamePictureManager.picklesTexture),
    SmokedFish(95, 195, GamePictureManager.smokedFishTexture),
    Wine(10, 120, GamePictureManager.wineTexture),
    IronBar(0, 200, GamePictureManager.ironBarTexture),
    IridiumBar(0, 540, GamePictureManager.iridiumBarTexture),
    CopperBar(0, 295, GamePictureManager.copperBarTexture),
    GoldBar(0, 450, GamePictureManager.goldBarTexture),;

    private int energy;
    private int sellPrice;
    private final TextureRegion texture;
    private final static HashMap<String, ArtisanGoodType> stringToArtisanGoodType = new HashMap<>();

    static {
        for (ArtisanGoodType value : ArtisanGoodType.values()) {
            stringToArtisanGoodType.put(value.name().toLowerCase(), value);
        }
    }


    ArtisanGoodType(int energy, int sellPrice, TextureRegion texture) {
        this.energy = energy;
        this.sellPrice = sellPrice;
        this.texture = texture;
    }

    public int getEnergy() {
        return energy;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public static ArtisanGoodType getArtisanGoodTypeByName(String name) {
        return stringToArtisanGoodType.getOrDefault(name.toLowerCase(), null);
    }
}
