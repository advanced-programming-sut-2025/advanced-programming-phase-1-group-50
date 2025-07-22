package com.stardew.models.app;

import com.stardew.view.*;
import com.stardew.view.StoreMenus.*;

import java.util.Scanner;

public enum Menus {
    MainMenu(new MainMenu()),
    LoginAndRegister(new LoginAndRegisterMenu()),
    GameMenu(new GameMenu()),
    ExitMenu(new ExitMenu()),
    ProfileMenu((new ProfileMenu())),
    BlackSmithMenu(new BlackSmithMenu()),
    CarpenterShopMenu(new CarpenterShopMenu()),
    FishShopMenu(new FishShopMenu()),
    JojaMartMenu(new JojaMartMenu()),
    MarnieRanchMenu(new MarnieRanchMenu()),
    PierreGeneralStoreMenu(new PierreGeneralStoreMenu()),
    StardopSaloonMenu(new StardopSaloonMenu()),
    TradeMenu(new TradeMenu());

    private final AppMenu menu;

    Menus(AppMenu menu) {
        this.menu = menu;
    }

    public AppMenu getMenu() {
        return menu;
    }

}
