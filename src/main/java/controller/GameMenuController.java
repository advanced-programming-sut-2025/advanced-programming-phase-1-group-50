package controller;

import java.util.*;
import java.util.regex.Matcher;

import controller.GameDateAndWeatherController.DateController;
import models.ColorPrinter;
import models.Result;
import models.ShippingBin;
import models.Trade;
import models.app.*;
import models.manuFactor.Ingredient;
import models.mapInfo.Map;
import models.stores.Sellable;
import models.userInfo.*;
import models.mapInfo.*;


public class GameMenuController {
    private final DateController dateController = new DateController();

    public User findUser(String username) {
        for (User u : App.users) {
            if (username.equals(u.getUsername())) {
                return u;
            }
        }
        return null;

    }

    public int calculateEnergyBasedOnComplexFormula(List<Position> positions) {
        if (positions.size() < 2) return 0;

        int numberOfTurns = 0;
        int prevDx = positions.get(1).getX() - positions.get(0).getX();
        int prevDy = positions.get(1).getY() - positions.get(0).getY();

        for (int i = 1; i < positions.size() - 1; i++) {
            int dx = positions.get(i + 1).getX() - positions.get(i).getX();
            int dy = positions.get(i + 1).getY() - positions.get(i).getY();

            if (dx != prevDx || dy != prevDy) {
                numberOfTurns++;
            }

            prevDx = dx;
            prevDy = dy;
        }

        int distance = positions.size();
        return (distance + 10 * numberOfTurns) / 20;
    }


    public List<Position> findShortestPath(Map map, int startX, int startY, int endX, int endY) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        boolean[][] visited = new boolean[250][200];
        Position[][] prev = new Position[250][200];
        Queue<Position> q = new LinkedList<>();
        q.add(new Position(startX, startY));
        visited[startX][startY] = true;
        while (!q.isEmpty()) {
            Position p = q.poll();
            if (p.getX() == endX && p.getY() == endY) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];

                if (nx >= 0 && nx < 250 && ny >= 0 && ny < 200 && !visited[nx][ny] && map.getTiles()[nx][ny].isWalkable()) {
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
        if (at == null) return null; // there is no found
        shortestPath.add(new Position(startX, startY));
        Collections.reverse(shortestPath);
        return shortestPath;
    }

    public Result createNewGamePlayers(String input, ArrayList<Player> players) {
        String regex = "\\s+";
        String[] tokens = input.split(regex);
        boolean checkFirstOfCommand = tokens[0].equals("game") && tokens[1].equals("new") && tokens[2].equals("-u");
        if (tokens.length == 0 || !checkFirstOfCommand) {
            return new Result(false, "invalid command");
        }
        if (tokens.length < 4) {
            return new Result(false, "please select 1 user least");
        }
        if (tokens.length > 6) {
            return new Result(false, "please select 3 users");
        }
        for (int i = 3; i < tokens.length; i++) {
            if (findUser(tokens[i]) == null) {
                return new Result(false, String.format("%s not found", tokens[i]));
            }
        }
        for (Game g : App.games) {
            for (int i = 3; i < tokens.length; i++) {
                if (g.getPlayers().contains(findUser(tokens[i]))) {
                    return new Result(false, String.format("%s is already in another game", tokens[i]));
                }
            }
        }

        for (int i = 3; i < tokens.length; i++) {
            Player p = new Player(findUser(tokens[i]).getUsername(), findUser(tokens[i]).getNickname(), findUser(tokens[i]));
            players.add(p);
        }


        return new Result(true, "players added successfully , now we wnt to choose maps");

    }

    public Result selectMapForCreateNewGame(int mapInput, Player player, int sx, int sy) {
        if (mapInput > 4 || mapInput < 1) {
            return new Result(false, "please select between available maps");
        }

        switch (mapInput) {
            case 1:
                player.setFarm(FarmFactory.makeFarm1(sx, sy));
                break;
            case 2:
                player.setFarm(FarmFactory.makeFarm2(sx, sy));
                break;
            case 3:
                player.setFarm(FarmFactory.makeFarm3(sx, sy));
                break;
            case 4:
                player.setFarm(FarmFactory.makeFarm4(sx, sy));
                break;

        }


        return new Result(true, "map selected successfully");
    }

