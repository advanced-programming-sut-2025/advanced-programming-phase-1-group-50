package com.stardew.view.StoreMenus;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.controller.StoreControllers.BlackSmithController;
import com.stardew.models.enums.StoreMenuCommands;
import com.stardew.view.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class BlackSmithMenu extends StoreMenu {

    public BlackSmithMenu() {
        controller = new BlackSmithController();
    }

    @Override
    public Stage getStage() {
        return null;
    }
}
