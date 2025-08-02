package com.stardew.controller.CookingCraftingControllers;

import com.stardew.controller.InventoryController;
import com.stardew.model.InventoryItemDTO;
import com.stardew.model.Result;
import com.stardew.model.animals.AnimalGood;
import com.stardew.model.animals.Fish;
import com.stardew.model.cooking.Food;
import com.stardew.model.cooking.Refrigerator;
import com.stardew.model.mapInfo.Eatable;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.InventoryItem;
import com.stardew.model.mapInfo.foraging.Crop;
import com.stardew.model.recipes.CookingRecipe;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CookingController {
    private static CookingController instance;

    private CookingController () {}

    public static synchronized CookingController getInstance() {
        if (instance == null) {
            instance = new CookingController();
        }
        return instance;
    }



    public void putInRefrigerator(Message message, Player player, ClientConnectionThread connection) {
        if (message == null) return;
        if (player == null) return;

        InventoryItemDTO itemDTO = message.getFromBody("item", InventoryItemDTO.class);
        InventoryItem item = InventoryController.getInstance().getInventoryItemByID(itemDTO, player.getInventoryItems());

        if (item == null) {
            Result result = new Result(false, "item not found!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }
        if (!(item instanceof Eatable eatable) || eatable.getEnergy() == 0) {
            Result result = new Result(false, "This item is not eatable! You cannot put it in refrigerator!!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        Refrigerator refrigerator = player.getBackpack().getRefrigerator();

        if (!player.getBackpack().getIngredientQuantity().containsKey(eatable)) {
            Result result = new Result(false, "You don't have this item in the backpack!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }
        if (!player.getBackpack().getRefrigerator().hasCapacity()) {
            Result result = new Result(false, "You don't have enough capacity in refrigerator!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        player.getBackpack().removeIngredients(eatable, 1);
        refrigerator.addItem(eatable, 1);
        Result result = new Result(true, "You put <" + eatable + "> successfully in refrigerator!");
        sendResultMessage(message.getRequestID(), connection, result);
    }

    public void pickFromRefrigerator(Message message, Player player, ClientConnectionThread connection) {
        if (message == null) return;
        if (player == null) return;

        InventoryItemDTO itemDTO = message.getFromBody("item", InventoryItemDTO.class);
        InventoryItem item = InventoryController.getInstance().getInventoryItemByID(itemDTO, player.getInventoryItems());

        if (!(item instanceof Eatable eatable)) {
            Result result = new Result(false, "Some wrong happened!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        Refrigerator refrigerator = player.getBackpack().getRefrigerator();

        if (!player.getBackpack().getRefrigerator().containEatable(eatable)) {
            Result result = new Result(false, "You don't have this item in the Refrigerator!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }
        if (!player.getBackpack().hasCapacity()) {
            Result result = new Result(false, "You don't have enough space in the Backpack!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }
        refrigerator.removeItem(eatable, 1);
        player.getBackpack().addIngredients(eatable, 1);
        Result result = new Result(true, "You pickUp <" + eatable + "> successfully!");
        sendResultMessage(message.getRequestID(), connection, result);
    }

    public void cookingPrepare(Message message, Player player, ClientConnectionThread connection) {
        String recipeName = message.getFromBody("name");

        CookingRecipe recipe = CookingRecipe.getRecipeByName(recipeName);

        if (recipe == null) {
            Result result = new Result(false, "Recipe not found!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }
        if (!player.getBackpack().containRecipe(recipe)) {
            Result result = new Result(false, "You don't have <" + recipe.name() + "> CookingRecipe in your backpack!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }
        if (!player.getBackpack().hasCapacity()) {
            Result result = new Result(false, "You don't have enough space in backpack!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        Result energyConsumptionResult = player.consumeEnergy(3);
        if (!energyConsumptionResult.getSuccessful()) {
            sendResultMessage(message.getRequestID(), connection, energyConsumptionResult);
            return;
        }

        HashMap<Ingredient, Integer> requiredIngredients = recipe.getIngredients();

        StringBuilder negativeResults = new StringBuilder();
        for (Ingredient requiredIngredient : requiredIngredients.keySet()) {

            Ingredient ingredientInBackpack = getIngredient(requiredIngredient, player);

            if (ingredientInBackpack == null)
                negativeResults.append("You don't have any <").append(requiredIngredient).append("> in your backpack!\n\n");

            else if (player.getBackpack().getIngredientQuantity().getOrDefault(ingredientInBackpack,0) < requiredIngredients.get(requiredIngredient)) {
                negativeResults.append("You don't have enough <").append(requiredIngredient).append("> in your backpack!\n\n");
            }
        }

        if (!negativeResults.isEmpty()) {
            Result result = new Result(false, negativeResults.toString());
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        //decrease all needed ingredients
        for (Ingredient requiredIngredient : requiredIngredients.keySet()) {
            Ingredient ingredientInBackpack = getIngredient(requiredIngredient, player);
            player.getBackpack().removeIngredients(ingredientInBackpack, requiredIngredients.get(requiredIngredient));
        }

        Food food = Food.getFoodByName(recipe.name());
        player.getBackpack().addIngredients(food, 1);

        Result result = new Result(true, "You cook <" + food + "> successfully!");
        sendResultMessage(message.getRequestID(), connection, result);
    }

    public void handleEat(Message message, Player player, ClientConnectionThread connection) {
        if (message == null) return;
        if (player == null) return;

        InventoryItemDTO itemDTO = message.getFromBody("item", InventoryItemDTO.class);
        InventoryItem item = InventoryController.getInstance().getInventoryItemByID(itemDTO, player.getInventoryItems());

        if (!(item instanceof Eatable eatable)) {
            Result result = new Result(false, "item not found!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        Refrigerator refrigerator = player.getBackpack().getRefrigerator();

        if (refrigerator.containEatable(eatable)) {
            refrigerator.removeItem(eatable, 1);
            player.addEnergy(eatable.getEnergy());
            Result result = new Result(true,
                    "You eat <" + eatable + "> successfully!And increased your energy " + eatable.getEnergy() + "!");
            sendResultMessage(message.getRequestID(), connection, result);
        }
        else {
            Result result = new Result(false, "You don't have Item <" + eatable + "> in your backpack!");
            sendResultMessage(message.getRequestID(), connection, result);
        }
    }

    public void handleGetRefrigeratorItems(Message message, Player player, ClientConnectionThread connection) {
        if (message == null) return;
        if (player == null) return;


        ArrayList<InventoryItemDTO> refrigeratorItems = new ArrayList<>();
        for (Eatable eatable : player.getBackpack().getRefrigerator().getItemsQuantity().keySet()) {
            InventoryItemDTO item = eatable.toDTO();
            item.setQuantity(player.getBackpack().getRefrigerator().getItemsQuantity().get(eatable));
            refrigeratorItems.add(item);
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("refrigerator", refrigeratorItems);
        Message response = new Message(body, MessageType.EVENT_IN_GAME_RESULT);
        response.setRequestID(message.getRequestID());
        connection.sendMessage(response);
    }


    private Ingredient getIngredient(Ingredient requiredIngredient, Player player) {
        for (Ingredient myIngredient : player.getBackpack().getIngredientQuantity().keySet()) {
            if ((myIngredient instanceof Crop crop && crop.getType().equals(requiredIngredient)) ||
                (myIngredient instanceof AnimalGood animalGood && animalGood.getType().equals(requiredIngredient)) ||
                (myIngredient instanceof Fish fish && fish.getType().equals(requiredIngredient)) ||
                (myIngredient.equals(requiredIngredient))) {
                return myIngredient;
            }
        }
        return null;
    }



    private void sendResultMessage(String requestID, ClientConnectionThread connection, Result result) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.EVENT_IN_GAME_RESULT);
        response.setRequestID(requestID);
        connection.sendMessage(response);
    }


}
