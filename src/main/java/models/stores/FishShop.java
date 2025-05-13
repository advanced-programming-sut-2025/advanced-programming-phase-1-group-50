package models.stores;

import models.recipes.CraftingRecipes;
import models.tools.PoleType;

import java.awt.*;
import java.util.ArrayList;

public class FishShop extends Store {

    private ArrayList<ShopItem> inventory;

    public FishShop(int x, int y, int width, int height) {
        super(new Rectangle(x, y, width, height), "willy", 9, 17);
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
    public char getSymbol() {
        return '≈';
    }

//    @Override
//    public void removeGood() {
//
//    }
//
//    @Override
//    public void addGood() {
//
//    }
//
//    @Override
//    public void sellProduct() {
//
//    }
//
//    @Override
//    public String showAllProducts() {
//        return "";
//    }
//
//    @Override
//    public String showAllAvailableProducts() {
//        return "";
//    }
//
//    @Override
//    public void purchase() {
//
//    }
}
