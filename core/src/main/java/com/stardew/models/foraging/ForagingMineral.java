package com.stardew.models.foraging;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.stores.Sellable;

import java.util.HashMap;

public enum ForagingMineral implements Ingredient, Sellable {
    Quartz(25, GamePictureManager.quartz),
    EarthCrystal(50, GamePictureManager.earthCrystal),
    FrozenTear(75, GamePictureManager.frozenTear),
    FireQuartz(100, GamePictureManager.fireQuartz),
    Emerald(250, GamePictureManager.emerald),
    Aquamarine(180, GamePictureManager.aquamarine),
    Ruby(250, GamePictureManager.ruby),
    Amethyst(100, GamePictureManager.amethyst),
    Topaz(80, GamePictureManager.topaz),
    Jade(200, GamePictureManager.jade),
    Diamond(750, GamePictureManager.diamond),
    PrismaticShard(2000, GamePictureManager.prismaticShard),
    Copper(5, GamePictureManager.copperOre),
    Iron(10, GamePictureManager.ironOre),
    Gold(25, GamePictureManager.goldOre),
    Iridium(100, GamePictureManager.iridiumOre),
    Coal(15, GamePictureManager.coalMineral);

    private final int sellPrice;
    private final TextureRegion texture;
    private final static HashMap<String, ForagingMineral> stringToForagingMineral = new HashMap<>();

    static {
        for (ForagingMineral value : ForagingMineral.values()) {
            stringToForagingMineral.put(value.name().toLowerCase(), value);
        }
    }


    ForagingMineral(int sellPrice, TextureRegion texture) {
        this.sellPrice = sellPrice;
        this.texture = texture;
    }

    public String getName() {
        return name();
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public static ForagingMineral getForagingMineralByName(String name) {
        return stringToForagingMineral.getOrDefault(name.toLowerCase(), null);
    }

    @Override
    public TextureRegion getInventoryTexture() {
        return null;
    }
}
