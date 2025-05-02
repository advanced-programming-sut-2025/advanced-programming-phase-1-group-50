package controller;
import java.util.ArrayList;

import controller.GameDateAndWeatherController.DateController;
import models.Result;
import models.app.*;
import models.userInfo.*;
import models.mapInfo.*;
public class GameMenuController {
    private final DateController dateController = new DateController();
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
            Player p = new Player(findUser(tokens[i]).getUsername(), findUser(tokens[i]).getNickname() , findUser(tokens[i]));
            players.add(p);
        }



        return new Result(true, "players added successfully , now we wnt to choose maps");

    }
    public Result selectMapForCreateNewGame(int  mapInput, Player player , int sx , int sy) {
        if(mapInput > 4 || mapInput < 1){
            return new Result(false, "please select between available maps");
        }

        switch (mapInput){
            case 1:
                player.setFarm(FarmFactory.makeFarm1(sx  ,sy));
                break;
            case 2:
                player.setFarm(FarmFactory.makeFarm2(sx , sy));
                break;
            case 3:
                player.setFarm(FarmFactory.makeFarm3(sx ,sy));
                break;
            case 4:
                player.setFarm(FarmFactory.makeFarm4(sx  ,sy));
                break;

        }


        return new Result(true, "map selected successfully");
    }

    public Result createNewGame(ArrayList<Player> players){
        ArrayList<Farm> maps = new ArrayList<>();
        for(Player p : players){
            maps.add(p.getFarm());

        }

        Map m = new Map(maps);
        m.buildMap(players);
        Game x = new Game(players, maps , App.getLoggedInUser() , m);


        App.games.add(x);
        App.setGame(x);
        App.getGame().setCurrentPlayingPlayer(players.getFirst());
        return new Result(true, "new game created successfully");
    }
    public Result loadGame(){
        return new Result(true, "load game successful");
    }
    public Result exitGame(){
        return new Result(true, "exit game successful");
    }
    public Result deleteGame(){
        return new Result(true, "delete game successful");
    }
    public Result nextTurn(){
        App.getGame().nextPlayerTurn();
        //App.getGame().getTime().advancedHour(1);
        return new Result(true, "next turn successful");
    }
    public Result buildGreenhouse(){
        if(App.getGame().getCurrentPlayingPlayer().getCoins() < 1000){
            return new Result(false , "you don't have enough money");
        }
        if (App.getGame().getCurrentPlayingPlayer()
                .getWoods() < 500) {

            return new Result(false , "you don't have enough woods");
        }
        App.getGame().getCurrentPlayingPlayer().minusCoins(1000);
        App.getGame().getCurrentPlayingPlayer().minusWoods(500);
        App.getGame().getCurrentPlayingPlayer().getFarm().getGreenHouse().setBroken(false);
        return new Result(true, "greenhouse build successfully");
    }
}
