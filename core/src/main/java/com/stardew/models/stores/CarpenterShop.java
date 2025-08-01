package com.stardew.models.stores;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.BackgroundColors;
import com.stardew.models.ColorPrinter;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.animals.HabitatSize;
import com.stardew.models.animals.HabitatType;
import com.stardew.models.app.App;
import com.stardew.models.mapInfo.Stone;
import com.stardew.models.mapInfo.Wood;
import com.stardew.models.userInfo.Coin;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class CarpenterShop extends Store {
    private final String backgroundCode = BackgroundColors.BRIGHT_RED;
    private final String colorCode = ColorPrinter.BRIGHT_CYAN;
    private ArrayList<ShopItem> inventory;

    public CarpenterShop(int x, int y, int width, int height) {
        super(GamePictureManager.carpenterShopRegions, new TextureRegion(GamePictureManager.carpenterShopTexture),
                new Rectangle(x, y, width, height), "Robin", 9, 20);
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

    public Result canPurchaseBuilding(String productName) {

        ShopItem item = null;

        for (ShopItem i : inventory) {
            if (i.getName().equals(productName)) {
                item = i;
                break;
            }
        }

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0) < item.getPrice()) {
            return new Result(false, "You don't have enough money");
        }

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(new Stone(),
                0) < ((CarpenterShopFarmBuildingsItem) item).getStoneCost()) {
            return new Result(false, "You don't have enough stones");
        }

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(new Wood(), 0) < ((CarpenterShopFarmBuildingsItem) item).getWoodCost()) {
            return new Result(false, "You don't have enough woods");
        }

        return new Result(true, "");
    }

    public Result canPurchaseShippingBin() {

        ShopItem item = inventory.getLast();

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0) < item.getPrice()) {
            return new Result(false, "You don't have enough money");
        }

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(new Wood(), 0) < ((CarpenterShopFarmBuildingsItem) item).getWoodCost()) {
            return new Result(false, "You don't have enough woods");
        }

        return new Result(true, "");
    }

    public Result purchaseShippingBin(int x, int y) {
        if (!App.getGame().getCurrentPlayingPlayer().getFarm().getRectangle().contains(x,y)) {
            return new Result(false, "You don't own this area");
        }
        App.getGame().getMap().addShippingBin(x, y);
        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(), (-1) * inventory.getLast().getPrice());
        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(new Wood(),
            ((CarpenterShopFarmBuildingsItem)inventory.getLast()).getWoodCost());
        return new Result(true, "You successfully purchased a shipping bin");
    }

    public void purchaseBuilding(String productName) {
        ShopItem item = null;

        for (ShopItem i : inventory) {
            if (i.getName().equals(productName)) {
                item = i;
                break;
            }
        }

        item.decreaseRemainingQuantity(1);
        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(new Coin(), item.getPrice());
        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(new Stone(),
            ((CarpenterShopFarmBuildingsItem) item).getStoneCost());
        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(new Wood(),
            ((CarpenterShopFarmBuildingsItem) item).getWoodCost());
    }

    @Override
    public Result purchaseProduct(int value, String productName) {

        ShopItem item = null;
        for (ShopItem i : inventory) {
            if (i.getName().equals(productName)) {
                item = i;
                break;
            }
        }

        int totalPrice = item.getPrice() * value;

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0) < totalPrice) {
            return new Result(false, "Not enough money");
        }

        if (item.getRemainingQuantity() < value) {
            return new Result(false, "Not enough stock");
        }

        if (item.name.equals("Wood")) {

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

        return new Result(true, "You successfully purchased " + value + " number(s) of " + productName);

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

    @Override
    public String getColor() {
        return colorCode;
    }

    @Override
    public String getBackground() {
        return backgroundCode;
    }

    @Override
    public Color getMiniMapColor() {
        return Color.LIGHT_GRAY;
    }


}
