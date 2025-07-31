package com.stardew.model.animals;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.ItemInventoryType;
import com.stardew.model.TextureID;
import com.stardew.model.mapInfo.Ingredient;

import java.util.HashMap;

public enum AnimalGoodType implements Ingredient {
    Egg(50, 35, TextureID.eggTexture , "egg"),
    LargeEgg(95, 53, TextureID.largeEggTexture , "largeEgg"),
    DuckEgg(95, 68, TextureID.duckEggTexture , "duckEgg"),
    DuckFeather(250, 0, TextureID.duckFeatherTexture , "duckFeather"),
    Wool(340, 0, TextureID.woolTexture , "wool"),
    RabbitFoot(565, 0, TextureID.rabbitFootTexture , "rabbitFoot"),
    DinosaurEgg(350, 0, TextureID.dinosaurEggTexture , "dinosaurEgg"),
    Milk(125, 68, TextureID.milkTexture , "milk"),
    LargeMilk(190, 90, TextureID.largeMilkTexture  , "largeMilk"),
    GoatMilk(225, 113, TextureID.goatMilkTexture , "goatMilk"),
    LargeGoatMilk(345, 158, TextureID.largeGoatMilkTexture ,"largeGoatMilk"),
    Truffle(625, 23, TextureID.truffleTexture , "truffle"),;

    private final int price;
    private final int energy;
    private final TextureID textureRegion;
    private final String id;

    private static final HashMap<String, AnimalGoodType> stringToAnimalGoodType = new HashMap<>();
    static {
        for (AnimalGoodType value : AnimalGoodType.values()) {
            stringToAnimalGoodType.put(value.name().toLowerCase(), value);
        }
    }

    AnimalGoodType(int price, int energy, TextureID textureRegion , String id) {
        this.price = price;
        this.energy = energy;
        this.textureRegion = textureRegion;
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public TextureID getInventoryTexture() {
        return textureRegion;
    }

    @Override
    public InventoryItemDTO toDTO() {
        return new InventoryItemDTO(getInventoryTexture() , false , 1, ItemInventoryType.animalGoodType , getId());
    }


    public int getEnergy() {
        return energy;
    }

    public static AnimalGoodType getAnimalGoodTypeByName(String name) {
        return stringToAnimalGoodType.getOrDefault(name.toLowerCase(), null);
    }

    public String getId() {
        return id;
    }




}
