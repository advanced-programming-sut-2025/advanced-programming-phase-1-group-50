package com.stardew.model.stores;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.model.Result;

import java.awt.*;
import java.util.ArrayList;

public class MarnieRanch extends Store{
//    private final String backgroundCode = BackgroundColors.RED;
//    private final String colorCode = ColorPrinter.PINK;
    private ArrayList<ShopItem> inventory;

    public MarnieRanch(int x, int y, int width, int height) {
//        super(GamePictureManager.marnieRanchRegions,new TextureRegion(GamePictureManager.marnieRanchTexture),new Rectangle(x, y, width, height), "Marnie", 9, 16);
        super(null,null,new Rectangle(x,y,width,height),"Marnie",9,16);
    }


    @Override
    public char getSymbol() {
        return '♞';
    }

    @Override
    public void loadInventory() {

        inventory = new ArrayList<>();
//        inventory.add(new MarnieRanchLiveStockItem("Chicken", AnimalType.Chicken, 800, 2));
//        inventory.add(new MarnieRanchLiveStockItem("Cow", AnimalType.Cow, 1500, 2));
//        inventory.add(new MarnieRanchLiveStockItem("Goat", AnimalType.Goat, 4000, 2));
//        inventory.add(new MarnieRanchLiveStockItem("Duck", AnimalType.Duck, 1200, 2));
//        inventory.add(new MarnieRanchLiveStockItem("Sheep", AnimalType.Sheep, 8000, 2));
//        inventory.add(new MarnieRanchLiveStockItem("Rabbit", AnimalType.Rabbit, 8000, 2));
//        inventory.add(new MarnieRanchLiveStockItem("Dinosaur", AnimalType.Dinosaur, 14000, 2));
//        inventory.add(new MarnieRanchLiveStockItem("Pig", AnimalType.Pig, 16000, 2));
//        inventory.add(new ShopItem("Hay", 50, Integer.MAX_VALUE));
//        inventory.add(new ShopItem("Milk Pail", 1000, 1));
//        inventory.add(new ShopItem("Shears", 1000, 1));

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

    public Result purchaseAnimal(String productName , String animalName) {
        //TODO
        return null;
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

//    @Override
//    public String getColor(){
//        return colorCode;
//    }
//
//    @Override
//    public String getBackground(){
//        return backgroundCode;
//    }
//
//    @Override
//    public Color getMiniMapColor() {
//        return Color.YELLOW;
//    }

}
