package com.stardew.model.gameApp;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.mapInfo.InventoryItem;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.ArrayList;
import java.util.HashMap;

public class HotBarService {
    private final Game game;
    public HotBarService(Game game) {
        this.game = game;
    }
    public void start(){
        sendHotBarUpdate();
    }

    public void sendHotBarUpdate(){
        for(Player player : game.getAllPlayers()){
            InventoryItemDTO[] hotbar = new InventoryItemDTO[10];
            int index = 0;
            for(InventoryItem item : player.getHotBar()){
                if(item != null) {
                    InventoryItemDTO itemDTO = item.toDTO();
                    System.out.println(itemDTO);
                    int amount;
                    if (!itemDTO.isTool()) {
                        amount = player.getQuantityOfIngredient(item);
                        itemDTO.setQuantity(amount);
                    }
                    hotbar[index] = itemDTO;
                    index++;
                }


            }

            ClientConnectionThread cl = game.getClientConnectionThreadByPlayer(player);
            if(cl != null){
                HashMap<String , Object> body = new HashMap<>();
                body.put("hotBar", hotbar);
                Message m = new Message(body , MessageType.UPDATE_HOT_BAR);
                cl.sendMessage(m);
            }
        }
    }
}
