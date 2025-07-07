package com.stardew.models.stores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.BackgroundColors;
import com.stardew.models.Bouquet;
import com.stardew.models.ColorPrinter;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.date.Season;
import com.stardew.models.foraging.CropType;
import com.stardew.models.foraging.Seeds;
import com.stardew.models.foraging.TreeSource;
import com.stardew.models.manuFactor.Dehydrator;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGoodType;
import com.stardew.models.userInfo.BackpackType;
import com.stardew.models.userInfo.Coin;

import java.awt.*;
import java.util.ArrayList;

public class PierreGeneralStore extends Store {
    private final String backgroundCode = BackgroundColors.YELLOW;
    private final String colorCode = ColorPrinter.BRIGHT_RED;
    private ArrayList<ShopItem> inventory;

    public PierreGeneralStore(int x, int y, int width, int height) {
        super(new Rectangle(x, y, width, height), "Pierre", 9, 23);
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
    public String showAllProducts() {
        StringBuilder message = new StringBuilder("PierreGeneralStore products:");
        for (ShopItem item : inventory) {
            message.append("\n" + "Name: ").append(item.name).append("  Price: ").append(item.price);
        }
        return message.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder message = new StringBuilder("PierreGeneralStore Available Products:");
        for (ShopItem item : inventory) {
            if (item.remainingQuantity > 0) {
                if (item instanceof PierreGeneralStoreSeedsItem && ((PierreGeneralStoreSeedsItem) item).getSeason().equals(App.getGame().getTime().getSeason())) {

                    message.append("\nName: ").append(item.name).append("   Price: ").append((item.price * 2) / 3).append("   Remaining: ").append(item.remainingQuantity);
                } else {
                    message.append("\nName: ").append(item.name).append("   Price: ").append(item.price).append("   " +
                            "Remaining: ");
                    if (item.remainingQuantity > 10000) {
                        message.append("infinity");
                    } else {
                        message.append(item.remainingQuantity);
                    }
                }
            }
        }
        return message.toString();
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

        int totalPrice = calculatePrice(item) * value;

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0) < totalPrice) {
            return new Result(false, "Not enough money");
        }

        if (item.getRemainingQuantity() < value) {
            return new Result(false, "Not enough stock");
        }

        if (item instanceof PierreGeneralStoreSeedsItem) {

            if (!App.getGame().getCurrentPlayingPlayer().getBackpack().hasCapacity()) {
                return new Result(false, "Not enough capacity in your inventory");
            }

            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(((PierreGeneralStoreSeedsItem) item).getSeeds(), value);

        } else if (item instanceof PierreGeneralStoreBackPackUpgrade) {

            if (item.name.equals("Large Pack")) {

                if (! App.getGame().getCurrentPlayingPlayer().getBackpack().getType().equals(BackpackType.Primary)) {
                    return new Result(false, "you can't upgrade the backpack to Large Pack from this level");
                }
                App.getGame().getCurrentPlayingPlayer().getBackpack().changeType(BackpackType.Big);

            } else {

                if (App.getGame().getCurrentPlayingPlayer().getBackpack().getType().equals(BackpackType.Primary)) {
                    return new Result(false, "you must first buy the Large Pack");
                }
                if (App.getGame().getCurrentPlayingPlayer().getBackpack().getType().equals(BackpackType.Deluxe)) {
                    return new Result(false, "you can't upgrade the backpack to Deluxe Pack from this level");
                }
                App.getGame().getCurrentPlayingPlayer().getBackpack().changeType(BackpackType.Deluxe);
            }

        } else if (item instanceof PierreGeneralStoreSaplingItem) {

            if (!App.getGame().getCurrentPlayingPlayer().getBackpack().hasCapacity()) {
                return new Result(false, "Not enough capacity in your inventory");
            }

            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(((PierreGeneralStoreSaplingItem) item).getSource(),value);

        } else {

            if (!App.getGame().getCurrentPlayingPlayer().getBackpack().hasCapacity() && !item.name.equals("Dehydrator")) {
                return new Result(false, "Not enough capacity in your inventory");
            }

            switch (item.name) {

                case "Rice":{
                    App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(CropType.UnMilledRice,value);
                    break;
                }
                case "Bouquet":{
                    App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Bouquet(),value);
                    break;
                }
                case "Dehydrator":{
                    App.getGame().getCurrentPlayingPlayer().getBackpack().addArtisanMachine(new Dehydrator());
                    break;
                }
                case "Oil":{
                    App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.Oil),value);
                    break;
                }
                case "Vinegar":{
                    App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.Vinegar),value);
                    break;
                }
            }

        }

        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(), -1 * totalPrice);
        item.decreaseRemainingQuantity(value);
        return new Result(true, "You successfully purchased " + value + "number(s) of " + productName);
    }

    @Override
    public void ResetQuantityEveryNight() {
        for (ShopItem item : inventory) {
            item.resetQuantityEveryNight();
        }
    }

    private int calculatePrice(ShopItem item) {
        if (item instanceof PierreGeneralStoreSeedsItem) {
            if (App.getGame().getTime().getSeason().equals(((PierreGeneralStoreSeedsItem) item).getSeason())) {
                return (item.price * 2) / 3;
            }
        }
        return item.getPrice();
    }

    @Override
    public char getSymbol() {
        return '⚙';
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
        return null;
    }


}
