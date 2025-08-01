package com.stardew.controller;

import com.badlogic.gdx.Screen;
import com.stardew.Main;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.app.Menus;
import com.stardew.view.GameMenu;
import com.stardew.view.LoginAndRegisterMenu;
import com.stardew.view.MainMenu;
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
        GameMenu gameMenu = new GameMenu();
        Main.getMain().setScreen(gameMenu);
        screen.dispose();

    }

    public void handleLogout(){
        logout();
        Screen screen = Main.getMain().getScreen();

        LoginAndRegisterMenu loginAndRegisterMenu = new LoginAndRegisterMenu(GamePictureManager.skin);
        Main.getMain().setScreen(loginAndRegisterMenu);
        screen.dispose();
    }




}
