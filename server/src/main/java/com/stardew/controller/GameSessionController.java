package com.stardew.controller;

import com.stardew.controller.AnimalsControllers.AnimalsController;
import com.stardew.controller.CookingCraftingControllers.ArtisanController;
import com.stardew.controller.CookingCraftingControllers.CookingController;
import com.stardew.controller.CookingCraftingControllers.CookingCraftingInfoController;
import com.stardew.controller.CookingCraftingControllers.CraftingController;
import com.stardew.model.AnimalDTO;
import com.stardew.model.PlayerDTO;
import com.stardew.model.ScoreBoardDTO;
import com.stardew.model.animals.Animal;
import com.stardew.model.gameApp.Game;
import com.stardew.model.userInfo.Coin;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameSessionController {
    private static GameSessionController instance;
    private final Map<Integer, Game> games = new ConcurrentHashMap<>();

    public static synchronized GameSessionController getInstance() {
        if (instance == null) {
            instance = new GameSessionController();
        }
        return instance;
    }

    public Game getGame(int id) {
        return games.get(id);
    }

    public void addGame(int id, Game game) {
        games.put(id, game);
    }

    public void removeGame(int id) {
        games.remove(id);
    }

    public void handleUpdatePlayers(Message message, ClientConnectionThread connection) {
        if (message == null) return;

        int id = message.getIntFromBody("id");
        Game game = games.get(id);
        if (game == null) return;

        if(!game.isStarted()) {
            game.setStarted(true);
            game.startTime();
            game.startHotBar();
        }

        int startX = message.getIntFromBody("startX");
        int startY = message.getIntFromBody("startY");
        int endY = message.getIntFromBody("endY");
        int endX = message.getIntFromBody("endX");

        HashMap<String, Object> body = new HashMap<>();
        body.put("main_player", game.getPlayer(connection).toDTO());
        body.put("other_players", game.getOtherVisiblePlayers(startX, startY, endX, endY, connection));
        Message response = new Message(body, MessageType.UPDATE_PLAYERS_RESULT);
        connection.sendMessage(response);
    }

    public void handleUpdateTiles(Message message, ClientConnectionThread connection) {
        if (message == null) return;

        int id = message.getIntFromBody("id");
        Game game = games.get(id);
        if (game == null) return;

        int startX = message.getIntFromBody("startX");
        int startY = message.getIntFromBody("startY");
        int endX = message.getIntFromBody("endX");
        int endY = message.getIntFromBody("endY");

        HashMap<String, Object> body = new HashMap<>();
        body.put("tiles", game.getVisibleTiles(startX, startY, endX, endY));
        body.put("placeables", game.getVisiblePlaceables(startX, startY, endX, endY));
        Message response = new Message(body, MessageType.UPDATE_TILES_RESULT);
        connection.sendMessage(response);
    }

    public void handleMapRequest(Message message, ClientConnectionThread connection) {
        if (message == null) return;
        int id = message.getIntFromBody("id");
        Game game = games.get(id);
        if (game == null) return;

        HashMap<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("mapWidth", game.getMap().getWidth());
        body.put("mapHeight", game.getMap().getHeight());
        Message response = new Message(body, MessageType.MAP_REQUEST_RESULT);

        connection.sendMessage(response);
    }

    public void handleUpdateAnimals(Message message, ClientConnectionThread connection) {
        if (message == null) return;
        int id = message.getIntFromBody("id");
        Game game = games.get(id);
        if (game == null) return;

        Player player = game.getPlayer(connection);
        if (player == null) return;

        ArrayList<AnimalDTO> animals = new ArrayList<>();
        for (Animal animal : player.getBackpack().getAllAnimals()) {
            if (animal.isOutOfHabitat())
                animals.add(animal.toDTO());
        }
        HashMap<String, Object> body = new HashMap<>();
        body.put("animals", animals);
        Message response = new Message(body, MessageType.UPDATE_ANIMALS_RESULT);
        connection.sendMessage(response);
    }

    public void handleUpdateHotBar(Message message, ClientConnectionThread connection) {
        if (message == null) return;
        int id = message.getIntFromBody("id");
        Game game = games.get(id);
        if (game == null) return;

        Player player = game.getPlayer(connection);
        InventoryController.getInstance().sendHotBarUpdate(player, connection);
    }

    public void handleEventInGame(Message message, ClientConnectionThread connection) {
        if (message == null) return;
        int id = message.getIntFromBody("id");
        Game game = games.get(id);
        if (game == null) return;

        Event event = message.getFromBody("event", Event.class);
        if (event == null) return;

        switch (event) {
            case Moving -> {
                Player player = game.getPlayer(connection);
                PlayerController.getInstance().handleMovement(player, game.getMap().getTiles(), message);
            }
            case ShowInventory -> {
                Player p = game.getPlayer(connection);
                InventoryController.getInstance().handleSendInventoryList(p , connection , message.getRequestID());
            }
            case RemoveItem -> {
                Player p = game.getPlayer(connection);
                InventoryController.getInstance().handleRemoveItem(p , message , connection , message.getRequestID());

            }
            case GetCookingOrCraftingInfo -> {
                Player player = game.getPlayer(connection);
                CookingCraftingInfoController.getInstance().handleGetInfo(message, player, connection);
            }
            case CookingFood -> {
                Player player = game.getPlayer(connection);
                CookingController.getInstance().cookingPrepare(message, player, connection);
            }
            case GetMyFarmInfo -> {
                Player player = game.getPlayer(connection);
                CookingCraftingInfoController.getInstance().handleGetFarmInfo(message, player, game.getMap(), connection);
            }
            case CraftingMachine -> {
                Player player = game.getPlayer(connection);
                CraftingController.getInstance().craftingCraft(message, player, game.getMap(), game.getTime(), connection);
            }

            case GetSkillInfo -> {
                Player player = game.getPlayer(connection);
                InventoryController.getInstance().handleSendSkillInfo(player , connection , message.getRequestID());

            }

            case GetRelationWithNPCInfo -> {
                Player player = game.getPlayer(connection);
                InventoryController.getInstance().handleSendRelationWithNPCInfo(player, connection , message.getRequestID());
            }

            case GetMapInfo -> {
                ArrayList<Player> players = game.getAllPlayers();
                InventoryController.getInstance().handleSendMapInfo(players , game , connection , message.getRequestID());

            }

            case ShuffleInventory -> {
                Player player = game.getPlayer(connection);
                InventoryController.getInstance().handleShuffleInventory(player, connection , message.getRequestID());

            }

            case SetCurrentItem -> {
                Player player = game.getPlayer(connection);
                InventoryController.getInstance().handleSetCurrentItem(player , message);

            }

            case CLickTile -> {
                Player player = game.getPlayer(connection);
                InventoryController.getInstance().handleClickTile(player, game , message , connection , message.getRequestID());
            }

            case GetRefrigeratorItems -> {
                Player player = game.getPlayer(connection);
                CookingController.getInstance().handleGetRefrigeratorItems(message, player, connection);
            }
            case PutInRefrigerator -> {
                Player player = game.getPlayer(connection);
                CookingController.getInstance().putInRefrigerator(message, player, connection);
            }
            case PickFromRefrigerator -> {
                Player player = game.getPlayer(connection);
                CookingController.getInstance().pickFromRefrigerator(message, player, connection);
            }
            case EatItem -> {
                Player player = game.getPlayer(connection);
                CookingController.getInstance().handleEat(message, player, connection);
            }
            case GetMachineDetails -> {
                Player player = game.getPlayer(connection);
                ArtisanController.getInstance().handleGetMachineInfo(message, player, connection);
            }
            case CheatFinishMachineProcess -> {
                Player player = game.getPlayer(connection);
                ArtisanController.getInstance().cheatFinishProcess(message, player);
            }
            case CancelMachineProcess -> {
                Player player = game.getPlayer(connection);
                ArtisanController.getInstance().cancelProcess(message, player, connection);
            }
            case CollectMachineProduct -> {
                Player player = game.getPlayer(connection);
                ArtisanController.getInstance().collectProduct(message, player, connection);
            }
            case IsReadyProduct -> {
                Player player = game.getPlayer(connection);
                ArtisanController.getInstance().isReadyProduct(message, player, connection);
            }
            case UseArtisanMachine -> {
                Player player = game.getPlayer(connection);
                ArtisanController.getInstance().artisanUse(message, player, connection);
            }

            case Reaction -> {
                ReactionController.getInstance().handleReactionProcess(message , game);
            }

            case GetPlayersRelationsInfo -> {
                Player player = game.getPlayer(connection);
                PlayersRelationController.getInstance().getRelations(message, player, connection);
            }

            case CheatCode -> {
                Player player = game.getPlayer(connection);
                CheatCodeController.getInstance().executeCheatCode(message , connection , game , player );
            }

            case GetAnimalsProductsInfo -> {
                Player player = game.getPlayer(connection);
                AnimalsController.getInstance().animalProductsInfo(message, player, connection);
            }
            case FeedAnimal -> {
                Player player = game.getPlayer(connection);
                AnimalsController.getInstance().feedAnimal(message, player, connection);
            }
            case PetAnimal -> {
                Player player = game.getPlayer(connection);
                AnimalsController.getInstance().petAnimal(message, player, connection);
            }
            case ShepherdAnimal -> {
                Player player = game.getPlayer(connection);
                AnimalsController.getInstance().shepherdAnimal(message, game, player, connection);
            }
            case CollectAnimalProduct -> {
                Player player = game.getPlayer(connection);
                AnimalsController.getInstance().collectProduct(message, game, player, connection);
            }
            case SellAnimal -> {
                Player player = game.getPlayer(connection);
                AnimalsController.getInstance().sellAnimal(message, player, connection);
            }

            case GetBetweenPlayersGifts -> {
                PlayersRelationController.getInstance().getAllGifts(message, connection);
            }

            case RateGift -> {
                PlayersRelationController.getInstance().rateGift(message,connection);
            }

            case SendGiftToPlayer -> {
                Player player = game.getPlayer(connection);
                PlayersRelationController.getInstance().sendGiftToPlayer(message, player, connection);
            }

            case CanHug -> {
                Player player = game.getPlayer(connection);
                PlayersRelationController.getInstance().canHug(message, player, connection);
            }

            case CanGiveFlower -> {
                Player player = game.getPlayer(connection);
                PlayersRelationController.getInstance().canGiveFlower(message, player, connection);
            }

            case CanAskMarriage -> {
                Player player = game.getPlayer(connection);
                PlayersRelationController.getInstance().canAskMarriage(message, player, connection);
            }

            case GetForSaleProducts -> {
                Player player = game.getPlayer(connection);
                PlayersRelationController.getInstance().getForSaleProducts(message, player, connection);
            }
        }

    }

    public void handleUpdateScoreBoard(Message message, ClientConnectionThread connection) {
        if(message == null) return;
        int id = message.getIntFromBody("id");
        Game game = games.get(id);
        if(game == null) return;



        ArrayList<ScoreBoardDTO> scoreBoardDTOS = new ArrayList<>();
        for(Player p : game.getAllPlayers()){
            scoreBoardDTOS.add(new ScoreBoardDTO(p.getUsername()
                , p.getBackpack().getIngredientQuantity().getOrDefault(new Coin() , 0)
                , p.getAbility().getFarmingLevel() , p.getAbility().getFishingLevel()
                , p.getAbility().getForagingLevel() , p.getAbility().getMiningLevel()) );
        }

        HashMap<String , Object> body = new HashMap<>();
        body.put("scoreBoard", scoreBoardDTOS);
        Message response = new Message(body , MessageType.UPDATE_SCOREBOARD_RESULT);
        connection.sendMessage(response);
    }
}
