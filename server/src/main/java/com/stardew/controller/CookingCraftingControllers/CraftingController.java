package com.stardew.controller.CookingCraftingControllers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.model.Result;
import com.stardew.model.gameApp.App;
import com.stardew.model.cooking.Food;
import com.stardew.model.mapInfo.*;
import com.stardew.model.mapInfo.foraging.TreeSource;
import com.stardew.model.mapInfo.manuFactor.ArtisanMachine;
import com.stardew.model.recipes.CookingRecipe;
import com.stardew.model.recipes.CraftingRecipes;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.HashMap;

public class CraftingController {
    private static CraftingController instance;

    private CraftingController() {}

    public static synchronized CraftingController getInstance() {
        if (instance == null) {
            instance = new CraftingController();
        }
        return instance;
    }



//    public Result craftingShowRecipes() {
//        Player player = App.getGame().getCurrentPlayingPlayer();
//        HashSet<CraftingRecipes> recipes = player.getBackpack().getCraftingRecipes();
//        StringBuilder output = new StringBuilder();
//        output.append("Crafting Recipes: \n");
//        int counter = 1;
//        for (CraftingRecipes recipe : recipes) {
//            output.append(String.format("%-2d. %s\n", counter, recipe));
//            counter++;
//        }
//        return new Result(true, output.toString());
//    }
//

