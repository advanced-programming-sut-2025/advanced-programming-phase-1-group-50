package com.stardew.models.animals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public enum AnimalType {
    Chicken(HabitatType.Coop , HabitatSize.Regular, 800, 1,
            new ArrayList<>(Arrays.asList(AnimalGoodType.Egg, AnimalGoodType.LargeEgg)), GamePictureManager.chickenTexture),
    Duck(HabitatType.Coop , HabitatSize.Big, 1200, 2,
            new ArrayList<>(Arrays.asList(AnimalGoodType.DuckEgg, AnimalGoodType.DuckFeather)), GamePictureManager.duckTexture),
    Rabbit(HabitatType.Coop , HabitatSize.Deluxe, 8000, 4,
            new ArrayList<>(Arrays.asList(AnimalGoodType.Wool, AnimalGoodType.RabbitFoot)), GamePictureManager.rabbitTexture),
    Dinosaur(HabitatType.Coop , HabitatSize.Big, 14000, 7,
            new ArrayList<>(List.of(AnimalGoodType.DinosaurEgg)), GamePictureManager.dinosaurTexture),
    Cow(HabitatType.Barn , HabitatSize.Regular, 1500, 1,
            new ArrayList<>(Arrays.asList(AnimalGoodType.Milk, AnimalGoodType.LargeMilk)), GamePictureManager.cowTexture),
    Goat(HabitatType.Barn , HabitatSize.Big, 4000, 2,
            new ArrayList<>(Arrays.asList(AnimalGoodType.GoatMilk, AnimalGoodType.LargeGoatMilk)), GamePictureManager.goatTexture),
    Sheep(HabitatType.Barn , HabitatSize.Deluxe, 8000, 3,
            new ArrayList<>(List.of(AnimalGoodType.Wool)), GamePictureManager.sheepTexture),
    Pig(HabitatType.Barn , HabitatSize.Deluxe, 16000, 0,
            new ArrayList<>(List.of(AnimalGoodType.Truffle)), GamePictureManager.pigTexture);


    private final HabitatType habitatType;
    private final HabitatSize habitatSize;
    private final int Price;
    private final int daysToGetProduct;
    private final ArrayList<AnimalGoodType> animalGoodTypes;
    private final TextureRegion textureRegion;

    private final static HashMap<String, AnimalType> stringToAnimalType = new HashMap<>();
    static {
        for (AnimalType value : AnimalType.values()) {
            stringToAnimalType.put(value.name().toLowerCase(), value);
        }
    }


     AnimalType(HabitatType habitatType, HabitatSize habitatSize, int price, int daysToGetProduct, ArrayList<AnimalGoodType> animalGoodTypes, TextureRegion textureRegion){
        this.habitatType = habitatType;
         this.habitatSize = habitatSize;
         this.Price = price;
         this.daysToGetProduct = daysToGetProduct;
         this.animalGoodTypes = animalGoodTypes;
         this.textureRegion = textureRegion;
     }

    public int getPrice() {
        return Price;
    }

    public HabitatType getAnimalHabitat() {
        return habitatType;
    }

    public HabitatSize getHabitatSize() {
         return habitatSize;
    }

    public int getDaysToGetProduct() {
         return daysToGetProduct;
    }

    public ArrayList<AnimalGoodType> getAnimalGoods() {
         return animalGoodTypes;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public static AnimalType getAnimalTypeByInput(String input) {
        return stringToAnimalType.getOrDefault(input, null);
    }
}
