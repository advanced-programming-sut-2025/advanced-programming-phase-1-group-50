package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ArtisanAsset {
    BeeHouse(List.of(
        ArtisanGoodAsset.Honey)),
    CharcoalKiln(List.of(
        ArtisanGoodAsset.Coal)),
    CheesePress(Arrays.asList(
        ArtisanGoodAsset.Cheese,
        ArtisanGoodAsset.GoatCheese)),
    Dehydrator(Arrays.asList(
        ArtisanGoodAsset.DriedMushroom,
        ArtisanGoodAsset.DriedFruit,
        ArtisanGoodAsset.Raisins)),
    FishSmoker(List.of(
        ArtisanGoodAsset.SmokedFish)),
    Furnace(Arrays.asList(
        ArtisanGoodAsset.IronBar,
        ArtisanGoodAsset.IridiumBar,
        ArtisanGoodAsset.CopperBar,
        ArtisanGoodAsset.GoldBar)),
    Keg(Arrays.asList(
        ArtisanGoodAsset.Beer,
        ArtisanGoodAsset.Vinegar,
        ArtisanGoodAsset.Coffee,
        ArtisanGoodAsset.Juice,
        ArtisanGoodAsset.Mead,
        ArtisanGoodAsset.PaleAle,
        ArtisanGoodAsset.Wine)),
    Loom(List.of(
        ArtisanGoodAsset.Cloth)),
    MayonnaiseMachine(Arrays.asList(
        ArtisanGoodAsset.Mayonnaise,
        ArtisanGoodAsset.DuckMayonnaise,
        ArtisanGoodAsset.DinosaurMayonnaise)),
    OilMaker(Arrays.asList(
        ArtisanGoodAsset.Oil,
        ArtisanGoodAsset.TruffleOil)),
    PreservesJar(Arrays.asList(
        ArtisanGoodAsset.Pickles,
        ArtisanGoodAsset.Jelly));

    private final ArrayList<ArtisanGoodAsset> products;
    private final Label descriptionLabel;

    ArtisanAsset(List<ArtisanGoodAsset> products) {
        this.products = new ArrayList<>(products);

        StringBuilder des = new StringBuilder();
        des.append("\n<").append(this.name()).append(">\n");
        for (ArtisanGoodAsset product : products) {
            des.append("---------------------------------------").append(product.getDescription().getText());
        }
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.CYAN;
        labelStyle.background = GamePictureManager.woodBackground;
        descriptionLabel = new Label(des.toString(), labelStyle);
        descriptionLabel.setAlignment(Align.center);
    }

    public ArrayList<ArtisanGoodAsset> getProducts() {
        return new ArrayList<>(products);
    }

    public Label getDescriptionLabel() {
        return descriptionLabel;
    }
}
