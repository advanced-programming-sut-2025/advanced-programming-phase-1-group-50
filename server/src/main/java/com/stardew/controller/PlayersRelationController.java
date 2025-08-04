package com.stardew.controller;

import com.stardew.model.PlayersRelation.BetweenPlayersGift;
import com.stardew.model.PlayersRelation.RelationWithPlayers;
import com.stardew.model.gameApp.Game;
import com.stardew.model.userInfo.Player;
import com.stardew.model.userInfo.RelationNetwork;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.HashMap;
import java.util.HashSet;
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

    public void getAllGifts(Message message,ClientConnectionThread clientConnectionThread) {
        if (message == null) {
            return;
        }

        int id = message.getIntFromBody("id");
        Game game = GameSessionController.getInstance().getGame(id);

        HashMap<String , Object> body = new HashMap<>();
        body.put("gifts", game.getGifts());
        Message response = new Message(body, MessageType.GET_BETWEEN_PLAYERS_GIFT_INFO);
        response.setRequestID(message.getRequestID());
        clientConnectionThread.sendMessage(response);
    }

    public void rateGift(Message message,ClientConnectionThread clientConnectionThread) {
        if (message == null) {
            return;
        }

            int id = message.getIntFromBody("id");
            Game game = GameSessionController.getInstance().getGame(id);

            int rate = message.getIntFromBody("rate");
            int giftId = message.getIntFromBody("giftId");

            BetweenPlayersGift gift = game.getGiftById(giftId);

            if (gift == null) {
                return;
            }

            gift.setRate(rate);
            gift.setRated();

            Player sender = null;
            Player receiver = null;

            for (Player p : game.getAllPlayers()) {
                if (p.getUsername().equals(gift.getSenderUsername())) {
                    sender = p;
                } else if (p.getUsername().equals(gift.getReceiverUsername())) {
                    receiver = p;
                }
            }

            if (sender == null || receiver == null) {
                return;
            }

        RelationNetwork tempNetwork = game.getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(sender);
        lookUpKey.add(receiver);
        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);
        if (!tempRelation.HaveGaveGiftToday()) {
            tempRelation.changeXp((rate - 3) * 30 + 15);
        }
        tempRelation.setHaveGaveGiftToday(true);
        tempNetwork.relationNetwork.put(lookUpKey, tempRelation);

        Message response = new Message(null, MessageType.RATE_GIFT_RESPONSE);
        response.setRequestID(message.getRequestID());
        clientConnectionThread.sendMessage(response);
    }
}
