package com.stardew.controller.NPCController;

import com.badlogic.gdx.Gdx;
import com.google.gson.reflect.TypeToken;
import com.stardew.model.NPC.NPCType;
import com.stardew.model.NPC.RelationWithNPCDTO;
import com.stardew.model.Result;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;


public class NPCController {

    public static void giftToNPC(int gameId, String productName, NPCType npc, Consumer<Result> callback) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("productName", productName);
            body.put("npc", npc);
            body.put("event", Event.GiftToNPC);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null && response.getType() == MessageType.GIFT_TO_NPC_RESULT) {
                Result result = response.getFromBody("result", Result.class);
                Gdx.app.postRunnable(() -> callback.accept(result));
            } else {
                Gdx.app.postRunnable(() -> callback.accept(new Result(false, "Server didn't respond")));
            }
        }).start();
    }

    public static void getRelationWithNPC(int gameId,NPCType npc, Consumer<RelationWithNPCDTO> callback) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("npc", npc);
            body.put("event", Event.GetRelationWithNPC);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null && response.getType() == MessageType.GET_RELATION_WITH_NPC_RESPONSE) {
               RelationWithNPCDTO relation = response.getFromBody("relation", RelationWithNPCDTO.class);
                Gdx.app.postRunnable(() -> callback.accept(relation));
            }
        }).start();
    }

    public static void getQuestsList(NPCType npc , Consumer<ArrayList<String>> callback) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("npc", npc);
            body.put("event", Event.GetNPCQuestsList);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);

            if (response != null && response.getType().equals(MessageType.GET_NPC_QUESTS_LIST_RESPONSE)) {
                Type type = new TypeToken<ArrayList<String>>() {}.getType();
                ArrayList<String> quests = response.getFromBody("quests", type);
                Gdx.app.postRunnable(() -> callback.accept(quests));
            }
        }).start();
    }

    public static void getNPCQuestsStatus(int gameId, NPCType npc , Consumer<ArrayList<Boolean>> callback) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("npc", npc);
            body.put("event", Event.GetNPCQuestsStatus);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null && response.getType() == MessageType.GET_NPC_QUESTS_STATUS_INFO) {
                Type type = new TypeToken<ArrayList<Boolean>>() {}.getType();
                ArrayList<Boolean> status = response.getFromBody("status", type);
                Gdx.app.postRunnable(() -> callback.accept(status));
            }
        }).start();
    }

    public static void doQuest(int gameId,NPCType npcType,int index, Consumer<Result> callback) {
        new Thread(() -> {
            if (index <= 0 || index >= 4) {
                Gdx.app.postRunnable(() -> callback.accept(new Result(false, "Invalid index")));
            }
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("npc", npcType);
            body.put("index", index);
            body.put("event",Event.DoQuest);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null && response.getType() == MessageType.DO_QUEST_RESULT) {
                Result result = response.getFromBody("result", Result.class);
                Gdx.app.postRunnable(() -> callback.accept(result));
            } else {
                Gdx.app.postRunnable(() -> callback.accept(new Result(false, "Server didn't respond")));
            }
        }).start();
    }

}

