package com.stardew.view;

import java.util.Scanner;

import com.stardew.models.app.*;

public class AppView {
    public void run() {

        Scanner scanner = new Scanner(System.in);
        do {
            App.getMenu().CheckCommand(scanner);
        } while (App.getMenu() != Menus.ExitMenu);
    }
}
