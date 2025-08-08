package com.stardew.controller.AnimalsControllers;

import com.stardew.controller.MiniGame.MiniGameController;
import com.stardew.controller.MiniGame.MiniGameControllersManager;
import com.stardew.model.AnimalDTO;
import com.stardew.model.HabitatDTO;
import com.stardew.model.Result;
import com.stardew.model.Tools.FishingPole;
import com.stardew.model.Tools.MilkPail;
import com.stardew.model.Tools.Shear;
import com.stardew.model.Tools.Tool;
import com.stardew.model.animals.*;
import com.stardew.model.gameApp.Game;
import com.stardew.model.gameApp.TimeProvider;
import com.stardew.model.gameApp.date.Season;
import com.stardew.model.gameApp.date.Weather;
import com.stardew.model.mapInfo.GameMap;
import com.stardew.model.mapInfo.Tile;
import com.stardew.model.userInfo.Coin;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class AnimalsController {
    private static AnimalsController instance;
    private final Map<ClientConnectionThread, Boolean> hasSentEmptyListMessage = new ConcurrentHashMap<>();


    private AnimalsController() {}

    public static synchronized AnimalsController getInstance() {
        if (instance == null) {
            instance = new AnimalsController();
        }
        return instance;
    }


    public void sendEmptyListMessage(ClientConnectionThread clientConnectionThread) {
        hasSentEmptyListMessage.put(clientConnectionThread, true);
    }

    public void notSentEmptyListMessage(ClientConnectionThread clientConnectionThread) {
        hasSentEmptyListMessage.put(clientConnectionThread, false);
    }

    public boolean hasSentEmptyListMessage(ClientConnectionThread clientConnectionThread) {
        return hasSentEmptyListMessage.getOrDefault(clientConnectionThread, false);
    }


    public Result build(Game game, Player player, int x, int y, String buildingName,ClientConnectionThread connectionThread) {
        GameMap map = game.getMap();
        HabitatType habitatType = Habitat.getHabitatTypeByInput(buildingName);
        HabitatSize habitatSize = Habitat.getHabitatSizeByInput(buildingName);

        if (habitatSize == null || habitatType == null) {
            return new Result(false, "Invalid size or type");
        }

        Habitat habitat = new Habitat(habitatType, habitatSize, x, y);

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
        connectionThread.sendMessage(prepareHabitatUIMessage(habitat));

        return new Result(true, "You purchased the building successfully.");
    }

    public Result buyAnimal(Game game, Player player, String animalT, String animalName) {
        AnimalsService animalsService = game.getAnimalsService();
        AnimalType animalType = AnimalType.getAnimalTypeByInput(animalT);

        if (player.getBackpack().getAnimalByName(animalName) != null)
            return new Result(false, "Animal with this name already exists! Please choose another name");
        if (animalType == null)
            return new Result(false, "Invalid animal type!");

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


        Animal animal = new Animal(animalType, game.getTime(), animalName, habitat);
        player.getBackpack().addAnimal(animal);
        habitat.addAnimal(animal);
        animalsService.addAnimal(animal);

        return new Result(true, "You buy a <" + animalType + "> with name <" + animalName + "> successfully!");
    }

    public void getAnimalsInfoInHabitat(Message message, Player player, ClientConnectionThread connection) {
        if (message == null || player == null || connection == null) return;

        Habitat habitat = player.getFarm().getHabitatByID(message.getFromBody("habitatID"));
        if (habitat == null) return;

        ArrayList<AnimalDTO> animals = new ArrayList<>();
        habitat.getAnimals().forEach(animal -> animals.add(animal.toDTO()));

        HashMap<String, Object> body = new HashMap<>();
        body.put("animals", animals);
        Message response = new Message(body, MessageType.EVENT_IN_GAME_RESULT);
        response.setRequestID(message.getRequestID());
        connection.sendMessage(response);
    }

    public void petAnimal(Message message, Player player, ClientConnectionThread connection) {
        if (message == null || player == null || connection == null) return;

        Animal animal = player.getBackpack().getAnimalByName(message.getFromBody("animalName"));

        if (animal == null) {
            Result result = new Result(false, "Animal not found! ");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        if (!animal.isOutOfHabitat()) {
            Result result = new Result(false, "<" + animal.getName() + ">  isn't out of habitat!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        animal.pet();

        Result result = new Result(true, "You pet <" + animal.getName() + "> successfully!");
        sendResultMessage(message.getRequestID(), connection, result);
    }

    public Result setFriendship(Player player, String animalName, int amount) {

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

    public void shepherdAnimal(Message message, Game game, Player player, ClientConnectionThread connection) {
        if (message == null || player == null || connection == null) return;

        Animal animal = player.getBackpack().getAnimalByName(message.getFromBody("animalName"));

        if (animal == null) {
            Result result = new Result(false, " Animal not found! ");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        if (animal.isOutOfHabitat()) {
            animal.goToHabitat();
            Tile animalTile = game.getMap().findTile(((int) animal.getPosition().x), ((int) animal.getPosition().y));
            animalTile.setPlaceable(null);
            animalTile.setWalkable(true);
            Result result = new Result(true, "You put <" + animal + "> in the habitat!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        if (!game.getTime().getWeather().equals(Weather.Sunny)) {
            Result result = new Result(false, "Weather is not Sunny! you can't shepherd your animal!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

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
            tile = game.getMap().findTile(((int) x), ((int) y));

        } while (tile == null || tile.getPlaceable() != null);

        animal.shepherdAnimal(x, y);
        animal.feed();
        animal.incrementFriendShip(8);
        tile.setPlaceable(animal);
        tile.setWalkable(false);

        Result result = new Result(true, "You shepherd your animal!");
        sendResultMessage(message.getRequestID(), connection, result);
    }

    public void feedAnimal(Message message, Player player, ClientConnectionThread connection) {
        if (message == null || player == null || connection == null) return;

        Animal animal = player.getBackpack().getAnimalByName(message.getFromBody("animalName"));

        if (animal == null) {
            Result result = new Result(false, "Animal not found!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        if (!player.getBackpack().hasEnoughHay(1)) {
            Result result = new Result(false, "You don't have enough hay to feed animal!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        if (animal.isOutOfHabitat()) {
            Result result = new Result(false, "You can't feed Animal out of habitat!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        player.getBackpack().decreaseHay(1);
        animal.feed();
        animal.incrementFriendShip(4);

        Result result = new Result(true, "You feed animal <" + animal.getName() + "> successfully!");
        sendResultMessage(message.getRequestID(), connection, result);
    }

    public void animalProductsInfo(Message message, Player player, ClientConnectionThread connection) {
        if (message == null || player == null || connection == null) return;

        Habitat habitat = player.getFarm().getHabitatByID(message.getFromBody("habitatID"));

        if (habitat == null) {
            Result result = new Result(false, "Habitat not found!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        ArrayList<Animal> animals = habitat.getAnimals();

        if (animals.isEmpty()) {
            Result result = new Result(false, "No animals found!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        StringBuilder output = new StringBuilder();
        output.append("Animals that their products haven't been collected yet:\n\n");

        for (Animal animal : animals) {
            if (animal.isReadyProduct())
                output.append(String.format("%-20s (%-9s ->   %-25s\n",
                    animal.getName(), animal.getType() + ")", animal.getType().getAnimalGoods()));
        }

        Result result = new Result(true, output.toString());
        sendResultMessage(message.getRequestID(), connection, result);
    }

    public void collectProduct(Message message, Game game, Player player, ClientConnectionThread connection) {
        if (message == null || player == null || connection == null) return;

        Animal animal = player.getBackpack().getAnimalByName(message.getFromBody("animalName"));

        if (animal == null) {
            Result result = new Result(false, " Animal not found!  ");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }
        if (!animal.isReadyProduct()) {
            Result result = new Result(false, "Product is not ready!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        Tool tool = player.getCurrentTool();

        if (animal.getType().equals(AnimalType.Sheep)) {
            if (!(tool instanceof Shear shear)) {
                Result result = new Result(false, "Your current tool is not Shear!");
                sendResultMessage(message.getRequestID(), connection, result);
                return;
            }

            Result energyConsumptionResult = shear.useTool(game.getTime().getWeather(), player);
            if (!energyConsumptionResult.getSuccessful()) {
                sendResultMessage(message.getRequestID(), connection, energyConsumptionResult);
                return;
            }
        } else if (animal.getType().equals(AnimalType.Cow) || animal.getType().equals(AnimalType.Goat)) {
            if (!(tool instanceof MilkPail milkPail)) {
                Result result = new Result(false, "Your current tool is not MilkPail!");
                sendResultMessage(message.getRequestID(), connection, result);
                return;
            }

            Result energyConsumptionResult = milkPail.useTool(game.getTime().getWeather(), player);
            if (!energyConsumptionResult.getSuccessful()) {
                sendResultMessage(message.getRequestID(), connection, energyConsumptionResult);
                return;
            }
        }

        AnimalGood animalGood = animal.getProduct();
        player.getBackpack().addIngredients(animalGood, 1);
        player.getAbility().increaseFarmingRate(5);
        animal.incrementFriendShip(5);

        Result result = new Result(true,
            String.format("You collect %s with quality %s. Base price: %s -> New Price: %s",
                animalGood.getType(), animalGood.getQuality(), animalGood.getType().getPrice(),
                animalGood.getSellPrice()));
        sendResultMessage(message.getRequestID(), connection, result);
    }

    public void sellAnimal(Message message, AnimalsService animalsService, Player player,
                           ClientConnectionThread connection) {
        if (message == null || player == null || connection == null) return;

        Animal animal = player.getBackpack().getAnimalByName(message.getFromBody("animalName"));

        if (animal == null) {
            Result result = new Result(false, " Animal not found!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        if (animal.isOutOfHabitat()) {
            Result result = new Result(false, "Animal must be in habitat!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        if (!animal.getHabitat().getAnimals().contains(animal)) {
            Result result = new Result(false, "You don't have this animal now!!\nPlease refresh window");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        double price = animal.getType().getPrice() * (((double) (animal.getFriendShip()) / 1000) + 0.3);

        player.getBackpack().addIngredients(new Coin(), ((int) price));
        player.getBackpack().getAllAnimals().remove(animal);
        animal.getHabitat().getAnimals().remove(animal);
        animalsService.removeAnimal(animal);

        Result result = new Result(true, "You sell Animal <" + animal.getName() + "> $" + price + "!");
        sendResultMessage(message.getRequestID(), connection, result);
    }

    public Result fishing(Player player, ClientConnectionThread connection, TimeProvider timeProvider,
                          FishingPole fishingPole) {

        int fishingLevel = player.getAbility().getFishingLevel();
        Weather weather = timeProvider.getTime().getWeather();
        Season season = timeProvider.getTime().getSeason();

        int numberOfFish = (int) Math.ceil(Math.random() * weather.getEffectivenessOnFishing() * (fishingLevel + 2));
        numberOfFish = Math.min(numberOfFish, 6);

        ArrayList<Fish> candidateFish = new ArrayList<>();
        ArrayList<FishType> availableFishType = FishType.getFishesBySeason(season, fishingLevel);

        for (int i = 0; i < numberOfFish; i++) {
            FishType fishType = availableFishType.get(new Random().nextInt(availableFishType.size()));
            double qualityValue = (Math.random() * (fishingLevel + 2) * fishingPole.getType().getEffectiveness()) /
                (7 - weather.getEffectivenessOnFishing());
            Quality quality = Quality.getQualityByValue(qualityValue);
            candidateFish.add(new Fish(fishType, quality));
        }

        player.getAbility().increaseFishingRate(10);


        int id = MiniGameControllersManager.getInstance().generateID();
        MiniGameController miniGameController =
            new MiniGameController(player, connection, candidateFish.toArray(new Fish[0]), fishingPole.getPoleType(),
                id);
        MiniGameControllersManager.getInstance().addController(miniGameController, id);

        // send_message_to_client_to_open_miniGame_window
        HashMap<String, Object> body = new HashMap<>();
        body.put("miniGame_ID", id);
        Message message = new Message(body, MessageType.START_MINI_GAME);
        connection.sendMessage(message);

        return new Result(true, "MiniGame Started");
    }


    private Message prepareHabitatUIMessage(Habitat habitat) {
        HashMap<String, Object> body = new HashMap<>();
        HabitatDTO habitatDTO = habitat.toHabitatDTO();
        body.put("habitatDTO", habitatDTO);
        return new Message(body, MessageType.ADD_NEW_HABITAT_UI);
    }



    private void sendResultMessage(String requestID, ClientConnectionThread connection, Result result) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.EVENT_IN_GAME_RESULT);
        response.setRequestID(requestID);
        connection.sendMessage(response);
    }

}
