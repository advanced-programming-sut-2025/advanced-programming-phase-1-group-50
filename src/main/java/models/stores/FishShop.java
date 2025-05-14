package models.stores;

import models.Result;
import models.app.App;
import models.cooking.Food;
import models.recipes.CraftingRecipes;
import models.tools.FishingPole;
import models.tools.PoleType;
import models.userInfo.Coin;

import java.awt.*;
import java.util.ArrayList;

public class FishShop extends Store {

    private ArrayList<ShopItem> inventory;

    public FishShop(int x, int y, int width, int height) {
        super(new Rectangle(x, y, width, height), "willy", 9, 17);
    }

    @Override
    public void loadInventory() {

        inventory = new ArrayList<>();

        inventory.add(new FishShopCraftingRecipe("Fish Smoker", CraftingRecipes.FishSmoker , 10000 , 1));
        inventory.add(new FishShopPoleItem("Training Rod", PoleType.Training , 0 , 25 , 1));
        inventory.add(new FishShopPoleItem("Bamboo Pole", PoleType.Bamboo , 0 , 500 , 1));
        inventory.add(new FishShopPoleItem("Fiberglass Rod", PoleType.Fiberglass , 2 , 1800 , 1));
        inventory.add(new FishShopPoleItem("Iridium Rod", PoleType.Iridium , 4 , 7500 , 1));
        inventory.add(new ShopItem("Trout Soup",250,1));

    }

    @Override
    public String showAllProducts() {
        StringBuilder message = new StringBuilder("FishShop products:");
        for (ShopItem item : inventory) {
            message.append("\n" + "Name: ").append(item.name).append("  Price: ").append(item.price);
        }
        return message.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder message = new StringBuilder("FishShop Available Products:");
        for (ShopItem item : inventory) {
            if (item.remainingQuantity > 0) {
                message.append("\nName: ").append(item.name).append("   Price: ").append(item.price).append("   Remaining: ").append(item.remainingQuantity);
            }
        }
        return message.toString();
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

        int totalPrice = item.getPrice() * value;
        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0) < value) {
            return new Result(false, "Not enough money");
        }

        if (item.remainingQuantity < value) {
            return new Result(false, "Not enough stocks");
        }


        if (item instanceof FishShopPoleItem) {

            if (((FishShopPoleItem) item).getFishingSkillRequired() < App.getGame().getCurrentPlayingPlayer().getAbility().getFishingLevel()) {
                return new Result(false, "your fishing level must be at least " + ((FishShopPoleItem) item).getFishingSkillRequired());
            }

            App.getGame().getCurrentPlayingPlayer().getBackpack().addTool(new FishingPole((((FishShopPoleItem) item).getType())));

        } else if (item instanceof FishShopCraftingRecipe) {

            App.getGame().getCurrentPlayingPlayer().getBackpack().addRecipe(((FishShopCraftingRecipe) item).getRecipe());

        } else {

            if(!App.getGame().getCurrentPlayingPlayer().getBackpack().hasCapacity()){
                return new Result(false, "Not enough capacity in your inventory");
            }
            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(Food.TroutSoup,value);
        }

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
        return '≈';
    }


}
