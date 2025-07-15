package com.stardew.models.stores;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.ColorPrinter;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.foraging.ForagingMineral;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGoodType;
import com.stardew.models.tools.Tool;
import com.stardew.models.userInfo.Coin;
import com.stardew.models.userInfo.Player;
import com.stardew.models.userInfo.TrashCan;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Blacksmith extends Store {
    private final String colorCode = ColorPrinter.GRAY;
    private final String backgroundCode = ColorPrinter.WHITE;
    private ArrayList<ShopItem> inventory;
    private final TextureRegion texture = new TextureRegion(GamePictureManager.blacksmithTexture);
    private final TextureRegion[][] regions = GamePictureManager.blacksmithRegions;

    public Blacksmith(int x, int y, int width, int height) {
        super(new Rectangle(x, y, width, height), "Clint", 9, 16);
    }

    @Override
    public void loadInventory() {

        inventory = new ArrayList<>();
        inventory.add(new BlackSmithStocksItem("Copper Ore", ForagingMineral.Copper, 75, Integer.MAX_VALUE));
        inventory.add(new BlackSmithStocksItem("Iron Ore", ForagingMineral.Iron, 150, Integer.MAX_VALUE));
        inventory.add(new BlackSmithStocksItem("Coal", ForagingMineral.Coal, 150, Integer.MAX_VALUE));
        inventory.add(new BlackSmithStocksItem("Gold Ore", ForagingMineral.Gold, 400, Integer.MAX_VALUE));
        inventory.add(new BlackSmithToolUpgradeItem("Copper Tool", 2000, 1));
        inventory.add(new BlackSmithToolUpgradeItem("Steel Tool", 5000, 1));
        inventory.add(new BlackSmithToolUpgradeItem("Gold Tool", 10000, 1));
        inventory.add(new BlackSmithToolUpgradeItem("Iridium Tool", 25000, 1));
        inventory.add(new BlackSmithToolUpgradeItem("Copper Trash Can", 1000, 1));
        inventory.add(new BlackSmithToolUpgradeItem("Steel Trash Can", 2500, 1));
        inventory.add(new BlackSmithToolUpgradeItem("Gold Trash Can", 5000, 1));
        inventory.add(new BlackSmithToolUpgradeItem("Iridium Trash Can", 12500, 1));

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


    @Override
    public void ResetQuantityEveryNight() {
        for (ShopItem item : inventory) {
            item.resetQuantityEveryNight();
        }
    }

    @Override
    public TextureRegion[][] getRegions() {
        return regions;
    }

    @Override
    public Result purchaseProduct(int value, String productName) {

//        if (!this.isOpen()) {
//            return new Result(false, "this store is currently closed");
//        }

        ShopItem item = null;

        for (ShopItem i : inventory) {
            if (i.name.equals(productName)) {
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


        if (!App.getGame().getCurrentPlayingPlayer().getBackpack().hasCapacity()) {
            return new Result(false, "Not enough capacity in your inventory");
        }

        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(), (-1) * totalPrice);
        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(((BlackSmithStocksItem) item).getType(),
            value);
        item.decreaseRemainingQuantity(value);

        return new Result(true, "You successfully purchased " + value + " number(s) of " + item.getName());
    }

    public Result upgradeTool(String toolName) {
        //fishingPole upgrade nemishe
        //milkPail upgrade nemishe
        //scythe upgrade nemishe
        //shear upgrade nemishe
        Player player = App.getGame().getCurrentPlayingPlayer();
        ArrayList<Tool> tools = player.getBackpack().getTools();
        HashMap<Ingredient, Integer> ingredients = player.getBackpack().getIngredientQuantity();
        int totalPrice;

        if (toolName.equals("TrashCan")) {

            TrashCan trashCan = player.getBackpack().getTrashCan();
            totalPrice = trashCan.getPriceForUpgrade();

            if (player.getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0) < totalPrice) {
                return new Result(false, "Not enough money");
            }

            switch (totalPrice) {
                case 0: {
                    return new Result(false, "This trashCan is at the highest level");
                }
                case 1000: {
                    if (ingredients.getOrDefault(new ArtisanGood(ArtisanGoodType.CopperBar), 0) < 5) {
                        return new Result(false, "you don't have enough Copper bar");
                    }
                    player.getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.CopperBar), -5);
                    inventory.get(8).decreaseRemainingQuantity(1);
                    break;
                }
                case 2500: {
                    if (ingredients.getOrDefault(new ArtisanGood(ArtisanGoodType.IronBar), 0) < 5) {
                        return new Result(false, "you don't have enough Iron bar");
                    }
                    player.getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.IronBar), -5);
                    inventory.get(9).decreaseRemainingQuantity(1);
                    break;
                }
                case 5000: {
                    if (ingredients.getOrDefault(new ArtisanGood(ArtisanGoodType.GoldBar), 0) < 5) {
                        return new Result(false, "you don't have enough Gold bar");
                    }
                    player.getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.GoldBar), -5);
                    inventory.get(10).decreaseRemainingQuantity(1);
                    break;
                }
                case 12500: {
                    if (ingredients.getOrDefault(new ArtisanGood(ArtisanGoodType.IridiumBar), 0) < 5) {
                        return new Result(false, "you don't have enough Iridium bar");
                    }
                    player.getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.IridiumBar), -5);
                    inventory.get(11).decreaseRemainingQuantity(1);
                    break;
                }

            }

            trashCan.upgradeTool();
            player.getBackpack().addIngredients(new Coin(), (-1) * totalPrice);
            return new Result(true, "TrashCan upgraded");

        }

        for (Tool t : tools) {

            if (t.getClass().getSimpleName().equals(toolName)) {

                totalPrice = t.getToolType().getPriceForUpgrade();

                if (player.getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0) < totalPrice) {
                    return new Result(false, "Not enough money");
                }

                switch (totalPrice) {
                    case 0: {
                        return new Result(false, "This tool is at the highest level");
                    }
                    case 2000: {
                        if (ingredients.getOrDefault(new ArtisanGood(ArtisanGoodType.CopperBar), 0) < 5) {
                            return new Result(false, "you don't have enough Copper bar");
                        }
                        player.getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.CopperBar), -5);
                        inventory.get(4).decreaseRemainingQuantity(1);
                        break;
                    }
                    case 5000: {
                        if (ingredients.getOrDefault(new ArtisanGood(ArtisanGoodType.IronBar), 0) < 5) {
                            return new Result(false, "you don't have enough Iron bar");
                        }
                        player.getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.IronBar), -5);
                        inventory.get(5).decreaseRemainingQuantity(1);
                        break;
                    }
                    case 10000: {
                        if (ingredients.getOrDefault(new ArtisanGood(ArtisanGoodType.GoldBar), 0) < 5) {
                            return new Result(false, "you don't have enough Gold bar");
                        }
                        player.getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.GoldBar), -5);
                        inventory.get(6).decreaseRemainingQuantity(1);
                        break;
                    }
                    case 25000: {
                        if (ingredients.getOrDefault(new ArtisanGood(ArtisanGoodType.IridiumBar), 0) < 5) {
                            return new Result(false, "you don't have enough Iridium bar");
                        }
                        player.getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.IridiumBar), -5);
                        inventory.get(7).decreaseRemainingQuantity(1);
                        break;
                    }

                }

                t.upgradeTool();
                player.getBackpack().addIngredients(new Coin(), (-1) * totalPrice);
                return new Result(true, "Tool upgraded");
            }
        }

        return new Result(false, "You don't have this tool");
    }

//    public boolean canUpgradeTool(String toolName) {
//
//        for (ShopItem item : inventory) {
//            if (item.name.equals(toolName)) {
//                if (item.remainingQuantity > 0) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }

    @Override
    public char getSymbol() {
        return '⚒';
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
    public TextureRegion getTexture() {
        return texture;
    }

    @Override
    public Color getMiniMapColor() {
        return Color.BLACK;
    }

}
