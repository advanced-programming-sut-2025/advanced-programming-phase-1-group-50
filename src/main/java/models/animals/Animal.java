package models.animals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Animal {
    Chicken(AnimalType.Coop , 800, new ArrayList<>(Arrays.asList(AnimalGood.Egg, AnimalGood.LargeEgg))),
    Duck(AnimalType.Coop , 1200, new ArrayList<>(Arrays.asList(AnimalGood.DuckEgg, AnimalGood.DuckFeather))),
    Rabbit(AnimalType.Coop , 8000, new ArrayList<>(Arrays.asList(AnimalGood.Wool, AnimalGood.RabbitFoot))),
    Dinosaur(AnimalType.Coop , 14000, new ArrayList<>(List.of(AnimalGood.DinosaurEgg))),
    Cow(AnimalType.Barn , 1500, new ArrayList<>(Arrays.asList(AnimalGood.Milk, AnimalGood.LargeMilk))),
    Goat(AnimalType.Barn , 4000, new ArrayList<>(Arrays.asList(AnimalGood.GoatMilk, AnimalGood.LargeGoatMilk))),
    Sheep(AnimalType.Barn , 8000, new ArrayList<>(List.of(AnimalGood.Wool))),
    Pig(AnimalType.Barn , 16000, new ArrayList<>(List.of(AnimalGood.Truffle)));


    private final AnimalType animalType;
    private final int Price;
    private final ArrayList<AnimalGood> animalGoods;


     Animal(AnimalType animalType , int price, ArrayList<AnimalGood> animalGoods){
        this.animalType = animalType;
        this.Price = price;
         this.animalGoods = animalGoods;
     }

    public int getPrice() {
        return Price;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public ArrayList<AnimalGood> getAnimalGoods() {
         return animalGoods;
    }
}