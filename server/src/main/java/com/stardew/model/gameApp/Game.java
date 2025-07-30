package com.stardew.model.gameApp;

//import com.stardew.controller.GameMenuController;
//import com.stardew.model.BetweenPlayersGift;
//import com.stardew.model.ShippingBin;
//import com.stardew.model.Trade;
//import com.stardew.model.animals.Animal;
//import com.stardew.model.date.Time;
//import com.stardew.model.foraging.Crop;
//import com.stardew.model.foraging.Growable;
//import com.stardew.model.foraging.Tree;
import com.stardew.model.*;
import com.stardew.model.gameApp.date.Time;
import com.stardew.model.mapInfo.Farm;
//import com.stardew.model.mapInfo.GreenHouse;
import com.stardew.model.mapInfo.GameMap;
import com.stardew.model.mapInfo.Placeable;
import com.stardew.model.mapInfo.Tile;
import com.stardew.model.userInfo.Player;
import com.stardew.model.userInfo.RelationNetwork;
import com.stardew.model.userInfo.RelationWithPlayers;
import com.stardew.model.userInfo.User;
import com.stardew.network.ClientConnectionThread;

import java.util.*;

public class Game {
    private final Map<ClientConnectionThread, Player> players;
    private final ArrayList<Farm> farms = new ArrayList<>();
    private final Time time;
    private final GameMap map;
    private final User gameCreator;
    private Player currentPlayingPlayer;
    private RelationNetwork relationsBetweenPlayers;
    private final ArrayList<BetweenPlayersGift> gifts = new ArrayList<>();
    private int giftIndex = 0;
    private int tradeIndex = 0;
    private final ArrayList<Trade> trades = new ArrayList<>();
//    private final GameMenuController gameMenuController = new GameMenuController();

    public Game(Map<ClientConnectionThread, Player> players, ArrayList<Farm> farms, User u, GameMap map) {
        this.farms.addAll(farms);
        this.players = players;
        this.gameCreator = u;
        this.time = new Time();
        this.map = map;
//        relationInitializer(players);
    }

    public ArrayList<BetweenPlayersGift> getGifts() {
        return gifts;
    }

    public RelationNetwork getRelationsBetweenPlayers() {
        return relationsBetweenPlayers;
    }

    public Player getPlayer(ClientConnectionThread connection) {
        return players.get(connection);
    }

    public ArrayList<Player> getAllPlayers() {
        return new ArrayList<>(players.values());
    }

    public Time getTime() {
        return time;
    }

    public User getGameCreator() {
        return gameCreator;
    }

    public Player getCurrentPlayingPlayer() {
        return currentPlayingPlayer;
    }

    public void setCurrentPlayingPlayer(Player currentPlayingPlayer) {
        this.currentPlayingPlayer = currentPlayingPlayer;
        this.currentPlayingPlayer.setConsumedEnergyInTurn(0);
    }

//    public Result nextPlayerTurn() {
//        int size = players.size();
//        int currentIndex = players.indexOf(currentPlayingPlayer);
//        int checkedPlayers = 0;
//
//        while (checkedPlayers < size) {
//            currentIndex = (currentIndex + 1) % size;
//            Player nextPlayer = players.get(currentIndex);
//
//            if (!nextPlayer.isFaintedToday()) {
//                setCurrentPlayingPlayer(nextPlayer);
//
//                break;
//            }
//            checkedPlayers++;
//        }
//
//        if (checkedPlayers == size) {
////            time.advancedDay(1);
//            return new Result(true, "All players have been fainted! Next day is started!\n" +
//                    "Current player: " + players.getFirst().getUsername() + "\n\n" + players.getFirst().UncheckedNotifications());
//        }
//
//        if (currentPlayingPlayer.equals(players.get(0))) {
////            time.advancedHour(1);
//            return new Result(true, "An hour passed!\n" +
//                    "Current player: " + players.getFirst().getUsername() + "\n\n" + players.getFirst().UncheckedNotifications());
//        }
//
//        return new Result(true, "Next player: " + currentPlayingPlayer.getUsername() + "\n\n" + currentPlayingPlayer.UncheckedNotifications());
//    }

    public GameMap getMap() {
        return map;
    }

    private void relationInitializer(ArrayList<Player> players) {
        relationsBetweenPlayers = new RelationNetwork();

        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                Set<Player> key = new HashSet<>(Arrays.asList(players.get(i), players.get(j)));
                relationsBetweenPlayers.relationNetwork.put(key, new RelationWithPlayers());
            }
        }
    }

    public void addGiftsIndex() {
        giftIndex++;
    }

    public int getGiftIndex() {
        return giftIndex;
    }

    public void addToGifts(BetweenPlayersGift gift) {
        gifts.add(gift);
    }

    public void addTradesIndex() {
        tradeIndex++;
    }

    public int getTradeIndex() {
        return tradeIndex;
    }

    public void addToTrades(Trade trade) {
        trades.add(trade);
    }

