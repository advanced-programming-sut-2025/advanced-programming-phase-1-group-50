package com.stardew.view.cheatConsole;

import com.stardew.controller.AbilityAndEnergyController.EnergyController;
import com.stardew.controller.AnimalsControllers.AnimalsController;
import com.stardew.controller.CookingAndCraftingControllers.CraftingController;
import com.stardew.controller.GameDateAndWeatherController.DateController;
import com.stardew.controller.GameDateAndWeatherController.WeatherController;
import com.stardew.controller.GameMenuController;
import com.stardew.models.enums.CheatCommand;

import java.util.regex.Matcher;

public class CheatHandler {
    private final DateController dateController = new DateController();
    private final WeatherController weatherController = new WeatherController();
    private final EnergyController energyController = new EnergyController();
    private final GameMenuController gameMenuController = new GameMenuController();
    private final CraftingController craftingController = new CraftingController();
    private final AnimalsController animalsController = new AnimalsController();


    public String executeCommand(String input) {
        if (input == null)
            return "invalid command";
        Matcher matcher;
        if ((matcher = CheatCommand.AdvancedTimeCheatCode.getMatcher(input)) != null) {
            return dateController.advancedTimeCheatCode(Integer.parseInt(matcher.group("advancedTime"))).getMessage();
        }
        else if ((matcher = CheatCommand.AdvancedDateCheatCode.getMatcher(input)) != null) {
            return dateController.advancedDateCheatCode(Integer.parseInt(matcher.group("advancedDate"))).getMessage();
        }
        else if ((matcher = CheatCommand.CheatWeatherSetCode.getMatcher(input)) != null) {
            return weatherController.cheatWeatherSetCode(matcher.group("weather").trim()).getMessage();
        }
        else if ((matcher = CheatCommand.CheatThunder.getMatcher(input)) != null) {
            return weatherController.cheatThunder(
                Integer.parseInt(matcher.group("thunderX")),
                Integer.parseInt(matcher.group("thunderY"))).getMessage();
        }
        else if ((matcher = CheatCommand.CheatSetEnergy.getMatcher(input)) != null) {
            return energyController.setEnergy(Integer.parseInt(matcher.group("energy"))).getMessage();
        }
        else if (CheatCommand.CheatUnlimitedEnergy.getMatcher(input) != null) {
            return energyController.setUnlimitedEnergy().getMessage();
        }
        else if ((matcher = CheatCommand.CheatAddDollars.getMatcher(input)) != null) {
            return gameMenuController.cheatAddDollars(matcher).getMessage();
        }
        else if ((matcher = CheatCommand.CheatAddItem.getMatcher(input)) != null) {
            return craftingController.addItem(
                matcher.group("itemName"),
                Integer.parseInt(matcher.group("count"))).getMessage();
        }
        else if ((matcher = CheatCommand.CheatSetFriendship.getMatcher(input)) != null) {
            return animalsController.setFriendship(
                matcher.group("animalName"),
                Integer.parseInt(matcher.group("amount"))).getMessage();
        }
        else if ((matcher = CheatCommand.NextTurn.getMatcher(input)) != null) {
            return gameMenuController.nextTurn().getMessage();
        }
        else {
            return "invalid command";
        }
    }
}
