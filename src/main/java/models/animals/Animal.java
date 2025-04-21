package models.animals;

public enum Animal {
    Chicken(AnimalType.Coop , 800),
    Duck(AnimalType.Coop , 1200),
    Rabbit(AnimalType.Coop , 8000),
    Dinosaur(AnimalType.Coop , 14000),
    Cow(AnimalType.Barn , 1500),
    Goat(AnimalType.Barn , 4000),
    Sheep(AnimalType.Barn , 8000),
    Pig(AnimalType.Barn , 16000);


    private final AnimalType animalType;
    private final int Price;
    // ArrayList<mahsoolat>


     Animal(AnimalType animalType , int price){
        this.animalType = animalType;
        this.Price = price;
    }

    public int getPrice() {
        return Price;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }
}