package com.stardew.controller.CookingAndCraftingControllers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.models.Result;
import com.stardew.models.animals.*;
import com.stardew.models.app.App;
import com.stardew.models.cooking.Food;
import com.stardew.models.foraging.*;
import com.stardew.models.manuFactor.ArtisanMachine;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGoodType;
import com.stardew.models.mapInfo.Stone;
import com.stardew.models.mapInfo.Tile;
import com.stardew.models.mapInfo.Wood;
import com.stardew.models.recipes.CookingRecipe;
import com.stardew.models.recipes.CraftingRecipes;
import com.stardew.models.tools.Tool;
import com.stardew.models.userInfo.Player;
import com.stardew.view.GridMap.TileSelectionWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CraftingController {

    public Result craftingShowRecipes() {
        Player player = App.getGame().getCurrentPlayingPlayer();
        HashSet<CraftingRecipes> recipes = player.getBackpack().getCraftingRecipes();
        StringBuilder output = new StringBuilder();
        output.append("Crafting Recipes: \n");
        int counter = 1;
        for (CraftingRecipes recipe : recipes) {
            output.append(String.format("%-2d. %s\n", counter, recipe));
            counter++;
        }
        return new Result(true, output.toString());
    }

//    public Result craftingCraft(CraftingRecipes recipe, Stage stage) {
//        Player player = App.getGame().getCurrentPlayingPlayer();
//
//        if (recipe == null)
//            return new Result(false, "Recipe not found!");
//        if (!player.getBackpack().containRecipe(recipe))
//            return new Result(false, "You don't have <" + recipe.name() + "> CraftingRecipe in your backpack!");
//        if (!player.getBackpack().hasCapacity())
//            return new Result(false, "You don't have enough space in backpack!");
//
//        Result energyConsumptionResult = player.consumeEnergy(2);
//        if (!energyConsumptionResult.getSuccessful())
//            return energyConsumptionResult;
//
//        HashMap<Ingredient, Integer> ingredients = recipe.getIngredients();
//        for (Ingredient ingredient : ingredients.keySet()) {
//            if (!(player.getBackpack().getIngredientQuantity().containsKey(ingredient) &&
//                player.getBackpack().getIngredientQuantity().getOrDefault(ingredient,0) >= ingredients.get(ingredient))) {
//                return new Result(false, "You don't have enough <" + ingredient + "> in your backpack!");
//            }
//        }
//        //decrease all needed ingredients
//        for (Ingredient ingredient : ingredients.keySet()) {
//            player.getBackpack().removeIngredients(ingredient, ingredients.get(ingredient));
//        }
//
//        ArtisanMachine artisanMachine = ArtisanMachine.getArtisanMachineByRecipe(recipe);
//        if (artisanMachine != null) {
//            TileSelectionWindow tileSelectionWindow = new TileSelectionWindow(stage, 1, 1);
//            stage.addActor(tileSelectionWindow);
//            tileSelectionWindow.setOnCloseCallback(tile -> {
//                if (tile == null)
//                    tileSelectionWindow.showResult(new Result(false, "You did not select a tile!"));
//                else {
//                    player.getBackpack().addArtisanMachine(artisanMachine);
//                    artisanMachine.prepareWindows(stage, tile.getPosition().getX(), tile.getPosition().getY());
//                    tile.setWalkable(false);
//                    tile.setPlaceable(artisanMachine);
//                    tileSelectionWindow.showResult(new Result(true, "You craft <" + recipe.name() + "> successfully!"));
//                }
//            });
//            return new Result(true, "Please select a tile to place the machine!");
//        }
//        else if (recipe.equals(CraftingRecipes.MysticTreeSeed)) {
//            player.getBackpack().addIngredients(TreeSource.MysticTreeSeeds, 1);
//            return new Result(true, "You craft <" + recipe.name() + "> successfully!");
//        }
//
//        return new Result(false, "Unknown recipe!");
//    }

    public Result addItem(String itemName, int quantity) {
        Player player = App.getGame().getCurrentPlayingPlayer();

        if (quantity <= 0)
            return new Result(false, "The quantity must be greater than zero!");
        if (!player.getBackpack().hasCapacity())
            return new Result(false, "You don't have enough space in backpack!");

        CraftingRecipes craftingRecipe = CraftingRecipes.getRecipeByName(itemName);
        if (craftingRecipe != null) {
            player.getBackpack().addRecipe(craftingRecipe);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        CookingRecipe cookingRecipe = CookingRecipe.getRecipeByName(itemName);
        if (cookingRecipe != null) {
            Food food = Food.getFoodByName(itemName);
            player.getBackpack().addIngredients(food,quantity);
            player.getBackpack().addRecipe(cookingRecipe);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        CropType cropType = CropType.getCropTypeByName(itemName);
        if (cropType != null) {
            player.getBackpack().addIngredients(new Crop(cropType, App.getGame().getTime(), null, 1, 1), quantity);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        FishType fishType = FishType.getFishTypeByName(itemName);
        if (fishType != null) {
            player.getBackpack().addIngredients(new Fish(fishType, Quality.Regular), quantity);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        Seeds seeds = Seeds.getSeedByName(itemName);
        if (seeds != null) {
            player.getBackpack().addIngredients(seeds, quantity);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        TreeSource treeSource = TreeSource.getTreeSourceByName(itemName);
        if (treeSource != null) {
            player.getBackpack().addIngredients(treeSource, quantity);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        Fruit fruit = Fruit.getFruitByName(itemName);
        if (fruit != null) {
            player.getBackpack().addIngredients(fruit, quantity);
            //CommonMushroom is also in this category
            ForagingCrop foragingCrop = ForagingCrop.getForagingCropByName(itemName);
            if (foragingCrop != null)
                player.getBackpack().addIngredients(foragingCrop, quantity);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        ForagingMineral foragingMineral = ForagingMineral.getForagingMineralByName(itemName);
        if (foragingMineral != null) {
            player.getBackpack().addIngredients(foragingMineral, quantity);
            //Coal is also in this category
            ArtisanGoodType artisanGoodType = ArtisanGoodType.getArtisanGoodTypeByName(itemName);
            if (artisanGoodType != null)
                player.getBackpack().addIngredients(new ArtisanGood(artisanGoodType), quantity);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        ForagingCrop foragingCrop = ForagingCrop.getForagingCropByName(itemName);
        if (foragingCrop != null) {
            player.getBackpack().addIngredients(foragingCrop, quantity);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        ArtisanGoodType artisanGoodType = ArtisanGoodType.getArtisanGoodTypeByName(itemName);
        if (artisanGoodType != null) {
            player.getBackpack().addIngredients(new ArtisanGood(artisanGoodType), quantity);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        Tool tool = Tool.getToolByName(itemName);
        if (tool != null) {
            player.getBackpack().addTool(tool);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        Fertilizer fertilizer = Fertilizer.getFertilizerByName(itemName);
        if (fertilizer != null) {
            player.getBackpack().addIngredients(fertilizer, quantity);
            return new Result(true, "You add <" + fertilizer + "> successfully!");
        }

        AnimalGoodType animalGoodType = AnimalGoodType.getAnimalGoodTypeByName(itemName);
        if (animalGoodType != null) {
            player.getBackpack().addIngredients(new AnimalGood(animalGoodType, Quality.Regular), quantity);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        if (itemName.equalsIgnoreCase("hay")) {
            player.getBackpack().increaseHay(quantity);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        if (itemName.equalsIgnoreCase("wood")) {
            player.getBackpack().addIngredients(new Wood(), quantity);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        if (itemName.equalsIgnoreCase("stone")) {
            player.getBackpack().addIngredients(new Stone(), quantity);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        return new Result(false, "There is no such Item!");
    }

}
