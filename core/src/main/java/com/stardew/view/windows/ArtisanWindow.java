package com.stardew.view.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.CookingAndCraftingControllers.ArtisanController;
import com.stardew.models.GameAssetManagers.ArtisanAsset;
import com.stardew.models.GameAssetManagers.ArtisanGoodAsset;
import com.stardew.models.GameAssetManagers.GamePictureManager;

import java.util.ArrayList;

public class ArtisanWindow extends CloseableWindow {
    private ArtisanController controller = new ArtisanController();


    public ArtisanWindow(ArtisanAsset artisanAsset, Stage stage, float x, float y) {
        super("Artisan Menu  ", stage);

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
                    //TODO crafting selected item
                    getChildren().forEach(Actor::clearListeners);
                    remove();
                }
            });
        }

        pack();
        setSize(150, 400);
        setPosition(x, y);

    }
}
