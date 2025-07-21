package com.stardew.view.StoreMenus;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.controller.StoreControllers.CarpenterShopController;

public class CarpenterShopMenu extends StoreMenu {

    public CarpenterShopMenu() {
        controller = new CarpenterShopController();
    }

    @Override
    public Stage getStage() {
        return null;
    }
}
