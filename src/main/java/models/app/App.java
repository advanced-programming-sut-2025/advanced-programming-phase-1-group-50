package models.app;

import models.date.Time;
import models.mapInfo.*;
import models.userInfo.User;
import models.waterBodies.Lake;

import java.awt.*;
import java.util.ArrayList;

public class App {
    public static ArrayList<User> users = new ArrayList<User>();
    public static ArrayList<Game> games = new ArrayList<Game>();
    public static Menus menu = Menus.LoginAndRegister;
    public static ArrayList<SecurityQuestion> securityQuestions = new ArrayList<>();
    public static User loggedInUser = null;
    public static Game currentGame = null;
    public static ArrayList<Farm> farms = new ArrayList<>();

    static{
        securityQuestions.add(new SecurityQuestion("product of 80 + 20 ?" , "100"));
        securityQuestions.add(new SecurityQuestion("product of 90 + 50 ?" , "140"));
        securityQuestions.add(new SecurityQuestion("product of 80 + 600 ?" , "680"));
    }
    static {
        ArrayList<Lake> lakes = new ArrayList<Lake>();
        ArrayList<Quarry> quarries = new ArrayList<Quarry>();
        ArrayList<Tree> trees = new ArrayList<Tree>();
        ArrayList<Stone> stones = new ArrayList<>();
        Farm farm1 = new Farm(new Cottage( 1, 1  ,4 ,4 ) ,
                new GreenHouse(5 ,5 , 6 ,6 ) , lakes , quarries ,trees, stones ,
                new Rectangle(10 , 10 ,100 ,80) , 1);
        Farm farm2 = new Farm(new Cottage( 0, 0 , 4 , 4 ) ,
                new GreenHouse(5 , 5, 6 , 6) , lakes , quarries ,trees, stones ,
                new Rectangle(10 , 10 ,100 ,80) , 2);
        Farm farm3 = new Farm(new Cottage( 0, 0 , 4 , 4  ) ,
                new GreenHouse(5 ,5 ,6 ,6 ) , lakes , quarries ,trees, stones ,
                new Rectangle(10 , 10 ,100 ,80) , 3);
        Farm farm4 = new Farm(new Cottage( 0, 0  , 4 , 4 ) ,
                new GreenHouse(5 , 5 , 6 , 6) , lakes , quarries ,trees, stones ,
                new Rectangle(10 , 10 ,100 ,80) , 4);
        farms.add(farm1);
        farms.add(farm2);
        farms.add(farm3);
        farms.add(farm4);
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
