package com.stardew.controller;


import com.stardew.model.InventoryItemDTO;
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


}
