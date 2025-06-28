package com.stardew.view;

import java.util.Scanner;

import com.stardew.controller.MainMenuController;

public class MainMenu implements AppMenu {
    private final MainMenuController controller = new MainMenuController();
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        input = input.trim();


    if (com.stardew.models.enums.MainMenuCommands.ShowCurrentMenu.getMatcher(input)!=null){
        System.out.println(controller.shewCurrentMenu());
    }
    else if(com.stardew.models.enums.MainMenuCommands.UserLogout.getMatcher(input)!=null){
        System.out.println(controller.logout());
    }
    else if(com.stardew.models.enums.MainMenuCommands.EnterProfileMenu.getMatcher(input)!=null){
        System.out.println(controller.enterProfileMenu());
    }
    else if(com.stardew.models.enums.MainMenuCommands.EnterGameMenu.getMatcher(input)!=null){
        System.out.println(controller.enterGameMenu());
    }
    else if(com.stardew.models.enums.MainMenuCommands.ExitMenu.getMatcher(input)!=null){
        System.out.println(controller.exitMainMenu());
    }
    else{
        System.out.println("invalid command");
    }

    }
}
