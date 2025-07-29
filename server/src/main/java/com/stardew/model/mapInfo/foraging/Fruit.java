package com.stardew.model.mapInfo.foraging;

import com.stardew.model.TextureID;
import com.stardew.model.mapInfo.Eatable;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.stores.Sellable;

import java.util.HashMap;

public enum Fruit implements Ingredient , Eatable , Sellable {
    Apricot(38, 59, TextureID.apricotTexture),
    Cherry(38, 80, TextureID.cherryTexture),
    Banana(75, 150, TextureID.bananaTexture),
    Mango(100, 130, TextureID.mangoTexture),
    Orange(38, 100, TextureID.orangeTexture),
    Peach(38, 140, TextureID.peachTexture),
    Apple(38, 100, TextureID.appleTexture),
    Pomegranate(38, 140, TextureID.pomegranateTexture),
    OakResin(0, 150, TextureID.oakResinTexture),
    MapleSyrup(0, 200, TextureID.mapleSyrupTexture),
    PineTar(0, 100, TextureID.pineTarTexture),
    Sap(-2, 2, TextureID.sapTexture),
    CommonMushroom(38, 40, TextureID.commonMushroom),
    MysticSyrup(500, 1000, TextureID.mysticSyrupTexture);

    private final int energy;
    private final int baseSellPrice;
    private final TextureID texture;
    private final static HashMap<String, Fruit> stringToFruit = new HashMap<>();

    static {
        for (Fruit value : Fruit.values()) {
            stringToFruit.put(value.name().toLowerCase(), value);
        }
    }


    Fruit(int energy, int baseSellPrice, TextureID texture) {
        this.energy = energy;
        this.baseSellPrice = baseSellPrice;
        this.texture = texture;
    }

    public int getEnergy() {
        return energy;
    }

    public int getSellPrice() {
        return baseSellPrice;
    }

    public static Fruit getFruitByName(String name) {
        return stringToFruit.getOrDefault(name.toLowerCase(), null);
    }

    public TextureID getInventoryTexture(){
        return texture;
    }
}
