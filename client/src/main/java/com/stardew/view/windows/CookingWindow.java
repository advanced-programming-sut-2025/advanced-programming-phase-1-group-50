package com.stardew.view.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.stardew.controller.CookingAndCraftingControllers.CookingController;
import com.stardew.models.GameAssetManagers.CookingAsset;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CookingWindow extends CloseableWindow {
    private final HashMap<CookingAsset, ImageButton> buttons = new HashMap<>();
    private final CookingController controller = new CookingController();

    public CookingWindow(Stage stage, Map<String, String> descriptions, Set<String> ownRecipes) {
        super("Cooking Menu", stage);

        createUI(descriptions, ownRecipes);

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


    private void createUI(Map<String, String> descriptions, Set<String> ownRecipes) {

        for (CookingAsset cookingAsset : CookingAsset.values()) {
            ImageButton imageButton = new ImageButton(cookingAsset.getStyle());
            imageButton.setDisabled(!ownRecipes.contains(cookingAsset.name()));
            buttons.put(cookingAsset, imageButton);

            SmartTooltip tooltip = SmartTooltip.getInstance();

            imageButton.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    //TODO
                    // change here: send message and get Result
//                    Result result = controller.cookingPrepare(cookingAsset.getRecipe());
//                    showResult(result);
                    return true;
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    tooltip.show(descriptions.get(cookingAsset.name()));
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    tooltip.hide();
                }
            });
        }
    }

}
