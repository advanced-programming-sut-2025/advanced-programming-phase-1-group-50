package view;

import java.util.Scanner;
import java.util.regex.*;

import models.app.App;
import models.app.Menus;
public class MainMenu implements AppMenu {
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        input = input.trim();
        Matcher matcher;

    if (models.enums.MainMenuCommands.ShowCurrentMenu.getMatcher(input)!=null){
        System.out.println("main menu.");
    }
    else if(models.enums.MainMenuCommands.UserLogout.getMatcher(input)!=null){
        App.setLoggedInUser(null);
        System.out.println("user logged out successsfuly");
    }
    else if(models.enums.MainMenuCommands.EnterProfileMenu.getMatcher(input)!=null){
        App.setMenu(Menus.ProfileMenu);
        System.out.println("you are now in profile menu");
    }
    else if(models.enums.MainMenuCommands.EnterGameMenu.getMatcher(input)!=null){
        App.setMenu(Menus.GameMenu);
        System.out.println("you are now in game menu");
    }
    else{
        System.out.println("invalid command");
    }

    }
}
