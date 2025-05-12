package controller.CookingAndCraftingControllers;

import models.Result;
import models.animals.AnimalGood;
import models.animals.Fish;
import models.app.App;
import models.cooking.Food;
import models.cooking.Refrigerator;
import models.foraging.Crop;
import models.foraging.TreeSource;
import models.manuFactor.ArtisanMachine;
import models.manuFactor.Ingredient;
import models.recipes.CookingRecipe;
import models.recipes.CraftingRecipes;
import models.userInfo.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class CookingController {

    public Result cookingRefrigeratorPutPick(String action, String itemName) {
        Food food = Food.getFoodByName(itemName);
        Player player = App.getGame().getCurrentPlayingPlayer();
        Refrigerator refrigerator = player.getBackpack().getRefrigerator();

        if (food == null)
            return new Result(false, "Food <" + itemName + "> not found!");

        if (action.equals("put")) {
            if (!player.getBackpack().getIngredientQuantity().containsKey(food))
                return new Result(false, "You don't have this food in the backpack!");
            player.getBackpack().removeIngredients(food, 1);
            refrigerator.addItem(food, 1);
            return new Result(true, "You put <" + itemName + "> successfully in refrigerator!");
        }
        else {
            if (!(player.getBackpack().getRefrigerator().getQuantity(food) == 0))
                return new Result(false, "You don't have this food in the Refrigerator!");
            player.getBackpack().getRefrigerator().removeItem(food, 1);
            player.getBackpack().addIngredients(food, 1);
            return new Result(true, "You pickUp <" + itemName + "> successfully!");
        }
    }

    public Result cookingShowRecipes() {
        Player player = App.getGame().getCurrentPlayingPlayer();
        ArrayList<CookingRecipe> recipes = player.getBackpack().getCookingRecipes();
        StringBuilder output = new StringBuilder();
        output.append("Cooking Recipes: \n");
        for (int i = 0; i < recipes.size(); i++) {
            output.append(i+1).append(". ").append(recipes.get(i).toString()).append("\n");
        }
        return new Result(true, output.toString());
    }

    public Result cookingPrepare(String ItemName) {
        CookingRecipe recipe = CookingRecipe.getRecipeByName(ItemName);
        Player player = App.getGame().getCurrentPlayingPlayer();

        if (recipe == null)
            return new Result(false, "Recipe <" + ItemName + "> not found!");
        if (!player.getBackpack().containRecipe(recipe))
            return new Result(false, "You don't have <" + ItemName + "> CookingRecipe in your backpack!");
        if (player.getBackpack().hasCapacity())
            return new Result(false, "You don't have enough space in backpack!");

        HashMap<Ingredient, Integer> requiredIngredients = recipe.getIngredients();

        for (Ingredient requiredIngredient : requiredIngredients.keySet()) {

            Ingredient ingredientInBackpack = getIngredient(requiredIngredient, player);

            if (ingredientInBackpack == null)
                return new Result(false, "You don't have any <" + requiredIngredient + "> in your backpack!");

            if (player.getBackpack().getIngredientQuantity().get(ingredientInBackpack) < requiredIngredients.get(ingredientInBackpack)) {
                return new Result(false, "You don't have enough <" + requiredIngredient + "> in your backpack!");
            }

            player.getBackpack().removeIngredients(ingredientInBackpack, requiredIngredients.get(requiredIngredient));
        }

        Food food = Food.getFoodByName(recipe.name());
        player.getBackpack().addIngredients(food, 1);

        player.consumeEnergy(3);

        return new Result(true, "You cook <" + food + "> successfully!");
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

    public Result eat(String foodName) {
        Food food = Food.getFoodByName(foodName);
        Player player = App.getGame().getCurrentPlayingPlayer();

        if (food == null)
            return new Result(false, "Food <" + foodName + "> not found!");
        if (player.getBackpack().getIngredientQuantity().containsKey(food)) {
            player.getBackpack().removeIngredients(food, 1);
            player.addEnergy(food.getEnergy());
            return new Result(true,
                    "You eat <" + food + "> successfully!And increased your energy " + food.getEnergy() + "!");
        }
        else
            return new Result(false, "You don't have Food <" + food + "> space in backpack!");
    }

}
