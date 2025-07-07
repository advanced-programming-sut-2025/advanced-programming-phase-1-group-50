package com.stardew.models.foraging;

import com.badlogic.gdx.graphics.Texture;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.stores.Sellable;

import java.util.HashMap;

public enum Fruit implements Ingredient, Sellable {
    Apricot(75, 59, GamePictureManager.apricotTexture),
    Cherry(75, 80, GamePictureManager.cherryTexture),
    Banana(75, 150, GamePictureManager.bananaTexture),
    Mango(75, 130, GamePictureManager.mangoTexture),
    Orange(75, 100, GamePictureManager.orangeTexture),
    Peach(75, 140, GamePictureManager.peachTexture),
    Apple(75, 100, GamePictureManager.appleTexture),
    Pomegranate(75, 140, GamePictureManager.pomegranateTexture),
    OakResin(75, 150, GamePictureManager.oakResinTexture),
    MapleSyrup(75, 200, GamePictureManager.mapleSyrupTexture),
    PineTar(75, 100, GamePictureManager.pineTarTexture),
    Sap(75, 2, GamePictureManager.sapTexture),
    CommonMushroom(75, 40, GamePictureManager.commonMushroom),
    MysticSyrup(75, 1000, GamePictureManager.mysticSyrupTexture);

    private final int energy;
    private final int baseSellPrice;
    private final Texture texture;
    private final static HashMap<String, Fruit> stringToFruit = new HashMap<>();

    static {
        for (Fruit value : Fruit.values()) {
            stringToFruit.put(value.name().toLowerCase(), value);
        }
    }


    Fruit(int energy, int baseSellPrice, Texture texture) {
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
}
