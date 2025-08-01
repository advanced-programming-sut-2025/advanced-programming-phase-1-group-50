package com.stardew.models.app;

import com.stardew.controller.GameMenuController;
import com.stardew.models.BetweenPlayersGift;
import com.stardew.models.Result;
import com.stardew.models.ShippingBin;
import com.stardew.models.Trade;
import com.stardew.models.animals.Animal;
import com.stardew.models.date.Time;
import com.stardew.models.foraging.Crop;
import com.stardew.models.foraging.Growable;
import com.stardew.models.foraging.Tree;
import com.stardew.models.mapInfo.GreenHouse;
import com.stardew.models.mapInfo.Map;
import com.stardew.models.mapInfo.Farm;
import com.stardew.models.mapInfo.Tile;
import com.stardew.models.userInfo.Player;

import java.util.*;

import com.stardew.models.userInfo.*;
import com.stardew.view.GameMenu;

public class Game {
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Farm> farms = new ArrayList<>();
    private Time time;
    private Map map;
    private final User gameCreator;
    private Player currentPlayingPlayer;
    private RelationNetwork relationsBetweenPlayers;
    private final ArrayList<BetweenPlayersGift> gifts = new ArrayList<>();
    private int giftIndex = 0;
    private int tradeIndex = 0;
    private final ArrayList<Trade> trades = new ArrayList<>();
    private final GameMenuController gameMenuController = new GameMenuController();

    public Game(ArrayList<Player> players, ArrayList<Farm> farms, User u, Map x) {
        this.farms.addAll(farms);
        this.players.addAll(players);
        this.gameCreator = u;
        this.time = new Time();
        this.map = x;
        relationInitializer(players);
    }

    public ArrayList<BetweenPlayersGift> getGifts() {
        return gifts;
    }

    public RelationNetwork getRelationsBetweenPlayers() {
        return relationsBetweenPlayers;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Time getTime() {
        return time;
    }

    public ArrayList<Farm> etFarms() {
        return farms;
    }

    public void setTime(Time time) {
        this.time = time;
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

    public Result nextPlayerTurn() {
        int size = players.size();
        int currentIndex = players.indexOf(currentPlayingPlayer);
        int checkedPlayers = 0;

        while (checkedPlayers < size) {
            currentIndex = (currentIndex + 1) % size;
            Player nextPlayer = players.get(currentIndex);

            if (!nextPlayer.isFaintedToday()) {
                setCurrentPlayingPlayer(nextPlayer);

                break;
            }
            checkedPlayers++;
        }

        if (checkedPlayers == size) {
            time.advancedDay(1);
            return new Result(true, "All players have been fainted! Next day is started!\n" +
                    "Current player: " + players.getFirst().getUsername() + "\n\n" + players.getFirst().UncheckedNotifications());
        }

        if (currentPlayingPlayer.equals(players.get(0))) {
            time.advancedHour(1);
            return new Result(true, "An hour passed!\n" +
                    "Current player: " + players.getFirst().getUsername() + "\n\n" + players.getFirst().UncheckedNotifications());
        }

        return new Result(true, "Next player: " + currentPlayingPlayer.getUsername() + "\n\n" + currentPlayingPlayer.UncheckedNotifications());
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
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

    public ArrayList<Trade> getTrades() {
        return trades;
    }

    public void callMethodsForTomorrow() {
        //((GameMenu) App.getMenu().getMenu()).doNights();

        for (Player player : players) {
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
            map.generateRandomForagingCrop(player.getFarm());
            map.generateRandomStoneFarm(player.getFarm());

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
        setCurrentPlayingPlayer(players.getFirst());

        for (ShippingBin bin : this.map.getShippingBins()) {
            bin.checkEveryNight();
        }

        for (RelationWithPlayers relation : App.getGame().relationsBetweenPlayers.relationNetwork.values()) {
            relation.checkEveryNight();
        }

        for (Player player : players) {
            player.getRelationWithAbigail().checkEveryNight(player);
            player.getRelationWithHarvey().checkEveryNight(player);
            player.getRelationWithLeah().checkEveryNight(player);
            player.getRelationWithRobin().checkEveryNight(player);
            player.getRelationWithSebastian().checkEveryNight(player);
        }

        this.getMap().getNpcVillage().getBlacksmith().ResetQuantityEveryNight();
        this.getMap().getNpcVillage().getMarnieRanch().ResetQuantityEveryNight();
        this.getMap().getNpcVillage().getPierreGeneralStore().ResetQuantityEveryNight();
        this.getMap().getNpcVillage().getJojaMart().ResetQuantityEveryNight();
        this.getMap().getNpcVillage().getFishShop().ResetQuantityEveryNight();
        this.getMap().getNpcVillage().getCarpenterShop().ResetQuantityEveryNight();
        this.getMap().getNpcVillage().getStardopSaloon().ResetQuantityEveryNight();

    }
}
