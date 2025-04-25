package models.app;

import models.userInfo.User;

import java.util.ArrayList;

public class App {
    public static ArrayList<User> users = new ArrayList<User>();
    public static ArrayList<Game> games = new ArrayList<Game>();
    public static Menus menu = Menus.LoginAndRegister;
    public static ArrayList<SecurityQuestion> securityQuestions = new ArrayList<>();
    public static User loggedInUser = null;
    public static Game currentGame = null;


    static{
        securityQuestions.add(new SecurityQuestion("product of 80 + 20 ?" , "100"));
        securityQuestions.add(new SecurityQuestion("product of 90 + 50 ?" , "140"));
        securityQuestions.add(new SecurityQuestion("product of 80 + 600 ?" , "680"));
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
    public static void setGame(Game game){
        App.currentGame = game;
    }
    public static Game getGame(){
        return currentGame;
    }
}
