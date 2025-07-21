package com.stardew.view.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.CookingAndCraftingControllers.ArtisanController;
import com.stardew.models.GameAssetManagers.ArtisanAsset;
import com.stardew.models.GameAssetManagers.ArtisanGoodAsset;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;

import java.util.ArrayList;

public class ArtisanWindow extends CloseableWindow {
    private ArtisanController controller = new ArtisanController();


    public ArtisanWindow(ArtisanAsset artisanAsset, Stage stage, float x, float y) {
        super("Artisan Menu", stage);

        pad(25, 5, 20, 0);
        defaults().space(10);

        ArrayList<ArtisanGoodAsset> products = artisanAsset.getProducts();
        for (ArtisanGoodAsset asset : products) {
            Image product = asset.getImage();
            add(product).row();

            SmartTooltip tooltip = SmartTooltip.getInstance();
            product.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Result result = controller.artisanUse(asset.getMachineMakerName(), asset.name());
                    showResult(result);
                    return true;
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    tooltip.show(asset.getDescription());
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    tooltip.hide();
                }
            });
        }

        pack();
        setSize(150, 45 + products.size() * 65);
        setPosition(x, y);

    }
}
