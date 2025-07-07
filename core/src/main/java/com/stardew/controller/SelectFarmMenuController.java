package com.stardew.controller;

import com.badlogic.gdx.Screen;
import com.stardew.Main;
import com.stardew.models.app.App;
import com.stardew.models.app.FarmFactory;
import com.stardew.models.app.Game;
import com.stardew.models.mapInfo.Farm;
import com.stardew.models.mapInfo.Map;
import com.stardew.models.userInfo.Player;
import com.stardew.view.GameMenu;
import com.stardew.view.GameScreenMenu;
import com.stardew.view.SelectFarmMenu;

import java.util.ArrayList;

public class SelectFarmMenuController {
    private SelectFarmMenu view;

    public void setView(SelectFarmMenu view) {
        this.view = view;
    }

    public void setPlayerFarm(Player player , String farmName , int sx , int sy) {

        switch (farmName){
            case "Farm 1" :
                player.setFarm(FarmFactory.makeFarm1(sx , sy));
                break;
            case "Farm 2" :
                player.setFarm(FarmFactory.makeFarm2(sx , sy));
                break;
            case "Farm 3" :
                player.setFarm(FarmFactory.makeFarm3(sx , sy));
                break;
            case "Farm 4" :
                player.setFarm(FarmFactory.makeFarm4(sx , sy));
                break;
        }
    }

    public void handleSelectFarm(ArrayList<Player> players) {
        String select1 = view.getSelectFarmPlayer1().getSelected();
        String select2 = view.getSelectFarmPlayer2().getSelected();
        String select3 = view.getSelectFarmPlayer3().getSelected();
        String select4 = view.getSelectFarmPlayer4().getSelected();
        ArrayList<String> selected = new ArrayList<>();
        selected.add(select1);
        selected.add(select2);
        selected.add(select3);
        selected.add(select4);

        int[] startXForMap = {0, 150, 0, 150};
        int[] startYForMap = {0, 0, 125, 125};

        for (int i = 0; i < players.size(); i++) {
            setPlayerFarm(players.get(i), selected.get(i) , startXForMap[i] , startYForMap[i]);
            if(players.get(i).getFarm() == null) System.out.println(players.get(i).getUsername() + "null");
        }

        ArrayList<Farm> farms = new ArrayList<>();
        for (Player p : players) {
            farms.add(p.getFarm());
            System.out.println(p.getUsername());
        }

        Map map = new Map(farms);
        map.buildMap(players);
        Game x = new Game(players, farms, App.getLoggedInUser(), map);
        for (Player p : players) {
            p.getPosition().setX(p.getFarm().getRectangle().x);
            p.getPosition().setY(p.getFarm().getRectangle().y);
        }
        App.games.add(x);
        App.setGame(x);
        App.getGame().setCurrentPlayingPlayer(players.getFirst());

        GameScreenMenu gameMenu = new GameScreenMenu();
        Screen currentScreen = Main.getMain().getScreen();
        Main.getMain().setScreen(gameMenu);
        currentScreen.dispose();






    }
}
