package com.stardew.model.stores;

import com.stardew.model.recipes.CookingRecipe;

public class StardopSaloonRecipeItem extends ShopItem {
    private final CookingRecipe recipe;

    public StardopSaloonRecipeItem(String name, CookingRecipe recipe , int price, int dailyLimit) {
        super(name, price, dailyLimit);
        this.recipe = recipe;
    }

    public CookingRecipe getRecipe() {
        return recipe;
    }
}
