package com.stardew.model.stores;

import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.date.Season;
import com.stardew.model.mapInfo.foraging.Seeds;
import com.stardew.model.mapInfo.foraging.TreeSource;
import com.stardew.model.userInfo.BackpackType;

import java.awt.*;
import java.util.ArrayList;

public class PierreGeneralStore extends Store {
    private ArrayList<ShopItem> inventory;

    public PierreGeneralStore(int gameId,int x, int y, int width, int height) {
        super(gameId,TextureID.pierresShopRegion,new Rectangle(x,y,width,height),"Pierre",9,23);
    }

    @Override
    public void loadInventory() {

        inventory = new ArrayList<>();

        //BackpackUpgrade
        inventory.add(new PierreGeneralStoreBackPackUpgrade("Large Pack", BackpackType.Big, 2000, 1));
        inventory.add(new PierreGeneralStoreBackPackUpgrade("Deluxe Pack", BackpackType.Deluxe, 10000, 1));

        //Saplings
        inventory.add(new PierreGeneralStoreSaplingItem("Apple Sapling", TreeSource.AppleSapling, 4000,
            Integer.MAX_VALUE));
        inventory.add(new PierreGeneralStoreSaplingItem("Apricot Sapling", TreeSource.AppleSapling, 2000,
            Integer.MAX_VALUE));
        inventory.add(new PierreGeneralStoreSaplingItem("Cherry Sapling", TreeSource.AppleSapling, 3400,
            Integer.MAX_VALUE));
        inventory.add(new PierreGeneralStoreSaplingItem("Orange Sapling", TreeSource.AppleSapling, 4000,
            Integer.MAX_VALUE));
        inventory.add(new PierreGeneralStoreSaplingItem("Peach Sapling", TreeSource.AppleSapling, 6000,
            Integer.MAX_VALUE));
        inventory.add(new PierreGeneralStoreSaplingItem("Pomegranate Sapling", TreeSource.AppleSapling, 6000,
            Integer.MAX_VALUE));

        //Spring Seeds
        inventory.add(new PierreGeneralStoreSeedsItem("Parsnip Seeds", Seeds.ParsnipSeeds, Season.Spring, 30, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Bean Starter", Seeds.BeanStarter, Season.Spring, 90, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Cauliflower Seeds", Seeds.CauliflowerSeeds, Season.Spring, 120
            , 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Potato Seeds", Seeds.PotatoSeeds, Season.Spring, 75, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Tulip Bulb", Seeds.TulipBulb, Season.Spring, 30, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Kale Seeds", Seeds.KaleSeeds, Season.Spring, 105, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Jazz Seeds", Seeds.JazzSeeds, Season.Spring, 45, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Garlic Seeds", Seeds.GarlicSeeds, Season.Spring, 60, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Rice Shoot", Seeds.RiceShoot, Season.Spring, 60, 5));

        //Summer Seeds
        inventory.add(new PierreGeneralStoreSeedsItem("Melon Seeds", Seeds.MelonSeeds, Season.Summer, 120, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Tomato Seeds", Seeds.TomatoSeeds, Season.Summer, 75, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Blueberry Seeds", Seeds.BlueberrySeeds, Season.Summer, 120, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Pepper Seeds", Seeds.PepperSeeds, Season.Summer, 60, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Wheat Seeds", Seeds.WheatSeeds, Season.Summer, 15, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Radish Seeds", Seeds.RadishSeeds, Season.Summer, 60, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Poppy Seeds", Seeds.PoppySeeds, Season.Summer, 150, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Spangle Seeds", Seeds.SpangleSeeds, Season.Summer, 75, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Hops Starter", Seeds.HopsStarter, Season.Summer, 90, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Corn Seeds", Seeds.CornSeeds, Season.Summer, 225, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Sunflower Seeds", Seeds.SunflowerSeeds, Season.Summer, 300, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Red Cabbage Seeds", Seeds.RedCabbageSeeds, Season.Summer, 150,
            5));

        //Fall Seeds
        inventory.add(new PierreGeneralStoreSeedsItem("Eggplant Seeds", Seeds.EggplantSeeds, Season.Fall, 30, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Corn Seeds", Seeds.CornSeeds, Season.Fall, 225, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Pumpkin Seeds", Seeds.PumpkinSeeds, Season.Fall, 150, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Bok Choy Seeds", Seeds.BokChoySeeds, Season.Fall, 75, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Yam Seeds", Seeds.YamSeeds, Season.Fall, 90, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Cranberry Seeds", Seeds.CranberrySeeds, Season.Fall, 360, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Sunflower Seeds", Seeds.SunflowerSeeds, Season.Fall, 300, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Fairy Seeds", Seeds.FairySeeds, Season.Fall, 300, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Amaranth Seeds", Seeds.AmaranthSeeds, Season.Fall, 105, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Grape Starter", Seeds.GrapeStarter, Season.Fall, 90, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Wheat Seeds", Seeds.WheatSeeds, Season.Fall, 15, 5));
        inventory.add(new PierreGeneralStoreSeedsItem("Artichoke Seeds", Seeds.ArtichokeSeeds, Season.Fall, 45, 5));

        inventory.add(new ShopItem("Rice", 200, Integer.MAX_VALUE));
        inventory.add(new ShopItem("Bouquet", 1000, 2));
        inventory.add(new ShopItem("Dehydrator", 10000, 1));
        inventory.add(new ShopItem("Oil", 200, Integer.MAX_VALUE));
        inventory.add(new ShopItem("Vinegar", 200, Integer.MAX_VALUE));


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
        return '⚙';
    }

}
