package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

public enum ArtisanGoodAsset {
    Honey(new Image(GamePictureManager.honeyTexture), "Honey"),
    Coal(new Image(GamePictureManager.coalTexture), "Coal"),
    Cheese(new Image(GamePictureManager.cheeseTexture), "Cheese"),
    GoatCheese(new Image(GamePictureManager.goatCheeseTexture), "GoatCheese"),
    DriedMushroom(new Image(GamePictureManager.driedMushroomsTexture), "DriedMushroom"),
    DriedFruit(new Image(GamePictureManager.driedFruitTexture), "DriedFruit"),
    Raisins(new Image(GamePictureManager.raisinsTexture), "Raisins"),
    SmokedFish(new Image(GamePictureManager.smokedFishTexture), "SmokedFish"),
    Beer(new Image(GamePictureManager.beerTexture), "Beer"),
    Vinegar(new Image(GamePictureManager.vinegarTexture), "Vinegar"),
    Coffee(new Image(GamePictureManager.coffeeTexture), "Coffee"),
    Juice(new Image(GamePictureManager.juiceTexture), "Juice"),
    Mead(new Image(GamePictureManager.meadTexture), "Mead"),
    PaleAle(new Image(GamePictureManager.paleAleTexture), "PaleAle"),
    Wine(new Image(GamePictureManager.wineTexture), "Wine"),
    Cloth(new Image(GamePictureManager.clothTexture), "Cloth"),
    Mayonnaise(new Image(GamePictureManager.mayonnaiseTexture), "Mayonnaise"),
    DuckMayonnaise(new Image(GamePictureManager.duckMayonnaiseTexture), "DuckMayonnaise"),
    DinosaurMayonnaise(new Image(GamePictureManager.dinosaurMayonnaiseTexture), "DinosaurMayonnaise"),
    TruffleOil(new Image(GamePictureManager.truffleOilTexture), "TruffleOil"),
    Oil(new Image(GamePictureManager.oilTexture), "Oil"),
    Jelly(new Image(GamePictureManager.jellyTexture), "Jelly"),
    Pickles(new Image(GamePictureManager.picklesTexture), "Pickles"),
    IronBar(new Image(GamePictureManager.ironBarTexture), "IronBar"),
    IridiumBar(new Image(GamePictureManager.iridiumBarTexture), "IridiumBar"),
    CopperBar(new Image(GamePictureManager.copperBarTexture), "CopperBar"),
    GoldBar(new Image(GamePictureManager.goldBarTexture), "GoldBar"),;

    private final Image image;
    private final Label description;

    ArtisanGoodAsset(Image image, String description) {
        this.image = image;

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.CYAN;
        labelStyle.background = GamePictureManager.woodBackground;
        this.description = new Label("\n\n      " + description + "      \n\n", labelStyle);
        this.description.setAlignment(Align.center);
    }

    public Image getImage() {
        return image;
    }

    public Label getDescription() {
        return description;
    }
}
