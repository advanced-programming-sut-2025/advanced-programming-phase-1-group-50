package com.stardew.controller.AnimalsControllers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.models.Result;
import com.stardew.models.animals.*;
import com.stardew.models.app.App;
import com.stardew.models.date.Season;
import com.stardew.models.date.Weather;
import com.stardew.models.mapInfo.Map;
import com.stardew.models.mapInfo.Tile;
import com.stardew.models.tools.FishingPole;
import com.stardew.models.tools.MilkPail;
import com.stardew.models.tools.Shear;
import com.stardew.models.tools.Tool;
import com.stardew.models.userInfo.Coin;
import com.stardew.models.userInfo.Player;
import com.stardew.view.GridMap.TileSelectionWindow;

import java.util.ArrayList;
import java.util.Random;

public class AnimalsController {

    public Result build(Stage stage, String buildingName) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        Map map = App.getGame().getMap();
        HabitatType habitatType = Habitat.getHabitatTypeByInput(buildingName);
        HabitatSize habitatSize = Habitat.getHabitatSizeByInput(buildingName);

        if (habitatType == null || habitatSize == null)
            return new Result(false, "Invalid building name or type");
//        if (!map.isAroundPlaceable(player, map.getNpcVillage().getCarpenterShop()))
//            return new Result(false, "You should be near CarpenterShop");

        TileSelectionWindow tileSelectionWindow = new TileSelectionWindow(stage,
            habitatType.getLengthX(), habitatType.getLengthY());
        stage.addActor(tileSelectionWindow);
        tileSelectionWindow.setOnCloseCallback(tile -> {
            if (tile == null)
                tileSelectionWindow.showResult(new Result(false, "You did not select a tile!"));
            else {
                //TODO according to store
                int x = tile.getPosition().getX();
                int y = tile.getPosition().getY();
                Habitat habitat = new Habitat(habitatType, habitatSize, x, y);
                habitat.prepareWindow(stage);
                Tile[][] tiles = map.getTiles();
                for (int i = x; i < x + habitatType.getLengthX(); i++) {
                    for (int j = y; j < y + habitatType.getLengthY(); j++) {
                        tiles[i][j].setPlaceable(habitat);
                        tiles[i][j].setWalkable(false);
                        tiles[i][j].setSymbol(habitat.getSymbol());
                    }
                }

                player.getFarm().addHabitat(habitat);
                player.getFarm().getPlaceables().add(habitat);

            }
        });

//        Result storeResult = map.getNpcVillage().getCarpenterShop().purchaseBuilding(habitatType, habitatSize);
//        if (!storeResult.getSuccessful())
//            return storeResult;
//
//        Habitat habitat = new Habitat(habitatType, habitatSize, x, y);
//
//        for (int i = x; i < x + habitatType.getLengthX(); i++) {
//            for (int j = y; j < y + habitatType.getLengthY(); j++) {
//                Tile tile = map.findTile(i, j);
//                tile.setPlaceable(habitat);
//                tile.setWalkable(false);
//                tile.setSymbol(habitat.getSymbol());
//            }
//        }
//
//        player.getFarm().addHabitat(habitat);
//        player.getFarm().getPlaceables().add(habitat);

        return new Result(true, "Please select a tile to place the machine!");
    }

    public Result buyAnimal(String animalT, String name) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        Map map = App.getGame().getMap();
        AnimalType animalType = AnimalType.getAnimalTypeByInput(animalT);

        if (player.getBackpack().getAnimalByName(name) != null)
            return new Result(false, "Animal with this name already exists! Please choose another name");
        if (animalType == null)
            return new Result(false, "Invalid animal type!");
//        if(!map.isAroundPlaceable(player, map.getNpcVillage().getMarnieRanch()))
//            return new Result(false, "You should be near Marnie Ranch Shop");

        Habitat habitat = null;
        for (Habitat habitat1 : player.getFarm().getHabitats()) {
            if (habitat1.getType().equals(animalType.getAnimalHabitat()) &&
                    habitat1.getSize().compareTo(animalType.getHabitatSize()) >= 0 &&
                    habitat1.hasEmptyCapacity()) {
                habitat = habitat1;
                break;
            }
        }
        if (habitat == null)
            return new Result(false, "You don't have any enough habitat to buy this animal!\n" +
                "Or type or size of habitats isn't compatible with animals!");

