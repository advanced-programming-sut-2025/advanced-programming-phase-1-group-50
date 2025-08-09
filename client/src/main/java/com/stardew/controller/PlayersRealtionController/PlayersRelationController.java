package com.stardew.controller.PlayersRealtionController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.model.PlayersRelation.BetweenPlayersGift;
import com.stardew.model.PlayersRelation.FriendshipLevelsWithPlayers;
import com.stardew.model.PlayersRelation.RelationWithPlayers;
import com.stardew.model.Result;
import com.stardew.models.ClientInfo.LoggedInUser;
import com.stardew.models.Notification.MarriageRequest;
import com.stardew.models.Notification.Notification;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.*;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.InPersonPlayersRelationsWindows.RespondMarriageWindow;
import com.stardew.view.PlayersRelationsWindows.FriendshipWindow;
import com.stardew.view.PlayersRelationsWindows.GiftHistoryWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class PlayersRelationController {
    public static void talkToPlayer(int gameId , ArrayList<String> players , String text, Consumer<Result> callback) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("players", players);
            body.put("text", text);
            body.put("event", Event.TalkToPlayer);
            Message message = new Message(body,MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null && response.getType().equals(MessageType.TALK_TO_PLAYER_RESULT)) {
                Result result = response.getFromBody("result", Result.class);
                Gdx.app.postRunnable(() -> callback.accept(result));
            } else {
                Gdx.app.postRunnable(() -> callback.accept(new Result(false,"Server didn't respond")));
            }
        }).start();
    }

    public static boolean canRateGift(BetweenPlayersGift gift) {
        return (gift.getReceiverUsername().equals(LoggedInUser.getUser().getUsername())) && (!gift.isRated());
    }

    public static void rateGift(int gameId, int giftId, int rate, GiftHistoryWindow giftHistoryWindow, FriendshipWindow friendshipWindow) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id",gameId);
            body.put("giftId",giftId);
            body.put("rate",rate);
            body.put("event" , Event.RateGift);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null && response.getType().equals(MessageType.RATE_GIFT_RESPONSE)) {
                    Gdx.app.postRunnable(() -> {
                        giftHistoryWindow.updateAllGifts();
                        friendshipWindow.updateRelations();
                    });
            }
        }).start();
    }

    public static void sendGiftToPlayer(int gameId, String productName, int quantity, String receiverUsername, Consumer<Result> callback) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("receiverUsername", receiverUsername);
            body.put("productName", productName);
            body.put("quantity", quantity);
            body.put("event", Event.SendGiftToPlayer);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null && response.getType().equals(MessageType.SEND_GIFT_TO_PLAYER_RESULT)) {
                Result result = response.getFromBody("result", Result.class);
                Gdx.app.postRunnable(() -> {
                    callback.accept(result);
                });
            }
        }).start();
    }

    public static void canHug(int gameId, String otherPlayerUsername, Consumer<Result> callback) {
        new Thread(() -> {
            if (otherPlayerUsername == null || LoggedInUser.getUser().getUsername().equals(otherPlayerUsername)) {
                callback.accept(new Result(false, "Invalid player"));
                return;
            }

            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("otherPlayerUsername", otherPlayerUsername);
            body.put("event",Event.CanHug);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);

            if (response != null && response.getType().equals(MessageType.CAN_HUG_RESULT)) {
                Result result = response.getFromBody("result", Result.class);
                callback.accept(result);
            } else {
                callback.accept(new Result(false, "Server did not respond"));
            }
        }).start();
    }

    public static void hug(String otherPlayerUsername) {
        //TODO
//
//        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
//        Set<Player> lookUpKey = new HashSet<>();
//        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
//        lookUpKey.add(otherPlayer);
//
//        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);
//
//        if (tempRelation.canHug()) {
//            if (!tempRelation.HaveHuggedToday()) {
//                tempRelation.setHaveHuggedToday(true);
//                tempRelation.changeXp(60);
//            }
//
//            if (tempRelation.isMarriage()) {
//                App.getGame().getCurrentPlayingPlayer().addEnergy(50);
//                otherPlayer.addEnergy(50);
//            }
//        }
    }

    public static void canGiveFlower(int gameId,String otherPlayerUsername, Consumer<Result> callback) {
        new Thread(() -> {
            if (otherPlayerUsername == null || LoggedInUser.getUser().getUsername().equals(otherPlayerUsername)) {
                callback.accept(new Result(false, "Invalid player"));
                return;
            }

            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("otherPlayerUsername", otherPlayerUsername);
            body.put("event",Event.CanGiveFlower);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);

            if (response != null && response.getType().equals(MessageType.CAN_GIVE_FLOWER_RESULT)) {
                Result result = response.getFromBody("result", Result.class);
                callback.accept(result);
            } else {
                callback.accept(new Result(false, "Server did not respond"));
            }
        }).start();
    }

    public static void giveFlower(String otherPlayerUsername) {
        //TODO
//
//        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
//        Set<Player> lookUpKey = new HashSet<>();
//        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
//        lookUpKey.add(otherPlayer);
//
//        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);
//
//        tempRelation.setGaveFlower();
//        tempRelation.setHaveGaveFlowerToday(true);
//        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(new Bouquet(), 1);
//        otherPlayer.getBackpack().addIngredients(new Bouquet(), 1);
//        if (tempRelation.isMarriage()) {
//            App.getGame().getCurrentPlayingPlayer().addEnergy(50);
//            otherPlayer.addEnergy(50);
//        }
//        tempRelation.changeXp(0);

    }

    public static void canAskMarriage(int gameId,String otherPlayerUsername, Consumer<Result> callback) {
        new Thread(() -> {
            if (otherPlayerUsername == null || LoggedInUser.getUser().getUsername().equals(otherPlayerUsername)) {
                callback.accept(new Result(false, "Invalid player"));
                return;
            }

            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("otherPlayerUsername", otherPlayerUsername);
            body.put("event",Event.CanAskMarriage);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);

            if (response != null && response.getType().equals(MessageType.CAN_ASK_MARRIAGE_RESULT)) {
                Result result = response.getFromBody("result", Result.class);
                callback.accept(result);
            } else {
                callback.accept(new Result(false, "Server did not respond"));
            }
        }).start();
    }

    public static void askMarriage(String otherPlayerUsername) {
        //TODO
        //otherPlayer.addNotification(new MarriageRequest("You are my soulmate. Every day with you feels like a dream. Will you stay by my side forever?", App.getGame().getCurrentPlayingPlayer()));
    }

    public static void checkForMarriageRequest(Stage stage) {
        MarriageRequest temp = null;

        for (Notification n : App.getGame().getCurrentPlayingPlayer().getNotifications()) {
            if (n instanceof MarriageRequest) {
                if (!n.isChecked()) {
                    temp = (MarriageRequest) n;
                    break;
                }
            }
        }

        if (temp == null) {
            return;
        }

        stage.addActor(new RespondMarriageWindow(stage, temp));
    }

    public static void respondMarriage(MarriageRequest temp,boolean accepted) {
        //TODO
//        for (Player player : App.getGame().getPlayers()) {
//            for (Notification notification : player.getNotifications()) {
//                if (notification instanceof MarriageRequest) {
//                    if (!notification.isChecked() && notification.getSender().equals(App.getGame().getCurrentPlayingPlayer())) {
//                        return new Result(false , "Del ke nist, karvansarast!");
//                    }
//                }
//            }
//        }
//
//        for (Notification notification : otherPlayer.getNotifications()) {
//            if (notification instanceof MarriageRequest && !notification.isChecked()) {
//                return new Result(false , "Vaisa to saf");
//            }
//        }
//
//        return new Result(true,"");
        temp.setChecked(true);

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
        lookUpKey.add(temp.getSender());

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);

        if (accepted) {

            App.getGame().getCurrentPlayingPlayer().setMarried(true);
            temp.getSender().setMarried(true);

            tempRelation.setMarriage();
            tempRelation.setFriendshipLevel(FriendshipLevelsWithPlayers.LevelFour);

        } else {

            tempRelation.setFriendshipLevel(FriendshipLevelsWithPlayers.LevelZero);
            temp.getSender().setRemainingNumsAfterMarriageRequestDenied(7);
        }
    }

}
