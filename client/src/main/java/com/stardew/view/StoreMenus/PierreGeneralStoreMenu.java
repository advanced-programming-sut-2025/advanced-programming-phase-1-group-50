package com.stardew.view.StoreMenus;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.controller.StoreControllers.PierreGeneralStoreController;
import com.stardew.view.AppMenu;

import java.util.Scanner;

public class PierreGeneralStoreMenu extends StoreMenu {

    public PierreGeneralStoreMenu() {
        controller = new PierreGeneralStoreController();
    }

    @Override
    public Stage getStage() {
        return null;
    }
}
