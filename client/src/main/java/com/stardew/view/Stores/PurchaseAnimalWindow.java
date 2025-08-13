package com.stardew.view.Stores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.math.Interpolation;
import com.stardew.controller.StoreController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.windows.CloseableWindow;

public class PurchaseAnimalWindow extends CloseableWindow {
    private final Stage stage;
    private final String productName;
    private final int unitPrice;
    private final StoreWindow storeWindow;
    private final int gameId;

    public PurchaseAnimalWindow(int gameId,Stage stage, StoreWindow storeWindow ,String productName, int price ) {
        super("Purchase " + productName, stage);
        this.stage = stage;
        this.gameId = gameId;
        this.storeWindow = storeWindow;
        this.productName = productName;
        this.unitPrice = price;
        buildUI();
    }

    private void buildUI() {
        pad(60);
        defaults().space(20);

        Label productLabel = new Label("Product: " + productName, GamePictureManager.skin);
        Label priceLabel = new Label("Price: $" + unitPrice, GamePictureManager.skin);
        Label nameLabel = new Label("Enter animal name:", GamePictureManager.skin);
        TextField nameField = new TextField("", GamePictureManager.skin);

        TextButton buyButton = new TextButton("Buy", GamePictureManager.skin);
        buyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buyButton.setDisabled(true);
                String animalName = nameField.getText().trim();
                if (!animalName.isEmpty()) {
                    StoreController.purchaseAnimal(gameId,productName, animalName , result -> Gdx.app.postRunnable(() -> {
                        storeWindow.refresh();
                        closeWindow();
                        showResult(result);
                    }));
                }
            }
        });

        add(productLabel).colspan(2).row();
        add(priceLabel).colspan(2).row();
        add(nameLabel).left().colspan(2).row();
        add(nameField).width(250).colspan(2).row();
        add(buyButton).width(100).height(40).colspan(2).padTop(10).row();

        pack();
        centerWindow();
    }

    private void centerWindow() {
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
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
