package com.stardew.models.foraging;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.stores.Sellable;

import java.util.HashMap;

public enum ForagingMineral implements Ingredient, Sellable {
    Quartz(25),
    EarthCrystal(50),
    FrozenTear(75),
    FireQuartz(100),
    Emerald(250),
    Aquamarine(180),
    Ruby(250),
    Amethyst(100),
    Topaz(80),
    Jade(200),
    Diamond(750),
    PrismaticShard(2000),
    Copper(5),
    Iron(10),
    Gold(25),
    Iridium(100),
    Coal(15);

    private final int sellPrice;
    private final static HashMap<String, ForagingMineral> stringToForagingMineral = new HashMap<>();

    static {
        for (ForagingMineral value : ForagingMineral.values()) {
            stringToForagingMineral.put(value.name().toLowerCase(), value);
        }
    }


    ForagingMineral(int sellPrice) {
        this.sellPrice = sellPrice;
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
