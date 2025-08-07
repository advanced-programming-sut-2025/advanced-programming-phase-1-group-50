package com.stardew.controller;

import com.badlogic.gdx.Gdx;
import com.stardew.model.Result;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;

import java.util.HashMap;
import java.util.function.Consumer;

public class StoreController {
    public static void purchaseAnimal(int gameId,String productName, String animalName, Consumer<Result> callback) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("productName", productName);
            body.put("animalName", animalName);
            body.put("event", Event.PurchaseAnimal);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null && response.getType().equals(MessageType.PURCHASE_ANIMAL_RESULT)) {
              Result result = response.getFromBody("result", Result.class);
              Gdx.app.postRunnable(() -> callback.accept(result));
            } else {
                Gdx.app.postRunnable(() -> callback.accept(new Result(false,"Server didn't respond")));
            }
        }).start();
    }

    public static void canPurchaseBuilding(int gameId,String productName, Consumer<Result> callback) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("productName", productName);
            body.put("event", Event.CanPurchaseBuilding);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null && response.getType().equals(MessageType.CAN_PURCHASE_BUILDING_RESULT)) {
                Result result = response.getFromBody("result", Result.class);
                Gdx.app.postRunnable(() -> callback.accept(result));
            } else {
                Gdx.app.postRunnable(() -> callback.accept(new Result(false,"Server didn't respond")));
            }
        }).start();
    }

    public static void purchaseShippingBin(int gameId, int x, int y , Consumer<Result> callback) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("x", x);
            body.put("y", y);
            body.put("event", Event.PurchaseShippingBin);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null && response.getType() == MessageType.PURCHASE_SHIPPING_BIN_RESULT) {
                Result result = response.getFromBody("result", Result.class);
                Gdx.app.postRunnable(() -> callback.accept(result));
            } else {
                Gdx.app.postRunnable(() -> callback.accept(new Result(false,"Server didn't respond")));
            }
        }).start();
    }

    public static void purchaseBuilding(int gameId, String buildingName,int x, int y , Consumer<Result> callback) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("buildingName", buildingName);
            body.put("x", x);
            body.put("y", y);
            body.put("event", Event.PurchaseBuilding);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null && response.getType() == MessageType.PURCHASE_BUILDING_RESULT) {
                Result result = response.getFromBody("result", Result.class);
                Gdx.app.postRunnable(() -> callback.accept(result));
            } else {
                Gdx.app.postRunnable(() -> callback.accept(new Result(false,"Server didn't respond")));
            }
        }).start();
    }

    public static void purchaseProduct(int gameId, String productName , int quantity , String assistantName , Consumer<Result> callback) {
        new Thread(() -> {
            HashMap<String,Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("productName", productName);
            body.put("quantity", quantity);
            body.put("assistantName", assistantName);
            body.put("event",Event.PurchaseProduct);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null && response.getType() == MessageType.PURCHASE_PRODUCT_RESULT) {
                Result result = response.getFromBody("result", Result.class);
                Gdx.app.postRunnable(() -> callback.accept(result));
            } else {
                Gdx.app.postRunnable(() -> callback.accept(new Result(false,"Server didn't respond")));
            }

        }).start();
    }
}
