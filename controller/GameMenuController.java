package controller;
import java.util.ArrayList;

import models.*;
import models.app.*;
import models.userInfo.*;
import models.mapInfo.*;
public class GameMenuController {
    public User findUser(String username){
        for(User u : App.users){
            if(username.equals(u.getUsername())){
                return u;
            }
        }
        return null;

    }
    public Result createNewGamePlayers(String input , ArrayList<Player> players){
        String regex = "\\s+";
        String[] tokens = input.split(regex);
        boolean checkFirstOfCommand = tokens[0].equals("game") && tokens[1].equals("new") && tokens[2].equals("-u");
        if(tokens.length == 0 || !checkFirstOfCommand){
            return new Result(false , "invalid command");
        }
        if(tokens.length < 4){
            return new Result(false, "please select 1 user atleast");
        }
        if(tokens.length > 6){
            return new Result(false, "please select 3 users");
        }
        for(int i=3 ; i<tokens.length ; i++){
            if(findUser(tokens[i]) == null){
                return new Result(false, String.format("%s not found" , tokens[i]));
            }
        }
        for(Game g : App.games){
            for(int i=3 ; i<tokens.length ; i++){
                if(g.getPlayers().contains(findUser(tokens[i]))){
                    return new Result(false, String.format("%s is already in another game" , tokens[i]));
                }
            }
        }
        
        for(int i= 3; i<tokens.length ; i++){
            Player p = new Player(findUser(tokens[i]).getUsername(), findUser(tokens[i]).getNickname());
            players.add(p);
        }



        return new Result(true, "players added succsessfuly , now we wnt to choose maps");

    }
    public Result selectMapForCreateNewGame(String mapInput, Player player) {
        Map selectedMap = null;
        for (Map map : Map.values()) {
            if (map.name().equalsIgnoreCase(mapInput.trim())) {
                selectedMap = map;
                break;
            }
        }
    
        if (selectedMap == null) {
            return new Result(false, "please select between available maps");
        }
    
        player.setMap(selectedMap);
        return new Result(true, "map selected successfully");
    }
    
    public Result createNewGame(ArrayList<Player> players){
        ArrayList<Map> maps = new ArrayList<>();
        for(Player p : players){
            maps.add(p.getMap());

        }
        Game x = new Game(players, maps , App.getLoggedInUser());
        App.games.add(x);
        App.setGame(x);
        return new Result(true, "new game created succsessfuly");
    }
}
