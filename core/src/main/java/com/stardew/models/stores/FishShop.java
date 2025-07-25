package com.stardew.models.stores;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.BackgroundColors;
import com.stardew.models.ColorPrinter;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.cooking.Food;
import com.stardew.models.recipes.CraftingRecipes;
import com.stardew.models.tools.FishingPole;
import com.stardew.models.tools.PoleType;
import com.stardew.models.userInfo.Coin;

import java.awt.*;
import java.util.ArrayList;

public class FishShop extends Store {
    private final  String backgroundCode = BackgroundColors.BRIGHT_CYAN;
    private final String colorCode = ColorPrinter.BRIGHT_BLUE;
    private ArrayList<ShopItem> inventory;

    public FishShop(int x, int y, int width, int height) {
        super(GamePictureManager.fishShopRegions,new TextureRegion(GamePictureManager.fishShopTexture),new Rectangle(x, y, width, height), "willy", 9, 17);
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
    public Result purchaseProduct(int value, String productName) {

        ShopItem item = null;

        for (ShopItem i : inventory) {
            if (i.getName().equals(productName)) {
                item = i;
            }
        }


        int totalPrice = item.getPrice() * value;
        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0) < totalPrice) {
            return new Result(false, "Not enough money");
        }

        if (item.remainingQuantity < value) {
            return new Result(false, "Not enough stock");
        }


        if (item instanceof FishShopPoleItem) {

            if (((FishShopPoleItem) item).getFishingSkillRequired() < App.getGame().getCurrentPlayingPlayer().getAbility().getFishingLevel()) {
                return new Result(false, "your fishing level must be at least " + ((FishShopPoleItem) item).getFishingSkillRequired());
            }

            App.getGame().getCurrentPlayingPlayer().getBackpack().addTool(new FishingPole((((FishShopPoleItem) item).getType())));
            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(), (-1) * totalPrice);

        } else if (item instanceof FishShopCraftingRecipe) {

            App.getGame().getCurrentPlayingPlayer().getBackpack().addRecipe(((FishShopCraftingRecipe) item).getRecipe());
            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(), (-1) * totalPrice);

        } else {

            if(!App.getGame().getCurrentPlayingPlayer().getBackpack().hasCapacity()){
                return new Result(false, "Not enough capacity in your inventory");
            }
            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(Food.TroutSoup,value);
            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(), (-1) * totalPrice);
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

    @Override
    public String getColor(){
        return colorCode;
    }

    @Override
    public String getBackground(){
        return backgroundCode;
    }

    @Override
    public Color getMiniMapColor() {
        return Color.CYAN;
    }

}
