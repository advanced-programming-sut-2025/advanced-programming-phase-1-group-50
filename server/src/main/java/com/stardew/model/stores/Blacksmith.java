package com.stardew.model.stores;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.model.Result;

import java.awt.*;
import java.util.ArrayList;

public class Blacksmith extends Store {
    //    private final String colorCode = ColorPrinter.GRAY;
    //    private final String backgroundCode = ColorPrinter.WHITE;
    private ArrayList<ShopItem> inventory;

    public Blacksmith(int x, int y, int width, int height) {
        //super(GamePictureManager.blacksmithRegions ,new TextureRegion(GamePictureManager.blacksmithTexture),new Rectangle(x, y, width, height), "Clint", 9, 16);
        super(null,null,new Rectangle(x, y, width, height), "Clint", 9, 16);
    }

    @Override
    public void loadInventory() {

        inventory = new ArrayList<>();
//        inventory.add(new BlackSmithStocksItem("Copper Ore", ForagingMineral.Copper, 75, Integer.MAX_VALUE));
//        inventory.add(new BlackSmithStocksItem("Iron Ore", ForagingMineral.Iron, 150, Integer.MAX_VALUE));
//        inventory.add(new BlackSmithStocksItem("Coal", ForagingMineral.Coal, 150, Integer.MAX_VALUE));
//        inventory.add(new BlackSmithStocksItem("Gold Ore", ForagingMineral.Gold, 400, Integer.MAX_VALUE));
//        inventory.add(new BlackSmithToolUpgradeItem("Copper Tool", 2000, 1));
//        inventory.add(new BlackSmithToolUpgradeItem("Steel Tool", 5000, 1));
//        inventory.add(new BlackSmithToolUpgradeItem("Gold Tool", 10000, 1));
//        inventory.add(new BlackSmithToolUpgradeItem("Iridium Tool", 25000, 1));
//        inventory.add(new BlackSmithToolUpgradeItem("Copper Trash Can", 1000, 1));
//        inventory.add(new BlackSmithToolUpgradeItem("Steel Trash Can", 2500, 1));
//        inventory.add(new BlackSmithToolUpgradeItem("Gold Trash Can", 5000, 1));
//        inventory.add(new BlackSmithToolUpgradeItem("Iridium Trash Can", 12500, 1));

    }

    @Override
    public ArrayList<ShopItem> showAllProducts() {
        ArrayList<ShopItem> items = new ArrayList<>();
        for (ShopItem item : inventory) {
//            if (item instanceof BlackSmithStocksItem) {
//                items.add(item);
//            }
        }
        return items;
    }

    @Override
    public ArrayList<ShopItem> showAvailableProducts() {
        ArrayList<ShopItem> availableProducts = new ArrayList<>();
        for (ShopItem item : inventory) {
//            if (item.remainingQuantity > 0 && item instanceof BlackSmithStocksItem) {
//                availableProducts.add(item);
//            }
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

//    @Override
//    public String getColor() {
//        return colorCode;
//    }
//
//    @Override
//    public String getBackground() {
//        return backgroundCode;
//    }
//
//    @Override
//    public Color getMiniMapColor() {
//        return Color.BLACK;
//    }





}
