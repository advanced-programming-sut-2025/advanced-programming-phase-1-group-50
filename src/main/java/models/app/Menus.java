package models.app;

import view.*;
import view.StoreMenus.*;

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
    StardopSaloonMenu(new StardopSaloonMenu());

    private final AppMenu menu;
    Menus(AppMenu menu){
        this.menu = menu;
    }
    public void CheckCommand(Scanner scanner){
        this.menu.check(scanner);
    }

}