    public void craftingCraft(Message message, Player player, GameMap map, ClientConnectionThread connection) {
        if (message == null) return;
        if (player == null) return;

        CraftingRecipes recipe = CraftingRecipes.getRecipeByName(message.getFromBody("name"));
        int x = message.getIntFromBody("x");
        int y = message.getIntFromBody("y");

        if (recipe == null) {
            Result result = new Result(false, "Recipe not found!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }
        if (!player.getBackpack().containRecipe(recipe)) {
            Result result = new Result(false, "You don't have <" + recipe.name() + "> CraftingRecipe in your backpack!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }
        if (!player.getBackpack().hasCapacity()) {
            Result result = new Result(false, "You don't have enough space in backpack!");
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        Result energyConsumptionResult = player.consumeEnergy(2);
        if (!energyConsumptionResult.getSuccessful()) {
            sendResultMessage(message.getRequestID(), connection, energyConsumptionResult);
            return;
        }

        StringBuilder negativeResults = new StringBuilder();
        HashMap<Ingredient, Integer> ingredients = recipe.getIngredients();
        for (Ingredient ingredient : ingredients.keySet()) {
            if (!(player.getBackpack().getIngredientQuantity().containsKey(ingredient) &&
                player.getBackpack().getIngredientQuantity().getOrDefault(ingredient,0) >= ingredients.get(ingredient))) {
                negativeResults.append("You don't have enough <").append(ingredient).append("> in your backpack!\n\n");
            }
        }
        if (!negativeResults.isEmpty()) {
            Result result = new Result(false, negativeResults.toString());
            sendResultMessage(message.getRequestID(), connection, result);
            return;
        }

        //decrease all needed ingredients
        for (Ingredient ingredient : ingredients.keySet()) {
            player.getBackpack().removeIngredients(ingredient, ingredients.get(ingredient));
        }

        ArtisanMachine artisanMachine = ArtisanMachine.getArtisanMachineByRecipe(recipe);
        if (artisanMachine != null) {
            Tile tile = map.findTile(x, y);
            if (tile == null) {
                sendResultMessage(message.getRequestID(), connection, new Result(false, "Please select a valid tile in your farm"));
                return;
            }
            if (tile.getPlaceable() != null) {
                sendResultMessage(message.getRequestID(), connection, new Result(false, "Selected tile is not free!"));
                return;
            }
            tile.setWalkable(false);
            tile.setPlaceable(artisanMachine);
            player.getBackpack().addArtisanMachine(artisanMachine);
            sendResultMessage(message.getRequestID(), connection, new Result(true, "You craft <" + recipe.name() + "> successfully!"));
        }
        else if (recipe.equals(CraftingRecipes.MysticTreeSeed)) {
            player.getBackpack().addIngredients(TreeSource.MysticTreeSeeds, 1);
            sendResultMessage(message.getRequestID(), connection, new Result(true, "You craft <" + recipe.name() + "> successfully!"));
        }

    }

//    public Result addItem(String itemName, int quantity) {
//        Player player = App.getGame().getCurrentPlayingPlayer();
//
//        if (quantity <= 0)
//            return new Result(false, "The quantity must be greater than zero!");
//        if (!player.getBackpack().hasCapacity())
//            return new Result(false, "You don't have enough space in backpack!");
//
//        CraftingRecipes craftingRecipe = CraftingRecipes.getRecipeByName(itemName);
//        if (craftingRecipe != null) {
//            player.getBackpack().addRecipe(craftingRecipe);
//            return new Result(true, "You add <" + itemName + "> successfully!");
//        }
//
//        CookingRecipe cookingRecipe = CookingRecipe.getRecipeByName(itemName);
//        if (cookingRecipe != null) {
//            Food food = Food.getFoodByName(itemName);
//            player.getBackpack().addIngredients(food,quantity);
//            player.getBackpack().addRecipe(cookingRecipe);
//            return new Result(true, "You add <" + itemName + "> successfully!");
//        }
//
//        CropType cropType = CropType.getCropTypeByName(itemName);
//        if (cropType != null) {
//            player.getBackpack().addIngredients(new Crop(cropType, App.getGame().getTime(), null, 1, 1), quantity);
//            return new Result(true, "You add <" + itemName + "> successfully!");
//        }
//
//        FishType fishType = FishType.getFishTypeByName(itemName);
//        if (fishType != null) {
//            player.getBackpack().addIngredients(new Fish(fishType, Quality.Regular), quantity);
//            return new Result(true, "You add <" + itemName + "> successfully!");
//        }
//
//        Seeds seeds = Seeds.getSeedByName(itemName);
//        if (seeds != null) {
//            player.getBackpack().addIngredients(seeds, quantity);
//            return new Result(true, "You add <" + itemName + "> successfully!");
//        }
//
//        TreeSource treeSource = TreeSource.getTreeSourceByName(itemName);
//        if (treeSource != null) {
//            player.getBackpack().addIngredients(treeSource, quantity);
//            return new Result(true, "You add <" + itemName + "> successfully!");
//        }
//
//        Fruit fruit = Fruit.getFruitByName(itemName);
//        if (fruit != null) {
//            player.getBackpack().addIngredients(fruit, quantity);
//            //CommonMushroom is also in this category
//            ForagingCrop foragingCrop = ForagingCrop.getForagingCropByName(itemName);
//            if (foragingCrop != null)
//                player.getBackpack().addIngredients(foragingCrop, quantity);
//            return new Result(true, "You add <" + itemName + "> successfully!");
//        }
//
//        ForagingMineral foragingMineral = ForagingMineral.getForagingMineralByName(itemName);
//        if (foragingMineral != null) {
//            player.getBackpack().addIngredients(foragingMineral, quantity);
//            //Coal is also in this category
//            ArtisanGoodType artisanGoodType = ArtisanGoodType.getArtisanGoodTypeByName(itemName);
//            if (artisanGoodType != null)
//                player.getBackpack().addIngredients(new ArtisanGood(artisanGoodType), quantity);
//            return new Result(true, "You add <" + itemName + "> successfully!");
//        }
//
//        ForagingCrop foragingCrop = ForagingCrop.getForagingCropByName(itemName);
//        if (foragingCrop != null) {
//            player.getBackpack().addIngredients(foragingCrop, quantity);
//            return new Result(true, "You add <" + itemName + "> successfully!");
//        }
//
//        ArtisanGoodType artisanGoodType = ArtisanGoodType.getArtisanGoodTypeByName(itemName);
//        if (artisanGoodType != null) {
//            player.getBackpack().addIngredients(new ArtisanGood(artisanGoodType), quantity);
//            return new Result(true, "You add <" + itemName + "> successfully!");
//        }
//
//        Tool tool = Tool.getToolByName(itemName);
//        if (tool != null) {
//            player.getBackpack().addTool(tool);
//            return new Result(true, "You add <" + itemName + "> successfully!");
//        }
//
//        Fertilizer fertilizer = Fertilizer.getFertilizerByName(itemName);
//        if (fertilizer != null) {
//            player.getBackpack().addIngredients(fertilizer, quantity);
//            return new Result(true, "You add <" + fertilizer + "> successfully!");
//        }
//
//        AnimalGoodType animalGoodType = AnimalGoodType.getAnimalGoodTypeByName(itemName);
//        if (animalGoodType != null) {
//            player.getBackpack().addIngredients(new AnimalGood(animalGoodType, Quality.Regular), quantity);
//            return new Result(true, "You add <" + itemName + "> successfully!");
//        }
//
//        if (itemName.equalsIgnoreCase("hay")) {
//            player.getBackpack().increaseHay(quantity);
//            return new Result(true, "You add <" + itemName + "> successfully!");
//        }
//
//        if (itemName.equalsIgnoreCase("wood")) {
//            player.getBackpack().addIngredients(new Wood(), quantity);
//            return new Result(true, "You add <" + itemName + "> successfully!");
//        }
//
//        if (itemName.equalsIgnoreCase("stone")) {
//            player.getBackpack().addIngredients(new Stone(), quantity);
//            return new Result(true, "You add <" + itemName + "> successfully!");
//        }
//
//        return new Result(false, "There is no such Item!");
//    }





    private void sendResultMessage(String requestID, ClientConnectionThread connection, Result result) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("result", result);
        Message response = new Message(body, MessageType.EVENT_IN_GAME_RESULT);
        response.setRequestID(requestID);
        connection.sendMessage(response);
    }

}
