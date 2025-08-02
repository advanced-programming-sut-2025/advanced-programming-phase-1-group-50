package com.stardew.model.stores;

import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.date.Season;
import com.stardew.model.mapInfo.foraging.Seeds;

import java.awt.*;
import java.util.ArrayList;

public class JojaMart extends Store {
    private ArrayList<ShopItem> inventory;

    public JojaMart(int gameId,int x, int y, int width, int height) {
        super(gameId,TextureID.jojaMartRegion, new Rectangle(x, y, width, height), "Morris", 9, 23);
    }

    @Override
    public void loadInventory() {

        inventory = new ArrayList<>();

        // Spring
        inventory.add(new JojaMartSeasonsStock("Parsnip Seeds", Seeds.ParsnipSeeds, Season.Spring, 25, 5));
        inventory.add(new JojaMartSeasonsStock("Bean Starter", Seeds.BeanStarter, Season.Spring, 75, 5));
        inventory.add(new JojaMartSeasonsStock("Cauliflower Seeds", Seeds.CauliflowerSeeds, Season.Spring, 100, 5));
        inventory.add(new JojaMartSeasonsStock("Potato Seeds", Seeds.PotatoSeeds, Season.Spring, 62, 5));
        inventory.add(new JojaMartSeasonsStock("Strawberry Seeds", Seeds.StrawberrySeeds, Season.Spring, 100, 5));
        inventory.add(new JojaMartSeasonsStock("Tulip Bulb", Seeds.TulipBulb, Season.Spring, 25, 5));
        inventory.add(new JojaMartSeasonsStock("Kale Seeds", Seeds.KaleSeeds, Season.Spring, 87, 5));
        inventory.add(new JojaMartSeasonsStock("Coffee Beans", Seeds.CoffeeBean, Season.Spring, 200, 1));
        inventory.add(new JojaMartSeasonsStock("Carrot Seeds", Seeds.CarrotSeeds, Season.Spring, 5, 10));
        inventory.add(new JojaMartSeasonsStock("Rhubarb Seeds", Seeds.RhubarbSeeds, Season.Spring, 100, 5));
        inventory.add(new JojaMartSeasonsStock("Jazz Seeds", Seeds.JazzSeeds, Season.Spring, 37, 5));

        // Summer
        inventory.add(new JojaMartSeasonsStock("Tomato Seeds", Seeds.TomatoSeeds, Season.Summer, 62, 5));
        inventory.add(new JojaMartSeasonsStock("Pepper Seeds", Seeds.PepperSeeds, Season.Summer, 50, 5));
        inventory.add(new JojaMartSeasonsStock("Wheat Seeds", Seeds.WheatSeeds, Season.Summer, 12, 10));
        inventory.add(new JojaMartSeasonsStock("Summer Squash Seeds", Seeds.SummerSquashSeeds, Season.Summer, 10, 10));
        inventory.add(new JojaMartSeasonsStock("Radish Seeds", Seeds.RadishSeeds, Season.Summer, 50, 5));
        inventory.add(new JojaMartSeasonsStock("Melon Seeds", Seeds.MelonSeeds, Season.Summer, 100, 5));
        inventory.add(new JojaMartSeasonsStock("Hops Starter", Seeds.HopsStarter, Season.Summer, 75, 5));
        inventory.add(new JojaMartSeasonsStock("Poppy Seeds", Seeds.PoppySeeds, Season.Summer, 125, 5));
        inventory.add(new JojaMartSeasonsStock("Spangle Seeds", Seeds.SpangleSeeds, Season.Summer, 62, 5));
        inventory.add(new JojaMartSeasonsStock("Starfruit Seeds", Seeds.StarfruitSeeds, Season.Summer, 400, 5));
        inventory.add(new JojaMartSeasonsStock("Coffee Beans", Seeds.CoffeeBean, Season.Summer, 200, 1));
        inventory.add(new JojaMartSeasonsStock("Sunflower Seeds", Seeds.SunflowerSeeds, Season.Summer, 125, 5));

        // Fall
        inventory.add(new JojaMartSeasonsStock("Corn Seeds", Seeds.CornSeeds, Season.Fall, 187, 5));
        inventory.add(new JojaMartSeasonsStock("Eggplant Seeds", Seeds.EggplantSeeds, Season.Fall, 25, 5));
        inventory.add(new JojaMartSeasonsStock("Pumpkin Seeds", Seeds.PumpkinSeeds, Season.Fall, 125, 5));
        inventory.add(new JojaMartSeasonsStock("Broccoli Seeds", Seeds.BroccoliSeeds, Season.Fall, 15, 5));
        inventory.add(new JojaMartSeasonsStock("Amaranth Seeds", Seeds.AmaranthSeeds, Season.Fall, 87, 5));
        inventory.add(new JojaMartSeasonsStock("Grape Starter", Seeds.GrapeStarter, Season.Fall, 75, 5));
        inventory.add(new JojaMartSeasonsStock("Beet Seeds", Seeds.BeetSeeds, Season.Fall, 20, 5));
        inventory.add(new JojaMartSeasonsStock("Yam Seeds", Seeds.YamSeeds, Season.Fall, 75, 5));
        inventory.add(new JojaMartSeasonsStock("Bok Choy Seeds", Seeds.BokChoySeeds, Season.Fall, 62, 5));
        inventory.add(new JojaMartSeasonsStock("Cranberry Seeds", Seeds.CranberrySeeds, Season.Fall, 300, 5));
        inventory.add(new JojaMartSeasonsStock("Sunflower Seeds", Seeds.SunflowerSeeds, Season.Fall, 125, 5));
        inventory.add(new JojaMartSeasonsStock("Fairy Seeds", Seeds.FairySeeds, Season.Fall, 250, 5));
        inventory.add(new JojaMartSeasonsStock("Rare Seed", Seeds.RareSeed, Season.Fall, 1000, 1));
        inventory.add(new JojaMartSeasonsStock("Wheat Seeds", Seeds.WheatSeeds, Season.Fall, 12, 5));

        // Winter
        inventory.add(new JojaMartSeasonsStock("Powdermelon Seeds", Seeds.PowdermelonSeeds, Season.Winter, 20, 10));

        //Permanent stock
        inventory.add(new JojaMartSeasonsStock("Ancient Seed", Seeds.AncientSeeds, Season.Special, 500, 1));
        inventory.add(new ShopItem("Joja cola", 75, Integer.MAX_VALUE));

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
        return 'J';
    }

}
