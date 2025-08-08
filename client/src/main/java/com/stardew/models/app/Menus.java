package com.stardew.models.app;

import com.stardew.view.*;

public enum Menus {
    MainMenu(new MainMenu("dasas")),
    LoginAndRegister(new LoginAndRegisterMenu()),
    GameMenu(new GameMenu()),
    ExitMenu(new ExitMenu()),
    ProfileMenu((new ProfileMenu())),
    TradeMenu(new TradeMenu());

    private final AppMenu menu;

    Menus(AppMenu menu) {
        this.menu = menu;
    }

    public AppMenu getMenu() {
        return menu;
    }

}
