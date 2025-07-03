package com.stardew.view.craftingWindows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.CookingAndCraftingControllers.CraftingController;
import com.stardew.models.GameAssetManagers.CraftingAsset;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;

import java.util.HashMap;

public class CraftingWindow extends Window {
    private HashMap<CraftingAsset, ImageButton> buttons = new HashMap<>();
    private CraftingController controller = new CraftingController();
    private Stage stage;


    public CraftingWindow(Stage stage) {
        super("Crafting Menu", GamePictureManager.skin);

        this.stage = stage;

        setModal(true);
        setMovable(false);
        setResizable(false);

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
        setBackground(GamePictureManager.windowWoodBackground);
        setPosition(
            Gdx.graphics.getWidth() / 2f - getWidth() / 2,
            Gdx.graphics.getHeight() / 2f - getHeight() / 2);
    }


    private void createUI() {

        for (CraftingAsset craftingAsset : CraftingAsset.values()) {
            buttons.put(craftingAsset, new ImageButton(craftingAsset.getStyle()));
        }

        //Player player = App.getGame().getCurrentPlayingPlayer();

        TooltipManager tooltipManager = TooltipManager.getInstance();
        tooltipManager.initialTime = 0f;
        tooltipManager.subsequentTime = 0f;

        for (CraftingAsset craftingAsset : CraftingAsset.values()) {
            ImageButton button = buttons.get(craftingAsset);
            //button.setDisabled(!player.getBackpack().containRecipe(craftingAsset.getRecipe()));
            button.setDisabled(true); //delete this line
            button.addListener(new Tooltip<>(craftingAsset.getDescription(), tooltipManager));
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    button.setDisabled(false);  //delete this line
//                    if (!button.isDisabled()) {
//                        Result result = controller.craftingCraft(craftingAsset.name());
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
}
