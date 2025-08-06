package com.stardew.controller;

import com.badlogic.gdx.Gdx;
import com.google.gson.reflect.TypeToken;
import com.stardew.model.PlayersRelation.RelationWithPlayers;
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
              Gdx.app.postRunnable(() -> {
                  callback.accept(result);
              });
            }
        }).start();
    }
}
