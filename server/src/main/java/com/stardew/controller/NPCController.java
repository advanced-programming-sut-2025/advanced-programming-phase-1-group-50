package com.stardew.controller;

import com.stardew.model.NPCs.NPC;
import com.stardew.model.NPCs.NPCType;
import com.stardew.model.NPCs.RelationWithNPC;
import com.stardew.model.Result;
import com.stardew.model.gameApp.Game;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.stores.Sellable;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

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

        if (npc.isFavoriteGift((Ingredient) Sellable.getSellableByName(productName,player))) {
            relationWithNPC.increaseNumericalFriendShipLevel(200);
        }

        if (relationWithNPC.isFirstTimeGiftToNPC()) {
            relationWithNPC.increaseNumericalFriendShipLevel(50);
            relationWithNPC.setFirstTimeGiftToNPC(false);
        }

        player.getBackpack().removeIngredients((Ingredient) Sellable.getSellableByName(productName,player),1);

        Result result = new Result(true, "Your gift has been received");
        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.GIFT_TO_NPC_RESULT);
        response.setRequestID(message.getRequestID());
        connectionThread.sendMessage(response);
    }
}
