package models.stores;

import models.BackgroundColors;
import models.ColorPrinter;
import models.Result;
import models.animals.AnimalType;
import models.app.App;
import models.tools.MilkPail;
import models.tools.Shear;
import models.userInfo.Coin;

import java.awt.*;
import java.util.ArrayList;

public class MarnieRanch extends Store {
    private final String backgroundCode = BackgroundColors.RED;
    private final String colorCode = ColorPrinter.PINK;
    private ArrayList<ShopItem> inventory;

    public MarnieRanch(int x, int y, int width, int height) {
        super(new Rectangle(x, y, width, height), "Marnie", 9, 16);
    }


    @Override
    public char getSymbol() {
        return '♞';
    }

    @Override
    public void loadInventory() {

        inventory = new ArrayList<>();
        inventory.add(new MarnieRanchLiveStockItem("Chicken", AnimalType.Chicken, 800, 2));
        inventory.add(new MarnieRanchLiveStockItem("Cow", AnimalType.Cow, 1500, 2));
        inventory.add(new MarnieRanchLiveStockItem("Goat", AnimalType.Goat, 4000, 2));
        inventory.add(new MarnieRanchLiveStockItem("Duck", AnimalType.Duck, 1200, 2));
        inventory.add(new MarnieRanchLiveStockItem("Sheep", AnimalType.Sheep, 8000, 2));
        inventory.add(new MarnieRanchLiveStockItem("Rabbit", AnimalType.Rabbit, 8000, 2));
        inventory.add(new MarnieRanchLiveStockItem("Dinosaur", AnimalType.Dinosaur, 14000, 2));
        inventory.add(new MarnieRanchLiveStockItem("Pig", AnimalType.Pig, 16000, 2));
        inventory.add(new ShopItem("Hay", 50, Integer.MAX_VALUE));
        inventory.add(new ShopItem("Milk Pail", 1000, 1));
        inventory.add(new ShopItem("Shears", 1000, 1));

    }

    @Override
    public String showAllProducts() {
        StringBuilder message = new StringBuilder("MarnieRanch products:");
        for (ShopItem item : inventory) {
            message.append("\n" + "Name: ").append(item.name).append("  Price: ").append(item.price);
        }
        return message.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder message = new StringBuilder("MarnieRanch Available Products:");
        for (ShopItem item : inventory) {
            if (item.remainingQuantity > 0) {
                message.append("\nName: ").append(item.name).append("   Price: ").append(item.price).append("   " +
                        "Remaining: ");
                if (item.remainingQuantity > 10000) {
                    message.append("infinity");
                } else {
                    message.append(item.remainingQuantity);
                }
            }
        }
        return message.toString();
    }

    public Result PurchaseAnimal(AnimalType type) {

        if (!this.isOpen()) {
            return new Result(false, "this store is currently closed");
        }

        ShopItem item = null;

        for (ShopItem i : inventory) {
            if (i instanceof MarnieRanchLiveStockItem) {
                if (((MarnieRanchLiveStockItem) i).getType().equals(type)) {
                    item = i;
                }
            }
        }

        if (item == null) {
            return new Result(false, "No such animal");
        }

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(new Coin(),0) < item.price) {
            return new Result(false, "You don't have enough money to purchase");
        }

        if (item.remainingQuantity == 0) {
            return new Result(false, "Not enough stock");
        }

        item.decreaseRemainingQuantity(1);
        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(new Coin(), item.price);

        return new Result(true , "you purchased a" + item.name + "successfully");
    }

    @Override
    public Result purchaseProduct(int value, String productName) {

        if (!this.isOpen()) {
            return new Result(false, "this store is currently closed");
        }

        ShopItem item = null;

        for (ShopItem i : inventory) {
            if (i.name.equals(productName)) {
                item = i;
            }
        }

        if (item == null) {
            return new Result(false, "No such product");
        }

        if (item instanceof MarnieRanchLiveStockItem) {
            return new Result(false, "You must use buy animal command in game menu");
        }

        int totalPrice = item.getPrice() * value;

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0) < totalPrice) {
            return new Result(false, "Not enough money");
        }

        if (item.getRemainingQuantity() < value) {
            return new Result(false, "Not enough stock");
        }

        if (item.name.equals("Hay")) {

            App.getGame().getCurrentPlayingPlayer().getBackpack().increaseHay(value);

        } else if (item.name.equals("Milk Pail")) {

            App.getGame().getCurrentPlayingPlayer().getBackpack().addTool(new MilkPail());

        } else if (item.name.equals("Shears")) {

            App.getGame().getCurrentPlayingPlayer().getBackpack().addTool(new Shear());

        }

        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(), (-1) * totalPrice);
        item.decreaseRemainingQuantity(value);

        return new Result(true, "");

    }

    @Override
    public void ResetQuantityEveryNight() {
        for (ShopItem item : inventory) {
            item.resetQuantityEveryNight();
        }
    }

    @Override
    public String getColor(){
        return colorCode;
    }

    @Override
    public String getBackground(){
        return backgroundCode;
    }


}
