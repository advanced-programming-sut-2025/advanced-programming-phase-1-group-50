package com.stardew.model.stores;

import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.cooking.Food;
import com.stardew.model.gameApp.TimeProvider;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.recipes.CookingRecipe;
import com.stardew.model.userInfo.Coin;
import com.stardew.model.userInfo.Player;

import java.awt.*;
import java.util.ArrayList;

public class StardopSaloon extends Store{
    private ArrayList<ShopItem> inventory;

    public StardopSaloon(TimeProvider timeProvider, int x, int y, int width, int height) {
        super(timeProvider,TextureID.stardopSaloonRegion,new Rectangle(x,y,width,height),"Gus",12,24);
    }

    @Override
    public void loadInventory() {

        inventory = new ArrayList<>();
        inventory.add(new StardopSaloonArtisanGoodItem("Beer", ArtisanGoodType.Beer,400,Integer.MAX_VALUE));
        inventory.add(new StardopSaloonArtisanGoodItem("Coffee", ArtisanGoodType.Coffee,300,Integer.MAX_VALUE));
        inventory.add(new StardopSaloonFoodItem("Salad", Food.Salad,220,Integer.MAX_VALUE));
        inventory.add(new StardopSaloonFoodItem("Bread", Food.Bread,120,Integer.MAX_VALUE));
        inventory.add(new StardopSaloonFoodItem("Spaghetti", Food.Spaghetti,240,Integer.MAX_VALUE));
        inventory.add(new StardopSaloonFoodItem("Pizza", Food.Pizza,600,Integer.MAX_VALUE));
        inventory.add(new StardopSaloonRecipeItem("Hashbrowns", CookingRecipe.HashBrowns,50,1));
        inventory.add(new StardopSaloonRecipeItem("Omelet", CookingRecipe.Omelet,100,1));
        inventory.add(new StardopSaloonRecipeItem("Pancakes", CookingRecipe.Pancakes,100,1));
        inventory.add(new StardopSaloonRecipeItem("Bread", CookingRecipe.Bread,100,1));
        inventory.add(new StardopSaloonRecipeItem("Tortilla", CookingRecipe.Tortilla,100,1));
        inventory.add(new StardopSaloonRecipeItem("Pizza", CookingRecipe.Pizza,150,1));
        inventory.add(new StardopSaloonRecipeItem("Maki Roll", CookingRecipe.MakiRoll,300,1));
        inventory.add(new StardopSaloonRecipeItem("Triple Shot  Espresso", CookingRecipe.TripleShotEspresso,5000,1));
        inventory.add(new StardopSaloonRecipeItem("Cookie", CookingRecipe.Cookie,300,1));

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

        if (item.getRemainingQuantity() < value) {
            return new Result(false, "Not enough stock");
        }

        if (item instanceof StardopSaloonRecipeItem) {

           player.getBackpack().addRecipe(((StardopSaloonRecipeItem) item).getRecipe());

        } else if (item instanceof StardopSaloonArtisanGoodItem) {

            if (!player.getBackpack().hasCapacity()) {
                return new Result(false, "Not enough capacity in your inventory");
            }

            player.getBackpack().addIngredients(new ArtisanGood(((StardopSaloonArtisanGoodItem) item).getType()),value);

        } else if (item instanceof StardopSaloonFoodItem) {

            if (!player.getBackpack().hasCapacity()) {
                return new Result(false, "Not enough capacity in your inventory");
            }

           player.getBackpack().addIngredients(((StardopSaloonFoodItem) item).getFood(),value);

        }

        player.getBackpack().removeIngredients(new Coin(), totalPrice);
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
        return '0';
    }

}
