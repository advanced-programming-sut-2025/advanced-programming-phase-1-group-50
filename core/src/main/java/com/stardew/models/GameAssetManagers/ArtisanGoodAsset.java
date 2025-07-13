package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public enum ArtisanGoodAsset {
    Honey(new Image(GamePictureManager.honeyTexture),
        "Honey\n----------\n No ingredients\n----------\n 4 Days\n----------\n 75 Energy",
        "BeeHouse"),

    Coal(new Image(GamePictureManager.coalTexture),
        "Coal\n----------\n 10 Wood\n----------\n 1 Hour\n----------\n Inedible",
        "CharcoalKiln"),

    Cheese(new Image(GamePictureManager.cheeseTexture),
        "Cheese\n----------\n 1 Milk (or)\n 1 LargeMilk\n----------\n 3 Hours\n----------\n 100 Energy",
        "CheesePress"),
    GoatCheese(new Image(GamePictureManager.goatCheeseTexture),
        "GoatCheese\n----------\n 1 GoatMilk (or)\n 1 LargeGoatMilk\n----------\n 3 Hours\n----------\n 100 Energy",
        "CheesePress"),

    DriedMushroom(new Image(GamePictureManager.driedMushroomsTexture),
        "DriedMushroom\n----------\n 5 Mushrooms\n----------\n Next morning\n----------\n 50 Energy",
        "Dehydrator"),
    DriedFruit(new Image(GamePictureManager.driedFruitTexture),
        "DriedFruit\n----------\n 5 Fruit\n----------\n Next morning\n----------\n 75 Energy",
        "Dehydrator"),
    Raisins(new Image(GamePictureManager.raisinsTexture),
        "Raisins\n----------\n 5 Grapes\n----------\n Next morning\n----------\n 125 Energy",
        "Dehydrator"),

    SmokedFish(new Image(GamePictureManager.smokedFishTexture),
        "SmokedFish\n----------\n 1 Fish\n 1 Coal\n----------\n 1 Hour\n----------\n 1.5 * Fish_Energy",
        "FishSmoker"),

    Beer(new Image(GamePictureManager.beerTexture),
        "Beer\n----------\n 1 Wheat\n----------\n 1 Day\n----------\n 50 Energy",
        "Keg"),
    Vinegar(new Image(GamePictureManager.vinegarTexture),
        "Vinegar\n----------\n 1 Rice\n----------\n 10 Hours\n----------\n 13 Energy",
        "Keg"),
    Coffee(new Image(GamePictureManager.coffeeTexture),
        "Coffee\n----------\n 5 Coffee_Bean\n----------\n 2 Hours\n----------\n 75 Energy",
        "Keg"),
    Juice(new Image(GamePictureManager.juiceTexture),
        "Juice\n----------\n 1 Any Vegetable\n----------\n 4 Days\n----------\n 2 * Ingredient_Energy",
        "Keg"),
    Mead(new Image(GamePictureManager.meadTexture),
        "Mead\n----------\n 1 Honey\n----------\n 10 Hours\n----------\n 100 Energy",
        "Keg"),
    PaleAle(new Image(GamePictureManager.paleAleTexture),
        "PaleAle\n----------\n 1 Hops\n----------\n 3 Days\n----------\n 50 Energy",
        "Keg"),
    Wine(new Image(GamePictureManager.wineTexture),
        "Wine\n----------\n 1 Fruit\n----------\n 7 Days\n----------\n 1.75 * Ingredient_Energy",
        "Keg"),

    Cloth(new Image(GamePictureManager.clothTexture),
        "Cloth\n----------\n 1 Wool\n----------\n 4 Hours\n----------\n Inedible",
        "Loom"),

    Mayonnaise(new Image(GamePictureManager.mayonnaiseTexture),
        "Mayonnaise\n----------\n 1 Egg (or) \n 1 LargeEgg\n----------\n 3 Hours\n----------\n 50 Energy",
        "MayonnaiseMachine"),
    DuckMayonnaise(new Image(GamePictureManager.duckMayonnaiseTexture),
        "DuckMayonnaise\n----------\n 1 Duck Egg\n----------\n 3 Hours\n----------\n 75 Energy",
        "MayonnaiseMachine"),
    DinosaurMayonnaise(new Image(GamePictureManager.dinosaurMayonnaiseTexture),
        "DinosaurMayonnaise\n----------\n 1 Dinosaur Egg\n----------\n 3 Hours\n----------\n 125 Energy",
        "MayonnaiseMachine"),

    TruffleOil(new Image(GamePictureManager.truffleOilTexture),
        "TruffleOil\n----------\n 1 Truffle\n----------\n 6 Hours\n----------\n 38 Energy",
        "OilMaker"),
    Oil(new Image(GamePictureManager.oilTexture),
        "Oil\n----------\n 1 Corn (or)\n 1 Sunflower seed\n 1 Sunflower\n----------\n 6 Hours\n 2 Days\n 1 Hour\n----------\n 13 Energy",
        "OilMaker"),

    Jelly(new Image(GamePictureManager.jellyTexture),
        "Jelly\n----------\n 1 Fruit\n----------\n 2 Days\n----------\n 2 * Ingredient_Energy",
        "PreservesJar"),
    Pickles(new Image(GamePictureManager.picklesTexture),
        "Pickles\n----------\n 1 Any Vegetable\n----------\n 6 Hours\n----------\n 1.75 * Ingredient_Energy",
        "PreservesJar"),

    IronBar(new Image(GamePictureManager.ironBarTexture),
        "IronBar\n----------\n 5 Any Ore\n 1 Coal\n----------\n 4 Hours\n----------\n Inedible",
        "Furnace"),
    IridiumBar(new Image(GamePictureManager.iridiumBarTexture),
        "IridiumBar\n----------\n 5 Any Ore\n 1 Coal\n----------\n 4 Hours\n----------\n Inedible",
        "Furnace"),
    CopperBar(new Image(GamePictureManager.copperBarTexture),
        "CopperBar\n----------\n 5 Any Ore\n 1 Coal\n----------\n 4 Hours\n----------\n Inedible",
        "Furnace"),
    GoldBar(new Image(GamePictureManager.goldBarTexture),
        "GoldBar\n----------\n 5 Any Ore\n 1 Coal\n----------\n 4 Hours\n----------\n Inedible",
        "Furnace");

    private final Image image;
    private final String description;
    private final String machineMaker;

    ArtisanGoodAsset(Image image, String description, String machineMaker) {
        this.image = image;
        this.description = description;
        this.machineMaker = machineMaker;
    }

    public Image getImage() {
        return image;
    }

    public String getDescription() {
        return "\n" + description + "\n";
    }

    public String getMachineMakerName() {
        return machineMaker;
    }
}
