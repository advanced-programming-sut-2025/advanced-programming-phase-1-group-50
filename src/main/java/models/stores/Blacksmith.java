package models.stores;

import models.ColorPrinter;
import models.Result;
import models.app.App;
import models.foraging.ForagingMineral;
import models.userInfo.Coin;

import java.awt.*;
import java.util.ArrayList;

public class Blacksmith extends Store {
    private final String colorCode = ColorPrinter.GRAY;
    private ArrayList<ShopItem> inventory;

    public Blacksmith(int x, int y, int width, int height) {
        super(new Rectangle(x, y, width, height), "Clint", 9, 16);
    }

    @Override
    public void loadInventory() {

        inventory = new ArrayList<>();
        inventory.add(new BlackSmithStocksItem("Cooper Ore", ForagingMineral.Copper, 75, Integer.MAX_VALUE));
        inventory.add(new BlackSmithStocksItem("Iron Ore", ForagingMineral.Iron, 150, Integer.MAX_VALUE));
        inventory.add(new BlackSmithStocksItem("Coal", ForagingMineral.Coal, 150, Integer.MAX_VALUE));
        inventory.add(new BlackSmithStocksItem("Gold Ore", ForagingMineral.Gold, 400, Integer.MAX_VALUE));
        inventory.add(new BlackSmithToolUpgradeItem("Cooper Tool", 2000, 1));
        inventory.add(new BlackSmithToolUpgradeItem("Steel Tool", 5000, 1));
        inventory.add(new BlackSmithToolUpgradeItem("Gold Tool", 10000, 1));
        inventory.add(new BlackSmithToolUpgradeItem("Iridium Tool", 25000, 1));
        inventory.add(new BlackSmithToolUpgradeItem("Cooper Trash Can", 1000, 1));
        inventory.add(new BlackSmithToolUpgradeItem("Steel Trash Can", 2500, 1));
        inventory.add(new BlackSmithToolUpgradeItem("Gold Trash Can", 5000, 1));
        inventory.add(new BlackSmithToolUpgradeItem("Iridium Trash Can", 12500, 1));

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
                message.append("\nName: ").append(item.name).append("   Price: ").append(item.price).append("   " +
                        "Remaining: ").append(item.remainingQuantity);
            }
        }
        return message.toString();
    }


    @Override
    public void ResetQuantityEveryNight() {
        for (ShopItem item : inventory) {
            item.resetQuantityEveryNight();
        }
    }

    @Override
    public Result purchaseProduct(int value, String productName) {

        ShopItem item = null;

        for (ShopItem i : inventory) {
            if (i.name.equals(productName)) {
                item = i;
            }
        }

        if (item == null) {
            return new Result(false, "No such product");
        }

        if (!(item instanceof BlackSmithStocksItem)) {
            return new Result(false, "You must use Tools upgrade command in game menu");
        }

        int totalPrice = item.getPrice() * value;

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0) < totalPrice) {
            return new Result(false, "Not enough money");
        }

        if (item.getRemainingQuantity() < value) {
            return new Result(false, "Not enough stock");
        }


        if (!App.getGame().getCurrentPlayingPlayer().getBackpack().hasCapacity()) {
            return new Result(false, "Not enough capacity in your inventory");
        }

        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(), (-1) * totalPrice);
        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(((BlackSmithStocksItem) item).getType(),
                value);
        item.decreaseRemainingQuantity(value);

        return new Result(true, "You successfully purchased " + value + "number(s) of " + productName);

    }

    public boolean canUpgradeTool(String toolName) {

        for (ShopItem item : inventory) {
            if (item instanceof BlackSmithStocksItem) {
                continue;
            }
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

    @Override
    public String getColor(){
        return colorCode;
    }

}
