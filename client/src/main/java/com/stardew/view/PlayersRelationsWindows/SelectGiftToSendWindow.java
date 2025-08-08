package com.stardew.view.PlayersRelationsWindows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.google.gson.reflect.TypeToken;
import com.stardew.model.SellableDTO;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.windows.CloseableWindow;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.HashMap;


public class SelectGiftToSendWindow extends CloseableWindow {
    private final Table productTable;
    private final String receiverUsername;
    private final int gameId;
    private final HashMap<String, Integer> products = new HashMap<>();

    public SelectGiftToSendWindow(int gameId, Stage stage, String receiver) {
        super("Gift Selection", stage);
        this.gameId = gameId;
        this.receiverUsername = receiver;

        pad(40);
        defaults().space(15);
        productTable = new Table();
        productTable.defaults().space(10);

        ScrollPane scrollPane = new ScrollPane(productTable, GamePictureManager.skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);

        Table headerTable = new Table();

        Label nameLabel = new Label("Name:", GamePictureManager.skin);
        nameLabel.setFontScale(1.2f);
        nameLabel.setColor(Color.BLACK);
        Label quantityLabel = new Label("Qty:", GamePictureManager.skin);
        quantityLabel.setFontScale(1.2f);
        quantityLabel.setColor(Color.BLACK);

        headerTable.add(nameLabel).width(200);
        headerTable.add(quantityLabel).width(80);
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
                    for (SellableDTO product : newProducts) {
                        products.put(product.getName(), product.getQuantity());
                    }
                    createUI();
                });
            }
        }).start();
    }

    private void createUI() {
        productTable.clear();
        if (products.isEmpty()) {

            Label emptyLabel = new Label("You don't have any items to gift.", GamePictureManager.skin);
            emptyLabel.setFontScale(1.2f);
            emptyLabel.setColor(Color.BLACK);
            emptyLabel.setWrap(true);
            emptyLabel.setAlignment(Align.center);

            productTable.add(emptyLabel)
                .colspan(2)
                .width(600)
                .expandX()
                .padTop(20)
                .center();
            productTable.row();

            return;
        }

        for (String item : products.keySet()) {
            final String productName = item;
            final int quantity = products.get(item);

            TextButton nameButton = new TextButton(productName, GamePictureManager.skin);
            nameButton.pad(5);
            nameButton.getLabel().setFontScale(0.9f);
            nameButton.getLabelCell().padLeft(10);

            nameButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    openSendGiftWindow(productName, quantity);
                }
            });

            Label qtyLabel = new Label("x" + quantity, GamePictureManager.skin);
            qtyLabel.setFontScale(1.2f);
            qtyLabel.setColor(Color.BLACK);

            productTable.add(nameButton).width(200).height(50);
            productTable.add(qtyLabel).width(80);
            productTable.row();
        }
    }

    private void openSendGiftWindow(String productName, int quantity) {
        stage.addActor(new SendGiftWindow(gameId, stage, this, receiverUsername, productName, quantity));
    }
}
