package com.stardew.controller;

import com.badlogic.gdx.Screen;
import com.stardew.Main;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.app.Menus;
import com.stardew.view.MainMenu;
import com.stardew.view.LobbyMenus.PreLobbyMenu;
import com.stardew.view.ProfileMenu;

public class MainMenuController {
    private MainMenu menu;
    public Result shewCurrentMenu() {
        return new Result(true, "you are now in the main menu");
    }

    public Result logout() {
        App.setLoggedInUser(null);
        return new Result(true, "user logged out successfully");
    }

    public Result enterProfileMenu() {
        App.setMenu(Menus.ProfileMenu);
        return new Result(true, "you are now in the profile menu");
    }

    public Result enterGameMenu() {
        App.setMenu(Menus.GameMenu);
        return new Result(true, "you are now in the game menu");
    }

    public Result exitMainMenu() {
        App.setMenu(Menus.LoginAndRegister);
        return new Result(true, "you are now in the login menu");
    }

    public void setView(MainMenu view) {
        this.menu = view;
    }

    public void goToProfileMenu() {
        Screen currentScreen = Main.getMain().getScreen();
        ProfileMenu profileMenu = new ProfileMenu();
        Main.getMain().setScreen(profileMenu);
        currentScreen.dispose();
    }

    public void goToGameMenu(){

        Screen screen = Main.getMain().getScreen();
        PreLobbyMenu preLobbyMenu = new PreLobbyMenu();
        Main.getMain().setScreen(preLobbyMenu);
        screen.dispose();

    }




}
