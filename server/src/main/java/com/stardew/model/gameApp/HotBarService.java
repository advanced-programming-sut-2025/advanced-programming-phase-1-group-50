package com.stardew.model.gameApp;

import com.stardew.controller.InventoryController;
import com.stardew.model.InventoryItemDTO;
import com.stardew.model.mapInfo.InventoryItem;
import com.stardew.model.userInfo.Coin;
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
        for (ClientConnectionThread connection : game.getConnections().keySet()) {
            Player player = game.getPlayer(connection);
            if (player == null) continue;
            InventoryController.getInstance().sendHotBarUpdate(player, connection);
        }

    }
}
