package models.app;

import models.mapInfo.Map;
import models.date.Time;
import models.userInfo.Player;

import java.awt.*;
import java.util.ArrayList;
import models.userInfo.*;
public class Game {
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Map> maps = new ArrayList<>();
    private Time time;
    private final User gameCreator ;
    public Game(ArrayList<Player> players , ArrayList<Map> maps , User u){
        for(Map m : maps){
            this.maps.add(m);
        }
        for(Player p : players){
            this.players.add(p);
        }
        this.gameCreator = u;


    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Time getTime() {
        return time;
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }

    public void setTime(Time time) {
        this.time = time;
    }



}
