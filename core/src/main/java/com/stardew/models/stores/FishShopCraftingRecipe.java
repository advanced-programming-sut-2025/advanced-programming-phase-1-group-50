package com.stardew.models.stores;

import com.stardew.models.recipes.CraftingRecipes;

public class FishShopCraftingRecipe extends ShopItem{

    private final CraftingRecipes recipe;


    public FishShopCraftingRecipe(String name, CraftingRecipes recipe,int price, int dailyLimit) {
        super(name, price, dailyLimit);
        this.recipe = recipe;
    }

    public CraftingRecipes getRecipe() {
        return recipe;
    }
}
