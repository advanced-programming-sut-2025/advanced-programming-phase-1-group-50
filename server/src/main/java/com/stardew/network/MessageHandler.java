package com.stardew.network;

import com.stardew.controller.ForgetPasswordController;
import com.stardew.controller.LobbyController;
import com.stardew.controller.LoginAndRegisterController;
import com.stardew.controller.SelectSecurityQuestionController;

public class MessageHandler {
    private static MessageHandler instance;
    private final LoginAndRegisterController loginAndRegisterController;
    private final SelectSecurityQuestionController selectSecurityQuestionController;
    private final ForgetPasswordController forgetPasswordController;
    private final LobbyController lobbyController;
    private final PreGameController preGameController;

    private MessageHandler() {
        loginAndRegisterController = new LoginAndRegisterController();
        selectSecurityQuestionController = new SelectSecurityQuestionController();
        forgetPasswordController = new ForgetPasswordController();
        lobbyController = LobbyController.getInstance();
        preGameController = PreGameController.getInstance();
        //TODO Other controllers
    }

    public synchronized static MessageHandler getInstance() {
        if (instance == null) {
            instance = new MessageHandler();
        }
        return instance;
    }

    public boolean handleMessage(Message message, ClientConnectionThread connection) {
        if (message == null) return false;

        switch (message.getType()) {
            case REGISTER_INITIAL -> {
                loginAndRegisterController.handleRegister(message, connection);
                return true;
            }
            case REGISTER_FINALLY -> {
                selectSecurityQuestionController.handleRegisterFinally(message, connection);
                return true;
            }
            case LOGIN -> {
                loginAndRegisterController.handleLogin(message, connection);
                return true;
            }
            case GET_SECURITY_QUESTION -> {
                forgetPasswordController.handleGetSecurityQuestion(message, connection);
                return true;
            }
            case FORGET_PASSWORD -> {
                forgetPasswordController.handleForgetPassword(message, connection);
                return true;
            }
            case CREATE_LOBBY -> {
                lobbyController.handleCreateLobby(message, connection);
                return true;
            }
            case SEND_LOBBIES -> {
                lobbyController.sendLobbies(connection);
                return true;
            }
            case START_GAME -> {
                lobbyController.handleStartGame(message, connection);
                return true;
            }
            case READY_STATUS -> {
                preGameController.handlePlayerReady(message, connection);
                return true;
            }
            case SEARCH_LOBBY ->{
                lobbyController.sendSearchLobby(message, connection);
                return true;
            }
            case JOIN_LOBBY ->{
                lobbyController.handleJoinLobby(message, connection);
                return true;
            }
            default -> {
                return false;
            }
        }
    }
}
