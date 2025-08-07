package com.stardew.model.stores;

import com.stardew.controller.AnimalsControllers.AnimalsController;
import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.Tools.MilkPail;
import com.stardew.model.Tools.Shear;
import com.stardew.model.animals.AnimalType;
import com.stardew.model.gameApp.Game;
import com.stardew.model.gameApp.TimeProvider;
import com.stardew.model.userInfo.Coin;
import com.stardew.model.userInfo.Player;

import java.awt.*;
import java.util.ArrayList;

public class MarnieRanch extends Store {
    private ArrayList<ShopItem> inventory;

    public MarnieRanch(TimeProvider timeProvider, int x, int y, int width, int height) {
        super(timeProvider, TextureID.marnieRegion, new Rectangle(x, y, width, height), "Marnie", 9, 16);
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
    public ArrayList<ShopItem> getAllProducts() {
        return (ArrayList<ShopItem>) inventory.clone();
    }

    @Override
    public ArrayList<ShopItem> getAvailableProducts() {
        ArrayList<ShopItem> availableProducts = new ArrayList<>();
        for (ShopItem item : inventory) {
            if (item.remainingQuantity > 0) {
                availableProducts.add(item);
            }
        }
        return availableProducts;
    }

    public Result purchaseAnimal(Game game, Player player, String productName, String animalName) {
        ShopItem item = null;

        for (ShopItem i : inventory) {
            if (i.getName().equals(productName)) {
                item = i;
                break;
            }
        }

        if (item == null) {
            return new Result(false,"Not such animal");
        }

        if (player.getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0) < item.getPrice()) {
            return new Result(false, "You don't have enough money to purchase");
        }

        Result result = AnimalsController.getInstance().buyAnimal(game, player, productName, animalName);

        if (result.getSuccessful()) {
            item.decreaseRemainingQuantity(1);
            player.getBackpack().removeIngredients(new Coin(), item.getPrice());
        }

        return result;
    }

    @Override
    public Result purchaseProduct(Player player, String productName, int value) {
        ShopItem item = null;

        for (ShopItem i : inventory) {
            if (i.getName().equals(productName)) {
                item = i;
                break;
            }
        }

        if (item == null) {
            return new Result(false, "No such product");
        }

        int totalPrice = item.getPrice() * value;

        if (player.getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0) < totalPrice) {
            return new Result(false, "Not enough money");
        }

        if (item.getRemainingQuantity() < value) {
            return new Result(false, "Not enough stock");
        }

        switch (item.name) {
            case "Hay" -> player.getBackpack().increaseHay(value);
            case "Milk Pail" -> player.getBackpack().addTool(new MilkPail());
            case "Shears" -> player.getBackpack().addTool(new Shear());
        }

        player.getBackpack().removeIngredients(new Coin(), totalPrice);
        item.decreaseRemainingQuantity(value);

        return new Result(true, "Successfully purchased");
    }

    @Override
    public void ResetQuantityEveryNight() {
        for (ShopItem item : inventory) {
            item.resetQuantityEveryNight();
        }
    }

}
