package controller.AnimalsControllers;

import models.Result;
import models.animals.*;
import models.app.App;
import models.date.Season;
import models.date.Weather;
import models.mapInfo.Map;
import models.mapInfo.Tile;
import models.tools.FishingPole;
import models.tools.MilkPail;
import models.tools.Shear;
import models.tools.Tool;
import models.userInfo.Player;

import java.util.ArrayList;
import java.util.Random;

public class AnimalsController {

    public Result build(String buildingName, int x, int y) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        Map map = App.getGame().getMap();
        HabitatType habitatType = Habitat.getHabitatTypeByInput(buildingName);
        HabitatSize habitatSize = Habitat.getHabitatSizeByInput(buildingName);

        if (habitatType == null || habitatSize == null)
            return new Result(false, "Invalid building name or type");
        if (!map.isAroundPlaceable(player, map.getNpcVillage().getCarpenterShop()))
            return new Result(false, "You should be near CarpenterShop");

        for (int i = x; i < x + habitatType.getLengthX(); i++) {
            for (int j = y; j < y + habitatType.getLengthY(); j++) {
                Tile tile = map.findTile(i, j);
                if (tile == null)
                    return new Result(false, "Invalid tile");
                if (tile.getPlaceable() != null)
                    return new Result(false, "You cannot build in this area! The area is not empty!");
            }
        }

        Result storeResult = map.getNpcVillage().getCarpenterShop().purchaseBuilding(habitatType, habitatSize);
        if (!storeResult.getSuccessful())
            return storeResult;

        Habitat habitat = new Habitat(habitatType, habitatSize, x, y);

        for (int i = x; i < x + habitatType.getLengthX(); i++) {
            for (int j = y; j < y + habitatType.getLengthY(); j++) {
                Tile tile = map.findTile(i, j);
                tile.setPlaceable(habitat);
                tile.setWalkable(false);
                tile.setSymbol(habitat.getSymbol());
            }
        }

        player.getFarm().addHabitat(habitat);
        player.getFarm().getPlaceables().add(habitat);

        return new Result(true, "A <" + buildingName + "> was built successfully!");
    }

    public Result buyAnimal(String animalT, String name) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        Map map = App.getGame().getMap();
        AnimalType animalType = AnimalType.getAnimalTypeByInput(animalT);

        if (player.getBackpack().getAnimalByName(name) != null)
            return new Result(false, "Animal with this name already exists! Please choose another name");
        if (animalType == null)
            return new Result(false, "Invalid animal type!");
        if(!map.isAroundPlaceable(player, map.getNpcVillage().getMarnieRanch()))
            return new Result(false, "You should be near Marnie Ranch Shop");

        Habitat habitat = null;
        for (Habitat habitat1 : player.getFarm().getHabitats()) {
            if (habitat1.getType().equals(animalType.getAnimalHabitat()) && habitat1.hasEmptyCapacity()) {
                habitat = habitat1;
                break;
            }
        }
        if (habitat == null)
            return new Result(false, "You don't have any enough capacity to buy this animalT!");

        Result storeResult = map.getNpcVillage().getMarnieRanch().PurchaseAnimal(animalType);
        if (!storeResult.getSuccessful())
            return storeResult;

        Animal animal = new Animal(animalType, name, habitat);
        player.getBackpack().addAnimal(animal);
        habitat.addAnimal(animal);

        return new Result(true, "You buy a <" + animalType + "> with name <" + name + "> successfully!");
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

    public Result shepherdAnimal(String animalName, int x, int y) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        Animal animal = player.getBackpack().getAnimalByName(animalName);
        Tile tile = App.getGame().getMap().findTile(x, y);

        if (animal == null)
            return new Result(false, "Animal <" + animalName + "> not found!");
        if (tile == null)
            return new Result(false, "This position not found!");
        if (tile.getPlaceable() != null)
            return new Result(false, "This position is not free!");

        if (animal.isOutOfHabitat()) {
            animal.goToHabitat();
            return new Result(true, "You put <" + animalName + "> in the habitat!");
        }

        if (!App.getGame().getTime().getWeather().equals(Weather.Sunny))
            return new Result(false, "Weather is not Sunny! you can't shepherd your animal!");

        animal.shepherdAnimal();
        animal.feed();
        animal.incrementFriendShip(8);

        return new Result(true, "You shepherd your animal!");
    }

    public Result feedHay(String animalName) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        Animal animal = player.getBackpack().getAnimalByName(animalName);

        if (animal == null)
            return new Result(false, "Animal <" + animalName + "> not found!");

        if (!player.getBackpack().hasEnoughHay(1))
            return new Result(false, "You don't have enough hay to feed animal!");

        player.getBackpack().decreaseHay(1);
        animal.feed();
        animal.incrementFriendShip(4);

        return new Result(true, "You feed animal <" + animalName + "> successfully!");
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
                output.append(String.format("%-20s (%-9s ->   %-25s\n",
                        animal.getName(), animal.getType() + ")", animal.getType().getAnimalGoods()));
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

        Tool tool = player.getCurrentTool();
        if (tool == null)
            return new Result(false, "you don't have a tool, please set your current tool!");

        if (animal.getType().equals(AnimalType.Sheep)) {

            if (!(tool instanceof Shear shear))
                return new Result(false, "Your current tool is not Shear!");

            Result energyConsumptionResult = shear.useTool();
            if (!energyConsumptionResult.getSuccessful())
                return energyConsumptionResult;
        }
        else if (animal.getType().equals(AnimalType.Cow) || animal.getType().equals(AnimalType.Goat)) {

            if (!(tool instanceof MilkPail milkPail))
                return new Result(false, "Your current tool is not MilkPail!");

            Result energyConsumptionResult = milkPail.useTool();
            if (!energyConsumptionResult.getSuccessful())
                return energyConsumptionResult;
        }

        AnimalGood animalGood = animal.getProduct();
        player.getBackpack().addIngredients(animalGood, 1);
        player.getAbility().increaseFarmingRate(5);
        animal.incrementFriendShip(5);

        return new Result(true,
                String.format("You collect %s with quality %s. Previous price: %s -> New Price: %s",
                        animalGood.getType(), animalGood.getQuality(), animalGood.getType().getPrice(), animalGood.getSellPrice()));
    }

    public Result sellAnimal(String animalName) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        Animal animal = player.getBackpack().getAnimalByName(animalName);

        if (animal == null)
            return new Result(false, "Animal <" + animalName + "> not found!");

        double price = animal.getType().getPrice() * (((double)(animal.getFriendShip()) / 1000) + 0.3);

        //TODO should I increase any money

        return new Result(true, "You sell Animal <" + animalName + "> $" + price + "!");
    }

    public Result fishing(FishingPole fishingPole) {
        Player player = App.getGame().getCurrentPlayingPlayer();
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
