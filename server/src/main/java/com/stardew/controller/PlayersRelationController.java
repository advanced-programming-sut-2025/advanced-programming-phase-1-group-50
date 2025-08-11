package com.stardew.controller;

import com.google.gson.reflect.TypeToken;
import com.stardew.model.Bouquet;
import com.stardew.model.Notification.MarriageRequest;
import com.stardew.model.Notification.Notification;
import com.stardew.model.PlayersRelation.BetweenPlayersGift;
import com.stardew.model.PlayersRelation.FriendshipLevelsWithPlayers;
import com.stardew.model.PlayersRelation.RelationWithPlayers;
import com.stardew.model.Result;
import com.stardew.model.SellableDTO;
import com.stardew.model.gameApp.Game;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.stores.Sellable;
import com.stardew.model.userInfo.Gender;
import com.stardew.model.userInfo.Player;
import com.stardew.model.userInfo.RelationNetwork;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
                        relations.put(p.getUsername(), temp);
                        break;
                    }
                }
            }

        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("relations", relations);
        Message response = new Message(body, MessageType.GET_BETWEEN_PLAYERS_RELATIONS_INFO);
        response.setRequestID(message.getRequestID());
        clientConnectionThread.sendMessage(response);
    }

    public void getAllGifts(Message message, ClientConnectionThread clientConnectionThread) {
        if (message == null) {
            return;
        }

        int id = message.getIntFromBody("id");
        Game game = GameSessionController.getInstance().getGame(id);

        HashMap<String, Object> body = new HashMap<>();
        body.put("gifts", game.getGifts());
        Message response = new Message(body, MessageType.GET_BETWEEN_PLAYERS_GIFT_INFO);
        response.setRequestID(message.getRequestID());
        clientConnectionThread.sendMessage(response);
    }

    public void rateGift(Message message, ClientConnectionThread clientConnectionThread) {
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

    public void sendGiftToPlayer(Message message, Player sender, ClientConnectionThread clientConnectionThread) {
        if (message == null || sender == null) {
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
        BetweenPlayersGift tempGift = new BetweenPlayersGift(productName, sender.getUsername(), receiverUsername,
            game.getGiftIndex());
        game.addToGifts(tempGift);

        try {
            receiver.getBackpack().addIngredients((Ingredient) Sellable.getSellableByName(productName, sender),
                quantity);
            sender.getBackpack().removeIngredients((Ingredient) Sellable.getSellableByName(productName, sender),
                quantity);
        } catch (Exception e) {
            return;
        }

        ClientConnectionThread temp = null;

        for (ClientConnectionThread connectionThread : game.getConnections().keySet()) {
            if (connectionThread.getUser().getUsername().equals(receiverUsername)) {
                temp = connectionThread;
                break;
            }
        }

        if (temp == null) {
            return;
        }


        receiver.addNotification(new Notification("you have received a gift", sender.getUsername()), temp);

        if (tempRelation.isMarriage()) {
            sender.addEnergy(50);
            receiver.addEnergy(50);
        }

        Result result = new Result(true, "He/She received your gift with id " + game.getGiftIndex());

        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.SEND_GIFT_TO_PLAYER_RESULT);
        response.setRequestID(message.getRequestID());
        clientConnectionThread.sendMessage(response);
    }

    public void canHug(Message message, Player player, ClientConnectionThread clientConnectionThread) {
        if (message == null || player == null) {
            return;
        }

        int id = message.getIntFromBody("id");
        Game game = GameSessionController.getInstance().getGame(id);
        String otherPlayerUsername = message.getFromBody("otherPlayerUsername");
        Player otherPlayer = null;

        for (Player p : game.getAllPlayers()) {
            if (p.getUsername().equals(otherPlayerUsername)) {
                otherPlayer = p;
                break;
            }
        }

        if (otherPlayer == null) {
            return;
        }

        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(player);
        lookUpKey.add(otherPlayer);

        RelationWithPlayers tempRelation = game.getRelationsBetweenPlayers().relationNetwork.get(lookUpKey);

        if (tempRelation == null) {
            return;
        }

        Result result;

        if (!tempRelation.canHug()) {
            result = new Result(false, "Your friendship level must be at least two");
        } else {
            result = new Result(true, "");
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.CAN_HUG_RESULT);
        response.setRequestID(message.getRequestID());
        clientConnectionThread.sendMessage(response);
    }

    public void canGiveFlower(Message message, Player player, ClientConnectionThread clientConnectionThread) {
        if (message == null || player == null) {
            return;
        }

        int id = message.getIntFromBody("id");
        Game game = GameSessionController.getInstance().getGame(id);
        String otherPlayerUsername = message.getFromBody("otherPlayerUsername");
        Player otherPlayer = null;

        for (Player p : game.getAllPlayers()) {
            if (p.getUsername().equals(otherPlayerUsername)) {
                otherPlayer = p;
                break;
            }
        }

        if (otherPlayer == null) {
            return;
        }

        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(player);
        lookUpKey.add(otherPlayer);

        RelationWithPlayers tempRelation = game.getRelationsBetweenPlayers().relationNetwork.get(lookUpKey);

        if (tempRelation == null) {
            return;
        }

        Result result;

        if (player.getBackpack().getIngredientQuantity().getOrDefault(new Bouquet(), 0) == 0) {
            result = new Result(false, "You don't have any bouquet!");
        } else {
            if (!tempRelation.canGiveFlower()) {
                result = new Result(false, "you can't give flower at this friendship level");
            } else {
                result = new Result(true, "");
            }
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.CAN_GIVE_FLOWER_RESULT);
        response.setRequestID(message.getRequestID());
        clientConnectionThread.sendMessage(response);
    }

    public void canAskMarriage(Message message, Player player, ClientConnectionThread clientConnectionThread) {
        if (message == null || player == null) {
            return;
        }

        int id = message.getIntFromBody("id");
        Game game = GameSessionController.getInstance().getGame(id);
        String otherPlayerUsername = message.getFromBody("otherPlayerUsername");
        Player otherPlayer = null;

        for (Player p : game.getAllPlayers()) {
            if (p.getUsername().equals(otherPlayerUsername)) {
                otherPlayer = p;
                break;
            }
        }

        if (otherPlayer == null) {
            return;
        }

        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(player);
        lookUpKey.add(otherPlayer);

        RelationWithPlayers tempRelation = game.getRelationsBetweenPlayers().relationNetwork.get(lookUpKey);

        if (tempRelation == null) {
            return;
        }

        Result result = null;

        for (Player p : game.getAllPlayers()) {
            for (Notification n : p.getNotifications()) {
                if (n instanceof MarriageRequest) {
                    if (!n.isChecked() && n.getSender().equals(player.getUsername())) {
                        result = new Result(false, "Del ke nist, karvansarast!");
                    }
                }
            }
        }

        for (Notification notification : otherPlayer.getNotifications()) {
            if (notification instanceof MarriageRequest && !notification.isChecked()) {
                result = new Result(false, "Vaisa to saf");
                break;
            }
        }


        if (result == null) {
            if (player.getCurrentUser().getGender().equals(Gender.Female) || otherPlayer.getCurrentUser().getGender().equals(Gender.Male)) {
                result = new Result(false, "Gender conflict!");
            } else if (tempRelation.isMarriage()) {
                result = new Result(false, "She's your wife!!!");
            } else if (!tempRelation.canRequestMarriage()) {
                result = new Result(false, "You can't request marriage at this friendship level!");
            } else if (otherPlayer.isMarried()) {
                result = new Result(false, "She's married!");
            } else {
                result = new Result(true, "");
            }
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.CAN_ASK_MARRIAGE_RESULT);
        response.setRequestID(message.getRequestID());
        clientConnectionThread.sendMessage(response);
    }

    public void getForSaleProducts(Message message, Player player, ClientConnectionThread clientConnectionThread) {
        if (message == null || player == null) {
            return;
        }

        ArrayList<SellableDTO> products = new ArrayList<>();

        HashMap<Ingredient, Integer> ingredientQuantity = player.getBackpack().getIngredientQuantity();

        for (Ingredient ingredient : ingredientQuantity.keySet()) {
            if (Sellable.isSellable(ingredient.toString()) && ingredientQuantity.get(ingredient) > 0) {
                Sellable s = (Sellable) ingredient;
                products.add(new SellableDTO(ingredientQuantity.get(ingredient), Sellable.getNameInString(s),
                    s.getSellPrice()));
            }
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("products", products);
        Message response = new Message(body, MessageType.GET_FOR_SALE_PRODUCTS_INFO);
        response.setRequestID(message.getRequestID());
        clientConnectionThread.sendMessage(response);
    }

    public void getPlayers(Message message, Player player, ClientConnectionThread clientConnectionThread) {
        if (message == null || player == null) {
            return;
        }

        int id = message.getIntFromBody("id");
        Game game = GameSessionController.getInstance().getGame(id);
        ArrayList<String> playerNames = new ArrayList<>();

        for (Player p : game.getAllPlayers()) {
            if (!p.getUsername().equals(player.getUsername())) {
                playerNames.add(p.getUsername());
            }
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("players", playerNames);
        Message response = new Message(body, MessageType.GET_PLAYERS_RESPONSE);
        response.setRequestID(message.getRequestID());
        clientConnectionThread.sendMessage(response);
    }

    public void talkToPlayer(Message message, Player player, ClientConnectionThread clientConnectionThread) {
        if (message == null || player == null) {
            return;
        }

        int id = message.getIntFromBody("id");
        Game game = GameSessionController.getInstance().getGame(id);
        String text = message.getFromBody("text");
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> receivers = message.getFromBody("players", type);

        for (Player p : game.getAllPlayers()) {
            if (receivers.contains(p.getUsername())) {

                RelationNetwork tempNetwork = game.getRelationsBetweenPlayers();
                Set<Player> lookUpKey = new HashSet<>();
                lookUpKey.add(player);
                lookUpKey.add(p);

                RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);
                if (!tempRelation.HaveTalkedToday()) {
                    tempRelation.changeXp(20);
                    tempRelation.setHaveTalkedToday(true);
                }

                if (tempRelation.isMarriage()) {
                    player.addEnergy(50);
                    p.addEnergy(50);
                }

                tempNetwork.relationNetwork.put(lookUpKey, tempRelation);

                ClientConnectionThread temp = null;

                for (ClientConnectionThread connectionThread : game.getConnections().keySet()) {
                    if (connectionThread.getUser().getUsername().equals(p.getUsername())) {
                        temp = connectionThread;
                        break;
                    }
                }

                if (temp == null) {
                    return;
                }

                p.addNotification(new Notification(text, player.getUsername()), temp);
            }
        }

        Result result = new Result(true, "Notification sent.");
        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.TALK_TO_PLAYER_RESULT);
        response.setRequestID(message.getRequestID());
        clientConnectionThread.sendMessage(response);
    }

    public boolean checkForOpenInPersonFriendshipMenu(int indexTileX, int indexTileY, Game game, Player player,
                                                      ClientConnectionThread clientConnectionThread) {
        if (game == null || player == null) {
            return false;
        }

        String otherPlayer = null;

        for (Player p : game.getAllPlayers()) {

            if (p.getUsername().equals(player.getUsername())) {
                continue;
            }
            float deltaX = (p.getPlayerPosition().getFirst()) - indexTileX;
            float deltaY = (p.getPlayerPosition().getSecond()) - indexTileY;
            if ((-0.5 < deltaX && 0.8 > deltaX) && (-0.7 < deltaY && 0.7 > deltaY)) {
                otherPlayer = p.getUsername();
                break;
            }
        }

        if (otherPlayer == null) {
            return false;
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("otherPlayer", otherPlayer);
        Message message = new Message(body, MessageType.OPEN_IN_PERSON_FRIENDSHIP_MENU);
        clientConnectionThread.sendMessage(message);

        return true;
    }

    public void hugPlayer(Message message, Player player) {
        if (message == null || player == null) {
            return;
        }

        int id = message.getIntFromBody("id");
        String otherPlayerName = message.getFromBody("otherPlayerUsername");
        Game game = GameSessionController.getInstance().getGame(id);

        Player otherPlayer = null;

        for (Player p : game.getAllPlayers()) {
            if (p.getUsername().equals(otherPlayerName)) {
                otherPlayer = p;
                break;
            }
        }

        if (otherPlayer == null) {
            return;
        }


        RelationNetwork tempNetwork = game.getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(player);
        lookUpKey.add(otherPlayer);

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);
        if (!tempRelation.HaveHuggedToday()) {
            tempRelation.setHaveHuggedToday(true);
            tempRelation.changeXp(60);
        }

        if (tempRelation.isMarriage()) {
            player.addEnergy(50);
            otherPlayer.addEnergy(50);
        }

        HashMap<String, Object> body = new HashMap<>();
        Message response = new Message(body, MessageType.HUG_ANIMATION);

        for (ClientConnectionThread connectionThread : game.getConnections().keySet()) {
            if (connectionThread.getUser().getUsername().equals(player.getUsername()) ||
                connectionThread.getUser().getUsername().equals(otherPlayerName)) {
                connectionThread.sendMessage(response);
            }
        }

    }

    public void giveFlower(Message message, Player player) {
        if (message == null || player == null) {
            return;
        }

        int id = message.getIntFromBody("id");
        String otherPlayerName = message.getFromBody("otherPlayerUsername");
        Game game = GameSessionController.getInstance().getGame(id);

        Player otherPlayer = null;

        for (Player p : game.getAllPlayers()) {
            if (p.getUsername().equals(otherPlayerName)) {
                otherPlayer = p;
                break;
            }
        }

        if (otherPlayer == null) {
            return;
        }


        RelationNetwork tempNetwork = game.getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(player);
        lookUpKey.add(otherPlayer);

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);
        tempRelation.setGaveFlower();
        tempRelation.setHaveGaveFlowerToday(true);
        player.getBackpack().removeIngredients(new Bouquet(), 1);
        otherPlayer.getBackpack().addIngredients(new Bouquet(), 1);

        if (tempRelation.isMarriage()) {
            player.addEnergy(50);
            otherPlayer.addEnergy(50);
        }
        tempRelation.changeXp(0);

        HashMap<String, Object> body = new HashMap<>();
        Message response = new Message(body, MessageType.GIVE_FLOWER_ANIMATION);

        for (ClientConnectionThread connectionThread : game.getConnections().keySet()) {
            if (connectionThread.getUser().getUsername().equals(player.getUsername()) ||
                connectionThread.getUser().getUsername().equals(otherPlayerName)) {
                connectionThread.sendMessage(response);
            }
        }

    }

    public void requestMarriage(Message message, Player player, ClientConnectionThread clientConnectionThread) {
        if (message == null || player == null) {
            return;
        }

        int id = message.getIntFromBody("id");
        String otherPlayerName = message.getFromBody("otherPlayerUsername");
        Game game = GameSessionController.getInstance().getGame(id);

        Player otherPlayer = null;

        for (Player p : game.getAllPlayers()) {
            if (p.getUsername().equals(otherPlayerName)) {
                otherPlayer = p;
                break;
            }
        }

        if (otherPlayer == null) {
            return;
        }

        ClientConnectionThread temp = null;
        for (ClientConnectionThread connectionThread : game.getConnections().keySet()) {
            if (connectionThread.getUser().getUsername().equals(otherPlayerName)) {
                temp = connectionThread;
                break;
            }
        }

        if (temp == null) {
            return;
        }


        otherPlayer.addNotification(new MarriageRequest("You are my soulmate. Every day with you feels like a dream. " +
            "Will you stay by my side forever?",
            player.getUsername()), temp);

        HashMap<String, Object> body = new HashMap<>();
        Message response = new Message(body, MessageType.MARRIAGE_ANIMATION);

        clientConnectionThread.sendMessage(response);
        temp.sendMessage(response);

    }

    public void respondMarriage(Message message, Player player) {
        if (message == null || player == null) {
            return;
        }

        int id = message.getIntFromBody("id");
        Game game = GameSessionController.getInstance().getGame(id);
        boolean accepted = message.getFromBody("status");
        MarriageRequest request = message.getFromBody("marriageRequest", MarriageRequest.class);

        MarriageRequest temp = null;

        for (Notification notification : player.getNotifications()) {
            if (notification instanceof MarriageRequest) {
                if (notification.getSender().equals(request.getSender()) && notification.getMessage().equals(request.getMessage())) {
                    temp = (MarriageRequest) notification;
                    break;
                }
            }
        }

        if (temp == null) {
            return;
        }

        Player sender = null;
        for (Player p : game.getAllPlayers()) {
            if (p.getUsername().equals(request.getSender())) {
                sender = p;
                break;
            }
        }

        if (sender == null) {
            return;
        }

        temp.setChecked(true);

        RelationNetwork tempNetwork = game.getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(player);
        lookUpKey.add(sender);

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);
        MessageType type;

        if (accepted) {
            type = MessageType.ACCEPT_MARRIAGE_REQUEST_ANIMATION;
            player.setMarried(true);
            sender.setMarried(true);
            tempRelation.setMarriage();
            tempRelation.setFriendshipLevel(FriendshipLevelsWithPlayers.LevelFour);
        } else {
            type = MessageType.REJECT_MARRIAGE_REQUEST_ANIMATION;
            tempRelation.setFriendshipLevel(FriendshipLevelsWithPlayers.LevelZero);
            sender.setRemainingNumsAfterMarriageRequestDenied(7);
        }

        HashMap<String, Object> body = new HashMap<>();
        Message response = new Message(body, type);

        for (ClientConnectionThread connectionThread : game.getConnections().keySet()) {
            if (connectionThread.getUser().getUsername().equals(player.getUsername()) ||
                connectionThread.getUser().getUsername().equals(sender.getUsername())) {
                connectionThread.sendMessage(response);
            }
        }

    }


}
