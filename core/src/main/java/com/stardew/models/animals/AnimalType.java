package com.stardew.models.animals;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;

import java.util.*;

public enum AnimalType {
    Chicken(HabitatType.Coop , HabitatSize.Regular, 800, 1,
            new ArrayList<>(Arrays.asList(AnimalGoodType.Egg, AnimalGoodType.LargeEgg)),
            GamePictureManager.chickenAnimations, GamePictureManager.chickenTexture),
    Duck(HabitatType.Coop , HabitatSize.Big, 1200, 2,
            new ArrayList<>(Arrays.asList(AnimalGoodType.DuckEgg, AnimalGoodType.DuckFeather)),
            GamePictureManager.duckAnimations, GamePictureManager.duckTexture),
    Rabbit(HabitatType.Coop , HabitatSize.Deluxe, 8000, 4,
            new ArrayList<>(Arrays.asList(AnimalGoodType.Wool, AnimalGoodType.RabbitFoot)),
            GamePictureManager.rabbitAnimations, GamePictureManager.rabbitTexture),
    Dinosaur(HabitatType.Coop , HabitatSize.Big, 14000, 7,
            new ArrayList<>(List.of(AnimalGoodType.DinosaurEgg)),
            GamePictureManager.dinosaurAnimations, GamePictureManager.dinosaurTexture),
    Cow(HabitatType.Barn , HabitatSize.Regular, 1500, 1,
            new ArrayList<>(Arrays.asList(AnimalGoodType.Milk, AnimalGoodType.LargeMilk)),
            GamePictureManager.cowAnimations, GamePictureManager.cowTexture),
    Goat(HabitatType.Barn , HabitatSize.Big, 4000, 2,
            new ArrayList<>(Arrays.asList(AnimalGoodType.GoatMilk, AnimalGoodType.LargeGoatMilk)),
            GamePictureManager.goatAnimations, GamePictureManager.goatTexture),
    Sheep(HabitatType.Barn , HabitatSize.Deluxe, 8000, 3,
            new ArrayList<>(List.of(AnimalGoodType.Wool)),
            GamePictureManager.sheepAnimations, GamePictureManager.sheepTexture),
    Pig(HabitatType.Barn , HabitatSize.Deluxe, 16000, 0,
            new ArrayList<>(List.of(AnimalGoodType.Truffle)),
            GamePictureManager.pigAnimations, GamePictureManager.pigTexture);


    private final HabitatType habitatType;
    private final HabitatSize habitatSize;
    private final int Price;
    private final int daysToGetProduct;
    private final ArrayList<AnimalGoodType> animalGoodTypes;
    private final Map<AnimalState, Animation<TextureRegion>> animations;
    private final TextureRegion normalTexture;

    private final static HashMap<String, AnimalType> stringToAnimalType = new HashMap<>();
    static {
        for (AnimalType value : AnimalType.values()) {
            stringToAnimalType.put(value.name().toLowerCase(), value);
        }
    }


     AnimalType(HabitatType habitatType, HabitatSize habitatSize, int price, int daysToGetProduct,
                ArrayList<AnimalGoodType> animalGoodTypes, Map<AnimalState, Animation<TextureRegion>> animations, TextureRegion normalTexture){
        this.habitatType = habitatType;
         this.habitatSize = habitatSize;
         this.Price = price;
         this.daysToGetProduct = daysToGetProduct;
         this.animalGoodTypes = animalGoodTypes;
         this.animations = animations;
         this.normalTexture = normalTexture;
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

    public Animation<TextureRegion> getAnimation(AnimalState state) {
        return animations.get(state);
    }

    public TextureRegion getNormalTexture() {
        return normalTexture;
    }

    public static AnimalType getAnimalTypeByInput(String input) {
        return stringToAnimalType.getOrDefault(input.toLowerCase(), null);
    }
}
