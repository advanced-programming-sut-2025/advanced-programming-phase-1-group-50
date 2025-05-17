package view.StoreMenus;

import controller.StoreControllers.StoreController;
import models.enums.StoreMenuCommands;
import view.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public abstract class StoreMenu implements AppMenu {

    protected StoreController controller;

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();

        if (StoreMenuCommands.ShowAllProducts.getMatcher(input) != null) {

            System.out.println(controller.showAllProducts());

        } else if (StoreMenuCommands.ShowAvailableProducts.getMatcher(input) != null) {

            System.out.println(controller.showAvailableProducts());

        } else if (StoreMenuCommands.PurchaseProduct.getMatcher(input) != null) {

            Matcher matcher = StoreMenuCommands.PurchaseProduct.getMatcher(input);
            System.out.println(controller.ProcessPurchaseCommand(matcher));

        } else if (StoreMenuCommands.Exit.getMatcher(input) != null) {

            controller.exit();
            System.out.println("Returning to game menu...");

        } else if (StoreMenuCommands.ShowCurrentMenu.getMatcher(input) != null) {

            System.out.println(controller.ShowCurrentMenu());

        } else {

            System.out.println("Invalid command");

        }

    }
}
