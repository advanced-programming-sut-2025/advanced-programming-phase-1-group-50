package models.stores;

import models.Result;
import models.animals.HabitatSize;
import models.animals.HabitatType;

import java.awt.*;
import java.util.ArrayList;

public class CarpenterShop extends Store {

    private ArrayList<ShopItem> inventory;

    public CarpenterShop(int x, int y, int width, int height) {
        super(new Rectangle(x, y, width, height), "Robin", 9, 20);
    }

    @Override
    public void loadInventory() {

        this.inventory = new ArrayList<>();
        inventory.add(new ShopItem("Wood",10,Integer.MAX_VALUE));
        inventory.add(new ShopItem("Stone",20,Integer.MAX_VALUE));
        inventory.add(new CarpenterShopFarmBuildingsItem("Barn", HabitatType.Barn, HabitatSize.Regular,6000,350,150,1));
        inventory.add(new CarpenterShopFarmBuildingsItem("Big Barn", HabitatType.Barn, HabitatSize.Big,12000,450,200,1));
        inventory.add(new CarpenterShopFarmBuildingsItem("Deluxe Barn", HabitatType.Barn, HabitatSize.Deluxe,25000,550,300,1));
        inventory.add(new CarpenterShopFarmBuildingsItem("Coop", HabitatType.Coop, HabitatSize.Regular,4000,300,100,1));
        inventory.add(new CarpenterShopFarmBuildingsItem("Big Coop", HabitatType.Coop, HabitatSize.Big,10000,400,150,1));
        inventory.add(new CarpenterShopFarmBuildingsItem("Deluxe Coop", HabitatType.Coop, HabitatSize.Deluxe,20000,500,200,1));
        inventory.add(new CarpenterShopFarmBuildingsItem("Shipping Bin",250,150,0,Integer.MAX_VALUE));

    }

    @Override
    public String showAllProducts() {
        StringBuilder message = new StringBuilder("CarpenterShop products:");
        for (ShopItem item : inventory) {
            message.append("\n" + "Name: ").append(item.name).append("  Price: ").append(item.price);
        }
        return message.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder message = new StringBuilder("CarpenterShop Available Products:");
        for (ShopItem item : inventory) {
            if (item.remainingQuantity > 0) {
                message.append("\nName: ").append(item.name).append("   Price: ").append(item.price).append("   Remaining: ").append(item.remainingQuantity);
            }
        }
        return message.toString();
    }

    @Override
    public Result purchaseProduct() {
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
