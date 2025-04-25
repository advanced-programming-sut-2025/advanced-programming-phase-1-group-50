package models.app;

import view.*;

import java.util.Scanner;

public enum Menus {
    MainMenu(new MainMenu()),
    LoginAndRegister(new LoginAndRegisterMenu()),
    GameMenu(new GameMenu()),
    ExitMenu(new ExitMenu()),
    ProfileMenu((new ProfileMenu()));
    private final AppMenu menu;
    Menus(AppMenu menu){
        this.menu = menu;
    }
    public void CheckCommand(Scanner scanner){
        this.menu.check(scanner);
    }

}