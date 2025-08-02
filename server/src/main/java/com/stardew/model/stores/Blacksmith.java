package com.stardew.model.stores;

import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.mapInfo.foraging.ForagingMineral;

import java.awt.*;
import java.util.ArrayList;

public class Blacksmith extends Store {
    private ArrayList<ShopItem> inventory;

    public Blacksmith(int gameId,int x, int y, int width, int height) {
        super(gameId,TextureID.blacksmithTextureRegion, new Rectangle(x, y, width, height), "Clint", 9, 16);
    }

    @Override
    public void loadInventory() {

        inventory = new ArrayList<>();
        inventory.add(new BlacksmithStocksItem("Copper Ore", ForagingMineral.Copper, 75, Integer.MAX_VALUE));
        inventory.add(new BlacksmithStocksItem("Iron Ore", ForagingMineral.Iron, 150, Integer.MAX_VALUE));
        inventory.add(new BlacksmithStocksItem("Coal", ForagingMineral.Coal, 150, Integer.MAX_VALUE));
        inventory.add(new BlacksmithStocksItem("Gold Ore", ForagingMineral.Gold, 400, Integer.MAX_VALUE));
        inventory.add(new BlacksmithToolUpgradeItem("Copper Tool", 2000, 1));
        inventory.add(new BlacksmithToolUpgradeItem("Steel Tool", 5000, 1));
        inventory.add(new BlacksmithToolUpgradeItem("Gold Tool", 10000, 1));
        inventory.add(new BlacksmithToolUpgradeItem("Iridium Tool", 25000, 1));
        inventory.add(new BlacksmithToolUpgradeItem("Copper Trash Can", 1000, 1));
        inventory.add(new BlacksmithToolUpgradeItem("Steel Trash Can", 2500, 1));
        inventory.add(new BlacksmithToolUpgradeItem("Gold Trash Can", 5000, 1));
        inventory.add(new BlacksmithToolUpgradeItem("Iridium Trash Can", 12500, 1));

    }

    @Override
    public ArrayList<ShopItem> showAllProducts() {
        ArrayList<ShopItem> items = new ArrayList<>();
        for (ShopItem item : inventory) {
            if (item instanceof BlacksmithStocksItem) {
                items.add(item);
            }
        }
        return items;
    }

    @Override
    public ArrayList<ShopItem> showAvailableProducts() {
        ArrayList<ShopItem> availableProducts = new ArrayList<>();
        for (ShopItem item : inventory) {
            if (item.remainingQuantity > 0 && item instanceof BlacksmithStocksItem) {
                availableProducts.add(item);
            }
        }
        return availableProducts;
    }


    @Override
    public void ResetQuantityEveryNight() {
        for (ShopItem item : inventory) {
            item.resetQuantityEveryNight();
        }
    }

    @Override
    public Result purchaseProduct(int value, String productName) {
        //TODO
        return null;
    }

    public Result upgradeTool(String toolName) {
        //TODO
        return null;
    }

    private boolean canUpgradeTool(String toolName) {

        for (ShopItem item : inventory) {
            if (item.name.equals(toolName)) {
                if (item.remainingQuantity > 0) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public char getSymbol() {
        return '⚒';
    }


}
