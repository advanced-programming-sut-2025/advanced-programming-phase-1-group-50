package com.stardew.controller;

import com.stardew.model.Notification.Notification;
import com.stardew.model.PlayersRelation.BetweenPlayersGift;
import com.stardew.model.PlayersRelation.RelationWithPlayers;
import com.stardew.model.Result;
import com.stardew.model.gameApp.Game;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.stores.Sellable;
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

    public void sendGiftToPlayer(Message message,Player sender,ClientConnectionThread clientConnectionThread) {
        if (message == null) {
            return;
        }

        int id = message.getIntFromBody("id");
        Game game = GameSessionController.getInstance().getGame(id);
        String receiverUsername = message.getFromBody("receiverUsername");
        String productName = message.getFromBody("productName");
        int quantity = message.getIntFromBody("quantity");

        Player receiver = null;

        for (Player p : game.getAllPlayers()) {
            if (p.getUsername().equals(receiverUsername)) {
                receiver = p;
                break;
            }
        }

        if (receiver == null) {
            return;
        }

        RelationNetwork tempNetwork = game.getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(receiver);
        lookUpKey.add(sender);

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);

        game.addGiftsIndex();
        BetweenPlayersGift tempGift = new BetweenPlayersGift(productName,sender.getUsername(),receiverUsername, game.getGiftIndex());
        game.addToGifts(tempGift);

        try {
            receiver.getBackpack().addIngredients((Ingredient) Sellable.getSellableByName(productName,sender),quantity);
            sender.getBackpack().removeIngredients((Ingredient) Sellable.getSellableByName(productName,sender),quantity);
        } catch (Exception e) {
            return;
        }

        receiver.addNotification(new Notification("you have received a gift", sender.getUsername())); // TODO

        if (tempRelation.isMarriage()) {
            sender.addEnergy(50);
            receiver.addEnergy(50);
        }

        Result result = new Result(true, "He/She received your gift with id " + game.getGiftIndex());

        HashMap<String , Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.SEND_GIFT_TO_PLAYER_RESULT);
        response.setRequestID(message.getRequestID());
        clientConnectionThread.sendMessage(response);
    }
}
