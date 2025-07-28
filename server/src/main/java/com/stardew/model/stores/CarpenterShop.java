package com.stardew.model.stores;

import com.stardew.model.Result;

import java.awt.*;
import java.util.ArrayList;

public class CarpenterShop extends Store{
//    private final String backgroundCode = BackgroundColors.BRIGHT_RED;
//    private final String colorCode = ColorPrinter.BRIGHT_CYAN;
    private ArrayList<ShopItem> inventory;

    public CarpenterShop(int x, int y, int width, int height) {
        //super(GamePictureManager.carpenterShopRegions, new TextureRegion(GamePictureManager.carpenterShopTexture), new Rectangle(x, y, width, height), "Robin", 9, 20);
        super(null,null,new Rectangle(x,y,width,height),"Robin",9,20);
    }

    @Override
    public void loadInventory() {

        this.inventory = new ArrayList<>();
//        inventory.add(new ShopItem("Wood", 10, Integer.MAX_VALUE));
//        inventory.add(new ShopItem("Stone", 20, Integer.MAX_VALUE));
//        inventory.add(new CarpenterShopFarmBuildingsItem("Barn", HabitatType.Barn, HabitatSize.Regular, 6000, 350,
//            150, 1));
//        inventory.add(new CarpenterShopFarmBuildingsItem("Big Barn", HabitatType.Barn, HabitatSize.Big, 12000, 450,
//            200, 1));
//        inventory.add(new CarpenterShopFarmBuildingsItem("Deluxe Barn", HabitatType.Barn, HabitatSize.Deluxe, 25000,
//            550, 300, 1));
//        inventory.add(new CarpenterShopFarmBuildingsItem("Coop", HabitatType.Coop, HabitatSize.Regular, 4000, 300,
//            100, 1));
//        inventory.add(new CarpenterShopFarmBuildingsItem("Big Coop", HabitatType.Coop, HabitatSize.Big, 10000, 400,
//            150, 1));
//        inventory.add(new CarpenterShopFarmBuildingsItem("Deluxe Coop", HabitatType.Coop, HabitatSize.Deluxe, 20000,
//            500, 200, 1));
//        inventory.add(new CarpenterShopFarmBuildingsItem("Shipping Bin", 250, 150, 0, Integer.MAX_VALUE));

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
        //TODO
        return null;
    }

    public Result canPurchaseShippingBin() {
        //TODO
        return null;
    }

    public Result purchaseShippingBin(int x, int y) {
        //TODO
        return null;
    }

    public void purchaseBuilding(String productName) {
        //TODO
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

//    @Override
//    public String getColor() {
//        return colorCode;
//    }
//
//    @Override
//    public String getBackground() {
//        return backgroundCode;
//    }
//
//    @Override
//    public Color getMiniMapColor() {
//        return Color.LIGHT_GRAY;
//    }
}