    public Result createNewGame(ArrayList<Player> players) {
        ArrayList<Farm> maps = new ArrayList<>();
        for (Player p : players) {
            maps.add(p.getFarm());

        }

        Map m = new Map(maps);
        m.buildMap(players);
        Game x = new Game(players, maps, App.getLoggedInUser(), m);

        for (Player p : players) {
            p.getPosition().setX(p.getFarm().getRectangle().x);
            p.getPosition().setY(p.getFarm().getRectangle().y);
        }
        App.games.add(x);
        App.setGame(x);
        App.getGame().setCurrentPlayingPlayer(players.getFirst());
        return new Result(true, "new game created successfully");
    }

    public Result loadGame() {
        return new Result(true, "load game successful");
    }

    public Result exitGame() {
        if(!App.getGame().getCurrentPlayingPlayer().getUsername().equals(App.getGame().getGameCreator().getUsername())){
            return new Result (false,"only game creators can exit game");
        }
        App.setGame(null);
        return new Result(true, "exit game successful");
    }

    public Result deleteGame() {
        return new Result(true, "delete game successful");
    }

    public Result nextTurn() {
        Result result = App.getGame().nextPlayerTurn();
        return new Result(true, "next turn successful\n" + result);
    }

    public Result buildGreenhouse() {
        HashMap<Ingredient, Integer> inventory = App.getGame().getCurrentPlayingPlayer()
                .getBackpack().getIngredientQuantity();

        Ingredient coin = null, wood = null;

        for (Ingredient ing : inventory.keySet()) {
            if (ing instanceof Coin) coin = ing;
            if (ing instanceof Wood) wood = ing;
        }

        if (coin == null || inventory.get(coin) < 1000) {
            return new Result(false, "you don't have enough money");
        }

        if (wood == null || inventory.get(wood) < 500) {
            return new Result(false, "you don't have enough woods");
        }

        inventory.put(coin, inventory.get(coin) - 1000);
        inventory.put(wood, inventory.get(wood) - 500);

        App.getGame().getCurrentPlayingPlayer().getFarm().getGreenHouse().setBroken(false);
        return new Result(true, "greenhouse built successfully");
    }


    public Result findPath(int endX, int endY, List<Position> positions) {


        if (App.getGame().getMap().findTile(endX, endY).getPlaceable() != null) {
            return new Result(false, "Wrong place , Wrong Time");
        }
        for (Player p : App.getGame().getPlayers()) {
            if (App.getGame().getCurrentPlayingPlayer().equals(p)) {
                continue;
            }
            if (p.getFarm().getRectangle().contains(endX, endY)) {
                if(App.getGame().getCurrentPlayingPlayer().canWalkToOtherFarm(p.getFarm())){
                    continue;
                }
                return new Result(false, "this position is not in your farm");
            }
        }

        List<Position> path = findShortestPath(
                App.getGame().getMap(),
                App.getGame().getCurrentPlayingPlayer().getPosition().getX(),
                App.getGame().getCurrentPlayingPlayer().getPosition().getY(),
                endX, endY
        );

        if (path == null) {
            return new Result(false, "No path found");
        }

        positions.clear();
        positions.addAll(path);

        int energy = calculateEnergyBasedOnComplexFormula(positions);
        return new Result(true, String.format("energy needed: %d", energy));
    }

    public Result walk(int endX, int endY, List<Position> positions) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        int energy = calculateEnergyBasedOnComplexFormula(positions);

        Result energyConsumptionResult = player.consumeEnergy(energy);
        if (!energyConsumptionResult.getSuccessful())
            return energyConsumptionResult;

        player.getPosition().setX(endX);
        player.getPosition().setY(endY);

