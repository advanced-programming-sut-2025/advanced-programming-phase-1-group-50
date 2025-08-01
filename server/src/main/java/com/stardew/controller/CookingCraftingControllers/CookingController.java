package com.stardew.controller.CookingCraftingControllers;

//import com.stardew.models.InventoryItem;
//import com.stardew.models.Result;
//import com.stardew.models.animals.AnimalGood;
//import com.stardew.models.animals.Fish;
//import com.stardew.models.app.App;
//import com.stardew.models.cooking.Eatable;
//import com.stardew.models.cooking.Food;
//import com.stardew.models.cooking.Refrigerator;
//import com.stardew.models.foraging.Crop;
//import com.stardew.models.foraging.ForagingMineral;
//import com.stardew.models.manuFactor.Ingredient;
//import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
//import com.stardew.models.recipes.CookingRecipe;
//import com.stardew.models.userInfo.Player;

import com.stardew.model.Result;
import com.stardew.model.animals.AnimalGood;
import com.stardew.model.animals.Fish;
import com.stardew.model.cooking.Food;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.foraging.Crop;
import com.stardew.model.recipes.CookingRecipe;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

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



//    public Result cookingRefrigeratorPutPick(String action, String itemName) {
//        Food food = Food.getFoodByName(itemName);
//        Player player = App.getGame().getCurrentPlayingPlayer();
//        Refrigerator refrigerator = player.getBackpack().getRefrigerator();
//
//        if (food == null)
//            return new Result(false, "Food <" + itemName + "> not found!");
//
//        if (action.equals("put")) {
//            if (!player.getBackpack().getIngredientQuantity().containsKey(food))
//                return new Result(false, "You don't have this food in the backpack!");
//            if (!player.getBackpack().getRefrigerator().hasCapacity())
//                return new Result(false, "You don't have enough capacity in refrigerator!");
//            player.getBackpack().removeIngredients(food, 1);
//            refrigerator.addItem(food, 1);
//            return new Result(true, "You put <" + itemName + "> successfully in refrigerator!");
//        }
//        else {
//            if (!player.getBackpack().getRefrigerator().containEatable(food))
//                return new Result(false, "You don't have this food in the Refrigerator!");
//            if (!player.getBackpack().hasCapacity())
//                return new Result(false, "You don't have enough space in the Backpack!");
//            player.getBackpack().getRefrigerator().removeItem(food, 1);
//            player.getBackpack().addIngredients(food, 1);
//            return new Result(true, "You pickUp <" + itemName + "> successfully!");
//        }
//    }
//
//    public Result putInRefrigerator(InventoryItem item) {
//        if (item == null)
//            return new Result(false, "item not found!");
//        if (!(item instanceof Eatable eatable) || eatable.getEnergy() == 0) {
//            if (item instanceof ForagingMineral)
//                System.out.println("ForagingMineral");
//            if (item instanceof ArtisanGood)
//                System.out.println("ArtisanGood");
//
//            return new Result(false, "This item is not eatable! You cannot put it in refrigerator!!");
//        }
//
//        Player player = App.getGame().getCurrentPlayingPlayer();
//        Refrigerator refrigerator = player.getBackpack().getRefrigerator();
//
//        if (!player.getBackpack().getIngredientQuantity().containsKey(eatable))
//            return new Result(false, "You don't have this item in the backpack!");
//        if (!player.getBackpack().getRefrigerator().hasCapacity())
//            return new Result(false, "You don't have enough capacity in refrigerator!");
//        player.getBackpack().removeIngredients(eatable, 1);
//        refrigerator.addItem(eatable, 1);
//        return new Result(true, "You put <" + eatable + "> successfully in refrigerator!");
//    }
//
//    public Result pickFromRefrigerator(Eatable eatable) {
//        if (eatable == null)
//            return new Result(false, "Eatable not found!");
//
//        Player player = App.getGame().getCurrentPlayingPlayer();
//        Refrigerator refrigerator = player.getBackpack().getRefrigerator();
//
//        if (!player.getBackpack().getRefrigerator().containEatable(eatable))
//            return new Result(false, "You don't have this item in the Refrigerator!");
//        if (!player.getBackpack().hasCapacity())
//            return new Result(false, "You don't have enough space in the Backpack!");
//        refrigerator.removeItem(eatable, 1);
//        player.getBackpack().addIngredients(eatable, 1);
//        return new Result(true, "You pickUp <" + eatable + "> successfully!");
//
//    }
//
//    public Result cookingShowRecipes() {
//        Player player = App.getGame().getCurrentPlayingPlayer();
//        HashSet<CookingRecipe> recipes = player.getBackpack().getCookingRecipes();
//        StringBuilder output = new StringBuilder();
//        output.append("Cooking Recipes: \n");
//        int counter = 1;
//        for (CookingRecipe recipe : recipes) {
//            output.append(String.format("%-2d. %s\n", counter, recipe));
//            counter++;
//        }
//        return new Result(true, output.toString());
//    }
//
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

//    public Result eat(Eatable item) {
//        if (item == null)
//            return new Result(false, "item not found!");
//
//        Player player = App.getGame().getCurrentPlayingPlayer();
//        Refrigerator refrigerator = player.getBackpack().getRefrigerator();
//
//        if (refrigerator.containEatable(item)) {
//            refrigerator.removeItem(item, 1);
//            player.addEnergy(item.getEnergy());
//            return new Result(true,
//                    "You eat <" + item + "> successfully!And increased your energy " + item.getEnergy() + "!");
//        }
//        else
//            return new Result(false, "You don't have Item <" + item + "> in your backpack!");
//    }




    private void sendResultMessage(String requestID, ClientConnectionThread connection, Result result) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.EVENT_IN_GAME_RESULT);
        response.setRequestID(requestID);
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


}
