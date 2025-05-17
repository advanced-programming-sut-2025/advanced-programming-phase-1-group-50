package view;

import controller.TradeController.TradeController;
import models.enums.TradeMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu implements AppMenu{

    private final TradeController controller = new TradeController();

    @Override
    public void check(Scanner scanner) {

        Matcher matcher = null;
        String input = scanner.nextLine();

        if ((matcher = TradeMenuCommands.trade.getMatcher(input))!= null) {
            System.out.println(controller.trade(matcher));
        } else if (TradeMenuCommands.tradeList.getMatcher(input) != null) {
            System.out.println(controller.tradeList());
        } else if ((matcher = TradeMenuCommands.tradeResponse.getMatcher(input)) != null) {
            System.out.println(controller.tradeResponse(matcher));
        } else if (TradeMenuCommands.tradeHistory.getMatcher(input) != null) {
            System.out.println(controller.tradeHistory());
        } else if (TradeMenuCommands.Exit.getMatcher(input) != null) {
            System.out.println(controller.exit());
        } else if (TradeMenuCommands.ShowCurrentMenu.getMatcher(input) != null) {
          System.out.println(controller.ShowCurrentMenu());
        } else {
            System.out.println("Invalid command");
        }

    }
}
