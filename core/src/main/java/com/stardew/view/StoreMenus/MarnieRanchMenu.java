package com.stardew.view.StoreMenus;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.controller.StoreControllers.MarnieRanchController;
import com.stardew.view.AppMenu;

import java.util.Scanner;

public class MarnieRanchMenu extends StoreMenu {

    public MarnieRanchMenu() {
        controller = new MarnieRanchController();
    }

    @Override
    public Stage getStage() {
        return null;
    }
}
