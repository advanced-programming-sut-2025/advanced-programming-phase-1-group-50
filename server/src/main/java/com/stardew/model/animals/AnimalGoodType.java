package com.stardew.model.animals;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.TextureID;
import com.stardew.model.mapInfo.Ingredient;

import java.util.HashMap;

public enum AnimalGoodType implements Ingredient {
    Egg(50, 35, TextureID.eggTexture),
    LargeEgg(95, 53, TextureID.largeEggTexture),
    DuckEgg(95, 68, TextureID.duckEggTexture),
    DuckFeather(250, 0, TextureID.duckFeatherTexture),
    Wool(340, 0, TextureID.woolTexture),
    RabbitFoot(565, 0, TextureID.rabbitFootTexture),
    DinosaurEgg(350, 0, TextureID.dinosaurEggTexture),
    Milk(125, 68, TextureID.milkTexture),
    LargeMilk(190, 90, TextureID.largeMilkTexture),
    GoatMilk(225, 113, TextureID.goatMilkTexture),
    LargeGoatMilk(345, 158, TextureID.largeGoatMilkTexture),
    Truffle(625, 23, TextureID.truffleTexture);

    private final int price;
    private final int energy;
    private final TextureID textureRegion;

    private static final HashMap<String, AnimalGoodType> stringToAnimalGoodType = new HashMap<>();
    static {
        for (AnimalGoodType value : AnimalGoodType.values()) {
            stringToAnimalGoodType.put(value.name().toLowerCase(), value);
        }
    }

    AnimalGoodType(int price, int energy, TextureID textureRegion) {
        this.price = price;
        this.energy = energy;
        this.textureRegion = textureRegion;
    }

    public int getPrice() {
        return price;
    }

    public TextureID getInventoryTexture() {
        return textureRegion;
    }



    public int getEnergy() {
        return energy;
    }

    public static AnimalGoodType getAnimalGoodTypeByName(String name) {
        return stringToAnimalGoodType.getOrDefault(name.toLowerCase(), null);
    }


}
