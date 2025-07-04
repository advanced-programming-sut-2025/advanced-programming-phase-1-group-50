package com.stardew.view.craftingWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.CookingAndCraftingControllers.ArtisanController;
import com.stardew.models.GameAssetManagers.ArtisanAsset;
import com.stardew.models.GameAssetManagers.ArtisanGoodAsset;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;

import java.util.ArrayList;

public class ArtisanWindow extends Window {
    private ArtisanController controller = new ArtisanController();
    private ImageButton closeButton;
    private Stage stage;


    public ArtisanWindow(ArtisanAsset artisanAsset, Stage stage, float x, float y) {
        super("Artisan Menu  ", GamePictureManager.skin);
        this.stage = stage;

        setModal(true);
        setMovable(false);
        setResizable(false);

        pad(30, 5, 25, 0);
        defaults().space(10);

        TooltipManager tooltipManager = TooltipManager.getInstance();
        tooltipManager.initialTime = 0f;
        tooltipManager.subsequentTime = 0f;

        ArrayList<ArtisanGoodAsset> products = artisanAsset.getProducts();
        for (ArtisanGoodAsset asset : products) {
            Image product = asset.getImage();
            add(product).row();
            product.addListener(new Tooltip<>(asset.getDescription(), tooltipManager));
            product.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    getChildren().forEach(Actor::clearListeners);
                    remove();
                }
            });
        }

        //adding close button for the window
        closeButton = new ImageButton(GamePictureManager.closeWindow);
        getTitleTable().add(closeButton);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getChildren().forEach(Actor::clearListeners);
                remove();
            }
        });

        pack();
        setBackground(GamePictureManager.windowWoodBackground);
        setPosition(x, y);

    }

    private void showResult(Result result) {
        Dialog dialog = new Dialog("Result", GamePictureManager.skin);
        dialog.setColor(Color.LIGHT_GRAY);
        dialog.pad(30, 5, 20, 5);
        dialog.setBackground(GamePictureManager.windowWoodBackground);
        dialog.text(result.getMessage());
        dialog.getContentTable().getCell(dialog.getContentTable().getChildren().first())
            .getActor().setColor(result.getSuccessful() ? Color.GREEN : Color.RED);
        dialog.button(new TextButton("OK", GamePictureManager.skin, "small"));
        dialog.show(stage);
    }
}
