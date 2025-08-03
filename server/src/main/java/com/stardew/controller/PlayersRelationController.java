package com.stardew.controller;

import com.stardew.model.PlayersRelation.RelationWithPlayers;
import com.stardew.model.gameApp.Game;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.HashMap;
import java.util.Set;

public class PlayersRelationController {
    private static PlayersRelationController instance;

    private PlayersRelationController() {}

    public static synchronized PlayersRelationController getInstance() {
        if (instance == null) {
            instance = new PlayersRelationController();
        }
        return instance;
    }

    public void getRelations(Message message, Player player, ClientConnectionThread clientConnectionThread) {

        if (player == null || message == null) {
            return;
        }

        int id = message.getIntFromBody("id");
        Game game = GameSessionController.getInstance().getGame(id);
        HashMap<String, RelationWithPlayers> relations = new HashMap<>();

        for (Player p : game.getAllPlayers()) {

            if (!p.getUsername().equals(player.getUsername())) {

                for (Set<Player> key : game.getRelationsBetweenPlayers().relationNetwork.keySet()) {
                    if (key.contains(p) && key.contains(player)) {
                        RelationWithPlayers temp = game.getRelationsBetweenPlayers().relationNetwork.get(key);
                        relations.put(p.getUsername(),temp);
                        break;
                    }
                }
            }

        }

        HashMap<String , Object> body = new HashMap<>();
        body.put("relations", relations);
        Message response = new Message(body, MessageType.GET_BETWEEN_PLAYERS_RELATIONS_INFO);
        response.setRequestID(message.getRequestID());
        clientConnectionThread.sendMessage(response);
    }
}
