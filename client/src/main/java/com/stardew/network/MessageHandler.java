package com.stardew.network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.stardew.Main;
import com.stardew.view.SelectFarmMenu;

public class MessageHandler {
    private static MessageHandler instance;


    private MessageHandler() {

    }

    public static MessageHandler getInstance() {
        if (instance == null) {
            instance = new MessageHandler();
        }
        return instance;
    }

    public boolean handleMessage(Message message) {
        if (message == null) return false;

        switch (message.getType()) {
            case GO_TO_SELECT_FARM_MENU -> {
                int id = message.getIntFromBody("lobbyID");
                Gdx.app.postRunnable(() -> {
                    Screen currentScreen = Main.getMain().getScreen();
                    Main.getMain().setScreen(new SelectFarmMenu(id));
                    currentScreen.dispose();
                });
                return true;
            }
            default -> {
                return false;
            }
        }
    }
}
