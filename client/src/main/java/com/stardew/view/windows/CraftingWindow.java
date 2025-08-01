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
import java.util.Map;
import java.util.Set;

public class CraftingWindow extends CloseableWindow {
    private final HashMap<CraftingAsset, ImageButton> buttons = new HashMap<>();
    private final CraftingController controller = new CraftingController();

    public CraftingWindow(Stage stage, Map<String, String> descriptions, Set<String> ownRecipes) {
        super("Crafting Menu", stage);

        createUI(descriptions, ownRecipes);

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


    private void createUI(Map<String, String> descriptions, Set<String> ownRecipes) {

        for (CraftingAsset craftingAsset : CraftingAsset.values()) {
            ImageButton imageButton = new ImageButton(craftingAsset.getStyle());
            buttons.put(craftingAsset, imageButton);

            imageButton.setDisabled(!ownRecipes.contains(craftingAsset.name()));

            SmartTooltip tooltip = SmartTooltip.getInstance();
            imageButton.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                    Result result = controller.craftingCraft(craftingAsset.getRecipe(), stage);
//                    showResult(result);
                    return true;
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    tooltip.show(descriptions.get(craftingAsset.name()));
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    tooltip.hide();
                }
            });
        }
    }
}
