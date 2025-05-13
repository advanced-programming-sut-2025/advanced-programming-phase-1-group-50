package models.stores;

import models.cooking.Food;
import models.manuFactor.artisanGoods.ArtisanGoodType;
import models.recipes.CookingRecipe;

import java.awt.*;
import java.util.ArrayList;

public class StardropSaloon extends Store {

    private ArrayList<ShopItem> inventory;

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
    public char getSymbol() {
        return '☕';
    }

//    @Override
//    public void removeGood() {
//
//    }
//
//    @Override
//    public void addGood() {
//
//    }
//
//    @Override
//    public void sellProduct() {
//
//    }
//
//    @Override
//    public String showAllProducts() {
//        return "";
//    }
//
//    @Override
//    public String showAllAvailableProducts() {
//        return "";
//    }
//
//    @Override
//    public void purchase() {
//
//    }
}
