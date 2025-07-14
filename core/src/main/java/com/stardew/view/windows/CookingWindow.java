package com.stardew.view.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.stardew.controller.CookingAndCraftingControllers.CookingController;
import com.stardew.models.GameAssetManagers.CookingAsset;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.Player;

import java.util.HashMap;

public class CookingWindow extends CloseableWindow {
    private final HashMap<CookingAsset, ImageButton> buttons = new HashMap<>();
    private final CookingController controller = new CookingController();

    public CookingWindow(Stage stage) {
        super("Cooking Menu", stage);

        createUI();

        pad(150);
        defaults().space(20);
        int counter = 0;
        for (CookingAsset asset : buttons.keySet()) {
            add(buttons.get(asset));
            counter++;
            if (counter % 7 == 0)
                row();
        }

        pack();
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);
    }


    private void createUI() {

        for (CookingAsset cookingAsset : CookingAsset.values()) {
            buttons.put(cookingAsset, new ImageButton(cookingAsset.getStyle()));
        }

        Player player = App.getGame().getCurrentPlayingPlayer();

        for (CookingAsset cookingAsset : CookingAsset.values()) {
            ImageButton imageButton = buttons.get(cookingAsset);
            imageButton.setDisabled(!player.getBackpack().containRecipe(cookingAsset.getRecipe()));

            SmartTooltip tooltip = SmartTooltip.getInstance();

            imageButton.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Result result = controller.cookingPrepare(cookingAsset.getRecipe());
                    showResult(result);
                    return true;
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    tooltip.show(cookingAsset.getDescription());
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    tooltip.hide();
                }
            });
        }
    }

}
