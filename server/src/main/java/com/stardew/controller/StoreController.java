package com.stardew.controller;

import com.stardew.model.Result;
import com.stardew.model.StoreGoodDTO;
import com.stardew.model.gameApp.Game;
import com.stardew.model.mapInfo.NpcVillage;
import com.stardew.model.stores.*;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreController {
    private static StoreController instance;

    private StoreController() {}

    public static synchronized StoreController getInstance() {
        if (instance == null) {
            instance = new StoreController();
        }
        return instance;
    }

    public void sendStoreGoodsInfo(Message message, ClientConnectionThread connectionThread) {
        if (message == null) {
            return;
        }

        int gameId = message.getIntFromBody("id");
        NpcVillage npcVillage = GameSessionController.getInstance().getGame(gameId).getMap().getNpcVillage();
        String assistantName = message.getFromBody("assistant");
        boolean isOnlyAvailable = message.getFromBody("onlyAvailable");
        Store store;

        switch (assistantName) {
            case "Clint":
                store = npcVillage.getBlacksmith();
                break;
            case "Robin":
                store = npcVillage.getCarpenterShop();
                break;
            case "Willy":
                store = npcVillage.getFishShop();
                break;
            case "Morris":
                store = npcVillage.getJojaMart();
                break;
            case "Marnie":
                store = npcVillage.getMarnieRanch();
                break;
            case "Pierre":
                store = npcVillage.getPierreGeneralStore();
                break;
            case "Gus":
                store = npcVillage.getStardopSaloon();
                break;
            default:
                return;
        }

        ArrayList<ShopItem> items;

        if (isOnlyAvailable) {
            items = store.getAvailableProducts();
        } else {
            items = store.getAllProducts();
        }

        ArrayList<StoreGoodDTO> goods = new ArrayList<>();
        for (ShopItem item : items) {
            boolean instanceOfMarnieRanchLiveStockItem = item instanceof MarnieRanchLiveStockItem;
            boolean instanceOfCarpenterShopFarmBuildingsItem = item instanceof CarpenterShopFarmBuildingsItem;
            goods.add(new StoreGoodDTO(item.getName(), item.getPrice(), item.getRemainingQuantity(),
                instanceOfMarnieRanchLiveStockItem, instanceOfCarpenterShopFarmBuildingsItem));
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("goods", goods);
        Message response = new Message(body, MessageType.GET_STORES_GOODS_INFO);
        response.setRequestID(message.getRequestID());
        connectionThread.sendMessage(response);
    }

    public void purchaseAnimal(Message message, Player player, ClientConnectionThread connectionThread) {
        if (message == null || player == null) {
            return;
        }

        int gameId = message.getIntFromBody("id");
        Game game = GameSessionController.getInstance().getGame(gameId);
        String productName = message.getFromBody("productName");
        String animalName = message.getFromBody("animalName");

        MarnieRanch shop = game.getMap().getNpcVillage().getMarnieRanch();
        Result result = shop.purchaseAnimal(game,player, productName, animalName);

        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.PURCHASE_ANIMAL_RESULT);
        response.setRequestID(message.getRequestID());
        connectionThread.sendMessage(response);
    }

    public void canPurchaseBuilding(Message message, Player player, ClientConnectionThread connectionThread) {
        if (message == null || player == null) {
            return;
        }

        int gameId = message.getIntFromBody("id");
        String productName = message.getFromBody("productName");
        CarpenterShop shop = GameSessionController.getInstance().getGame(gameId).getMap().getNpcVillage().getCarpenterShop();
        Result result = shop.canPurchaseBuilding(player,productName);

        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.CAN_PURCHASE_BUILDING_RESULT);
        response.setRequestID(message.getRequestID());
        connectionThread.sendMessage(response);

    }

    public void purchaseShippingBin(Message message, Player player, ClientConnectionThread connectionThread) {
        if (message == null || player == null) {
            return;
        }

        int gameId = message.getIntFromBody("id");
        Game game = GameSessionController.getInstance().getGame(gameId);
        int selectedX = message.getIntFromBody("x");
        int selectedY = message.getIntFromBody("y");

        CarpenterShop shop = game.getMap().getNpcVillage().getCarpenterShop();
        Result result = shop.purchaseShippingBin(game,player,selectedX,selectedY);

        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.PURCHASE_SHIPPING_BIN_RESULT);
        response.setRequestID(message.getRequestID());
        connectionThread.sendMessage(response);

    }

    public void purchaseBuilding(Message message, Player player, ClientConnectionThread connectionThread) {
        if (message == null || player == null) {
            return;
        }

        int gameId = message.getIntFromBody("id");
        Game game = GameSessionController.getInstance().getGame(gameId);
        String buildingName = message.getFromBody("buildingName");
        int selectedX = message.getIntFromBody("x");
        int selectedY = message.getIntFromBody("y");

        CarpenterShop shop = game.getMap().getNpcVillage().getCarpenterShop();
        Result result = shop.purchaseBuilding(game,player,buildingName,selectedX,selectedY);

        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.PURCHASE_BUILDING_RESULT);
        response.setRequestID(message.getRequestID());
        connectionThread.sendMessage(response);
    }


}
