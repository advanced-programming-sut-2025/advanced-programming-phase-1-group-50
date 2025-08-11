package com.stardew.controller;

import com.stardew.model.NPC.NPCFriendshipLevel;
import com.stardew.model.NPC.RelationWithNPCDTO;
import com.stardew.model.NPCs.*;
import com.stardew.model.Result;
import com.stardew.model.gameApp.Game;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.stores.Sellable;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;


import java.util.ArrayList;
import java.util.HashMap;

public class NPCController {
    private static NPCController instance;

    private NPCController() {}

    public static synchronized NPCController getInstance() {
        if (instance == null) {
            instance = new NPCController();
        }
        return instance;
    }

    public void giftToNPC(Message message, Player player, ClientConnectionThread connectionThread) {
        if (message == null || player == null) {
            return;
        }

        int gameId = message.getIntFromBody("id");
        Game game = GameSessionController.getInstance().getGame(gameId);
        String productName = message.getFromBody("productName");
        NPCType npcType = message.getFromBody("npc", NPCType.class);
        NPC npc = game.getNPCByType(npcType);

        RelationWithNPC relationWithNPC = switch (npcType) {
            case NPCType.Abigail -> player.getRelationWithAbigail();
            case NPCType.Harvey -> player.getRelationWithHarvey();
            case NPCType.Leah -> player.getRelationWithLeah();
            case NPCType.Sebastian -> player.getRelationWithSebastian();
            case NPCType.Robin -> player.getRelationWithRobin();
        };


        if (relationWithNPC == null) {
            return;
        }

        if (npc.isFavoriteGift((Ingredient) Sellable.getSellableByName(productName, player))) {
            relationWithNPC.increaseNumericalFriendShipLevel(200);
        }

        if (relationWithNPC.isFirstTimeGiftToNPC()) {
            relationWithNPC.increaseNumericalFriendShipLevel(50);
            relationWithNPC.setFirstTimeGiftToNPC(false);
        }

        player.getBackpack().removeIngredients((Ingredient) Sellable.getSellableByName(productName, player), 1);

        Result result = new Result(true, "Your gift has been received");
        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.GIFT_TO_NPC_RESULT);
        response.setRequestID(message.getRequestID());
        connectionThread.sendMessage(response);
    }

    public void getRelationWithNPC(Message message, Player player, ClientConnectionThread connectionThread) {
        if (message == null || player == null) {
            return;
        }

        NPCType npcType = message.getFromBody("npc", NPCType.class);
        RelationWithNPCDTO relation = switch (npcType) {
            case NPCType.Abigail -> RelationWithNPC.getRelationWithNPCDTO(player.getRelationWithAbigail());
            case NPCType.Harvey -> RelationWithNPC.getRelationWithNPCDTO(player.getRelationWithHarvey());
            case NPCType.Robin -> RelationWithNPC.getRelationWithNPCDTO(player.getRelationWithRobin());
            case NPCType.Leah -> RelationWithNPC.getRelationWithNPCDTO(player.getRelationWithLeah());
            case NPCType.Sebastian -> RelationWithNPC.getRelationWithNPCDTO(player.getRelationWithSebastian());
        };

        HashMap<String, Object> body = new HashMap<>();
        body.put("relation", relation);
        Message response = new Message(body, MessageType.GET_RELATION_WITH_NPC_RESPONSE);
        response.setRequestID(message.getRequestID());
        connectionThread.sendMessage(response);

    }

    public void getQuestsStatus(Message message, ClientConnectionThread connectionThread) {
        if (message == null || connectionThread == null) {
            return;
        }

        int gameId = message.getIntFromBody("id");
        NPCType npcType = message.getFromBody("npc", NPCType.class);
        NPC npc = GameSessionController.getInstance().getGame(gameId).getNPCByType(npcType);

        ArrayList<Boolean> status = new ArrayList<>();
        status.add(npc.isFirstQuestDone());
        status.add(npc.isSecondQuestDone());
        status.add(npc.isThirdQuestDone());

        HashMap<String, Object> body = new HashMap<>();
        body.put("status", status);
        Message response = new Message(body, MessageType.GET_NPC_QUESTS_STATUS_INFO);
        response.setRequestID(message.getRequestID());
        connectionThread.sendMessage(response);

    }

    public void doQuest(Message message, Player player, ClientConnectionThread connectionThread) {
        if (message == null || player == null) {
            return;
        }

        int gameId = message.getIntFromBody("id");
        NPCType npcType = message.getFromBody("npc", NPCType.class);
        int index = message.getIntFromBody("index");
        NPC npc = GameSessionController.getInstance().getGame(gameId).getNPCByType(npcType);

        RelationWithNPC relation = switch (npcType) {
            case NPCType.Abigail -> player.getRelationWithAbigail();
            case NPCType.Harvey -> player.getRelationWithHarvey();
            case NPCType.Robin -> player.getRelationWithRobin();
            case NPCType.Leah -> player.getRelationWithLeah();
            case NPCType.Sebastian -> player.getRelationWithSebastian();
        };

        boolean isRewardTwice = relation.getNpcFriendshipLevel().equals(NPCFriendshipLevel.LevelTwo);

        Result result;
        if (index == 1) {
            result = npc.doFirstQuest(gameId, player, isRewardTwice);
        } else if (index == 2) {
            result = npc.doSecondQuest(gameId, player, isRewardTwice);
        } else {
            result = npc.doThirdQuest(gameId, player, isRewardTwice);
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.DO_QUEST_RESULT);
        response.setRequestID(message.getRequestID());
        connectionThread.sendMessage(response);

    }


    public void handleTalkWithNPC(Message message, ClientConnectionThread connectionThread, Player player, Game game) {
        NPCType type = message.getFromBody("npcType", NPCType.class);
        String npcName = type.toString();
        String input = message.getFromBody("input");
        PromptBuilder promptBuilder = new PromptBuilder(npcName, game.getTime().getSeason().name()
            , game.getTime().getWeather().getName()
            , getFriendShipLevelByNPC(type, player), getNPCJobByNPC(type)
            , getPlayerAction(player), String.format("date : %d , hour : %d", game.getTime().getDate(),
            game.getTime().getHour()), input);


        String prompt = promptBuilder.build();
        String response = "";
        try {
            response = NPCAi.getNPCResponse(prompt);
        } catch (Exception e) {
           response = "Error in getting NPC response from llm.";
        }

        if (!response.equals("Error in getting NPC response from llm.")) {
            RelationWithNPC temp = getRelationWithNPCByNPCType(type, player);

            if (temp != null && temp.isFirstTimeToSpeakWithNPC()) {
                temp.setFirstTimeToSpeakWithNPC(false);
                temp.increaseNumericalFriendShipLevel(20);
            }
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("result", response);
        Message responseMessage = new Message(body, MessageType.TALK_TO_NPC_RESULT);
        responseMessage.setRequestID(message.getRequestID());
        connectionThread.sendMessage(responseMessage);
    }

    private int getFriendShipLevelByNPC(NPCType type, Player player) {
        return switch (type) {
            case NPCType.Abigail -> player.getRelationWithAbigail().getNumericalFriendShipLevel() / 200;
            case NPCType.Harvey -> player.getRelationWithHarvey().getNumericalFriendShipLevel() / 200;
            case NPCType.Robin -> player.getRelationWithRobin().getNumericalFriendShipLevel() / 200;
            case NPCType.Leah -> player.getRelationWithLeah().getNumericalFriendShipLevel() / 200;
            case NPCType.Sebastian -> player.getRelationWithSebastian().getNumericalFriendShipLevel() / 200;
        };
    }

    private RelationWithNPC getRelationWithNPCByNPCType (NPCType type, Player player) {
        return switch (type) {
            case NPCType.Abigail -> player.getRelationWithAbigail();
            case NPCType.Harvey -> player.getRelationWithHarvey();
            case NPCType.Robin -> player.getRelationWithRobin();
            case NPCType.Leah -> player.getRelationWithLeah();
            case NPCType.Sebastian -> player.getRelationWithSebastian();
        };
    }

    private String getNPCJobByNPC(NPCType type) {
        return switch (type) {
            case NPCType.Sebastian -> "developer";
            case NPCType.Abigail -> "student";
            case NPCType.Harvey -> "doctor";
            case NPCType.Robin -> "Carpenter";
            case NPCType.Leah -> "artist";
        };
    }


    private String getPlayerAction(Player player) {
        int energy = player.getEnergy();
        StringBuilder builder = new StringBuilder();
        builder.append("player Actions : ");

        if (energy < 100) {
            builder.append("player is busy and tired ");
        }
        if (player.isFaintedToday()) {
            builder.append("player is fainted today , because of trying hard for his(her) farm ");
        }

        if (player.isMarried()) {
            builder.append("player is married , he(she) loves his(her) family ");
        }

        return builder.toString();


    }
}
