package com.stardew.view.craftingWindows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
    private ImageButton closeButton;

    public CookingWindow(Stage stage) {
        super("Cooking Menu", GamePictureManager.skin);

        this.stage = stage;

        setModal(true);
        setMovable(false);
        setResizable(false);

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
        setBackground(GamePictureManager.windowWoodBackground);
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

        //adding close button for the window
        closeButton = new ImageButton(GamePictureManager.closeWindow);
        getTitleTable().add(closeButton);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getChildren().forEach(Actor::clearListeners);
                remove();
            }
        });
    }

    private void showResult(Result result) {
        Dialog dialog = new Dialog("Result", GamePictureManager.skin);
        dialog.setColor(Color.LIGHT_GRAY);
        dialog.pad(30, 5, 20, 5);
        dialog.setBackground(GamePictureManager.windowWoodBackground);
        dialog.text(result.getMessage());
        dialog.getContentTable().getCell(dialog.getContentTable().getChildren().first())
            .getActor().setColor(result.getSuccessful() ? Color.GREEN : Color.RED);
        dialog.button(new TextButton("OK", GamePictureManager.skin, "small"));
        dialog.show(stage);
    }

}
