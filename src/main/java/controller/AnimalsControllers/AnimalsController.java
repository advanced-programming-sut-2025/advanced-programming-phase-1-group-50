package controller.AnimalsControllers;

import models.Result;
import models.animals.*;
import models.app.App;
import models.date.Season;
import models.date.Weather;
import models.tools.FishingPole;
import models.userInfo.Player;

import java.util.ArrayList;
import java.util.Random;

public class AnimalsController {

    public Result build(String buildingName, int x, int y) {
        return null;
    }

    public Result buyAnimal(String animal, String name) {
        return null;
    }

    public Result pet(String animalName) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        Animal animal = player.getBackpack().getAnimalByName(animalName);

        if (animal == null)
            return new Result(false, "Animal <" + animalName + "> not found!");

        animal.pet();

        return new Result(true, "You pet <" + animalName + "> successfully!");
    }

    public Result setFriendship(String animalName, int amount) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        Animal animal = player.getBackpack().getAnimalByName(animalName);

        if (animal == null)
            return new Result(false, "Animal <" + animalName + "> not found!");
        if (amount <= 0)
            return new Result(false, "You can't set friendship to negative amount!");

        animal.setFriendShip(amount);

        return new Result(true, "You set friendship with <" + animalName + "> to " + amount + "!");
    }

    public Result animalsInfo() {
        Player player = App.getGame().getCurrentPlayingPlayer();
        ArrayList<Animal> animals = player.getBackpack().getAllAnimals();

        if (animals.isEmpty())
            return new Result(false, "No animals found!");

        StringBuilder animalsInfo = new StringBuilder();
        animalsInfo.append("Animals:\n");
        for (int i = 0; i < animals.size(); i++) {
            animalsInfo.append(String.format("\t%-2d: \n", i+1));
            animalsInfo.append(String.format("\t    Name: %s\n", animals.get(i).getName()));
            animalsInfo.append(String.format("\t    Type: %s\n", animals.get(i).getType()));
            animalsInfo.append(String.format("\t    LevelOfFriendship: %d\n", animals.get(i).getFriendShip()));
            animalsInfo.append(String.format("\t    hasPettedToday: %s\n", animals.get(i).hasPettedToday()));
            animalsInfo.append(String.format("\t    hasFedToday: %s\n", animals.get(i).hasFedToday()));
        }

        return new Result(true, animalsInfo.toString());
    }

    public Result shepherdAnimal(String animalName) {
        //weather
        //call feed method
        //increment friendship
        return null;
    }

    public Result feedHay(String animalName) {
        //call feed method
        return null;
    }

    public Result animalProduces() {
        Player player = App.getGame().getCurrentPlayingPlayer();
        ArrayList<Animal> animals = player.getBackpack().getAllAnimals();

        if (animals.isEmpty())
            return new Result(false, "No animals found!");

        StringBuilder output = new StringBuilder();
        output.append("Animals that their products haven't been collected yet:\n");

        for (Animal animal : animals) {
            if (animal.isReadyProduct())
                output.append(animal.getType()).append(" -> ").append(animal.getName()).append("\n");
        }

        return new Result(true, output.toString());
    }

    public Result collectProduce(String animalName) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        Animal animal = player.getBackpack().getAnimalByName(animalName);

        if (animal == null)
            return new Result(false, "Animal <" + animalName + "> not found!");
        if (!animal.isReadyProduct())
            return new Result(false, "Product is not ready!");

        //TODO for tools

        AnimalGood animalGood = animal.getProduct();
        player.getBackpack().addIngredients(animalGood, 1);
        player.getAbility().increaseFarmingRate(5);

        return new Result(true,
                String.format("You collect %s with quality %s. Previous price: %s -> New Price: %s",
                        animalGood.getType(), animalGood.getQuality(), animalGood.getType().getPrice(), animalGood.getSellPrice()));
    }

    public Result sellAnimal(String animalName) {
        return null;
    }

    public Result fishing() {
        Player player = App.getGame().getCurrentPlayingPlayer();
        FishingPole fishingPole = new FishingPole();//delete
        int fishingLevel = player.getAbility().getFishingLevel();
        Weather weather = App.getGame().getTime().getWeather();
        Season season = App.getGame().getTime().getSeason();

        int numberOfFish = (int) Math.ceil(Math.random() * weather.getEffectivenessOnFishing() * (fishingLevel + 2));
        numberOfFish = Math.min(numberOfFish, 6);

        ArrayList<Fish> caughtFish = new ArrayList<>();
        ArrayList<FishType> availableFishType = FishType.getFishesBySeason(season, fishingLevel);

        for (int i = 0; i < numberOfFish; i++) {
            FishType fishType = availableFishType.get(new Random().nextInt(availableFishType.size()));
            double qualityValue = (Math.random() * (fishingLevel + 2) * fishingPole.getType().getEffectiveness()) /
                    (7 - weather.getEffectivenessOnFishing());
            Quality quality = Quality.getQualityByValue(qualityValue);
            caughtFish.add(new Fish(fishType, quality));
        }

        StringBuilder output = new StringBuilder();
        output.append(String.format("Number of Fishes: %d\n" ,numberOfFish));
        for (Fish fish : caughtFish) {
            output.append("\t").append(fish.toString()).append("\n");
        }

        player.getAbility().increaseFishingRate(5);

        for (Fish fish : caughtFish) {
            player.getBackpack().addIngredients(fish, 1);
        }

        return new Result(true, output.toString());
    }
}
