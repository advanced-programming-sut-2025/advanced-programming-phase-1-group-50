package com.stardew.controller;

import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.app.Menus;

public class MainMenuController {
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
}