//    public ArrayList<Trade> getTrades() {
//        return trades;
//    }

    public GameState getGameState(int startX, int startY, int endX, int endY, ClientConnectionThread connection) {
        ArrayList<TileDTO> tiles = new ArrayList<>();
        ArrayList<PlaceableDTO> placeables = new ArrayList<>();
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                Tile tile = map.getTiles()[i][j];
                if (tile == null) continue;
                tiles.add(tile.toDTO());
                Placeable placeable = tile.getPlaceable();
                if (placeable != null) {
                    placeables.add(new PlaceableDTO(i, j, placeable.getBounds().width, placeable.getBounds().height, placeable.getTexture()));
                }
            }
        }
        PlayerDTO player = players.get(connection).toDTO();

        return new GameState(tiles, placeables, player);
    }


//    public void callMethodsForTomorrow() {
//        //((GameMenu) App.getMenu().getMenu()).doNights();
//
//        for (Player player : players) {
//            int ratio = 1;
//            if (player.getRemainingNumsAfterMarriageRequestDenied() > 0){
//                ratio = 2;
//                player.setRemainingNumsAfterMarriageRequestDenied(player.getRemainingNumsAfterMarriageRequestDenied() - 1);
//            }
//            if (player.isFaintedToday()) {
//                player.setEnergy(150/ratio);
//            }
//            else {
//                player.setEnergy(200/ratio);
//            }
//            player.setFaintedToday(false);
//            Iterator<Tree> treeIterator = player.getFarm().getTrees().iterator();
//            while (treeIterator.hasNext()) {
//                Tree tree = treeIterator.next();
//                tree.grow(time);
//                if (!tree.canBeAlive(time)) {
//                    treeIterator.remove();
//                    player.getFarm().getPlaceables().remove(tree);
//                    Tile tile = map.findTile(tree.getBounds().x, tree.getBounds().y);
//                    tile.setWalkable(true);
//                    tile.setPlaceable(null);
//                    tile.setSymbol('.');
//                    tile.setFertilizer(null);
//                    tile.setPlowed(false);
//                }
//            }
//
//            Iterator<Crop> cropIterator = player.getFarm().getCrops().iterator();
//            while (cropIterator.hasNext()) {
//                Crop crop = cropIterator.next();
//                crop.grow(time);
//                if (!crop.canBeAlive(time)) {
//                    cropIterator.remove();
//
//                    player.getFarm().getPlaceables().removeIf(p -> p == crop);
//                    Tile tile = map.findTile(crop.getBounds().x, crop.getBounds().y);
//                    tile.setWalkable(true);
//                    tile.setPlaceable(null);
//                    tile.setSymbol('.');
//                    tile.setFertilizer(null);
//                    tile.setPlowed(false);
//                }
//            }
//
//            GreenHouse gh = player.getFarm().getGreenHouse();
//            Iterator<Growable> cropIterator2 = gh.getGrowables().iterator();
//            if(!gh.isBroken()) {
//                while (cropIterator2.hasNext()) {
//                    Growable growable = cropIterator2.next();
//                    growable.grow(time);
//                    if (!growable.canBeAlive(time)) {
//                        cropIterator2.remove();
//                        if (growable instanceof Crop) {
//                            player.getFarm().getPlaceables().removeIf(p -> p == growable);
//
//                        } else if (growable instanceof Tree) {
//                            player.getFarm().getPlaceables().remove(growable);
//                        }
//                    }
//                }
//            }
//            map.generateRandomForagingCrop(player.getFarm());
//            map.generateRandomStoneFarm(player.getFarm());
//
//            for (Animal animal : player.getBackpack().getAllAnimals()) {
//                if (animal.isOutOfHabitat()) {
//                    animal.decrementFriendShip(20);
//                }
//                if (!animal.hasFedYesterday()) {
//                    animal.decrementFriendShip(20);
//                }
//                if (!animal.hasPettedYesterday()) {
//                    animal.decrementFriendShip((animal.getFriendShip() / 200) + 10);
//                }
//            }
//        }
//        map.GotThunderByStormyWeather();
//        map.randomForagingMineralGenerator();
//        setCurrentPlayingPlayer(players.getFirst());
//
//        for (ShippingBin bin : this.map.getShippingBins()) {
//            bin.checkEveryNight();
//        }
//
//        for (RelationWithPlayers relation : App.getGame().relationsBetweenPlayers.relationNetwork.values()) {
//            relation.checkEveryNight();
//        }
//
//        for (Player player : players) {
//            player.getRelationWithAbigail().checkEveryNight(player);
//            player.getRelationWithHarvey().checkEveryNight(player);
//            player.getRelationWithLeah().checkEveryNight(player);
//            player.getRelationWithRobin().checkEveryNight(player);
//            player.getRelationWithSebastian().checkEveryNight(player);
//        }
//
//        this.getMap().getNpcVillage().getBlacksmith().ResetQuantityEveryNight();
//        this.getMap().getNpcVillage().getMarnieRanch().ResetQuantityEveryNight();
//        this.getMap().getNpcVillage().getPierreGeneralStore().ResetQuantityEveryNight();
//        this.getMap().getNpcVillage().getJojaMart().ResetQuantityEveryNight();
//        this.getMap().getNpcVillage().getFishShop().ResetQuantityEveryNight();
//        this.getMap().getNpcVillage().getCarpenterShop().ResetQuantityEveryNight();
//        this.getMap().getNpcVillage().getStardopSaloon().ResetQuantityEveryNight();
//
//    }
}
