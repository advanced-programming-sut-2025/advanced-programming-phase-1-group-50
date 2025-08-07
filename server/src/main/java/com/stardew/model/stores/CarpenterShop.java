package com.stardew.model.stores;

import com.stardew.controller.AnimalsControllers.AnimalsController;
import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.animals.HabitatSize;
import com.stardew.model.animals.HabitatType;
import com.stardew.model.gameApp.Game;
import com.stardew.model.mapInfo.Stone;
import com.stardew.model.mapInfo.Wood;
import com.stardew.model.userInfo.Coin;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;

import java.awt.*;
import java.util.ArrayList;

public class CarpenterShop extends Store {
    private ArrayList<ShopItem> inventory;

    public CarpenterShop(int gameId, int x, int y, int width, int height) {
        super(gameId, TextureID.carpenterShopTextureRegion, new Rectangle(x, y, width, height), "Robin", 9, 20);
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

    public Result canPurchaseBuilding(Player player, String productName) {
        ShopItem item = null;

        for (ShopItem i : inventory) {
            if (i.getName().equals(productName)) {
                item = i;
                break;
            }
        }

        if (item == null) {
            return new Result(false, "No such product ");
        }

        if (player.getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0) < item.getPrice()) {
            return new Result(false, "You don't have enough money");
        }

        if (player.getBackpack().getIngredientQuantity().getOrDefault(new Stone(),
            0) < ((CarpenterShopFarmBuildingsItem) item).getStoneCost()) {
            return new Result(false, "You don't have enough stones");
        }

        if (player.getBackpack().getIngredientQuantity().getOrDefault(new Wood(), 0) < ((CarpenterShopFarmBuildingsItem) item).getWoodCost()) {
            return new Result(false, "You don't have enough woods");
        }

        return new Result(true, "");
    }

    public Result purchaseShippingBin(Game game, Player player, int x, int y) {
        if (!player.getFarm().getRectangle().contains(x, y)) {
            return new Result(false, "You don't own this area");
        }

        game.getMap().addShippingBin(x, y);
        player.getBackpack().removeIngredients(new Coin(), inventory.getLast().getPrice());
        player.getBackpack().removeIngredients(new Wood(),
            ((CarpenterShopFarmBuildingsItem) inventory.getLast()).getWoodCost());
        return new Result(true, "You successfully purchased a shipping bin");
    }

    public Result purchaseBuilding(Game game, Player player, String productName, int x, int y , ClientConnectionThread connectionThread) {
        Result result = new Result() ;

        result = switch (productName) {
            case "Barn" -> AnimalsController.getInstance().build(game, player, x, y, "barn",connectionThread);
            case "Big Barn" -> AnimalsController.getInstance().build(game, player, x, y, "big_barn",connectionThread);
            case "Deluxe Barn" -> AnimalsController.getInstance().build(game, player, x, y, "deluxe_barn",connectionThread);
            case "Coop" -> AnimalsController.getInstance().build(game, player, x, y, "coop",connectionThread);
            case "Big Coop" -> AnimalsController.getInstance().build(game, player, x, y, "big_coop",connectionThread);
            case "Deluxe Coop" -> AnimalsController.getInstance().build(game, player, x, y, "deluxe_coop",connectionThread);
            default -> result;
        };

        ShopItem item = null;

        for (ShopItem i : inventory) {
            if (i.getName().equals(productName)) {
                item = i;
                break;
            }
        }

        if (item == null) {
            return new Result(false, "Not such product");
        }

        item.decreaseRemainingQuantity(1);
        player.getBackpack().removeIngredients(new Coin(), item.getPrice());
        player.getBackpack().removeIngredients(new Stone(), ((CarpenterShopFarmBuildingsItem) item).getStoneCost());
        player.getBackpack().removeIngredients(new Wood(), ((CarpenterShopFarmBuildingsItem) item).getWoodCost());

        return result;
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
        return 'w';
    }
}
