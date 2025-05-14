package models.stores;

import models.Result;
import models.foraging.ForagingMineral;
import models.manuFactor.artisanGoods.ArtisanGoodType;

import java.awt.*;
import java.util.ArrayList;

public class Blacksmith extends Store {

    private ArrayList<ShopItem> inventory;

    public Blacksmith(int x, int y, int width, int height) {
        super(new Rectangle(x, y, width, height), "Clint", 9, 16);
    }


    @Override
    public char getSymbol() {
        return '⚒';
    }

    @Override
    public void loadInventory() {

        inventory = new ArrayList<>();
        inventory.add(new BlackSmithStocksItem("Cooper Ore", ForagingMineral.Copper, 75 , Integer.MAX_VALUE));
        inventory.add(new BlackSmithStocksItem("Iron Ore", ForagingMineral.Iron, 150 , Integer.MAX_VALUE));
        inventory.add(new BlackSmithStocksItem("Coal", ForagingMineral.Coal, 150 , Integer.MAX_VALUE));
        inventory.add(new BlackSmithStocksItem("Gold Ore", ForagingMineral.Gold, 400 , Integer.MAX_VALUE));
        inventory.add(new BlackSmithToolUpgradeItem("Cooper Tool", ArtisanGoodType.CopperBar,5,2000,1));
        inventory.add(new BlackSmithToolUpgradeItem("Steel Tool", ArtisanGoodType.IronBar,5,5000,1));
        inventory.add(new BlackSmithToolUpgradeItem("Gold Tool", ArtisanGoodType.GoldBar,5,10000,1));
        inventory.add(new BlackSmithToolUpgradeItem("Iridium Tool", ArtisanGoodType.IridiumBar,5,25000,1));
        inventory.add(new BlackSmithToolUpgradeItem("Cooper Trash Can", ArtisanGoodType.CopperBar,5,1000,1));
        inventory.add(new BlackSmithToolUpgradeItem("Steel Trash Can", ArtisanGoodType.IronBar,5,2500,1));
        inventory.add(new BlackSmithToolUpgradeItem("Gold Trash Can", ArtisanGoodType.GoldBar,5,5000,1));
        inventory.add(new BlackSmithToolUpgradeItem("Iridium Trash Can", ArtisanGoodType.IridiumBar,5,12500,1));

    }

    @Override
    public String showAllProducts() {
        StringBuilder message = new StringBuilder("Blacksmith products:");
        for (ShopItem item : inventory) {
            message.append("\n" + "Name: ").append(item.name).append("  Price: ").append(item.price);
        }
        return message.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder message = new StringBuilder("Blacksmith Available Products:");
        for (ShopItem item : inventory) {
            if (item.remainingQuantity > 0) {
                message.append("\nName: ").append(item.name).append("   Price: ").append(item.price).append("   Remaining: ").append(item.remainingQuantity);
            }
        }
        return message.toString();
    }

    @Override
    public Result purchaseProduct() {
        return null;
    }

    @Override
    public void ResetQuantityEveryNight() {
        for (ShopItem item : inventory) {
            item.resetQuantityEveryNight();
        }
    }


}
