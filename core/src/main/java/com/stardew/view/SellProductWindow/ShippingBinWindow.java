package com.stardew.view.SellProductWindow;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.ShippingBin;
import com.stardew.models.app.App;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.stores.Sellable;
import com.stardew.view.windows.CloseableWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShippingBinWindow extends CloseableWindow {
    private final ShippingBin shippingBin;
    private final Table productTable;

    public ShippingBinWindow(Stage stage, ShippingBin shippingBin) {
        super("Shipping bin window", stage);
        this.shippingBin = shippingBin;

        pad(40);
        defaults().space(15);
        productTable = new Table();
        productTable.defaults().space(10);

        ScrollPane scrollPane = new ScrollPane(productTable, GamePictureManager.skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);

        Table headerTable = new Table();
        headerTable.add(new Label("Name", GamePictureManager.skin)).width(200);
        headerTable.add(new Label("Sell Price", GamePictureManager.skin)).width(100);
        headerTable.add(new Label("Qty", GamePictureManager.skin)).width(80);
        add(headerTable).row();

        add(scrollPane).width(400).height(400).row();

        refreshProducts();

        pack();
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }

    protected void refreshProducts() {
        productTable.clear();
        List<Sellable> items = new ArrayList<>();

        HashMap<Ingredient, Integer> ingredientQuantity = App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity();

        for (Ingredient ingredient : ingredientQuantity.keySet()) {
            if (Sellable.isSellable(ingredient.toString()) && ingredientQuantity.get(ingredient) > 0) {
                items.add((Sellable) ingredient);
            }
        }

        if (items.isEmpty()) {
            Label emptyLabel = new Label("You don't have any sellable items in your backpack.", GamePictureManager.skin);
            emptyLabel.setWrap(true);
            productTable.add(emptyLabel)
                .colspan(3)
                .width(380)
                .padTop(20)
                .padLeft(10)
                .padRight(10);
            productTable.row();
            return;
        }


        for (Sellable item : items) {
            final String productName = Sellable.getNameInString(item);
            final int price = item.getSellPrice();
            final int quantity = ingredientQuantity.getOrDefault((Ingredient) item, 0);

            TextButton nameButton = new TextButton(productName, GamePictureManager.skin);
            nameButton.pad(5);
            nameButton.getLabel().setFontScale(0.9f);
            nameButton.getLabelCell().padLeft(10);

            nameButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    openSellWindow(productName, quantity, price);
                }
            });

            Label priceLabel = new Label("$" + price, GamePictureManager.skin);
            Label qtyLabel = new Label("x" + quantity, GamePictureManager.skin);

            productTable.add(nameButton).width(200).height(50);
            productTable.add(priceLabel).width(100);
            productTable.add(qtyLabel).width(80);
            productTable.row();
        }
    }

    private void openSellWindow(String productName, int quantity, int price) {
        stage.addActor(new SellProductWindow(stage, this, shippingBin, productName, quantity, price));
    }
}
