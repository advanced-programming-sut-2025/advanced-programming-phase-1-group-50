package com.stardew.view.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.CookingAndCraftingControllers.CookingController;
import com.stardew.models.GameAssetManagers.CookingAsset;
import com.stardew.models.GameAssetManagers.GamePictureManager;

import java.util.HashMap;

public class CookingWindow extends CloseableWindow {
    private HashMap<CookingAsset, ImageButton> buttons = new HashMap<>();
    private CookingController controller = new CookingController();

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
            Gdx.graphics.getWidth() / 2f - getWidth() / 2,
            Gdx.graphics.getHeight() / 2f - getHeight() / 2);
    }


    private void createUI() {

        for (CookingAsset cookingAsset : CookingAsset.values()) {
            buttons.put(cookingAsset, new ImageButton(cookingAsset.getStyle()));
        }

        //Player player = App.getGame().getCurrentPlayingPlayer();

        TooltipManager tooltipManager = TooltipManager.getInstance();
        tooltipManager.initialTime = 0f;
        tooltipManager.subsequentTime = 0f;

        for (CookingAsset cookingAsset : CookingAsset.values()) {
            ImageButton button = buttons.get(cookingAsset);
            //button.setDisabled(!player.getBackpack().containRecipe(cookingAsset.getRecipe()));
            button.setDisabled(true);  //delete this line
            button.addListener(new Tooltip<>(cookingAsset.getDescription(), tooltipManager));
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    button.setDisabled(false);  //delete this line
//                    if (!button.isDisabled()) {
//                        Result result = controller.cookingPrepare(cookingAsset.name());
//                        showResult(result);
//                    }
                }
            });
        }
    }

}
