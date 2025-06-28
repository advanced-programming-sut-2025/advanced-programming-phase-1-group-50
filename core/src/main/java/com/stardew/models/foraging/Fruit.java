package com.stardew.models.foraging;

import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.stores.Sellable;

import java.util.HashMap;

public enum Fruit implements Ingredient, Sellable {
    Apricot(75, 59),
    Cherry(75, 80),
    Banana(75, 150),
    Mango(75, 130),
    Orange(75, 100),
    Peach(75, 140),
    Apple(75, 100),
    Pomegranate(75, 140),
    OakResin(75, 150),
    MapleSyrup(75, 200),
    PineTar(75, 100),
    Sap(75, 2),
    CommonMushroom(75, 40),
    MysticSyrup(75, 1000);

    private final int energy;
    private final int baseSellPrice;
    private final static HashMap<String, Fruit> stringToFruit = new HashMap<>();

    static {
        for (Fruit value : Fruit.values()) {
            stringToFruit.put(value.name().toLowerCase(), value);
        }
    }


    Fruit(int energy, int baseSellPrice) {
        this.energy = energy;
        this.baseSellPrice = baseSellPrice;
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
