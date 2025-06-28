package com.stardew.models.app;

import com.stardew.models.Placeable;
import com.stardew.models.date.Time;
import com.stardew.models.foraging.Tree;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.mapInfo.*;
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


    static {
        securityQuestions.add(new SecurityQuestion("what is your favorite color?", "default"));
        securityQuestions.add(new SecurityQuestion("what is your favorite animal?", "default"));
        securityQuestions.add(new SecurityQuestion("what  is your favorite football club?", "Inter Milan" +
                " , Forza Inter!"));

        securityQuestions.add(new SecurityQuestion("what is your favorite food?", "default"));
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
