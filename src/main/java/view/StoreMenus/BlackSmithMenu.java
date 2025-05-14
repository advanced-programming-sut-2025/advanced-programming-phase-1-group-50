package view.StoreMenus;

import controller.StoreControllers.BlackSmithController;
import models.enums.StoreMenuCommands;
import view.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class BlackSmithMenu extends StoreMenu {

    public BlackSmithMenu() {
        controller = new BlackSmithController();
    }
}
