package controller.CookingAndCraftingControllers;

import models.Result;
import models.app.App;
import models.cooking.Food;
import models.foraging.Fertilizer;
import models.foraging.Seeds;
import models.foraging.TreeSource;
import models.manuFactor.ArtisanMachine;
import models.manuFactor.Ingredient;
import models.recipes.CookingRecipe;
import models.recipes.CraftingRecipes;
import models.tools.Tool;
import models.userInfo.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class CraftingController {

    public Result craftingShowRecipes() {
        Player player = App.getGame().getCurrentPlayingPlayer();
        ArrayList<CraftingRecipes> recipes = player.getBackpack().getCraftingRecipes();
        StringBuilder output = new StringBuilder();
        output.append("Crafting Recipes: \n");
        for (int i = 0; i < recipes.size(); i++) {
            output.append(i+1).append(". ").append(recipes.get(i).toString()).append("\n");
        }
        return new Result(true, output.toString());
    }

    public Result craftingCraft(String itemName) {
        CraftingRecipes recipe = CraftingRecipes.getRecipeByName(itemName);
        Player player = App.getGame().getCurrentPlayingPlayer();

        if (recipe == null)
            return new Result(false, "Recipe <" + itemName + "> not found!");
        if (!player.getBackpack().containRecipe(recipe))
            return new Result(false, "You don't have <" + itemName + "> CraftingRecipe in your backpack!");
        if (!player.getBackpack().hasCapacity())
            return new Result(false, "You don't have enough space in backpack!");

        Result energyConsumptionResult = player.consumeEnergy(2);
        if (!energyConsumptionResult.getSuccessful())
            return energyConsumptionResult;

        HashMap<Ingredient, Integer> ingredients = recipe.getIngredients();
        for (Ingredient ingredient : ingredients.keySet()) {
            if (!(player.getBackpack().getIngredientQuantity().containsKey(ingredient) &&
                    player.getBackpack().getIngredientQuantity().get(ingredient) >= ingredients.get(ingredient))) {
                return new Result(false, "You don't have enough <" + ingredient + "> in your backpack!");
            }
            player.getBackpack().removeIngredients(ingredient, ingredients.get(ingredient));
        }

        ArtisanMachine artisanMachine = ArtisanMachine.getArtisanMachineByRecipe(recipe);
        if (artisanMachine != null)
            player.getBackpack().addArtisanMachine(artisanMachine);
        if (recipe.equals(CraftingRecipes.MysticTreeSeed))
            player.getBackpack().addIngredients(TreeSource.MysticTreeSeeds, 1);

        return new Result(true, "You craft <" + itemName + "> successfully!");
    }

    public Result addItem(String itemName, int quantity) {
        Player player = App.getGame().getCurrentPlayingPlayer();

        if (quantity <= 0)
            return new Result(false, "The quantity must be greater than zero!");
        if (!player.getBackpack().hasCapacity())
            return new Result(false, "You don't have enough space in backpack!");

        CraftingRecipes craftingRecipe = CraftingRecipes.getRecipeByName(itemName);
        ArtisanMachine machine;
        if (craftingRecipe != null) {
            if ((machine = ArtisanMachine.getArtisanMachineByRecipe(craftingRecipe)) != null) {
                player.getBackpack().addArtisanMachine(machine);
            }
            else if (craftingRecipe.equals(CraftingRecipes.MysticTreeSeed)) {
                player.getBackpack().addIngredients(TreeSource.MysticTreeSeeds, quantity);
            }
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        CookingRecipe cookingRecipe = CookingRecipe.getRecipeByName(itemName);
        if (cookingRecipe != null) {
            Food food = Food.getFoodByName(itemName);
            player.getBackpack().addIngredients(food, quantity);
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

        Tool tool = Tool.getToolByName(itemName);
        if (tool != null) {
            for (int i = 0; i < quantity; i++) player.getBackpack().addTool(tool);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        Fertilizer fertilizer = Fertilizer.getFertilizerByName(itemName);
        if (fertilizer != null) {
            player.getBackpack().addIngredients(fertilizer, quantity);
            return new Result(true, "You add <" + fertilizer + "> successfully!");
        }
        
        if (itemName.equalsIgnoreCase("hay")) {
            player.getBackpack().increaseHay(quantity);
            return new Result(true, "You add <" + itemName + "> successfully!");
        }

        return new Result(false, "There is no such Item!");
    }

}
