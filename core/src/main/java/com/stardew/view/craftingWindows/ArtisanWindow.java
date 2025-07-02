package com.stardew.view.craftingWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.CookingAndCraftingControllers.ArtisanController;
import com.stardew.models.GameAssetManagers.ArtisanAsset;
import com.stardew.models.GameAssetManagers.ArtisanGoodAsset;
import com.stardew.models.GameAssetManagers.GamePictureManager;

import java.util.ArrayList;

public class ArtisanWindow extends Window {
    ArtisanController controller = new ArtisanController();


    public ArtisanWindow(ArtisanAsset artisanAsset, float x, float y) {
        super("Artisan Menu", GamePictureManager.skin);

        setModal(true);
        setMovable(false);
        setResizable(false);

        pad(50, 5, 60, 5);
        defaults().space(20);

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

        pack();
        setBackground(GamePictureManager.windowWoodBackground);
        setColor(Color.CORAL);
        setPosition(x, y);

    }
}
