package com.stardew.network;

import com.stardew.controller.LoginAndRegisterController;
import com.stardew.controller.SelectSecurityQuestionController;

public class MessageHandler {
    private static MessageHandler instance;
    private final LoginAndRegisterController loginAndRegisterController;
    private final SelectSecurityQuestionController selectSecurityQuestionController;

    private MessageHandler() {
        loginAndRegisterController = new LoginAndRegisterController();
        selectSecurityQuestionController = new SelectSecurityQuestionController();
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
            default -> {
                return false;
            }
        }
    }
}
