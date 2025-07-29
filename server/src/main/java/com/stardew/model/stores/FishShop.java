package com.stardew.model.stores;

import com.stardew.model.Result;
import com.stardew.model.Tools.PoleType;
import com.stardew.model.recipes.CraftingRecipes;

import java.awt.*;
import java.util.ArrayList;

public class FishShop extends Store{
    private ArrayList<ShopItem> inventory;

    public FishShop(int x, int y, int width, int height) {
//        super(GamePictureManager.fishShopRegions,new TextureRegion(GamePictureManager.fishShopTexture),new Rectangle(x, y, width, height), "willy", 9, 17);
        super(null,null,new Rectangle(x,y,width,height),"willy",9,17);
    }

    @Override
    public void loadInventory() {

        inventory = new ArrayList<>();

        inventory.add(new FishShopCraftingRecipe("Fish Smoker", CraftingRecipes.FishSmoker , 10000 , 1));
        inventory.add(new FishShopPoleItem("Training Rod", PoleType.Training , 0 , 25 , 1));
        inventory.add(new FishShopPoleItem("Bamboo Pole", PoleType.Bamboo , 0 , 500 , 1));
        inventory.add(new FishShopPoleItem("Fiberglass Rod", PoleType.Fiberglass , 2 , 1800 , 1));
        inventory.add(new FishShopPoleItem("Iridium Rod", PoleType.Iridium , 4 , 7500 , 1));
        inventory.add(new ShopItem("Trout Soup",250,1));

    }

    @Override
    public ArrayList<ShopItem> showAllProducts() {
        return (ArrayList<ShopItem>) inventory.clone();
    }

    @Override
    public ArrayList<ShopItem> showAvailableProducts() {
        ArrayList<ShopItem> availableProducts = new ArrayList<>();
        for (ShopItem item : inventory) {
            if (item.remainingQuantity > 0) {
                availableProducts.add(item);
            }
        }
        return availableProducts;
    }

    @Override
    public Result purchaseProduct(int value, String productName) {
        //TODO
        return null;
    }

    @Override
    public void ResetQuantityEveryNight() {
        for (ShopItem item : inventory) {
            item.resetQuantityEveryNight();
        }
    }

    @Override
    public char getSymbol() {
        return '≈';
    }

}
