package models.stores;

import models.Result;
import models.animals.AnimalType;
import models.animals.HabitatSize;
import models.animals.HabitatType;

import java.awt.*;
import java.util.ArrayList;

public class MarnieRanch extends Store {

    private ArrayList<ShopItem> inventory;

    public MarnieRanch(int x, int y, int width, int height) {
        super(new Rectangle(x, y, width, height), "Marnie", 9, 16);
    }


    @Override
    public char getSymbol() {
        return '♞';
    }

    @Override
    public void loadInventory() {

        inventory = new ArrayList<>();
        inventory.add(new MarnieRanchLiveStockItem("Chicken", AnimalType.Chicken, HabitatType.Coop , HabitatSize.Regular, 800 ,2));
        inventory.add(new MarnieRanchLiveStockItem("Cow", AnimalType.Cow, HabitatType.Barn , HabitatSize.Regular, 1500 ,2));
        inventory.add(new MarnieRanchLiveStockItem("Goat", AnimalType.Goat, HabitatType.Barn , HabitatSize.Big, 4000 ,2));
        inventory.add(new MarnieRanchLiveStockItem("Duck", AnimalType.Duck, HabitatType.Coop , HabitatSize.Big, 1200 ,2));
        inventory.add(new MarnieRanchLiveStockItem("Sheep", AnimalType.Sheep, HabitatType.Barn , HabitatSize.Deluxe, 8000 ,2));
        inventory.add(new MarnieRanchLiveStockItem("Rabbit", AnimalType.Rabbit, HabitatType.Coop , HabitatSize.Deluxe, 8000 ,2));
        inventory.add(new MarnieRanchLiveStockItem("Dinosaur", AnimalType.Dinosaur, HabitatType.Coop , HabitatSize.Big, 14000 ,2));
        inventory.add(new MarnieRanchLiveStockItem("Pig", AnimalType.Pig, HabitatType.Barn , HabitatSize.Deluxe, 16000 ,2));
        inventory.add(new ShopItem("Hay",50,Integer.MAX_VALUE));
        inventory.add(new ShopItem("Milk Pail",1000,1));
        inventory.add(new ShopItem("Shears",1000,1));

    }

    @Override
    public String showAllProducts() {
        StringBuilder message = new StringBuilder("MarnieRanch products:");
        for (ShopItem item : inventory) {
            message.append("\n" + "Name: ").append(item.name).append("  Price: ").append(item.price);
        }
        return message.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder message = new StringBuilder("MarnieRanch Available Products:");
        for (ShopItem item : inventory) {
            if (item.remainingQuantity > 0) {
                message.append("\nName: ").append(item.name).append("   Price: ").append(item.price).append("   Remaining: ").append(item.remainingQuantity);
            }
        }
        return message.toString();
    }

    @Override
    public Result purchaseProduct(int value, String productName) {
        return null;
    }

    @Override
    public void ResetQuantityEveryNight() {
        for (ShopItem item : inventory) {
            item.resetQuantityEveryNight();
        }
    }

}
