package controller;
import java.util.*;

import controller.GameDateAndWeatherController.DateController;
import models.Result;
import models.app.*;
import models.manuFactor.Ingredient;
import models.mapInfo.Map;
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
    public int calculateEnergyBasedOnShortestDistance(List<Position> shortestPath){
        int energy = 0;
        for(Position p : shortestPath){
            energy++;
        }
        return  energy / 20;
    }
    public List<Position> findShortestPath(Map map , int startX , int startY , int endX , int endY){
        int[] dx= {1, -1 , 0 , 0};
        int[] dy = {0 , 0 , 1 , -1};
        boolean[][] visited = new boolean[250][200];
        Position[][] prev = new Position[250][200];
        Queue<Position> q = new LinkedList<>();
        q.add(new Position(startX, startY));
        visited[startX][startY] = true;
        while(!q.isEmpty()){
            Position p = q.poll();
            if(p.getX() == endX && p.getY() == endY){
                break;
            }
            for(int  i = 0 ; i < 4 ; i++){
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];

                if(nx >= 0 && nx < 250 && ny >= 0 && ny < 200 && !visited[nx][ny] && map.getTiles()[nx][ny].isWalkable()){
                    visited[nx][ny] = true;
                    prev[nx][ny] = p;
                    q.add(new Position(nx, ny));
                }
            }

        }
        List<Position> shortestPath = new LinkedList<>();
        Position at = new Position(endX, endY);
        while (at != null && !(at.getX() == startX && at.getY() == startY)) {
            shortestPath.add(at);
            at = prev[at.getX()][at.getY()];
        }
        if (at == null) return null; // مسیر پیدا نشد
        shortestPath.add(new Position(startX, startY));
        Collections.reverse(shortestPath);
        return shortestPath;
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
    public Result findPath(int endX, int endY, List<Position> positions){
        if(!App.getGame().getMap().getTiles()[endX][endY].isWalkable()){
            return new Result(false ,"Wrong place , Wrong Time");
        }
        for(Player p : App.getGame().getPlayers()){
            if(App.getGame().getCurrentPlayingPlayer().equals(p)){
                continue;
            }
            if(p.getFarm().getRectangle().contains(endX, endY)){
                return new Result(false ,"this position is not in your farm");
            }
        }

        List<Position> path = findShortestPath(
                App.getGame().getMap(),
                App.getGame().getCurrentPlayingPlayer().getPosition().getX(),
                App.getGame().getCurrentPlayingPlayer().getPosition().getY(),
                endX, endY
        );

        if(path == null){
            return new Result(false ,"No path found");
        }

        positions.clear();
        positions.addAll(path);

        int energy = calculateEnergyBasedOnShortestDistance(positions);
        return new Result(true, String.format("energy needed: %d", energy));
    }

    public Result walk(int endX , int endY , List<Position> positions){
        int energy = calculateEnergyBasedOnShortestDistance(positions);
        if(energy > App.getGame().getCurrentPlayingPlayer().getEnergy()){
            App.getGame().getCurrentPlayingPlayer().faint();
            // midoonam grammeresh dorost nist vali khob :)
            return new Result(false ,"you are fainted");
        }
        App.getGame().getCurrentPlayingPlayer().getPosition().setX(endX);
        App.getGame().getCurrentPlayingPlayer().getPosition().setY(endY);
        App.getGame().getCurrentPlayingPlayer().consumeEnergy(energy);
        return new Result(true , String.format("you have teleported successfully to " + endX + " " + endY ));
    }
    public Result helpReadingMap(){
        StringBuilder sb = new StringBuilder();
        sb.append("T : tree" + "\n");
        sb.append("S : stone" + "\n");
        sb.append("W : wood" + "\n");
        sb.append("C : cottage" + "\n");
        sb.append("G : greenhouse" + "\n");
        sb.append("* : crop" + "\n");
        sb.append("Q : quarry" + "\n");
        sb.append("L : lake"  + "\n");
        sb.append("+ : wall" + "\n");
        return new Result(true , sb.toString());

    }
    public Result inventoryShow(){
        StringBuilder sb = new StringBuilder();
        for(java.util.Map.Entry<Ingredient , Integer> entry : App.getGame().getCurrentPlayingPlayer().getBackpack()
                .getIngredientQuantity().entrySet()){
            sb.append(String.format("%s -> quantity : %d" , entry.getKey().getClass().getSimpleName(), entry.getValue()));
            sb.append("\n");

        }
        return new Result(true , sb.toString());

    }
    public Result inventoryTrash(String name , int number , boolean hasNumber){
        for(java.util.Map.Entry<Ingredient , Integer> entry : App.getGame().getCurrentPlayingPlayer().getBackpack()
                .getIngredientQuantity().entrySet()){
            if(entry.getKey().getClass().getSimpleName().equals(name)){
                if(hasNumber){
                    App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(entry.getKey() , number);
                    return new Result(true , String.format("%s removed from backpack", entry.getKey()
                            .getClass().getSimpleName()));
                }
                else {
                    int quantity = entry.getValue();
                    App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(entry.getKey() , quantity);
                    return new Result(true , String.format("%s removed from backpack", entry.getKey()
                            .getClass().getSimpleName()));
                }

            }
        }
        return new Result(true , String.format("%s not found", name));
    }
}
