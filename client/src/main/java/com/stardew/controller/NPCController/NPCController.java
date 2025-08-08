package com.stardew.controller.NPCController;

import com.badlogic.gdx.Gdx;
import com.google.gson.reflect.TypeToken;
import com.stardew.model.NPC.NPCFriendshipLevel;
import com.stardew.model.NPC.RelationWithNPCDTO;
import com.stardew.model.Result;
import com.stardew.models.NPCs.*;
import com.stardew.models.app.App;
import com.stardew.models.mapInfo.NpcHome;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.regex.Matcher;

public class NPCController {

    public Result meetNPC(Matcher matcher) {

        NpcHome home;

        switch (matcher.group("NPCname")) {
            case "Abigail" -> {

                home = App.getGame().getMap().getNpcHomes().getFirst();

                if (App.getGame().getMap().isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), home)) {

                    NPCFriendshipLevel temp =
                        App.getGame().getCurrentPlayingPlayer().getRelationWithAbigail().getNpcFriendshipLevel();

                    if (App.getGame().getCurrentPlayingPlayer().getRelationWithAbigail().isFirstTimeToSpeakWithNPC()) {
                        App.getGame().getCurrentPlayingPlayer().getRelationWithAbigail().setFirstTimeToSpeakWithNPC(false);
                        App.getGame().getCurrentPlayingPlayer().getRelationWithAbigail().increaseNumericalFriendShipLevel(20);
                    }

                    return new Result(true, home.getNpc().getDialogue(temp));

                } else {

                    return new Result(false, "You must be near the NPCHome");

                }
            }
            case "Sebastian" -> {

                home = App.getGame().getMap().getNpcHomes().get(4);

                if (App.getGame().getMap().isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), home)) {

                    NPCFriendshipLevel temp =
                        App.getGame().getCurrentPlayingPlayer().getRelationWithSebastian().getNpcFriendshipLevel();

                    if (App.getGame().getCurrentPlayingPlayer().getRelationWithSebastian().isFirstTimeToSpeakWithNPC()) {
                        App.getGame().getCurrentPlayingPlayer().getRelationWithSebastian().setFirstTimeToSpeakWithNPC(false);
                        App.getGame().getCurrentPlayingPlayer().getRelationWithSebastian().increaseNumericalFriendShipLevel(20);
                    }

                    return new Result(true, home.getNpc().getDialogue(temp));

                } else {

                    return new Result(false, "You must be near the NPCHome");

                }
            }
            case "Leah" -> {

                home = App.getGame().getMap().getNpcHomes().get(3);

                if (App.getGame().getMap().isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), home)) {

                    NPCFriendshipLevel temp =
                        App.getGame().getCurrentPlayingPlayer().getRelationWithLeah().getNpcFriendshipLevel();

                    if (App.getGame().getCurrentPlayingPlayer().getRelationWithLeah().isFirstTimeToSpeakWithNPC()) {
                        App.getGame().getCurrentPlayingPlayer().getRelationWithLeah().setFirstTimeToSpeakWithNPC(false);
                        App.getGame().getCurrentPlayingPlayer().getRelationWithLeah().increaseNumericalFriendShipLevel(20);
                    }

                    return new Result(true, home.getNpc().getDialogue(temp));

                } else {

                    return new Result(false, "You must be near the NPCHome");

                }
            }
            case "Robin" -> {

                home = App.getGame().getMap().getNpcHomes().get(2);

                if (App.getGame().getMap().isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), home)) {

                    NPCFriendshipLevel temp =
                        App.getGame().getCurrentPlayingPlayer().getRelationWithRobin().getNpcFriendshipLevel();

                    if (App.getGame().getCurrentPlayingPlayer().getRelationWithRobin().isFirstTimeToSpeakWithNPC()) {
                        App.getGame().getCurrentPlayingPlayer().getRelationWithRobin().setFirstTimeToSpeakWithNPC(false);
                        App.getGame().getCurrentPlayingPlayer().getRelationWithRobin().increaseNumericalFriendShipLevel(20);
                    }

                    return new Result(true, home.getNpc().getDialogue(temp));

                } else {

                    return new Result(false, "You must be near the NPCHome");

                }
            }
            case "Harvey" -> {

                home = App.getGame().getMap().getNpcHomes().get(1);

                if (App.getGame().getMap().isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), home)) {

                    NPCFriendshipLevel temp =
                        App.getGame().getCurrentPlayingPlayer().getRelationWithHarvey().getNpcFriendshipLevel();

                    if (App.getGame().getCurrentPlayingPlayer().getRelationWithHarvey().isFirstTimeToSpeakWithNPC()) {
                        App.getGame().getCurrentPlayingPlayer().getRelationWithHarvey().setFirstTimeToSpeakWithNPC(false);
                        App.getGame().getCurrentPlayingPlayer().getRelationWithHarvey().increaseNumericalFriendShipLevel(20);
                    }

                    return new Result(true, home.getNpc().getDialogue(temp));

                } else {

                    return new Result(false, "You must be near the NPCHome");

                }
            }
        }

        return new Result(false, "Not such NPC");

    }

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

    public static ArrayList<String> getQuestsList(NPCType npc) {
        return switch (npc) {
            case NPCType.Abigail -> NPCQuests.AbigailQuests.getQuestsNames();
            case NPCType.Harvey -> NPCQuests.HarveyQuests.getQuestsNames();
            case NPCType.Robin -> NPCQuests.RobinQuests.getQuestsNames();
            case NPCType.Leah -> NPCQuests.LeahQuests.getQuestsNames();
            case NPCType.Sebastian -> NPCQuests.SebastianQuests.getQuestsNames();
        };
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

