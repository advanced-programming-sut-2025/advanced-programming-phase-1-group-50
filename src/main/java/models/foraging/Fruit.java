package models.foraging;

import models.manuFactor.Ingredient;

public enum Fruit implements Ingredient {
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


    Fruit(int energy, int baseSellPrice) {
        this.energy = energy;
        this.baseSellPrice = baseSellPrice;
    }

    public int getEnergy() {
        return energy;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }
}
