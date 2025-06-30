package com.stardew.view.StoreMenus;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.controller.StoreControllers.StardopSaloonController;
import com.stardew.view.AppMenu;

import java.util.Scanner;

public class StardopSaloonMenu extends StoreMenu {

    public StardopSaloonMenu() {
        controller = new StardopSaloonController();
    }

    @Override
    public Stage getStage() {
        return null;
    }
}
