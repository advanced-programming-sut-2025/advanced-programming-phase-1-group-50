package com.stardew.view.ShippingBin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.google.gson.reflect.TypeToken;
import com.stardew.model.SellableDTO;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.Stores.UpdatableWindow;
import com.stardew.view.windows.CloseableWindow;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class ShippingBinWindow extends CloseableWindow implements UpdatableWindow {
    private final int gameId;
    private final int shippingBinId;
    private final Table productTable;
    private final ArrayList<SellableDTO> products = new ArrayList<>();

    public ShippingBinWindow(int gameId,int shippingBinId,Stage stage) {
        super("Shipping bin window", stage);
        this.gameId = gameId;
        this.shippingBinId = shippingBinId;
        addWindowToList();

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

        refresh();

        pack();
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }

    @Override
    public void refresh() {
        products.clear();
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("event", Event.GetForSaleProducts);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null && response.getType().equals(MessageType.GET_FOR_SALE_PRODUCTS_INFO)) {
                Type type = new TypeToken<ArrayList<SellableDTO>>() {}.getType();
                ArrayList<SellableDTO> newProducts = response.getFromBody("products", type);
                Gdx.app.postRunnable(() -> {
                    products.addAll(newProducts);
                    createUI();
                });
            }
        }).start();
    }

    private void createUI() {
        productTable.clear();
        if (products.isEmpty()) {
            Label emptyLabel = new Label("You don't have any sellable items in your backpack.", GamePictureManager.skin);
            emptyLabel.setColor(Color.BLACK);
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

        for (SellableDTO product : products) {
            final String productName = product.getName();
            final int price = product.getPrice();
            final int quantity = product.getQuantity();
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
        stage.addActor(new SellProductWindow(gameId,stage, this, shippingBinId, productName, quantity, price));
    }

    @Override
    protected void closeWindow() {
        removeWindowFromList();
        getChildren().forEach(Actor::clearListeners);

        addAction(Actions.sequence(
            Actions.parallel(
                Actions.fadeOut(0.3f),
                Actions.scaleTo(0.7f, 0.7f, 0.3f)
            ),
            Actions.removeActor()
        ));
    }
}
