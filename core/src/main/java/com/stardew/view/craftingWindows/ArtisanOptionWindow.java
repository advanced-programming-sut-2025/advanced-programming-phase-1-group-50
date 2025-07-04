package com.stardew.view.craftingWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.CookingAndCraftingControllers.ArtisanController;
import com.stardew.models.GameAssetManagers.ArtisanAsset;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;

public class ArtisanOptionWindow extends Window {
    private final ArtisanController controller = new ArtisanController();
    private TextButton cancelProcessButton;
    private TextButton cheatFinishProcessButton;
    private TextButton showInfoButton;
    private TextButton collectProductButton;
    private ImageButton closeButton;
    private Stage stage;

    public ArtisanOptionWindow(ArtisanAsset artisanAsset,Stage stage, float x, float y) {
        super("options", GamePictureManager.skin);

        setModal(true);
        setMovable(false);
        setResizable(false);

        pad(30, 5, 20, 0);
        defaults().space(20);
        pack();
        setBackground(GamePictureManager.windowWoodBackground);
        setPosition(x, y);
        setSize(250, 400);

        this.stage = stage;
        showInfoButton = new TextButton("Show Information", GamePictureManager.skin);
        cheatFinishProcessButton = new TextButton("Finish_Process(cheat)", GamePictureManager.skin);
        cancelProcessButton = new TextButton("Cancel Process", GamePictureManager.skin);
        collectProductButton = new TextButton("Collect Product", GamePictureManager.skin);

        add(showInfoButton).row();
        add(cheatFinishProcessButton).row();
        add(cancelProcessButton).row();

//        if (!controller.isReadyProduct(artisanAsset.name()))
            add(collectProductButton).row();

        TooltipManager tooltipManager = TooltipManager.getInstance();
        tooltipManager.initialTime = 0f;
        tooltipManager.resetTime = 0f;
        tooltipManager.subsequentTime = 0f;

        showInfoButton.addListener(new Tooltip<>(artisanAsset.getDescriptionLabel(), tooltipManager));
        cheatFinishProcessButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.cheatFinishProcess(artisanAsset.name());
            }
        });
        cancelProcessButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.cancelProcess(artisanAsset.name());
                showResult(result);
            }
        });
        collectProductButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.artisanGet(artisanAsset.name());
                showResult(result);
            }
        });

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
