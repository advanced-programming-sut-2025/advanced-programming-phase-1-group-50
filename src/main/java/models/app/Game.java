package models.app;

import models.date.Time;
import models.mapInfo.Map;
import models.mapInfo.Farm;
import models.userInfo.Player;

import java.util.ArrayList;
import models.userInfo.*;
public class Game {
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Farm> farms = new ArrayList<>();
    private Time time ;
    private Map map;
    private final User gameCreator ;
    private Player currentPlayingPlayer;

    public Game(ArrayList<Player> players , ArrayList<Farm> farms , User u , Map x) {
        this.farms.addAll(farms);
        this.players.addAll(players);
        this.gameCreator = u;
        this.time = new Time();
        this.map = x;

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
    }
    public void nextPlayerTurn() {
        int size = players.size();
        int currentIndex = players.indexOf(currentPlayingPlayer);
        int checkedPlayers = 0;

        while (checkedPlayers < size) {
            currentIndex = (currentIndex + 1) % size;
            Player nextPlayer = players.get(currentIndex);

            if (!nextPlayer.isFaintedToday()) {
                currentPlayingPlayer = nextPlayer;
                break;
            }
            checkedPlayers++;
        }

        if (checkedPlayers == size) {

            // method marboot be farda ro call mikonim.
            //TODO
        }

        if (currentPlayingPlayer == players.get(0)) {
            App.getGame().getTime().advancedHour(1);
        }
    }

    public Map getMap() {
        return map;
    }
    public void setMap(Map map) {
        this.map = map;
    }



}
