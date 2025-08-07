package com.stardew.model.stores;

import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.Tools.FishingPole;
import com.stardew.model.Tools.PoleType;
import com.stardew.model.cooking.Food;
import com.stardew.model.recipes.CraftingRecipes;
import com.stardew.model.userInfo.Coin;
import com.stardew.model.userInfo.Player;

import java.awt.*;
import java.util.ArrayList;

public class FishShop extends Store {
    private ArrayList<ShopItem> inventory;

    public FishShop(int gameId, int x, int y, int width, int height) {
        super(gameId, TextureID.fishShopTextureRegion, new Rectangle(x, y, width, height), "Willy", 9, 17);
    }

    @Override
    public void loadInventory() {

        inventory = new ArrayList<>();

        inventory.add(new FishShopCraftingRecipe("Fish Smoker", CraftingRecipes.FishSmoker, 10000, 1));
        inventory.add(new FishShopPoleItem("Training Rod", PoleType.Training, 0, 25, 1));
        inventory.add(new FishShopPoleItem("Bamboo Pole", PoleType.Bamboo, 0, 500, 1));
        inventory.add(new FishShopPoleItem("Fiberglass Rod", PoleType.Fiberglass, 2, 1800, 1));
        inventory.add(new FishShopPoleItem("Iridium Rod", PoleType.Iridium, 4, 7500, 1));
        inventory.add(new ShopItem("Trout Soup", 250, 1));

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

    @Override
    public Result purchaseProduct(Player player, String productName, int value) {
        ShopItem item = null;

        for (ShopItem i : inventory) {
            if (i.getName().equals(productName)) {
                item = i;
            }
        }

        if (item == null) {
            return new Result(false, "No such product ");
        }


        int totalPrice = item.getPrice() * value;
        if (player.getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0) < totalPrice) {
            return new Result(false, "Not enough money");
        }

        if (item.remainingQuantity < value) {
            return new Result(false, "Not enough stock");
        }


        if (item instanceof FishShopPoleItem) {

            if (((FishShopPoleItem) item).getFishingSkillRequired() < player.getAbility().getFishingLevel()) {
                return new Result(false,
                    "your fishing level must be at least " + ((FishShopPoleItem) item).getFishingSkillRequired());
            }

            player.getBackpack().addTool(new FishingPole((((FishShopPoleItem) item).getType())));
            player.getBackpack().removeIngredients(new Coin(), totalPrice);

        } else if (item instanceof FishShopCraftingRecipe) {

            player.getBackpack().addRecipe(((FishShopCraftingRecipe) item).getRecipe());
            player.getBackpack().removeIngredients(new Coin(), totalPrice);

        } else {

            if (!player.getBackpack().hasCapacity()) {
                return new Result(false, "Not enough capacity in your inventory");
            }
            player.getBackpack().addIngredients(Food.TroutSoup, value);
            player.getBackpack().removeIngredients(new Coin(), totalPrice);
            item.decreaseRemainingQuantity(value);

        }

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
        return '≈';
    }

}
