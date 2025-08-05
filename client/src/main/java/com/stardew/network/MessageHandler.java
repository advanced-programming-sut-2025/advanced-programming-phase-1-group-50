package com.stardew.network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.google.gson.reflect.TypeToken;
import com.stardew.Main;
import com.stardew.controller.GameStateController;
import com.stardew.model.LobbyDTO;
import com.stardew.view.GameScreenMenu;
import com.stardew.view.LobbyMenus.LobbyMenu;
import com.stardew.view.LobbyMenus.PreLobbyMenu;
import com.stardew.view.SelectFarmMenu;
import com.stardew.view.miniGame.MiniGameWindow;

import java.util.ArrayList;

public class MessageHandler {
    private static MessageHandler instance;
    private final GameStateController gameStateController;


    private MessageHandler() {
        gameStateController = GameStateController.getInstance();
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
                LobbyDTO lobbyDTO = message.getFromBody("lobbyDTO", LobbyDTO.class);
                Screen currentScreen = Main.getMain().getScreen();
                if(currentScreen instanceof LobbyMenu lobbyMenu) {
                    if(lobbyMenu.getLobby().id == id) {
                        Gdx.app.postRunnable(() -> {
                            lobbyMenu.updateLobbyInfo(lobbyDTO);
                        });
                    }
                }
                return true;
            }
            case SEND_ONLINE_USERS_RESULT -> {
                ArrayList<String> onlineUsers = message.getFromBody("onlineUsers", new TypeToken<ArrayList<String>>(){}.getType());
                if(Main.getMain().getScreen() instanceof PreLobbyMenu preLobbyMenu){
                    preLobbyMenu.updateOnlineUsers(onlineUsers);
                }
                return true;
            }
            case GO_TO_GAME_SCREEN -> {
                int id = message.getIntFromBody("id");
                Gdx.app.postRunnable(() -> {
                    SelectFarmMenu currentScreen = ((SelectFarmMenu) Main.getMain().getScreen());
                    currentScreen.startGameTransition(id);
                });
                return true;
            }
            case MAP_REQUEST_RESULT -> {
                gameStateController.handleRequestMap(message);
                return true;
            }
            case UPDATE_GAME_RESULT -> {
                gameStateController.handleUpdate(message);
                return true;
            }
            case UPDATE_TIME -> {
                gameStateController.handleUpdateTime(message);
                //ArtisanMachinesManager.getInstance().updateMachines();
                return true;
            }
            case UPDATE_HOT_BAR -> {
                gameStateController.updateHotBar(message);
                gameStateController.handleUpdateCoin(message);
                return true;
            }
            case SHOW_INVENTORY_RESULT -> {
                gameStateController.handleUpdateInventoryList(message);
                return true;
            }

            case UPDATE_REACTION -> {
                gameStateController.handleUpdateReaction(message);
                return true;
            }
            case UPDATE_ANIMALS_RESULT -> {
                gameStateController.handleUpdateAnimals(message);
                return true;
            }
            case START_MINI_GAME -> {
                Stage stage = ((GameScreenMenu) Main.getMain().getScreen()).getStage();
                stage.addActor(new MiniGameWindow(stage, message.getIntFromBody("miniGame_ID")));
                return true;
            }
            case MINI_GAME_REQUESTS -> {
                MiniGameWindow miniGame = MiniGameWindow.getInstance();
                if (miniGame != null) miniGame.handleMessages(message);
                return true;
            }

            default -> {
                return false;
            }
        }
    }
}
