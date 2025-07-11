package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

public enum ArtisanGoodAsset {
    Honey(new Image(GamePictureManager.honeyTexture),
        "Honey\n----------\n No ingredients\n----------\n 4 Days\n----------\n 75 Energy"),
    Coal(new Image(GamePictureManager.coalTexture),
        "Coal\n----------\n 10 Wood\n----------\n 1 Hour\n----------\n Inedible"),
    Cheese(new Image(GamePictureManager.cheeseTexture),
        "Cheese\n----------\n 1 Milk (or)\n 1 LargeMilk\n----------\n 3 Hours\n----------\n 100 Energy"),
    GoatCheese(new Image(GamePictureManager.goatCheeseTexture),
        "GoatCheese\n----------\n 1 GoatMilk (or)\n 1 LargeGoatMilk\n----------\n 3 Hours\n----------\n 100 Energy"),
    DriedMushroom(new Image(GamePictureManager.driedMushroomsTexture),
        "DriedMushroom\n----------\n 5 Mushrooms\n----------\n Next morning\n----------\n 50 Energy"),
    DriedFruit(new Image(GamePictureManager.driedFruitTexture),
        "DriedFruit\n----------\n 5 Fruit\n----------\n Next morning\n----------\n 75 Energy"),
    Raisins(new Image(GamePictureManager.raisinsTexture),
        "Raisins\n----------\n 5 Grapes\n----------\n Next morning\n----------\n 125 Energy"),
    SmokedFish(new Image(GamePictureManager.smokedFishTexture),
        "SmokedFish\n----------\n 1 Fish\n 1 Coal\n----------\n 1 Hour\n----------\n 1.5 * Fish_Energy"),
    Beer(new Image(GamePictureManager.beerTexture),
        "Beer\n----------\n 1 Wheat\n----------\n 1 Day\n----------\n 50 Energy"),
    Vinegar(new Image(GamePictureManager.vinegarTexture),
        "Vinegar\n----------\n 1 Rice\n----------\n 10 Hours\n----------\n 13 Energy"),
    Coffee(new Image(GamePictureManager.coffeeTexture),
        "Coffee\n----------\n 5 Coffee_Bean\n----------\n 2 Hours\n----------\n 75 Energy"),
    Juice(new Image(GamePictureManager.juiceTexture),
        "Juice\n----------\n 1 Any Vegetable\n----------\n 4 Days\n----------\n 2 * Ingredient_Energy"),
    Mead(new Image(GamePictureManager.meadTexture),
        "Mead\n----------\n 1 Honey\n----------\n 10 Hours\n----------\n 100 Energy"),
    PaleAle(new Image(GamePictureManager.paleAleTexture),
        "PaleAle\n----------\n 1 Hops\n----------\n 3 Days\n----------\n 50 Energy"),
    Wine(new Image(GamePictureManager.wineTexture),
        "Wine\n----------\n 1 Fruit\n----------\n 7 Days\n----------\n 1.75 * Ingredient_Energy"),
    Cloth(new Image(GamePictureManager.clothTexture),
        "Cloth\n----------\n 1 Wool\n----------\n 4 Hours\n----------\n Inedible"),
    Mayonnaise(new Image(GamePictureManager.mayonnaiseTexture),
        "Mayonnaise\n----------\n 1 Egg (or) \n 1 LargeEgg\n----------\n 3 Hours\n----------\n 50 Energy"),
    DuckMayonnaise(new Image(GamePictureManager.duckMayonnaiseTexture),
        "DuckMayonnaise\n----------\n 1 Duck Egg\n----------\n 3 Hours\n----------\n 75 Energy"),
    DinosaurMayonnaise(new Image(GamePictureManager.dinosaurMayonnaiseTexture),
        "DinosaurMayonnaise\n----------\n 1 Dinosaur Egg\n----------\n 3 Hours\n----------\n 125 Energy"),
    TruffleOil(new Image(GamePictureManager.truffleOilTexture),
        "TruffleOil\n----------\n 1 Truffle\n----------\n 6 Hours\n----------\n 38 Energy"),
    Oil(new Image(GamePictureManager.oilTexture),
        "Oil\n----------\n 1 Corn (or)\n 1 Sunflower seed\n 1 Sunflower\n----------\n 6 Hours\n 2 Days\n 1 Hour\n----------\n 13 Energy"),
    Jelly(new Image(GamePictureManager.jellyTexture),
        "Jelly\n----------\n 1 Fruit\n----------\n 2 Days\n----------\n 2 * Ingredient_Energy"),
    Pickles(new Image(GamePictureManager.picklesTexture),
        "Pickles\n----------\n 1 Any Vegetable\n----------\n 6 Hours\n----------\n 1.75 * Ingredient_Energy"),
    IronBar(new Image(GamePictureManager.ironBarTexture),
        "IronBar\n----------\n 5 Any Ore\n 1 Coal\n----------\n 4 Hours\n----------\n Inedible"),
    IridiumBar(new Image(GamePictureManager.iridiumBarTexture),
        "IridiumBar\n----------\n 5 Any Ore\n 1 Coal\n----------\n 4 Hours\n----------\n Inedible"),
    CopperBar(new Image(GamePictureManager.copperBarTexture),
        "CopperBar\n----------\n 5 Any Ore\n 1 Coal\n----------\n 4 Hours\n----------\n Inedible"),
    GoldBar(new Image(GamePictureManager.goldBarTexture),
        "GoldBar\n----------\n 5 Any Ore\n 1 Coal\n----------\n 4 Hours\n----------\n Inedible"),;

    private final Image image;
    private final String description;

    ArtisanGoodAsset(Image image, String description) {
        this.image = image;
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public String getDescription() {
        return "\n" + description + "\n";
    }
}
