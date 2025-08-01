package com.stardew.controller;


import com.stardew.model.InventoryItemDTO;
import com.stardew.model.ItemInventoryType;
import com.stardew.model.Tools.Tool;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.InventoryItem;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

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

}
