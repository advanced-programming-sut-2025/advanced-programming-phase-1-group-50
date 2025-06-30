package com.stardew.view.StoreMenus;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.controller.StoreControllers.FishShopController;
import com.stardew.view.AppMenu;

import java.util.Scanner;

public class FishShopMenu extends StoreMenu {

    public FishShopMenu() {
        controller = new FishShopController();
    }

    @Override
    public Stage getStage() {
        return null;
    }
}
