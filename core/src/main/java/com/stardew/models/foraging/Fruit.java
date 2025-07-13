package com.stardew.models.foraging;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.stores.Sellable;

import java.util.HashMap;

public enum Fruit implements Ingredient, Sellable {
    Apricot(38, 59, GamePictureManager.apricotTexture),
    Cherry(38, 80, GamePictureManager.cherryTexture),
    Banana(75, 150, GamePictureManager.bananaTexture),
    Mango(100, 130, GamePictureManager.mangoTexture),
    Orange(38, 100, GamePictureManager.orangeTexture),
    Peach(38, 140, GamePictureManager.peachTexture),
    Apple(38, 100, GamePictureManager.appleTexture),
    Pomegranate(38, 140, GamePictureManager.pomegranateTexture),
    OakResin(0, 150, GamePictureManager.oakResinTexture),
    MapleSyrup(0, 200, GamePictureManager.mapleSyrupTexture),
    PineTar(0, 100, GamePictureManager.pineTarTexture),
    Sap(-2, 2, GamePictureManager.sapTexture),
    CommonMushroom(38, 40, GamePictureManager.commonMushroom),
    MysticSyrup(500, 1000, GamePictureManager.mysticSyrupTexture);

    private final int energy;
    private final int baseSellPrice;
    private final TextureRegion texture;
    private final static HashMap<String, Fruit> stringToFruit = new HashMap<>();

    static {
        for (Fruit value : Fruit.values()) {
            stringToFruit.put(value.name().toLowerCase(), value);
        }
    }


    Fruit(int energy, int baseSellPrice, TextureRegion texture) {
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

    @Override
    public TextureRegion getInventoryTexture() {
        return texture;
    }
}
