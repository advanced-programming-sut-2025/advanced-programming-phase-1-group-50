package com.stardew.controller;

import com.stardew.model.ServerApp;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMenuController {
    private static MainMenuController instance;
    private MainMenuController() {

    }
    public static MainMenuController getInstance() {
        if (instance == null) {
            instance = new MainMenuController();
        }
        return instance;
    }

    public void handleLogin(Message message , ClientConnectionThread connectionThread) {
        connectionThread.setUser(null);
        HashMap<String , Object> body = new HashMap<>();
        Message m = new Message(body , MessageType.LOGOUT_RESPONSE);
        m.setRequestID(message.getRequestID());
        connectionThread.sendMessage(m);

        ArrayList<String> onlineUsers = ServerApp.getOnlineUsernames();

        HashMap<String, Object> body2 = new HashMap<>();
        body2.put("onlineUsers", onlineUsers);
        Message updateMsg = new Message(body2, MessageType.SEND_ONLINE_USERS_RESULT);

        synchronized (ServerApp.getClientConnectionThreads()) {
            for (ClientConnectionThread c : ServerApp.getClientConnectionThreads()) {
                c.sendMessage(updateMsg);
            }
        }


    }
}