        return new Result(true, String.format("you have teleported successfully to " + endX + " " + endY));
    }

    public Result helpReadingMap() {
        String sb =
                "T : tree\n" +
                "S : stone\n" +
                "W : wood\n" +
                "C : cottage\n" +
                "G : greenhouse\n" +
                "* : crop\n" +
                "Q : quarry\n" +
                "L : lake\n" +
                "d : door\n" +
                "+ : wall\n";
        return new Result(true, sb);

    }

    public Result inventoryShow() {
        StringBuilder sb = new StringBuilder();
        for (java.util.Map.Entry<Ingredient, Integer> entry : App.getGame().getCurrentPlayingPlayer().getBackpack()
                .getIngredientQuantity().entrySet()) {
            sb.append(String.format("%s -> quantity : %d", entry.getKey(), entry.getValue()));
            sb.append("\n");

        }
        return new Result(true, sb.toString());

    }

    public Result cheatAddDollars(Matcher matcher) {
        int value = Integer.parseInt((matcher.group("amount")));
        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(), value);
        return new Result(true, value + "g added");
    }

    public Result inventoryTrash(String name, int number, boolean hasNumber) {
        for (java.util.Map.Entry<Ingredient, Integer> entry : App.getGame().getCurrentPlayingPlayer().getBackpack()
                .getIngredientQuantity().entrySet()) {
            if (entry.getKey().getClass().getSimpleName().equals(name)) {
                if (hasNumber) {
                    App.getGame().getCurrentPlayingPlayer().getBackpack().inventoryTrash(entry.getKey(), number);
                    return new Result(true, String.format("%s removed from backpack", entry.getKey()
                            .getClass().getSimpleName()));
                } else {
                    int quantity = entry.getValue();
                    App.getGame().getCurrentPlayingPlayer().getBackpack().inventoryTrash(entry.getKey(), quantity);
                    return new Result(true, String.format("%s removed from backpack", entry.getKey()
                            .getClass().getSimpleName()));
                }

            }
        }
        return new Result(true, String.format("%s not found", name));
    }

    public Result walkPlayersToTheirHome() {
        StringBuilder output = new StringBuilder();

        for (Player p : App.getGame().getPlayers()) {
            if(p.isFaintedToday()) break;
            int cottageX = p.getFarm().getCottage().getBounds().x;
            int cottageY = p.getFarm().getCottage().getBounds().y;


            List<Position> path = findShortestPath(
                    App.getGame().getMap(),
                    App.getGame().getCurrentPlayingPlayer().getPosition().getX(),
                    App.getGame().getCurrentPlayingPlayer().getPosition().getY(),
                    cottageX, cottageY
            );

            if (path == null) {
                return new Result(false, "No path found for Player : " + p.getUsername());
            }

            int energy = calculateEnergyBasedOnComplexFormula(path);
            if (p.getEnergy() >= energy) {
                p.getPosition().setX(cottageX);
                p.getPosition().setY(cottageY);
                p.consumeEnergy(energy);
                output.append(String.format("Player <%s> walked to its home.\n", p.getUsername()));
            }
            else {
                p.setFaintedToday(true);
                output.append(String.format("Player <%s> fainted while walking to home!\n", p.getUsername()));
            }
        }
        return new Result(true, output.toString());


    }

    public Result useGreenHouseForWatering() {
        Player player = App.getGame().getCurrentPlayingPlayer();
        if (player.getFarm().getGreenHouse().isBroken()) {
            return new Result(false, "your green house is broken");
        }
        player.getFarm().getGreenHouse().watering();
        return new Result(true, "your plants in your green house are watering");
    }

    public Result useGreenHouseForHarvesting() {
        Player player = App.getGame().getCurrentPlayingPlayer();
        if (player.getFarm().getGreenHouse().isBroken()) {
            return new Result(false, "your green house is broken");
        }
        player.getFarm().getGreenHouse().harvestGrowable();
        return new Result(true, "your plants in your green house are harvesting");
    }

    public Result storeMenu() {

        Map gameMap = App.getGame().getMap();

        if (gameMap.isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), gameMap.getNpcVillage().getBlacksmith())) {

            if (gameMap.getNpcVillage().getBlacksmith().isOpen()) {

                App.setMenu(Menus.BlackSmithMenu);
                return new Result(true, "Now you are in the blacksmith");

            } else {

                return new Result(false, "Blacksmith is closed");

            }


        } else if (gameMap.isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), gameMap.getNpcVillage().getCarpenterShop())) {

            if (gameMap.getNpcVillage().getCarpenterShop().isOpen()) {

                App.setMenu(Menus.CarpenterShopMenu);
                return new Result(true, "Now you are in the carpenterShop");

            } else {

                return new Result(false, "carpenterShop is closed");

            }

        } else if (gameMap.isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), gameMap.getNpcVillage().getMarnieRanch())) {

            if (gameMap.getNpcVillage().getMarnieRanch().isOpen()) {

                App.setMenu(Menus.MarnieRanchMenu);
                return new Result(true, "Now you are in the Marnie's Ranch");

            } else {

                return new Result(false, "MarnieRanch is closed");

            }

        } else if (gameMap.isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), gameMap.getNpcVillage().getJojaMart())) {

            if (gameMap.getNpcVillage().getJojaMart().isOpen()) {

                App.setMenu(Menus.JojaMartMenu);
                return new Result(true, "Now you are in the Joja Mart");

            } else {

                return new Result(false, "Joja Mart is closed");

            }

        } else if (gameMap.isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), gameMap.getNpcVillage().getPierreGeneralStore())) {

            if (gameMap.getNpcVillage().getPierreGeneralStore().isOpen()) {

                App.setMenu(Menus.PierreGeneralStoreMenu);
                return new Result(true, "Now you are in the Pierre General Store");

            } else {

                return new Result(false, "Pierre General Store is closed");

            }


        } else if (gameMap.isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), gameMap.getNpcVillage().getFishShop())) {

            if (gameMap.getNpcVillage().getFishShop().isOpen()) {

                App.setMenu(Menus.FishShopMenu);
                return new Result(true, "Now you are in the Fish Shop");

            } else {

                return new Result(false, "Fish Shop is closed");

            }


        } else if (gameMap.isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), gameMap.getNpcVillage().getStardopSaloon())) {

            if (gameMap.getNpcVillage().getStardopSaloon().isOpen()) {

                App.setMenu(Menus.StardopSaloonMenu);
                return new Result(true, "Now you are in the Stardop Saloon");

            } else {

                return new Result(false, "StardopSaloon is closed");

            }


        } else {

            return new Result(false, "you must be near a store");

        }
    }
    public Result sellProduct(Matcher  matcher) {

        int amount = 1;
        if (matcher.group("amount") != null) {
            amount = Integer.parseInt(matcher.group("amount"));
        }

        ShippingBin temp = null;

        for (ShippingBin bin : App.getGame().getMap().getShippingBins()) {
            if (App.getGame().getMap().isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), bin)) {
                temp = bin;
                break;
            }
        }

        if (temp == null) {
            return new Result(false, "you must be near a shipping bin");
        }

        if (!Sellable.isSellable(matcher.group("productName"))) {
            return new Result(false, "you can't sell this product");
        }

        if (Sellable.getSellableByName(matcher.group("productName")) == null) {
            return new Result(false, "Not enough stock");
        }

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault((Ingredient) Sellable.getSellableByName(matcher.group("productName")), 0) < amount ) {
            return new Result(false, "Not enough stock");
        }

        int price = amount * Sellable.getSellableByName(matcher.group("productName")).getSellPrice();

        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients((Ingredient) Sellable.getSellableByName(matcher.group("productName")), amount);
        temp.increaseRevenue(App.getGame().getCurrentPlayingPlayer(),price);

        return new Result(true, "you have sold this product successfully");
    }

    public Result startTrade() {

        App.setMenu(Menus.TradeMenu);
        StringBuilder message = new StringBuilder("Now you are in the Trade Menu\nAvailable players: ");

        for (Player player : App.getGame().getPlayers()) {
            message.append("\n").append(player.getUsername());
        }

        message.append("\nNew requests:");

        for (Trade trade : App.getGame().getTrades()) {
            if (trade.getBuyer().equals(App.getGame().getCurrentPlayingPlayer()) && !trade.isViewed()) {
                trade.setViewed(true);
                message.append("\n").append(trade);
            }
        }

        return new Result(true, message.toString());
    }

    public Result printMapAll(){
        StringBuilder sb = new StringBuilder();
        Map map = App.getGame().getMap();
        for (int y = 0; y < 200; y++) {
            for (int x = 0; x < 250; x++) {
                Tile tile = map.findTile(x, y);
                if (tile.equals(map.findTile(App.getGame().getCurrentPlayingPlayer().getPosition()))) {
                    sb.append('p');
                } else {
                    if(tile.getPlaceable()!=null){
                        sb.append(tile.getPlaceable().getBackground()).append(tile.getPlaceable().getColor())
                                .append(tile.getSymbol()).append(ColorPrinter.RESET);
                    }
                    else{
                        sb.append(ColorPrinter.BRIGHT_WHITE).append(tile.getSymbol()).append(ColorPrinter.RESET);
                    }
                }
            }
            sb.append("\n");
        }
        return new Result(true, sb.toString());
    }
    public Result printMap(int x , int y , int size){
        StringBuilder sb = new StringBuilder();
        Map map = App.getGame().getMap();
        for (int i = y; i< y+size; i++) {
            for (int j = x; j < x + size; j++) {
                Tile tile = map.findTile(j ,i);
                if (tile.equals(map.findTile(App.getGame().getCurrentPlayingPlayer().getPosition()))) {
                    sb.append('p');
                } else {
                    if(tile.getPlaceable()!=null){
                        sb.append(tile.getPlaceable().getBackground()).append(tile.getPlaceable().getColor())
                                .append(tile.getSymbol()).append(ColorPrinter.RESET);
                    }
                    else{
                        sb.append(ColorPrinter.BRIGHT_WHITE).append(tile.getSymbol()).append(ColorPrinter.RESET);
                    }
                }
            }
            sb.append("\n");
        }
        return new Result(true, sb.toString());

    }
}
