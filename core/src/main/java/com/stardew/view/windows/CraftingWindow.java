package com.stardew.view.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.stardew.controller.CookingAndCraftingControllers.CraftingController;
import com.stardew.models.GameAssetManagers.CraftingAsset;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.Player;

import java.util.HashMap;

public class CraftingWindow extends CloseableWindow {
    private final HashMap<CraftingAsset, ImageButton> buttons = new HashMap<>();
    private final CraftingController controller = new CraftingController();

    public CraftingWindow(Stage stage) {
        super("Crafting Menu", stage);

        createUI();

        pad(150);
        defaults().space(20);
        int counter = 0;
        for (CraftingAsset asset : buttons.keySet()) {
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

        for (CraftingAsset craftingAsset : CraftingAsset.values()) {
            buttons.put(craftingAsset, new ImageButton(craftingAsset.getStyle()));
        }

        Player player = App.getGame().getCurrentPlayingPlayer();

        for (CraftingAsset craftingAsset : CraftingAsset.values()) {
            ImageButton imageButton = buttons.get(craftingAsset);

            imageButton.setDisabled(!player.getBackpack().containRecipe(craftingAsset.getRecipe()));

            SmartTooltip tooltip = SmartTooltip.getInstance();
            imageButton.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (!imageButton.isDisabled()) {
                        Result result = controller.craftingCraft(craftingAsset.getRecipe(), stage);
                        showResult(result);
                        return true;
                    }
                    return false;
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    tooltip.show(craftingAsset.getDescription());
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    tooltip.hide();
                }
            });
        }
    }
}
