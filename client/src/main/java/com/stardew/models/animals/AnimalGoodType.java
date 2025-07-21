package com.stardew.models.animals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.manuFactor.Ingredient;

import java.util.HashMap;

public enum AnimalGoodType implements Ingredient {
    Egg(50, 35, GamePictureManager.eggTexture),
    LargeEgg(95, 53, GamePictureManager.largeEggTexture),
    DuckEgg(95, 68, GamePictureManager.duckEggTexture),
    DuckFeather(250, 0, GamePictureManager.duckFeatherTexture),
    Wool(340, 0, GamePictureManager.woolTexture),
    RabbitFoot(565, 0, GamePictureManager.rabbitFootTexture),
    DinosaurEgg(350, 0, GamePictureManager.dinosaurEggTexture),
    Milk(125, 68, GamePictureManager.milkTexture),
    LargeMilk(190, 90, GamePictureManager.largeMilkTexture),
    GoatMilk(225, 113, GamePictureManager.goatMilkTexture),
    LargeGoatMilk(345, 158, GamePictureManager.largeGoatMilkTexture),
    Truffle(625, 23, GamePictureManager.truffleTexture);

    private final int price;
    private final int energy;
    private final TextureRegion textureRegion;

    private static final HashMap<String, AnimalGoodType> stringToAnimalGoodType = new HashMap<>();
    static {
        for (AnimalGoodType value : AnimalGoodType.values()) {
            stringToAnimalGoodType.put(value.name().toLowerCase(), value);
        }
    }

    AnimalGoodType(int price, int energy, TextureRegion textureRegion) {
        this.price = price;
        this.energy = energy;
        this.textureRegion = textureRegion;
    }

    public int getPrice() {
        return price;
    }

    public TextureRegion getInventoryTexture() {
        return textureRegion;
    }

    public int getEnergy() {
        return energy;
    }

    public static AnimalGoodType getAnimalGoodTypeByName(String name) {
        return stringToAnimalGoodType.getOrDefault(name.toLowerCase(), null);
    }
}
