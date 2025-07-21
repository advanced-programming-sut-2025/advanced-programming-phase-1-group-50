package com.stardew.view.SellProductWindow;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.SellProductController.SellProductController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.ShippingBin;
import com.stardew.view.windows.CloseableWindow;

public class SellProductWindow extends CloseableWindow {
    private final int unitPrice;
    private final int maxQuantity;

    private final Label totalPriceLabel;
    private final Label quantityLabel;
    private int selectedQuantity = 1;

    public SellProductWindow(Stage stage, ShippingBinWindow shippingBinWindow, ShippingBin shippingBin, String productName, int quantity, int price) {
        super("Sell Product Window", stage);
        this.unitPrice = price;
        this.maxQuantity = quantity;

        pad(60);
        defaults().space(20);

        Label productLabel = new Label("Product: " + productName, GamePictureManager.skin);
        Label priceLabel = new Label("Price: $" + unitPrice, GamePictureManager.skin);
        Label availableLabel = new Label("Available: " + maxQuantity, GamePictureManager.skin);

        quantityLabel = new Label(String.valueOf(selectedQuantity), GamePictureManager.skin);
        totalPriceLabel = new Label("Total: $" + unitPrice, GamePictureManager.skin);

        TextButton minusButton = new TextButton("-", GamePictureManager.skin);
        TextButton plusButton = new TextButton("+", GamePictureManager.skin);

        minusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedQuantity > 1) {
                    selectedQuantity--;
                    updateLabels();
                }
            }
        });

        plusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedQuantity < maxQuantity) {
                    selectedQuantity++;
                    updateLabels();
                }
            }
        });

        TextButton sellButton = new TextButton("Sell", GamePictureManager.skin);
        sellButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = SellProductController.sellProduct(productName,shippingBin,selectedQuantity);
                shippingBinWindow.refreshProducts();
                closeWindow();
                showResult(result);
            }
        });

        add(productLabel).colspan(3).row();
        add(priceLabel).colspan(3).row();
        add(availableLabel).colspan(3).row();

        add(minusButton).width(100).height(50);
        add(quantityLabel).center().width(80);
        add(plusButton).width(100).height(50).row();

        add(totalPriceLabel).colspan(3).row();
        add(sellButton).colspan(3).padTop(10).row();

        pack();
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);
    }

    private void updateLabels() {
        quantityLabel.setText(String.valueOf(selectedQuantity));
        totalPriceLabel.setText("Total: $" + (selectedQuantity * unitPrice));
    }

    @Override
    protected void closeWindow() {
        addAction(Actions.sequence(
            Actions.parallel(
                Actions.fadeOut(0.2f),
                Actions.scaleTo(0.7f, 0.7f, 0.2f, Interpolation.fade)
            ),
            Actions.removeActor()
        ));
    }
}
