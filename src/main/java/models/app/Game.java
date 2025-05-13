package models.app;

import models.BetweenPlayersGift;
import models.animals.Animal;
import models.date.Time;
import models.foraging.Crop;
import models.foraging.Tree;
import models.mapInfo.Map;
import models.mapInfo.Farm;
import models.mapInfo.Tile;
import models.userInfo.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import models.userInfo.*;

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

    public void setRelationsBetweenPlayers(RelationNetwork relationsBetweenPlayers) {
        this.relationsBetweenPlayers = relationsBetweenPlayers;
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

    public void nextPlayerTurn() {
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
            //TODO
            // at this time all of players are fainted , so we go to next day.
        }

        if (currentPlayingPlayer.equals(players.get(0))) {
            App.getGame().getTime().advancedHour(1);
        }
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

    public String giftList(Player player) {

        StringBuilder message = new StringBuilder("gift list:");
        for (BetweenPlayersGift gift : gifts) {
            if (gift.getReceiver().equals(player)) {
                message.append("\n" + "ID: ").append(gift.getId()).append("  ").append(gift.getIngredient()).append(
                        "  Rate: ").append(gift.getRate());
            }
        }
        return message.toString();

    }

    public String giftHistory(Player player1, Player player2) {

        StringBuilder message = new StringBuilder("gift history:");
        for (BetweenPlayersGift gift : gifts) {
            if ((gift.getReceiver().equals(player1) && gift.getSender().equals(player2)) || (gift.getReceiver().equals(player2) && gift.getSender().equals(player1))) {
                message.append("\n" + "ID: ").append(gift.getId()).append("  ").append(gift.getIngredient()).append(
                        "   Sender: ").append(gift.getSender());
                message.append("    Receiver: ").append(gift.getReceiver()).append("    Rate: ").append(gift.getRate());
            }
        }
        return message.toString();

    }
    public void callMethodsForTomorrow() {
        for (Player player : players) {
            for (Tree tree : player.getFarm().getTrees()) {
               tree.grow(time);
               if(!tree.canBeAlive(time)){
                   player.getFarm().getTrees().remove(tree);
                   player.getFarm().getPlaceables().remove(tree);
                   Tile tile = map.findTile(tree.getBounds().x, tree.getBounds().y);
                   tile.setWalkable(true);
                   tile.setPlaceable(null);
                   tile.setSymbol('.');
                   tile.setFertilizer(null);
                   tile.setPlowed(false);
               }
            }
            for (Crop crop : player.getFarm().getCrops()) {
                crop.grow(time);
                if(!crop.canBeAlive(time)){
                    player.getFarm().getCrops().removeIf(c -> c == crop);
                    player.getFarm().getPlaceables().removeIf(c -> c == crop);
                    Tile tile = map.findTile(crop.getBounds().x, crop.getBounds().y);
                    tile.setWalkable(true);
                    tile.setPlaceable(null);
                    tile.setSymbol('.');
                    tile.setFertilizer(null);
                    tile.setPlowed(false);
                }
            }
            for (Animal animal : player.getBackpack().getAllAnimals()) {
                if(animal.isOutOfHabitat()){
                    animal.decrementFriendShip(20);
                }
                if(!animal.hasFedToday()){
                    animal.decrementFriendShip(20);
                }
                if(!animal.hasPettedToday()){
                    animal.decrementFriendShip((animal.getFriendShip() / 200) + 10);
                }
            }
        }
        map.GotThunderByStormyWeather();
        map.randomForagingMineralGenerator();

    }
}
