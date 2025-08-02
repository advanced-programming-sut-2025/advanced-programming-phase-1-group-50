package com.stardew.controller;

import com.stardew.model.TextureID;
import com.stardew.model.gameApp.Game;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.HashMap;

public class ReactionController {
    private static ReactionController instance;
    private ReactionController() {

    }
    public static ReactionController getInstance() {
        if (instance == null) {
            instance = new ReactionController();

        }
        return instance;
    }

    public void handleReactionProcess(Message message , Game game) {
        TextureID emoji = message.getFromBody("emoji" , TextureID.class);
        String username = message.getFromBody("username");
        HashMap<String , Object> body = new HashMap<>();
        body.put("emoji", emoji);
        body.put("username", username);
        Message m = new Message(body , MessageType.UPDATE_REACTION);
        for(ClientConnectionThread cl : game.clientConnectionThreads()){
            cl.sendMessage(m);
        }
    }
}
