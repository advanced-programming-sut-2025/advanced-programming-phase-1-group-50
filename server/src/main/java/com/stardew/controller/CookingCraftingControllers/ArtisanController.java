package com.stardew.controller.CookingCraftingControllers;

import com.stardew.model.Result;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanMachine;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.HashMap;

public class ArtisanController {
    private static ArtisanController instance;

    private ArtisanController() {}

    public static ArtisanController getInstance() {
        if (instance == null) {
            instance = new ArtisanController();
        }
        return instance;
    }




    public void artisanUse(Message message, Player player, ClientConnectionThread connection) {
        if (message == null) return;
        if (player == null) return;
        if (connection == null) return;

        String machineID = message.getFromBody("machineID");
        ArtisanMachine artisanMachine = player.getBackpack().getArtisanMachineByID(machineID);
        String itemName = message.getFromBody("itemName");

        if (artisanMachine == null) {
            Result result = new Result(false, "Artisan Machine not found!  ");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }
        if (artisanMachine.isAnyProducing()) {
            Result result = new Result(false, "Artisan Machine is producing right now!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        Result result = artisanMachine.canUse(player, itemName);
        if (result.getSuccessful()) {
            artisanMachine.use();
        }
        sendResultMessage(message.getRequestID(), connection, result);
    }

    public void collectProduct(Message message, Player player, ClientConnectionThread connection) {
        if (message == null) return;
        if (player == null) return;

        String machineID = message.getFromBody("machineID");
        ArtisanMachine artisanMachine = player.getBackpack().getArtisanMachineByID(machineID);

        if (artisanMachine == null) {
            Result result = new Result(false, "Artisan Machine not found!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        Result result = artisanMachine.isReady();
        if (result.getSuccessful()) {
            ArtisanGood artisanGood = artisanMachine.get();
            player.getBackpack().addIngredients(artisanGood, 1);
            artisanMachine.reset();
            Result result1 = new Result(true, "You got <"+ artisanGood +"> good successfully!");
            sendResultMessage(message.getRequestID(), connection, result1);
        } else
            sendResultMessage(message.getRequestID(), connection, result);
    }

    public void cancelProcess(Message message, Player player, ClientConnectionThread connection) {
        if (message == null) return;
        if (player == null) return;

        String machineID = message.getFromBody("machineID");
        ArtisanMachine artisanMachine = player.getBackpack().getArtisanMachineByID(machineID);

        if (artisanMachine == null) {
            Result result = new Result(false, "Artisan Machine not found! ");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        if (!artisanMachine.isAnyProducing()) {
            Result result = new Result(false, "Artisan Machine is not producing!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        artisanMachine.reset();
        Result result = new Result(true, "You cancelled the Artisan machine process!");
        sendResultMessage(message.getRequestID(), connection, result);
    }

    public void isReadyProduct(Message message, Player player, ClientConnectionThread connection) {
        if (message == null) return;
        if (player == null) return;

        String machineID = message.getFromBody("machineID");
        ArtisanMachine artisanMachine = player.getBackpack().getArtisanMachineByID(machineID);

        if (artisanMachine == null) {
            sendResultMessage(message.getRequestID(), connection, new Result(false, "Artisan Machine not found!"));
            return;
        }

        Result result = artisanMachine.isReady();
        sendResultMessage(message.getRequestID(), connection, result);
    }

    public void cheatFinishProcess(Message message, Player player) {
        if (message == null) return;

        String machineID = message.getFromBody("machineID");
        ArtisanMachine artisanMachine = player.getBackpack().getArtisanMachineByID(machineID);

        if (artisanMachine == null) {
            return;
        } if (!artisanMachine.isAnyProducing()) {
            return;
        }

        artisanMachine.setCheatReady(true);
    }

    public void handleGetMachineInfo(Message message, Player player, ClientConnectionThread connection) {
        if (message == null) return;
        if (player == null) return;
        if (connection == null) return;

        String machineID = message.getFromBody("machineID");
        ArtisanMachine artisanMachine = player.getBackpack().getArtisanMachineByID(machineID);

        if (artisanMachine == null) return;

        HashMap<String, Object> body = new HashMap<>();
        boolean isProducing = artisanMachine.isAnyProducing();
        body.put("isProducing", isProducing);
        if (isProducing) {
            body.put("passedTime", artisanMachine.getPassedTime());
            body.put("totalProcessingTime", artisanMachine.getTotalProcessingTime());
        }
        Message response = new Message(body, MessageType.EVENT_IN_GAME_RESULT);
        response.setRequestID(message.getRequestID());
        connection.sendMessage(response);
    }


    private void sendResultMessage(String requestID, ClientConnectionThread connection, Result result) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.EVENT_IN_GAME_RESULT);
        response.setRequestID(requestID);
        connection.sendMessage(response);
    }

}
