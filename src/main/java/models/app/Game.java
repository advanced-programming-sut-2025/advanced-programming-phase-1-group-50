package models.app;

import models.mapInfo.Map;
import models.date.Time;
import models.userInfo.Player;

import java.util.ArrayList;

public class Game {
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Map> maps = new ArrayList<>();
    private Time time;

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
