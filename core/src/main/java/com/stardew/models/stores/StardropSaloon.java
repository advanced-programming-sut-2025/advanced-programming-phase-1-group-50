package com.stardew.models.stores;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.BackgroundColors;
import com.stardew.models.ColorPrinter;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.cooking.Food;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGoodType;
import com.stardew.models.recipes.CookingRecipe;
import com.stardew.models.userInfo.Coin;

import java.awt.*;
import java.util.ArrayList;

public class StardropSaloon extends Store {
    private final String backgroundCode = BackgroundColors.BRIGHT_BLUE;
    private final String colorCode= ColorPrinter.PURPLE;
    private ArrayList<ShopItem> inventory;
    private final TextureRegion[][] regions = GamePictureManager.stardopSaloonRegions;
    private final TextureRegion texture = new TextureRegion(GamePictureManager.stardopSaloonTexture);

    public StardropSaloon(int x, int y, int width, int height) {
        super(new Rectangle(x, y, width, height), "Gus", 12, 24);
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
    public ArrayList<ShopItem> showAllProducts() {
        return (ArrayList<ShopItem>) inventory.clone();
//        StringBuilder message = new StringBuilder("StardopSaloon products:");
//        for (ShopItem item : inventory) {
//            message.append("\n" + "Name: ").append(item.name).append("  Price: ").append(item.price);
//        }
//        return message.toString();
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
//        StringBuilder message = new StringBuilder("StardopSaloon Available Products:");
//        for (ShopItem item : inventory) {
//            if (item.remainingQuantity > 0) {
//                message.append("\nName: ").append(item.name).append("   Price: ").append(item.price).append("   Remaining: ");
//                if (item.remainingQuantity > 10000) {
//                    message.append("infinity");
//                } else {
//                    message.append(item.remainingQuantity);
//                }
//            }
//        }
//        return message.toString();
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

        int totalPrice = item.getPrice() * value;

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0) < totalPrice) {
            return new Result(false, "Not enough money");
        }

        if (item.getRemainingQuantity() < value) {
            return new Result(false, "Not enough stock");
        }

        if (item instanceof StardopSaloonRecipeItem) {

                App.getGame().getCurrentPlayingPlayer().getBackpack().addRecipe(((StardopSaloonRecipeItem) item).getRecipe());

        } else if (item instanceof StardopSaloonArtisanGoodItem) {

           if (!App.getGame().getCurrentPlayingPlayer().getBackpack().hasCapacity()) {
               return new Result(false, "Not enough capacity in your inventory");
           }

           App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new ArtisanGood(((StardopSaloonArtisanGoodItem) item).getType()),value);

        } else if (item instanceof StardopSaloonFoodItem) {

            if (!App.getGame().getCurrentPlayingPlayer().getBackpack().hasCapacity()) {
                return new Result(false, "Not enough capacity in your inventory");
            }

            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(((StardopSaloonFoodItem) item).getFood(),value);

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
    public TextureRegion[][] getRegions() {
        return regions;
    }

    @Override
    public char getSymbol() {
        return '0';
    }

    @Override
    public String getColor(){
        return colorCode;
    }

    @Override
    public String getBackground(){
        return backgroundCode;
    }

    @Override
    public TextureRegion getTexture() {
        return texture;
    }

    @Override
    public Color getMiniMapColor() {
        return Color.PINK;
    }


}
