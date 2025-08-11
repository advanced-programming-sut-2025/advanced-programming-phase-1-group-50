package com.stardew.controller;

import com.stardew.controller.AnimalsControllers.AnimalsController;
import com.stardew.controller.CookingCraftingControllers.ArtisanController;
import com.stardew.controller.CookingCraftingControllers.CookingController;
import com.stardew.controller.CookingCraftingControllers.CookingCraftingInfoController;
import com.stardew.controller.CookingCraftingControllers.CraftingController;
import com.stardew.model.AnimalDTO;
import com.stardew.model.ScoreBoardDTO;
import com.stardew.model.animals.Animal;
import com.stardew.model.gameApp.Game;
import com.stardew.model.mapInfo.Farm;
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

    public void stopAllGames() {
        games.forEach((id, game) -> game.stopGameProcess());
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

        Player player = game.getPlayer(connection);
        if (player == null) return;
        HashMap<String, Object> body = new HashMap<>();
        body.put("main_player", player.toDTO());
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

        if (animals.isEmpty() && AnimalsController.getInstance().hasSentEmptyListMessage(connection)) {
            return;
        }
        if (animals.isEmpty()) {
            AnimalsController.getInstance().sendEmptyListMessage(connection);
        } else {
            AnimalsController.getInstance().notSentEmptyListMessage(connection);
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
        if (player == null) return;
        InventoryController.getInstance().sendHotBarUpdate(player, connection);
    }

    public void handleEventInGame(Message message, ClientConnectionThread connection) {
        if (message == null) return;
        int id = message.getIntFromBody("id");
        Game game = games.get(id);
        if (game == null) return;

        Event event = message.getFromBody("event", Event.class);
        if (event == null) return;

        Player player = game.getPlayer(connection);
        if (player == null) return;

        switch (event) {
            case Moving -> {
                PlayerController.getInstance().handleMovement(player, game, message);
            }
            case ExitGame -> {
                game.getConnections().remove(connection);
                if (game.getConnections().isEmpty()) {
                    game.stopGameProcess();
                    removeGame(id);
                    LobbyController.getInstance().removeId(id);
                }
            }
            case ShowInventory -> {
                InventoryController.getInstance().handleSendInventoryList(player, connection , message.getRequestID());
            }
            case RemoveItem -> {
                InventoryController.getInstance().handleRemoveItem(player, message , connection , message.getRequestID());

            }
            case GetCookingOrCraftingInfo -> {
                CookingCraftingInfoController.getInstance().handleGetInfo(message, player, connection);
            }
            case CookingFood -> {
                CookingController.getInstance().cookingPrepare(message, player, connection);
            }
            case GetMyFarmInfo -> {
                CookingCraftingInfoController.getInstance().handleGetFarmInfo(message, player, game.getMap(), connection);
            }
            case CraftingMachine -> {
                CraftingController.getInstance().craftingCraft(message, player, game.getMap(), game.getTime(), connection);
            }

            case GetSkillInfo -> {
                InventoryController.getInstance().handleSendSkillInfo(player , connection , message.getRequestID());
            }

            case GetRelationWithNPCInfo -> {
                InventoryController.getInstance().handleSendRelationWithNPCInfo(player, connection , message.getRequestID());
            }

            case GetMapInfo -> {
                ArrayList<Player> players = game.getAllPlayers();
                InventoryController.getInstance().handleSendMapInfo(players , game , connection , message.getRequestID());

            }

            case ShuffleInventory -> {
                InventoryController.getInstance().handleShuffleInventory(player, connection , message.getRequestID());

            }

            case SetCurrentItem -> {
                InventoryController.getInstance().handleSetCurrentItem(player , message);
            }

            case CLickTile -> {
                InventoryController.getInstance().handleClickTile(player, game , message , connection , message.getRequestID());
            }

            case GetRefrigeratorItems -> {
                CookingController.getInstance().handleGetRefrigeratorItems(message, player, connection);
            }
            case PutInRefrigerator -> {
                CookingController.getInstance().putInRefrigerator(message, player, connection);
            }
            case PickFromRefrigerator -> {
                CookingController.getInstance().pickFromRefrigerator(message, player, connection);
            }
            case EatItem -> {
                CookingController.getInstance().handleEat(message, player, connection);
            }
            case GetMachineDetails -> {
                ArtisanController.getInstance().handleGetMachineInfo(message, player, connection);
            }
            case CheatFinishMachineProcess -> {
                ArtisanController.getInstance().cheatFinishProcess(message, player);
            }
            case CancelMachineProcess -> {
                ArtisanController.getInstance().cancelProcess(message, player, connection);
            }
            case CollectMachineProduct -> {
                ArtisanController.getInstance().collectProduct(message, player, connection);
            }
            case IsReadyProduct -> {
                ArtisanController.getInstance().isReadyProduct(message, player, connection);
            }
            case UseArtisanMachine -> {
                ArtisanController.getInstance().artisanUse(message, player, connection);
            }

            case Reaction -> {
                ReactionController.getInstance().handleReactionProcess(message , game);
            }

            case GetPlayersRelationsInfo -> {
                PlayersRelationController.getInstance().getRelations(message, player, connection);
            }

            case CheatCode -> {
                CheatCodeController.getInstance().executeCheatCode(message , connection , game , player );
            }
            case GetAnimalsInfoInHabitat -> {
                AnimalsController.getInstance().getAnimalsInfoInHabitat(message, player, connection);
            }
            case GetAnimalsProductsInfo -> {
                AnimalsController.getInstance().animalProductsInfo(message, player, connection);
            }
            case FeedAnimal -> {
                AnimalsController.getInstance().feedAnimal(message, player, connection);
            }
            case PetAnimal -> {
                AnimalsController.getInstance().petAnimal(message, player, connection);
            }
            case ShepherdAnimal -> {
                AnimalsController.getInstance().shepherdAnimal(message, game, player, connection);
            }
            case CollectAnimalProduct -> {
                AnimalsController.getInstance().collectProduct(message, game, player, connection);
            }
            case SellAnimal -> {
                AnimalsController.getInstance().sellAnimal(message, game.getAnimalsService(), player, connection);
            }

            case GetBetweenPlayersGifts -> {
                PlayersRelationController.getInstance().getAllGifts(message, connection);
            }

            case RateGift -> {
                PlayersRelationController.getInstance().rateGift(message,connection);
            }

            case SendGiftToPlayer -> {
                PlayersRelationController.getInstance().sendGiftToPlayer(message, player, connection);
            }

            case CanHug -> {
                PlayersRelationController.getInstance().canHug(message, player, connection);
            }

            case CanGiveFlower -> {
                PlayersRelationController.getInstance().canGiveFlower(message, player, connection);
            }

            case CanAskMarriage -> {
                PlayersRelationController.getInstance().canAskMarriage(message, player, connection);
            }

            case GetForSaleProducts -> {
                PlayersRelationController.getInstance().getForSaleProducts(message, player, connection);
            }

            case GetStoreGoods -> {
                StoreController.getInstance().sendStoreGoodsInfo(message,connection);
            }

            case PurchaseAnimal -> {
                StoreController.getInstance().purchaseAnimal(message,player,connection);
            }

            case CanPurchaseBuilding -> {
                StoreController.getInstance().canPurchaseBuilding(message,player,connection);
            }

            case PurchaseShippingBin -> {
                StoreController.getInstance().purchaseShippingBin(message,player,connection);
            }

            case PurchaseBuilding -> {
                StoreController.getInstance().purchaseBuilding(message,player,connection);
            }

            case PurchaseProduct -> {
                StoreController.getInstance().purchaseProduct(message,player,connection);
            }

            case UpgradeTrashCan -> {
                StoreController.getInstance().upgradeTrashCan(message,player,connection);
            }

            case UpgradeTool -> {
                StoreController.getInstance().upgradeTool(message,player,connection);
            }

            case IsStoreOpen -> {
                StoreController.getInstance().isStoreOpen(message,connection);
            }

            case SellProduct -> {
                StoreController.getInstance().sellProduct(message,player,connection);
            }

            case GiftToNPC -> {
                NPCController.getInstance().giftToNPC(message,player,connection);
            }

            case GetRelationWithNPC -> {
                NPCController.getInstance().getRelationWithNPC(message,player,connection);
            }

            case BuildGreenhouse -> {
                BuildingController.getInstance().handleBuildGreenhouse(message , connection , player);
            }

            case GetNPCQuestsStatus -> {
             NPCController.getInstance().getQuestsStatus(message,connection);
            }

            case DoQuest -> {
                NPCController.getInstance().doQuest(message,player,connection);
            }

            case ShowGreenhouseGrowable -> {
                BuildingController.getInstance().greenHouseGrowableRequest(message , connection , player);
            }

            case GetPlayersRequest -> {
                PlayersRelationController.getInstance().getPlayers(message,player,connection);
            }

            case TalkToPlayer -> {
                PlayersRelationController.getInstance().talkToPlayer(message, player, connection);
            }

            case TalkToNPC -> {
                NPCController.getInstance().handleTalkWithNPC(message , connection , player , game);
            }

            case Hug -> {
                PlayersRelationController.getInstance().hugPlayer(message, player);
            }

            case GiveFlower -> {
                PlayersRelationController.getInstance().giveFlower(message, player);
            }

            case RequestMarriage -> {
                PlayersRelationController.getInstance().requestMarriage(message, player,connection);
            }

            case MarriageRequestResponse -> {
                PlayersRelationController.getInstance().respondMarriage(message , player);
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

    public void sendGreenhousePosition(Message message, ClientConnectionThread connection) {
        if(message == null) return;
        int id = message.getIntFromBody("id");
        Game game = games.get(id);
        if(game == null) return;

        Player player = game.getPlayer(connection);
        if (player == null) return;
        Farm farm = player.getFarm();
        if(farm == null) return;


        int x = farm.getGreenHouse().getBounds().x;
        int y = farm.getGreenHouse().getBounds().y;
        int width = farm.getGreenHouse().getBounds().width;
        int height = farm.getGreenHouse().getBounds().height;


        HashMap<String , Object> body = new HashMap<>();
        body.put("x", x);
        body.put("y", y);
        body.put("width", width);
        body.put("height", height);
        Message response = new Message(body , MessageType.GREENHOUSE_POSITION_RESULT);
        response.setRequestID(message.getRequestID());
        connection.sendMessage(response);

    }
}
