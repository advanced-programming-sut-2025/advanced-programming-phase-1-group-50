package com.stardew.model.mapInfo.foraging;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.ItemInventoryType;
import com.stardew.model.TextureID;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.stores.Sellable;

import java.util.HashMap;

public enum ForagingMineral implements Ingredient , Sellable {
    Quartz(25, TextureID.quartz),
    EarthCrystal(50, TextureID.earthCrystal),
    FrozenTear(75, TextureID.frozenTear),
    FireQuartz(100, TextureID.fireQuartz),
    Emerald(250, TextureID.emerald),
    Aquamarine(180, TextureID.aquamarine),
    Ruby(250, TextureID.ruby),
    Amethyst(100, TextureID.amethyst),
    Topaz(80, TextureID.topaz),
    Jade(200, TextureID.jade),
    Diamond(750, TextureID.diamond),
    PrismaticShard(2000, TextureID.prismaticShard),
    Copper(5, TextureID.copperOre),
    Iron(10, TextureID.ironOre),
    Gold(25, TextureID.goldOre),
    Iridium(100, TextureID.iridiumOre),
    Coal(15, TextureID.coalMineral);

    private final int sellPrice;
    private final TextureID texture;
    private final static HashMap<String, ForagingMineral> stringToForagingMineral = new HashMap<>();

    static {
        for (ForagingMineral value : ForagingMineral.values()) {
            stringToForagingMineral.put(value.name().toLowerCase(), value);
        }
    }


    ForagingMineral(int sellPrice, TextureID texture) {
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
    public TextureID getInventoryTexture() {
        return texture;
    }

    @Override
    public InventoryItemDTO toDTO() {
        return new InventoryItemDTO(getInventoryTexture() , false , 1 , ItemInventoryType.foragingMineral , name()  );
    }

    @Override
    public String getId() {
        return name();
    }


}
