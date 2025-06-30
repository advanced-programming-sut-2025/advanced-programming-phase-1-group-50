package com.stardew.view.StoreMenus;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.controller.StoreControllers.JojaMartController;
import com.stardew.view.AppMenu;

import java.util.Scanner;

public class JojaMartMenu extends StoreMenu {

    public JojaMartMenu() {
        controller = new JojaMartController();
    }

    @Override
    public Stage getStage() {
        return null;
    }
}