//        Result storeResult = map.getNpcVillage().getMarnieRanch().PurchaseAnimal(animalType);
//        if (!storeResult.getSuccessful())
//            return storeResult;

        Animal animal = new Animal(animalType, name, habitat);
        player.getBackpack().addAnimal(animal);
        habitat.addAnimal(animal);

        return new Result(true, "You buy a <" + animalType + "> with name <" + name + "> successfully!");
    }

    public Result pet(Animal animal) {

        if (animal == null)
            return new Result(false, "Animal not found!");

        if (!animal.isOutOfHabitat())
            return new Result(false, "<" + animal.getName() + ">  isn't out of habitat!");

        animal.pet();

        return new Result(true, "You pet <" + animal.getName() + "> successfully!");
    }

    public Result setFriendship(String animalName, int amount) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        Animal animal = player.getBackpack().getAnimalByName(animalName);

        if (animal == null)
            return new Result(false, "Animal <" + animalName + "> not found!");
        if (amount <= 0)
            return new Result(false, "You can't set friendship to negative amount!");
        if (amount > 1000)
            return new Result(false, "You can't set friendship more than 1000!");

        animal.setFriendShip(amount);

        return new Result(true, "You set friendship with <" + animalName + "> to " + amount + "!");
    }

    public Result animalInfo(Animal animal) {

        if (animal == null)
            return new Result(false, "No animals found!");

        String animalsInfo = "\n\n" +
            String.format("     Name: %s   \n\n", animal.getName()) +
            String.format("     Type: %s   \n\n", animal.getType()) +
            String.format("     LevelOfFriendship: %d   \n\n", animal.getFriendShip()) +
            String.format("     hasPettedToday: %s   \n\n", animal.hasPettedToday()) +
            String.format("     hasFedToday: %s   \n\n", animal.hasFedToday());

        return new Result(true, animalsInfo);
    }

    public Result shepherdAnimal(Animal animal) {

        if (animal == null)
            return new Result(false, "Animal not found!");

        if (animal.isOutOfHabitat()) {
            animal.goToHabitat();
            Tile animalTile = App.getGame().getMap().findTile(((int) animal.getPosition().x), ((int)animal.getPosition().y));
            animalTile.setPlaceable(null);
            animalTile.setWalkable(true);
            return new Result(true, "You put <" + animal + "> in the habitat!");
        }

        if (!App.getGame().getTime().getWeather().equals(Weather.Sunny))
            return new Result(false, "Weather is not Sunny! you can't shepherd your animal!");

        float randomX, randomY;
        float x, y;
        Tile tile;

        do {
            randomX = new Random().nextInt(3) + 3;
            if (new Random().nextBoolean())
                randomX *= -1;
            randomY = new Random().nextInt(3) + 2f;

            x = animal.getHabitat().getPosition().x + randomX;
            y = animal.getHabitat().getPosition().y - randomY;
            tile = App.getGame().getMap().findTile(((int) x), ((int) y));

        } while (tile == null || tile.getPlaceable() != null);

        animal.shepherdAnimal(x, y);
        animal.feed();
        animal.incrementFriendShip(8);
        tile.setPlaceable(animal);
        tile.setWalkable(false);

        return new Result(true, "You shepherd your animal!");
    }

    public Result feedHay(Animal animal) {
        Player player = App.getGame().getCurrentPlayingPlayer();

        if (animal == null)
            return new Result(false, "Animal not found!");

        if (!player.getBackpack().hasEnoughHay(1))
            return new Result(false, "You don't have enough hay to feed animal!");

        if (animal.isOutOfHabitat())
            return new Result(false, "You can't feed Animal out of habitat!");

        player.getBackpack().decreaseHay(1);
        animal.feed();
        animal.incrementFriendShip(4);

        return new Result(true, "You feed animal <" + animal.getName() + "> successfully!");
    }

    public Result animalProduces(Habitat habitat) {
        if (habitat == null)
            return new Result(false, "Habitat not found!");

        ArrayList<Animal> animals = habitat.getAnimals();

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

    public Result collectProduce(Animal animal) {
        Player player = App.getGame().getCurrentPlayingPlayer();

        if (animal == null)
            return new Result(false, "Animal not found!");
        if (!animal.isReadyProduct())
            return new Result(false, "Product is not ready!");

        Tool tool = player.getCurrentTool();

        if (animal.getType().equals(AnimalType.Sheep)) {
            if (tool == null)
                return new Result(false, "you don't have a tool, please set your current tool!");

            if (!(tool instanceof Shear shear))
                return new Result(false, "Your current tool is not Shear!");

            Result energyConsumptionResult = shear.useTool();
            if (!energyConsumptionResult.getSuccessful())
                return energyConsumptionResult;
        }
        else if (animal.getType().equals(AnimalType.Cow) || animal.getType().equals(AnimalType.Goat)) {
            if (tool == null)
                return new Result(false, "you don't have a tool, please set your current tool!");

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
                String.format("You collect %s with quality %s. Base price: %s -> New Price: %s",
                        animalGood.getType(), animalGood.getQuality(), animalGood.getType().getPrice(), animalGood.getSellPrice()));
    }

    public Result sellAnimal(Animal animal) {
        Player player = App.getGame().getCurrentPlayingPlayer();

        if (animal == null)
            return new Result(false, "Animal not found!");

        if (animal.isOutOfHabitat())
            return new Result(false, "Animal must be in habitat!");

        if (!animal.getHabitat().getAnimals().contains(animal))
            return new Result(false, "You don't have this animal now!!\nPlease refresh window");

        double price = animal.getType().getPrice() * (((double)(animal.getFriendShip()) / 1000) + 0.3);

        player.getBackpack().addIngredients(new Coin(), ((int) price));
        player.getBackpack().getAllAnimals().remove(animal);
        animal.getHabitat().getAnimals().remove(animal);

        return new Result(true, "You sell Animal <" + animal.getName() + "> $" + price + "!");
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
            output.append("\t").append(fish.getInfo()).append("\n");
        }

        player.getAbility().increaseFishingRate(5);

        for (Fish fish : caughtFish) {
            player.getBackpack().addIngredients(fish, 1);
        }

        return new Result(true, output.toString());
    }
}
