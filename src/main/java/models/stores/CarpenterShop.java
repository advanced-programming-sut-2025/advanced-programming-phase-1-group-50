package models.stores;

import models.Result;
import models.animals.HabitatSize;
import models.animals.HabitatType;
import models.app.App;
import models.mapInfo.Farm;
import models.mapInfo.Stone;
import models.mapInfo.Wood;
import models.userInfo.Coin;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class CarpenterShop extends Store {

    private ArrayList<ShopItem> inventory;

    public CarpenterShop(int x, int y, int width, int height) {
        super(new Rectangle(x, y, width, height), "Robin", 9, 20);
    }

    @Override
    public void loadInventory() {

        this.inventory = new ArrayList<>();
        inventory.add(new ShopItem("Wood", 10, Integer.MAX_VALUE));
        inventory.add(new ShopItem("Stone", 20, Integer.MAX_VALUE));
        inventory.add(new CarpenterShopFarmBuildingsItem("Barn", HabitatType.Barn, HabitatSize.Regular, 6000, 350,
                150, 1));
        inventory.add(new CarpenterShopFarmBuildingsItem("Big Barn", HabitatType.Barn, HabitatSize.Big, 12000, 450,
                200, 1));
        inventory.add(new CarpenterShopFarmBuildingsItem("Deluxe Barn", HabitatType.Barn, HabitatSize.Deluxe, 25000,
                550, 300, 1));
        inventory.add(new CarpenterShopFarmBuildingsItem("Coop", HabitatType.Coop, HabitatSize.Regular, 4000, 300,
                100, 1));
        inventory.add(new CarpenterShopFarmBuildingsItem("Big Coop", HabitatType.Coop, HabitatSize.Big, 10000, 400,
                150, 1));
        inventory.add(new CarpenterShopFarmBuildingsItem("Deluxe Coop", HabitatType.Coop, HabitatSize.Deluxe, 20000,
                500, 200, 1));
        inventory.add(new CarpenterShopFarmBuildingsItem("Shipping Bin", 250, 150, 0, Integer.MAX_VALUE));

    }

    @Override
    public String showAllProducts() {
        StringBuilder message = new StringBuilder("CarpenterShop products:");
        for (ShopItem item : inventory) {
            message.append("\n" + "Name: ").append(item.name).append("  Price: ").append(item.price);
        }
        return message.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder message = new StringBuilder("CarpenterShop Available Products:");
        for (ShopItem item : inventory) {
            if (item.remainingQuantity > 0) {
                message.append("\nName: ").append(item.name).append("   Price: ").append(item.price).append("   " +
                        "Remaining: ").append(item.remainingQuantity);
            }
        }
        return message.toString();
    }

    public boolean purchaseBuilding(HabitatType type, HabitatSize size) {

        ShopItem item = null;

        for (ShopItem i : inventory) {
            if (i instanceof CarpenterShopFarmBuildingsItem) {
                if (((CarpenterShopFarmBuildingsItem) i).getHabitatType().equals(type) && ((CarpenterShopFarmBuildingsItem) i).getHabitatSize().equals(size)) {
                    item = i;
                }
            }
        }

        if (item == null) {
            return false;
        }

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().get(new Coin()) < item.price) {
            return false;
        }

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().get(new Stone()) < ((CarpenterShopFarmBuildingsItem) item).getStoneCost()) {
            return false;
        }

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().get(new Wood()) < ((CarpenterShopFarmBuildingsItem) item).getWoodCost()) {
            return false;
        }

        if (item.remainingQuantity == 0) {
            return false;
        }

        item.decreaseRemainingQuantity(1);
        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(new Coin(), item.price);
        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(new Stone(),
                ((CarpenterShopFarmBuildingsItem) item).getStoneCost());
        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(new Wood(),
                ((CarpenterShopFarmBuildingsItem) item).getWoodCost());
        return true;
    }

    @Override
    public Result purchaseProduct(int value, String productName) {

        ShopItem item = null;
        for (ShopItem i : inventory) {
            if (i.name.equals(productName)) {
                item = i;
                break;
            }
        }

        if (item == null) {
            return new Result(false, "No such product");
        }

        if (item instanceof CarpenterShopFarmBuildingsItem && (!item.name.equals("Shipping Bin"))) {
            return new Result(false, "You must build building command in game menu");
        }

        int totalPrice = item.getPrice() * value;

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0) < totalPrice) {
            return new Result(false, "Not enough money");
        }

        if (item.getRemainingQuantity() < value) {
            return new Result(false, "Not enough stocks");
        }

        if (item.name.equals("Shipping Bin")) {

            Farm temp = App.getGame().getCurrentPlayingPlayer().getFarm();
            Random rand = new Random();

            while (true) {

                int randomX = rand.nextInt(250);
                int randomY = rand.nextInt(200);

                if (App.getGame().getCurrentPlayingPlayer().getFarm().getRectangle().contains(randomX, randomY)) {
                    if(App.getGame().getMap().addShippingBin(randomX, randomY)) {
                        break;
                    }
                }

            }

        } else if (item.name.equals("Wood")){

            if (!App.getGame().getCurrentPlayingPlayer().getBackpack().hasCapacity()) {
                return new Result(false, "Not enough capacity in your inventory");
            }

            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Wood(), value);

        } else {

            if (!App.getGame().getCurrentPlayingPlayer().getBackpack().hasCapacity()) {
                return new Result(false, "Not enough capacity in your inventory");
            }

            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Stone(), value);

        }

        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(), (-1) * totalPrice);
        item.decreaseRemainingQuantity(value);

        return new Result(true, "You successfully purchased " + value + "number(s) of " + productName);

    }

    @Override
    public void ResetQuantityEveryNight() {
        for (ShopItem item : inventory) {
            item.resetQuantityEveryNight();
        }
    }

    @Override
    public char getSymbol() {
        return 'w';
    }

}
