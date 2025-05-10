package models.app;

import models.NPCs.NPC;
import models.NPCs.NPCType;
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
    public final ArrayList<NPC> npcs = new ArrayList<>();

    public Game(ArrayList<Player> players , ArrayList<Farm> farms , User u , Map x) {
        this.farms.addAll(farms);
        this.players.addAll(players);
        this.gameCreator = u;
        this.time = new Time();
        this.map = x;
        npcs.add(new NPC(NPCType.Abigail));
        npcs.add(new NPC(NPCType.Sebastian));
        npcs.add(new NPC(NPCType.Harvey));
        npcs.add(new NPC(NPCType.Leah));
        npcs.add(new NPC(NPCType.Robin));
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
    public void nextPlayerTurn(){
        int size = players.size();
        if(this.currentPlayingPlayer == players.get(size - 1)){
            currentPlayingPlayer = players.get(0);

        }
        else {
            for(int i = 0; i < size; i++){
                if(currentPlayingPlayer == players.get(i)){
                    currentPlayingPlayer = players.get(i + 1);
                    break;
                }
            }
        }
    }
    public Map getMap() {
        return map;
    }
    public void setMap(Map map) {
        this.map = map;
    }



}
