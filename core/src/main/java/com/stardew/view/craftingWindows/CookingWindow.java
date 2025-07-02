package com.stardew.view.craftingWindows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.CookingAndCraftingControllers.CookingController;
import com.stardew.models.GameAssetManagers.CookingAsset;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;

import java.util.HashMap;

public class CookingWindow extends Window {
    private HashMap<CookingAsset, ImageButton> buttons = new HashMap<>();
    private CookingController controller = new CookingController();
    private Stage stage;

    {
        for (CookingAsset cookingAsset : CookingAsset.values()) {
            buttons.put(cookingAsset, new ImageButton(cookingAsset.getStyle()));
        }

        //Player player = App.getGame().getCurrentPlayingPlayer();

        TooltipManager tooltipManager = new TooltipManager();
        tooltipManager.initialTime = 0.2f;
        tooltipManager.subsequentTime = 0.1f;

        for (CookingAsset cookingAsset : CookingAsset.values()) {
            ImageButton button = buttons.get(cookingAsset);
            //button.setDisabled(!player.getBackpack().containRecipe(cookingAsset.getRecipe()));
            button.setDisabled(true);  //delete this line
            button.addListener(new Tooltip<>(cookingAsset.getDescription(), tooltipManager));
            button.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    button.setDisabled(false);  //delete this line
//                    if (!button.isDisabled()) {
//                        Result result = controller.cookingPrepare(cookingAsset.name());
//                        Dialog dialog = new Dialog("Result", GamePictureManager.skin);
//                        dialog.setColor(Color.LIGHT_GRAY);
//                        dialog.text(result.getMessage());
//                        dialog.getContentTable().getCell(dialog.getContentTable().getChildren().first())
//                            .getActor().setColor(result.getSuccessful() ? Color.GREEN : Color.RED);
//                        dialog.button(new TextButton("OK", GamePictureManager.skin, "small"));
//                        dialog.show(stage);
//                    }
                }
            });
        }

    }


    public CookingWindow(Stage stage) {
        super("Cooking Menu", GamePictureManager.skin);

        this.stage = stage;

        setModal(true);
        setMovable(false);
        setResizable(false);

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
        setColor(Color.BROWN);
        setPosition(
            Gdx.graphics.getWidth() / 2f - getWidth() / 2,
            Gdx.graphics.getHeight() / 2f - getHeight() / 2);
    }

}
