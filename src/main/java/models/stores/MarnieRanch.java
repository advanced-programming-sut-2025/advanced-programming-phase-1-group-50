package models.stores;

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
