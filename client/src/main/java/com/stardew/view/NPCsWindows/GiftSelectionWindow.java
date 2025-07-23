package com.stardew.view.NPCsWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.stardew.controller.NPCController.NPCController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.NPCs.NPC;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.stores.Sellable;
import com.stardew.view.windows.CloseableWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GiftSelectionWindow extends CloseableWindow {
    private final Table productTable;
    private final NPC receiver;

    public GiftSelectionWindow(Stage stage, NPC receiver) {
        super("Gift Selection", stage);
        this.receiver = receiver;

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
        productTable.clear();
        List<Sellable> items = new ArrayList<>();

        HashMap<Ingredient, Integer> ingredientQuantity =
            App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity();

        for (Ingredient ingredient : ingredientQuantity.keySet()) {
            if (Sellable.isSellable(ingredient.toString()) && ingredientQuantity.get(ingredient) > 0) {
                items.add((Sellable) ingredient);
            }
        }

        if (items.isEmpty()) {

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


        for (Sellable item : items) {
            final String productName = Sellable.getNameInString(item);
            final int quantity = ingredientQuantity.getOrDefault((Ingredient) item, 0);

            TextButton nameButton = new TextButton(productName, GamePictureManager.skin);
            nameButton.pad(5);
            nameButton.getLabel().setFontScale(0.9f);
            nameButton.getLabelCell().padLeft(10);

            nameButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    sendGift(productName);
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

    private void sendGift(String productName) {
        Result result = NPCController.giftToNPC(productName, receiver);
        showResult(result);
        refreshProducts();
    }
}
