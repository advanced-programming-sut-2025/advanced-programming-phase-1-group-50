package com.stardew.model.gameApp;

import com.stardew.controller.AnimalsControllers.AnimalsService;
import com.stardew.model.*;
import com.stardew.model.PlayersRelation.BetweenPlayersGift;
import com.stardew.model.PlayersRelation.RelationWithPlayers;
import com.stardew.model.animals.Animal;
import com.stardew.model.gameApp.date.Time;
import com.stardew.model.mapInfo.*;
import com.stardew.model.mapInfo.foraging.Crop;
import com.stardew.model.mapInfo.foraging.Growable;
import com.stardew.model.mapInfo.foraging.Tree;
import com.stardew.model.userInfo.Player;
import com.stardew.model.userInfo.RelationNetwork;
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
    private final TimeService timeService;
    private final HotBarService hotBarService;
    private final AnimalsService animalsService;
    private boolean started = false;
    private int shippingBinId = 0;
//    private final GameMenuController gameMenuController = new GameMenuController();

    public Game(Map<ClientConnectionThread, Player> players, ArrayList<Farm> farms, User u, GameMap map , Time time) {
        this.farms.addAll(farms);
        this.players = players;
        this.gameCreator = u;
        this.time = time;
        this.time.setGame(this);
        this.map = map;
        this.timeService = new TimeService(time, this);
        this.hotBarService = new HotBarService(this);
        this.animalsService = new AnimalsService();
        relationInitializer(players);
    }

    public BetweenPlayersGift getGiftById(int id) {
        for (BetweenPlayersGift gift : gifts) {
            if (gift.getId() == id) {
                return gift;
            }
        }
        return null;
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

    public Map<ClientConnectionThread, Player> getConnections() {
        return players;
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

    private void relationInitializer(Map<ClientConnectionThread, Player> players) {
        ArrayList<Player> playerArrayList = new ArrayList<>(players.values());

        relationsBetweenPlayers = new RelationNetwork();

        for (int i = 0; i < playerArrayList.size(); i++) {
            for (int j = i + 1; j < playerArrayList.size(); j++) {
                Set<Player> key = new HashSet<>(Arrays.asList(playerArrayList.get(i), playerArrayList.get(j)));
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


    public ArrayList<PlayerDTO> getOtherVisiblePlayers(int startX, int startY, int endX, int endY, ClientConnectionThread connection) {
        ArrayList<PlayerDTO> otherPlayers = new ArrayList<>();
        for (ClientConnectionThread connectionThread : players.keySet()) {
            if (connectionThread.equals(connection)) continue;
            Player player = players.get(connectionThread);
            if (player.getPlayerPosition().getFirst() > startX && player.getPlayerPosition().getFirst() < endX &&
                player.getPlayerPosition().getSecond() > startY && player.getPlayerPosition().getSecond() < endY) {
                otherPlayers.add(player.toDTO());
            }
        }
        return otherPlayers;
    }

    public ArrayList<TileDTO> getVisibleTiles(int startX, int startY, int endX, int endY) {
        ArrayList<TileDTO> tiles = new ArrayList<>();
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                Tile tile = map.findTile(i, j);
                if (tile == null) continue;
                tiles.add(tile.toDTO());
            }
        }
        return tiles;
    }

    public ArrayList<PlaceableDTO> getVisiblePlaceables(int startX, int startY, int endX, int endY) {
        ArrayList<PlaceableDTO> placeables = new ArrayList<>();
        Set<Placeable> placeablesSet = Collections.newSetFromMap(new IdentityHashMap<>());
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                Tile tile = map.findTile(i, j);
                if (tile == null) continue;
                Placeable placeable = tile.getPlaceable();
                if (placeable != null) placeablesSet.add(placeable);
            }
        }

        for (Placeable placeable : placeablesSet) {
            placeables.add(placeable.toPlaceableDTO());
        }
        return placeables;
    }


    public void callMethodsForTomorrow() {
        //((GameMenu) App.getMenu().getMenu()).doNights();

        for (Player player : getAllPlayers()) {
            int ratio = 1;
            if (player.getRemainingNumsAfterMarriageRequestDenied() > 0){
                ratio = 2;
                player.setRemainingNumsAfterMarriageRequestDenied(player.getRemainingNumsAfterMarriageRequestDenied() - 1);
            }
            if (player.isFaintedToday()) {
                player.setEnergy(150/ratio);
            }
            else {
                player.setEnergy(200/ratio);
            }
            player.setFaintedToday(false);


            Iterator<Tree> treeIterator = player.getFarm().getTrees().iterator();
            while (treeIterator.hasNext()) {
                Tree tree = treeIterator.next();
                tree.grow(time);
                if (!tree.canBeAlive(time)) {
                    treeIterator.remove();
                    player.getFarm().getPlaceables().remove(tree);
                    Tile tile = map.findTile(tree.getBounds().x, tree.getBounds().y);
                    tile.setWalkable(true);
                    tile.setPlaceable(null);
                    tile.setSymbol('.');
                    tile.setFertilizer(null);
                    tile.setPlowed(false);
                }
            }


            Iterator<Crop> cropIterator = player.getFarm().getCrops().iterator();
            while (cropIterator.hasNext()) {
                Crop crop = cropIterator.next();
                crop.grow(time);
                if (!crop.canBeAlive(time)) {
                    cropIterator.remove();

                    player.getFarm().getPlaceables().removeIf(p -> p == crop);
                    Tile tile = map.findTile(crop.getBounds().x, crop.getBounds().y);
                    tile.setWalkable(true);
                    tile.setPlaceable(null);
                    tile.setSymbol('.');
                    tile.setFertilizer(null);
                    tile.setPlowed(false);
                }
            }



            GreenHouse gh = player.getFarm().getGreenHouse();
            Iterator<Growable> cropIterator2 = gh.getGrowables().iterator();
            if(!gh.isBroken()) {
                while (cropIterator2.hasNext()) {
                    Growable growable = cropIterator2.next();
                    growable.grow(time);
                    if (!growable.canBeAlive(time)) {
                        cropIterator2.remove();
                        if (growable instanceof Crop) {
                            player.getFarm().getPlaceables().removeIf(p -> p == growable);

                        } else if (growable instanceof Tree) {
                            player.getFarm().getPlaceables().remove(growable);
                        }
                    }
                }
            }


            map.generateRandomForagingCrop();
            map.generateRandomStoneFarm();



            for (Animal animal : player.getBackpack().getAllAnimals()) {
                if (animal.isOutOfHabitat()) {
                    animal.decrementFriendShip(20);
                }
                if (!animal.hasFedYesterday()) {
                    animal.decrementFriendShip(20);
                }
                if (!animal.hasPettedYesterday()) {
                    animal.decrementFriendShip((animal.getFriendShip() / 200) + 10);
                }
            }
        }
        map.GotThunderByStormyWeather();
        map.randomForagingMineralGenerator();
        map.setTileWateredFalse();




        for (ShippingBin bin : this.map.getShippingBins()) {
            bin.checkEveryNight();
        }



        for (RelationWithPlayers relation : relationsBetweenPlayers.relationNetwork.values()) {
            relation.checkEveryNight();
        }

        for (Player player : getAllPlayers()) {
            player.getRelationWithAbigail().checkEveryNight(player);
            player.getRelationWithHarvey().checkEveryNight(player);
            player.getRelationWithLeah().checkEveryNight(player);
            player.getRelationWithRobin().checkEveryNight(player);
            player.getRelationWithSebastian().checkEveryNight(player);
        }
//        System.out.println("relations are done");
//
        this.getMap().getNpcVillage().getBlacksmith().ResetQuantityEveryNight();
        this.getMap().getNpcVillage().getMarnieRanch().ResetQuantityEveryNight();
        this.getMap().getNpcVillage().getPierreGeneralStore().ResetQuantityEveryNight();
        this.getMap().getNpcVillage().getJojaMart().ResetQuantityEveryNight();
        this.getMap().getNpcVillage().getFishShop().ResetQuantityEveryNight();
        this.getMap().getNpcVillage().getCarpenterShop().ResetQuantityEveryNight();
        this.getMap().getNpcVillage().getStardopSaloon().ResetQuantityEveryNight();
//
//        System.out.println("completed");

    }

    public TimeService getTimeService() {
        return timeService;
    }

    public AnimalsService getAnimalsService() { return animalsService; }

    public ArrayList<ClientConnectionThread> clientConnectionThreads() {
        ArrayList<ClientConnectionThread> clientConnectionThreads = new ArrayList<>();
        for(Map.Entry<ClientConnectionThread , Player> entry : players.entrySet()) {
            ClientConnectionThread connection = entry.getKey();
            clientConnectionThreads.add(connection);
        }
        return clientConnectionThreads;
    }

    public void startTime(){
        timeService.start();
    }

    public void startHotBar(){
        hotBarService.start();
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public void stopGameProcess() {
        timeService.stop();
        animalsService.stopUpdating();
    }


    public void changeTileInWinter(){
        for(Tile[] tile : map.getTiles()) {
            for(Tile t : tile) {
                t.checkSeasonIsWinter();
            }
        }
    }


    public void changeTileInSpring(){
        for(Tile[] tile : map.getTiles()) {
            for(Tile t : tile) {
                t.checkIsSeasonSpring();
            }
        }
    }

    public int getShippingBinId() {
        return this.shippingBinId;
    }

    public void increaseShippingBinId() {
        this.shippingBinId++;
    }

}
