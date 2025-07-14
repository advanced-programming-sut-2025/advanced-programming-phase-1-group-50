package com.stardew.view.windows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.stores.ShopItem;
import com.stardew.models.stores.Store;

import java.util.List;

public class StoreWindow extends CloseableWindow {
    private final Store store;
    private final Table productTable;
    private final CheckBox availableOnlyCheckbox;

    public StoreWindow(Stage stage, Store store) {
        super(store.getShopAssistantName() + "'s store", stage);
        this.store = store;

        pad(40);
        defaults().space(15);

        availableOnlyCheckbox = new CheckBox("Only Available", GamePictureManager.skin);
        availableOnlyCheckbox.setChecked(true);
        availableOnlyCheckbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                refreshProducts();
            }
        });

        productTable = new Table();
        productTable.defaults().space(10);

        ScrollPane scrollPane = new ScrollPane(productTable, GamePictureManager.skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);

        add(availableOnlyCheckbox).left().row();

        Table headerTable = new Table();
        headerTable.add(new Label("Name", GamePictureManager.skin)).width(200);
        headerTable.add(new Label("Price", GamePictureManager.skin)).width(100);
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

    private void refreshProducts() {
        productTable.clear();

        List<ShopItem> items = availableOnlyCheckbox.isChecked()
            ? store.showAvailableProducts()
            : store.showAllProducts();

        for (ShopItem item : items) {
            final String productName = item.getName();
            final int price = item.getPrice();
            final int quantity = item.getRemainingQuantity();

            TextButton nameButton = new TextButton(productName, GamePictureManager.skin);
            nameButton.pad(5);
            nameButton.getLabel().setFontScale(0.9f);
            nameButton.getLabelCell().padLeft(10);

            if (quantity == 0) {
                nameButton.setColor(Color.DARK_GRAY);
                nameButton.setDisabled(true);
            } else {
                nameButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        openPurchaseWindow(productName, quantity, price);
                    }
                });
            }

            Label priceLabel = new Label("$" + price, GamePictureManager.skin);
            Label qtyLabel = new Label("x" + (item.getRemainingQuantity()>10000 ? " infinity" : quantity ), GamePictureManager.skin);

            if (quantity == 0) {
                priceLabel.setColor(Color.DARK_GRAY);
                qtyLabel.setColor(Color.DARK_GRAY);
            }

            productTable.add(nameButton).width(200).height(50);
            productTable.add(priceLabel).width(100);
            productTable.add(qtyLabel).width(80);
            productTable.row();
        }
    }

    private void openPurchaseWindow(String productName, int quantity, int price) {
        PurchaseWindow purchaseWindow = new PurchaseWindow(stage, store, productName, quantity, price);
        stage.addActor(purchaseWindow);
    }
}
