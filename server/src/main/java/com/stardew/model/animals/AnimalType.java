package com.stardew.model.animals;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.model.TextureID;

import java.util.*;

public enum AnimalType {
    Chicken(HabitatType.Coop , HabitatSize.Regular, 800, 1,
        new ArrayList<>(Arrays.asList(AnimalGoodType.Egg, AnimalGoodType.LargeEgg)),
        TextureID.chickenAnimations, TextureID.chickenTexture),
    Duck(HabitatType.Coop , HabitatSize.Big, 1200, 2,
        new ArrayList<>(Arrays.asList(AnimalGoodType.DuckEgg, AnimalGoodType.DuckFeather)),
        TextureID.duckAnimations, TextureID.duckTexture),
    Rabbit(HabitatType.Coop , HabitatSize.Deluxe, 8000, 4,
        new ArrayList<>(Arrays.asList(AnimalGoodType.Wool, AnimalGoodType.RabbitFoot)),
        TextureID.rabbitAnimations, TextureID.rabbitTexture),
    Dinosaur(HabitatType.Coop , HabitatSize.Big, 14000, 7,
        new ArrayList<>(List.of(AnimalGoodType.DinosaurEgg)),
        TextureID.dinosaurAnimations, TextureID.dinosaurTexture),
    Cow(HabitatType.Barn , HabitatSize.Regular, 1500, 1,
        new ArrayList<>(Arrays.asList(AnimalGoodType.Milk, AnimalGoodType.LargeMilk)),
        TextureID.cowAnimations, TextureID.cowTexture),
    Goat(HabitatType.Barn , HabitatSize.Big, 4000, 2,
        new ArrayList<>(Arrays.asList(AnimalGoodType.GoatMilk, AnimalGoodType.LargeGoatMilk)),
        TextureID.goatAnimations, TextureID.goatTexture),
    Sheep(HabitatType.Barn , HabitatSize.Deluxe, 8000, 3,
        new ArrayList<>(List.of(AnimalGoodType.Wool)),
        TextureID.sheepAnimations, TextureID.sheepTexture),
    Pig(HabitatType.Barn , HabitatSize.Deluxe, 16000, 0,
        new ArrayList<>(List.of(AnimalGoodType.Truffle)),
        TextureID.pigAnimations, TextureID.pigTexture);


    private final HabitatType habitatType;
    private final HabitatSize habitatSize;
    private final int Price;
    private final int daysToGetProduct;
    private final ArrayList<AnimalGoodType> animalGoodTypes;
    //TODO : this field should be Map<AnimalState , AnimationID> , we send AnimationID to client , then client will extract the real Animation from class AnimationIDManager
    // TOdo : for player we do like animalAnimation , we send AnimationID to client,
    private final Map<AnimalState, Animation<TextureRegion>> animations;
    private final TextureID normalTexture;

    private final static HashMap<String, AnimalType> stringToAnimalType = new HashMap<>();
    static {
        for (AnimalType value : AnimalType.values()) {
            stringToAnimalType.put(value.name().toLowerCase(), value);
        }
    }


    AnimalType(HabitatType habitatType, HabitatSize habitatSize, int price, int daysToGetProduct,
               ArrayList<AnimalGoodType> animalGoodTypes, Map<AnimalState, Animation<TextureRegion>> animations, TextureID normalTexture){
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

    public TextureID getNormalTexture() {
        return normalTexture;
    }

    public static AnimalType getAnimalTypeByInput(String input) {
        return stringToAnimalType.getOrDefault(input.toLowerCase(), null);
    }
}
