package com.stardew.model.mapInfo.manuFactor.ArtisanGoods;

import com.stardew.model.TextureID;

import java.util.HashMap;

public enum ArtisanGoodType {
    Honey(75, 350, TextureID.honeyTexture),
    CheeseByMilk(100, 230, TextureID.cheeseTexture),
    CheeseByLargeMilk(100, 345, TextureID.cheeseByLargeMilkTexture),
    GoatCheeseByMilk(100, 400, TextureID.goatCheeseTexture),
    GoatCheeseByLargeMilk(100, 600, TextureID.goatCheeseByLargeMilkTexture),
    Beer(50, 200, TextureID.beerTexture),
    Vinegar(13, 100, TextureID.vinegarTexture),
    Coffee(75, 150, TextureID.coffeeTexture),
    Mead(100, 300, TextureID.meadTexture),
    PaleAle(50, 300, TextureID.paleAleTexture),
    Raisins(125, 600, TextureID.raisinsTexture),
    Coal(0, 50, TextureID.coalTexture),
    Cloth(0, 470, TextureID.clothTexture),
    Mayonnaise(50, 190, TextureID.mayonnaiseTexture),
    DuckMayonnaise(75, 37, TextureID.duckMayonnaiseTexture),
    DinosaurMayonnaise(125, 800, TextureID.dinosaurMayonnaiseTexture),
    TruffleOil(38, 1065, TextureID.truffleOilTexture),
    Oil(13, 100, TextureID.oilTexture),
    DriedMushroom(50, 100, TextureID.driedMushroomsTexture),
    DriedFruit(45, 150, TextureID.driedFruitTexture),
    Jelly(23, 85, TextureID.jellyTexture),
    Juice(65, 220, TextureID.juiceTexture),
    Pickles(43, 120, TextureID.picklesTexture),
    SmokedFish(95, 195, TextureID.smokedFishTexture),
    Wine(10, 120, TextureID.wineTexture),
    IronBar(0, 200, TextureID.ironBarTexture),
    IridiumBar(0, 540, TextureID.iridiumBarTexture),
    CopperBar(0, 295, TextureID.copperBarTexture),
    GoldBar(0, 450, TextureID.goldBarTexture),;

    private int energy;
    private int sellPrice;
    private final TextureID texture;
    private final static HashMap<String, ArtisanGoodType> stringToArtisanGoodType = new HashMap<>();

    static {
        for (ArtisanGoodType value : ArtisanGoodType.values()) {
            stringToArtisanGoodType.put(value.name().toLowerCase(), value);
        }
    }


    ArtisanGoodType(int energy, int sellPrice, TextureID texture) {
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

    public TextureID getTexture() {
        return texture;
    }

    public static ArtisanGoodType getArtisanGoodTypeByName(String name) {
        return stringToArtisanGoodType.getOrDefault(name.toLowerCase(), null);
    }
}
