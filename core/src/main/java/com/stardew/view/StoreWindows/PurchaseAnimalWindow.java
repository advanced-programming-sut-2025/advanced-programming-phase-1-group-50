package com.stardew.view.StoreWindows;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.math.Interpolation;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.stores.MarnieRanch;
import com.stardew.models.stores.Store;
import com.stardew.view.windows.CloseableWindow;

public class PurchaseAnimalWindow extends CloseableWindow {
    private final Stage stage;
    private final String productName;
    private final int unitPrice;
    private final Store store;
    private final StoreWindow storeWindow;

    public PurchaseAnimalWindow(Stage stage, StoreWindow storeWindow , Store store, String productName, int price ) {
        super("Purchase " + productName, stage);
        this.stage = stage;
        this.storeWindow = storeWindow;
        this.store = store;
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
                String animalName = nameField.getText().trim();
                if (!animalName.isEmpty()) {
                    Result result = ((MarnieRanch)store).purchaseAnimal(productName, animalName);
                    storeWindow.refreshProducts();
                    closeWindow();
                    showResult(result);
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
