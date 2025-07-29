package com.stardew.model.stores;

import com.stardew.model.recipes.CraftingRecipes;

public class FishShopCraftingRecipe extends ShopItem {
    private final CraftingRecipes recipe;


    public FishShopCraftingRecipe(String name, CraftingRecipes recipe,int price, int dailyLimit) {
        super(name, price, dailyLimit);
        this.recipe = recipe;
    }

    public CraftingRecipes getRecipe() {
        return recipe;
    }
}
