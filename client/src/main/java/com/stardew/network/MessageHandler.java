package com.stardew.network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.google.gson.reflect.TypeToken;
import com.stardew.Main;
import com.stardew.model.LobbyDTO;
import com.stardew.model.Result;
import com.stardew.view.LobbyMenus.LobbyMenu;
import com.stardew.view.LobbyMenus.PreLobbyMenu;
import com.stardew.view.SelectFarmMenu;

import java.util.ArrayList;

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
            case LOBBY_PLAYERS_LIST_UPDATED -> {
                int id = message.getIntFromBody("lobbyID");
                ArrayList<String> playerNames = message.getFromBody("players", new TypeToken<ArrayList<String>>(){}.getType());
                Screen currentScreen = Main.getMain().getScreen();
                if(currentScreen instanceof LobbyMenu lobbyMenu) {
                    if(lobbyMenu.getLobby().id == id) {
                        Gdx.app.postRunnable(() -> {
                            lobbyMenu.updatePlayerList(playerNames);
                            lobbyMenu.setDestroyLobby(true);
                        });
                    }
                }
                return true;
            }
            case JOIN_LOBBY_RESULT -> {
                Result result = message.getFromBody("result", Result.class);
                LobbyDTO lobbyDTO = message.getFromBody("lobbyDTO", LobbyDTO.class);
                String username = message.getFromBody("username", String.class);
                if (result != null && result.getSuccessful()) {
                    Gdx.app.postRunnable(() -> {
                        Screen current = Main.getMain().getScreen();
                        Main.getMain().setScreen(new LobbyMenu(lobbyDTO ,username));
                        current.dispose();
                    });
                } else {
                    Gdx.app.postRunnable(() -> {
                        System.out.println(result.getMessage());
                    });
                }
                return true;
            }
            case LEAVE_LOBBY_RESULT, DESTROY_LOBBY_RESULT -> {
                Result result = message.getFromBody("result", Result.class);

                if(result != null && result.getSuccessful()) {
                    Gdx.app.postRunnable(() -> {
                        Screen current = Main.getMain().getScreen();
                        Main.getMain().setScreen(new PreLobbyMenu());
                        current.dispose();
                    });
                }
                return true;
            }

            default -> {
                return false;
            }
        }
    }
}
