package com.stardew.controller;


import com.stardew.model.InventoryItemDTO;
import com.stardew.model.ItemInventoryType;
import com.stardew.model.TextureID;
import com.stardew.model.Tools.Tool;
import com.stardew.model.gameApp.Game;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.InventoryItem;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.model.mapInfo.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class InventoryController {
    private static InventoryController instance;
    private InventoryController() {}

    public static InventoryController getInstance() {
        if (instance == null)
            instance = new InventoryController();
        return instance;
    }

    public void handleSendInventoryList(Player player , ClientConnectionThread connection , String requestID) {
        ArrayList<InventoryItem> in = player.getInventoryItems();
        ArrayList<InventoryItemDTO> out = new ArrayList<>();
        for (InventoryItem i : in) {
            InventoryItemDTO dto = i.toDTO();
            int amount;
            if(!dto.isTool()){
                amount = player.getQuantityOfIngredient(i);
                dto.setQuantity(amount);
            }
            out.add(dto);
        }
        HashMap<String , Object> body = new HashMap<>();
        body.put("inventory", out);
        body.put("username", player.getUsername());
        Message message = new Message(body , MessageType.SHOW_INVENTORY_RESULT);
        message.setRequestID(requestID);
        connection.sendMessage(message);
    }

    public void handleRemoveItem(Player player, Message message,  ClientConnectionThread connection , String requestID) {
        InventoryItemDTO itemDTO = message.getFromBody("itemDTO", InventoryItemDTO.class);
        if(itemDTO != null){
            if(itemDTO.isTool()){
                InventoryItem item = getInventoryItemByID(itemDTO , player.getInventoryItems());
                if(item != null){
                    Tool t = (Tool) item;
                    player.getBackpack().removeTool(t);
                }
            }
            else {
                InventoryItem item = getInventoryItemByID(itemDTO , player.getInventoryItems());
                if(item != null){
                    Ingredient i = (Ingredient) item;
                    player.getBackpack().removeIngredients(i , player.getQuantityOfIngredient(i));
                }
            }
        }
        handleSendInventoryList(player, connection , requestID);
        sendHotBarUpdate(player , connection);

    }

    public InventoryItem getInventoryItemByID(InventoryItemDTO inventoryItemDTO , ArrayList<InventoryItem> inventoryItems) {
        for(InventoryItem i : inventoryItems) {
            if(i.getId().equals(inventoryItemDTO.getId())){
                return i;
            }
        }
        return null;
    }

    public void sendHotBarUpdate(Player player, ClientConnectionThread connection) {
        InventoryItemDTO[] hotbar = new InventoryItemDTO[10];
        int index = 0;
        for (InventoryItem item : player.getHotBar()) {
            if (item != null) {
                InventoryItemDTO itemDTO2 = item.toDTO();
                System.out.println(itemDTO2);
                int amount;
                if (!itemDTO2.isTool()) {
                    amount = player.getQuantityOfIngredient(item);
                    itemDTO2.setQuantity(amount);
                }
                hotbar[index] = itemDTO2;
                index++;
            }

            HashMap<String, Object> body = new HashMap<>();
            body.put("hotBar", hotbar);
            Message m = new Message(body, MessageType.UPDATE_HOT_BAR);
            connection.sendMessage(m);
        }


    }

    public void handleSendSkillInfo(Player player , ClientConnectionThread connection , String requestID ) {
        int farmingLevel = player.getAbility().getFarmingLevel();
        int miningLevel = player.getAbility().getMiningLevel();
        int foragingLevel = player.getAbility().getForagingLevel();
        int fishingLevel = player.getAbility().getFishingLevel();

        int farmingRate = player.getAbility().getFarmingRate();
        int miningRate = player.getAbility().getMiningRate();
        int foragingRate = player.getAbility().getForagingRate();
        int fishingRate = player.getAbility().getFishingRate();


        HashMap<String , Object> body = new HashMap<>();
        body.put("farmingLevel", farmingLevel);
        body.put("miningLevel", miningLevel);
        body.put("foragingLevel", foragingLevel);
        body.put("farmingRate", farmingRate);
        body.put("miningRate", miningRate);
        body.put("foragingRate", foragingRate);
        body.put("fishingRate", fishingRate);
        body.put("fishingLevel", fishingLevel);
        Message m = new Message(body , MessageType.GET_SKILL_INFO_RESULT);
        m.setRequestID(requestID);
        connection.sendMessage(m);
    }

    public void handleSendRelationWithNPCInfo(Player player, ClientConnectionThread connection , String requestID ) {
        int relationWithAbigail = player.getRelationWithAbigail().getNumericalFriendShipLevel() / 200;
        int relationWithHarvey = player.getRelationWithHarvey().getNumericalFriendShipLevel() / 200;
        int relationWithLeah = player.getRelationWithLeah().getNumericalFriendShipLevel() / 200;
        int relationWithSebastian = player.getRelationWithSebastian().getNumericalFriendShipLevel() / 200;
        int relationWithRobin = player.getRelationWithRobin().getNumericalFriendShipLevel() / 200;


        HashMap<String , Object> body = new HashMap<>();
        body.put("relationWithAbigail", relationWithAbigail);
        body.put("relationWithHarvey", relationWithHarvey);
        body.put("relationWithLeah", relationWithLeah);
        body.put("relationWithSebastian", relationWithSebastian);
        body.put("relationWithRobin", relationWithRobin);
        Message m = new Message(body , MessageType.GET_RELATION_INFO_RESULT);
        m.setRequestID(requestID);
        connection.sendMessage(m);
    }

    public void handleSendMapInfo(ArrayList<Player> players, Game game , ClientConnectionThread connection , String requestID ) {
        Tile[][] tiles = game.getMap().getTiles();
        TextureID[][] textureIDS = new TextureID[250][200];
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                Tile tile = tiles[x][y];

                if(isPlayerInThisTile(players , tile )) {
                    textureIDS[x][y] = TextureID.skillsTextureRegion;
                }
                else {
                    if(tile.getPlaceable()!=null) {
                        if (tile.getPlaceable().getTexture() != null) {
                            textureIDS[x][y] = tile.getPlaceable().getTexture();
                        }
                    }
                }

            }
        }
        HashMap<String , Object> body = new HashMap<>();
        body.put("tiles" , textureIDS);
        Message m = new Message(body , MessageType.GET_MAP_INFO_RESULT);
        m.setRequestID(requestID);
        connection.sendMessage(m);
    }

    private boolean isPlayerInThisTile(ArrayList<Player> players , Tile tile) {
        for(Player p : players) {
            int x = (int) Math.floor(p.getPlayerPosition().getFirst());
            int y =(int) Math.floor(p.getPlayerPosition().getSecond());

            if(tile.getPosition().getX() == x && tile.getPosition().getY() == y) {
                return true;
            }
        }
        return false;
    }

    public void handleShuffleInventory(Player player, ClientConnectionThread connection , String requestID ) {
        player.shuffleInventoryItems();
        handleSendInventoryList(player, connection , requestID);
        sendHotBarUpdate(player , connection);
    }

}
