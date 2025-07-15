package com.stardew.view.StoreWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.stores.Blacksmith;
import com.stardew.models.stores.ShopItem;
import com.stardew.models.stores.Store;
import com.stardew.view.windows.CloseableWindow;

import java.util.List;

public class StoreWindow extends CloseableWindow {
    private final Store store;
    private final Table productTable;
    private final SelectBox<String> filterBox;

    public StoreWindow(Stage stage, Store store) {
        super(store.getShopAssistantName() + "'s store", stage);
        this.store = store;

        pad(40);
        defaults().space(15);

        filterBox = new SelectBox<>(GamePictureManager.skin);
        filterBox.setItems("All products", "Only available products");
        filterBox.setSelected("All products");
        filterBox.addListener(new ChangeListener() {
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

        add(filterBox).left().row();

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

    protected void refreshProducts() {
        productTable.clear();

        List<ShopItem> items = filterBox.getSelected().equals("Only available products")
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
            Label qtyLabel = new Label("x" + (quantity > 10000 ? "infinity" : quantity), GamePictureManager.skin);

            if (quantity == 0) {
                priceLabel.setColor(Color.DARK_GRAY);
                qtyLabel.setColor(Color.DARK_GRAY);
            }

            productTable.add(nameButton).width(200).height(50);
            productTable.add(priceLabel).width(100);
            productTable.add(qtyLabel).width(80);
            productTable.row();
        }

        if (store.getShopAssistantName().equalsIgnoreCase("Clint")) {
            TextButton upgradeToolButton = new TextButton("Upgrade Tool", GamePictureManager.skin);
            upgradeToolButton.pad(5);
            upgradeToolButton.getLabel().setFontScale(0.9f);
            upgradeToolButton.getLabelCell().padLeft(10);
            upgradeToolButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ToolUpgradeWindow toolWindow = new ToolUpgradeWindow(stage);
                    stage.addActor(toolWindow);
                }
            });

            TextButton upgradeTrashButton = new TextButton("Upgrade Trash Can", GamePictureManager.skin);
            upgradeTrashButton.pad(5);
            upgradeTrashButton.getLabel().setFontScale(0.9f);
            upgradeTrashButton.getLabelCell().padLeft(10);
            upgradeTrashButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Result result = ((Blacksmith) store).upgradeTool("TrashCan");
                    TrashCanUpgradeWindow trashWindow = new TrashCanUpgradeWindow( result, stage);
                    stage.addActor(trashWindow);
                }
            });

            productTable.add(upgradeToolButton).colspan(3).width(380).height(50).row();
            productTable.add(upgradeTrashButton).colspan(3).width(380).height(50).row();
        }
    }


    private void openPurchaseWindow(String productName, int quantity, int price) {
        PurchaseWindow purchaseWindow = new PurchaseWindow(stage, store, this, productName, quantity, price);
        stage.addActor(purchaseWindow);
    }
}
