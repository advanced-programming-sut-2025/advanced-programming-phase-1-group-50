package view.StoreMenus;

import controller.StoreControllers.JojaMartController;
import view.AppMenu;

import java.util.Scanner;

public class JojaMartMenu extends StoreMenu {

    public JojaMartMenu() {
        controller = new JojaMartController();
    }

}
