package com.stardew.controller.PlayersRealtionController;

import com.badlogic.gdx.Gdx;
import com.stardew.model.Notification.MarriageRequest;
import com.stardew.model.PlayersRelation.BetweenPlayersGift;
import com.stardew.model.Result;
import com.stardew.models.ClientInfo.LoggedInUser;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.PlayersRelationsWindows.FriendshipWindow;
import com.stardew.view.PlayersRelationsWindows.GiftHistoryWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class PlayersRelationController {
    public static void talkToPlayer(int gameId , ArrayList<String> players , String text, boolean isPublic,Consumer<Result> callback) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("players", players);
            body.put("text", text);
            body.put("isPublic",isPublic);
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

    public static void hug(int gameId,String otherPlayerUsername) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("otherPlayerUsername", otherPlayerUsername);
            body.put("event",Event.Hug);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            NetworkManager.getConnection().sendMessage(message);
        }).start();
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

    public static void giveFlower(int gameId,String otherPlayerUsername) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("otherPlayerUsername", otherPlayerUsername);
            body.put("event",Event.GiveFlower);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            NetworkManager.getConnection().sendMessage(message);
        }).start();
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

    public static void askMarriage(int gameId,String otherPlayerUsername) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("otherPlayerUsername", otherPlayerUsername);
            body.put("event",Event.RequestMarriage);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            NetworkManager.getConnection().sendMessage(message);
        }).start();
    }

    public static void respondMarriage(int gameId,MarriageRequest temp, boolean accepted) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("status", accepted);
            body.put("marriageRequest", temp);
            body.put("event",Event.MarriageRequestResponse);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            NetworkManager.getConnection().sendMessage(message);
        }).start();
    }

}
