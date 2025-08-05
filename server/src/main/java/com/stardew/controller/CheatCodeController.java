package com.stardew.controller;

import com.stardew.controller.AnimalsControllers.AnimalsController;
import com.stardew.controller.CookingCraftingControllers.CraftingController;
import com.stardew.model.Result;
import com.stardew.model.gameApp.CheatCommand;
import com.stardew.model.gameApp.Game;
import com.stardew.model.userInfo.Coin;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.HashMap;
import java.util.regex.Matcher;

public class CheatCodeController {
    private static CheatCodeController instance;
    private final DateController dateController = new DateController();
    private final WeatherController weatherController = new WeatherController();
    private final EnergyController energyController = new EnergyController();
    private final CraftingController craftingController = CraftingController.getInstance();
    private final AnimalsController animalsController = AnimalsController.getInstance();
    private CheatCodeController() {

    }
    public static CheatCodeController getInstance() {
        if(instance == null) {
            instance = new CheatCodeController();

        }
        return instance;
    }

    public void executeCheatCode(Message message , ClientConnectionThread clientConnection  , Game game , Player player ) {
        String input = message.getFromBody("command");
        String result = null;
        if (input == null)
            result =  "invalid command";
        Matcher matcher;
        if ((matcher = CheatCommand.AdvancedTimeCheatCode.getMatcher(input)) != null) {
            result =  dateController.advancedTimeCheatCode(Integer.parseInt(matcher.group("advancedTime"))  , game.getTime()).getMessage();
        }
        else if ((matcher = CheatCommand.AdvancedDateCheatCode.getMatcher(input)) != null) {
            result =  dateController.advancedDateCheatCode(Integer.parseInt(matcher.group("advancedDate")) , game.getTime()).getMessage();
        }
        else if ((matcher = CheatCommand.CheatWeatherSetCode.getMatcher(input)) != null) {
            result =  weatherController.cheatWeatherSetCode(matcher.group("weather").trim() , game.getTime()).getMessage();
        }
//        else if ((matcher = CheatCommand.CheatThunder.getMatcher(input)) != null) {
//            return weatherController.cheatThunder(
//                Integer.parseInt(matcher.group("thunderX")),
//                Integer.parseInt(matcher.group("thunderY"))).getMessage();
//        }
        else if ((matcher = CheatCommand.CheatSetEnergy.getMatcher(input)) != null) {
            result =  energyController.setEnergy(Integer.parseInt(matcher.group("energy")) , player).getMessage();
        }
        else if (CheatCommand.CheatUnlimitedEnergy.getMatcher(input) != null) {
            result =  energyController.setUnlimitedEnergy(player).getMessage();
        }
        else if ((matcher = CheatCommand.CheatAddDollars.getMatcher(input)) != null) {
            result = cheatAddDollars(matcher , player).getMessage();
        }
        else if ((matcher = CheatCommand.CheatAddItem.getMatcher(input)) != null) {
            result = craftingController.addItem(
                matcher.group("itemName"),
                Integer.parseInt(matcher.group("count")),
                player,
                game.getTime()).getMessage();
        }
        else if ((matcher = CheatCommand.CheatSetFriendship.getMatcher(input)) != null) {
            result = animalsController.setFriendship(
                player,
                matcher.group("animalName"),
                Integer.parseInt(matcher.group("amount"))).getMessage();
        }

        else {
            result =  "invalid command";
        }

        HashMap<String , Object> body = new HashMap<>();
        body.put("result", result);
        Message m = new Message(body , MessageType.CHEAT_CODE_RESULT);
        m.setRequestID(message.getRequestID());
        clientConnection.sendMessage(m);
        InventoryController.getInstance().handleSendInventoryList(player , clientConnection , message.getRequestID());

    }

    public Result cheatAddDollars(Matcher matcher , Player player) {
        try {

            int value = Integer.parseInt((matcher.group("amount")));

            if (player.getBackpack().getIngredientQuantity().getOrDefault(new Coin(),0) + value < 0) {
                return new Result(false , "Invalid amount");
            }

            player.getBackpack().addIngredients(new Coin(), value);
            return new Result(true, value + "g added");

        } catch (Exception e) {
            return new Result(false , "Invalid amount");
        }
    }
}
