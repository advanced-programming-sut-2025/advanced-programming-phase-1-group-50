package view;

import java.awt.*;
import java.util.Scanner;
import models.app.*;
public class AppView {
    public void run() {

        Scanner scanner = new Scanner(System.in);
        do {
            App.getMenu().CheckCommand(scanner);
        } while (App.getMenu() != Menus.ExitMenu);
    }
}
