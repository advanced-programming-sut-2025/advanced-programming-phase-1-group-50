package com.stardew.models.app;

import com.stardew.models.Placeable;
import com.stardew.models.date.Time;
import com.stardew.models.foraging.Tree;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.mapInfo.*;
import com.stardew.models.userInfo.Avatar;
import com.stardew.models.userInfo.Gender;
import com.stardew.models.userInfo.Player;
import com.stardew.models.userInfo.User;
import com.stardew.models.waterBodies.Lake;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static ArrayList<User> users = new ArrayList<User>();
    public static ArrayList<Game> games = new ArrayList<Game>();
    public static Menus menu = Menus.LoginAndRegister;
    public static ArrayList<SecurityQuestion> securityQuestions = new ArrayList<>();
    public static User loggedInUser = null;
    public static Game currentGame = null;
    public static Game gameSample = null;


    static {
        securityQuestions.add(new SecurityQuestion("what is your favorite color?", "default"));
        securityQuestions.add(new SecurityQuestion("what is your favorite animal?", "default"));
        securityQuestions.add(new SecurityQuestion("what  is your favorite football club?", "Inter Milan" +
            " , Forza Inter!"));

        securityQuestions.add(new SecurityQuestion("what is your favorite food?", "default"));
    }

    static {
        User u = new User("ali" , "wwwwww" , "fkmd" , "emua@dfjk.com" , Gender.Male , securityQuestions.get(0)  , Avatar.abigail);
        users.add(u);
        User u2 = new User("mamad" ,"ksdfkklf" , "fkmd" , "emua@dfjk.com" , Gender.Male , securityQuestions.get(0) , Avatar.abigail );
        users.add(u2);
        User u3 = new User("hossein" ,"ksdfkklf" , "fkmd" , "emua@dfjk.com" , Gender.Male , securityQuestions.get(0) , Avatar.abigail );
        users.add(u3);
        User u4 = new User("sina" ,"ksdfkklf" , "fkmd" , "emua@dfjk.com" , Gender.Male , securityQuestions.get(0) , Avatar.abigail );
        users.add(u4);
        ArrayList<Player> players = new ArrayList<>();
        Player p = new Player(u.getUsername() , u.getNickname() , u);
        players.add(p);
        Player p2 = new Player(u2.getUsername() , u2.getNickname() , u2);
        players.add(p2);
        Player p3 = new Player(u3.getUsername() , u3.getNickname() , u3);
        players.add(p3);
        Player p4 = new Player(u4.getUsername() , u4.getNickname() , u4);
        players.add(p4);
        p.setFarm(FarmFactory.makeFarm1(0,0));
        p2.setFarm(FarmFactory.makeFarm2(150,0));
        p3.setFarm(FarmFactory.makeFarm3(0,125));
        p4.setFarm(FarmFactory.makeFarm4(150,125));
        ArrayList<Farm> farms = new ArrayList<>();
        farms.add(p.getFarm());
        farms.add(p2.getFarm());
        farms.add(p3.getFarm());
        farms.add(p4.getFarm());
        Map m = new Map(farms);
        m.buildMap(players);
        gameSample = new Game(players , farms , u , m);
        gameSample.setCurrentPlayingPlayer(p);

    }





    public static void setMenu(Menus menu) {
        App.menu = menu;
    }

    public static Menus getMenu() {
        return menu;
    }

    public static void setLoggedInUser(User loggedinUser) {
        App.loggedInUser = loggedinUser;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setGame(Game game) {
        App.currentGame = game;
    }

    public static Game getGame() {
        return currentGame;
    }

}
