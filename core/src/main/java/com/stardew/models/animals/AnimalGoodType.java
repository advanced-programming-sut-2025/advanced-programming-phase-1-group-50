package com.stardew.models.animals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.manuFactor.Ingredient;

import java.util.HashMap;

public enum AnimalGoodType implements Ingredient {
    Egg(50, GamePictureManager.eggTexture),
    LargeEgg(95, GamePictureManager.largeEggTexture),
    DuckEgg(95, GamePictureManager.duckEggTexture),
    DuckFeather(250, GamePictureManager.duckFeatherTexture),
    Wool(340, GamePictureManager.woolTexture),
    RabbitFoot(565, GamePictureManager.rabbitFootTexture),
    DinosaurEgg(350, GamePictureManager.dinosaurEggTexture),
    Milk(125, GamePictureManager.milkTexture),
    LargeMilk(190, GamePictureManager.largeMilkTexture),
    GoatMilk(225, GamePictureManager.goatMilkTexture),
    LargeGoatMilk(345, GamePictureManager.largeGoatMilkTexture),
    Truffle(625, GamePictureManager.truffleTexture);

    private final int price;
    private final TextureRegion textureRegion;

    private static final HashMap<String, AnimalGoodType> stringToAnimalGoodType = new HashMap<>();
    static {
        for (AnimalGoodType value : AnimalGoodType.values()) {
            stringToAnimalGoodType.put(value.name().toLowerCase(), value);
        }
    }

    AnimalGoodType(int price, TextureRegion textureRegion) {
        this.price = price;
        this.textureRegion = textureRegion;
    }

    public int getPrice() {
        return price;
    }

    public TextureRegion getInventoryTexture() {
        return textureRegion;
    }

    public static AnimalGoodType getAnimalGoodTypeByName(String name) {
        return stringToAnimalGoodType.getOrDefault(name.toLowerCase(), null);
    }
}
