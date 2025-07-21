package com.stardew.view.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.CookingAndCraftingControllers.ArtisanController;
import com.stardew.models.GameAssetManagers.ArtisanAsset;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;

public class ArtisanOptionWindow extends CloseableWindow {
    private final ArtisanController controller = new ArtisanController();
    private final TextButton cancelProcessButton;
    private final TextButton cheatFinishProcessButton;
    private final TextButton showInfoButton;
    private final TextButton collectProductButton;

    public ArtisanOptionWindow(ArtisanAsset artisanAsset,Stage stage, float x, float y) {
        super("options", stage);

        pad(30, 5, 20, 0);
        defaults().space(20);
        pack();
        setPosition(x, y);
        setSize(250, 400);

        showInfoButton = new TextButton("Show Information", GamePictureManager.skin);
        cheatFinishProcessButton = new TextButton("Finish_Process(cheat)", GamePictureManager.skin);
        cancelProcessButton = new TextButton("Cancel Process", GamePictureManager.skin);
        collectProductButton = new TextButton("Collect Product", GamePictureManager.skin);

        add(showInfoButton).row();
        add(cheatFinishProcessButton).row();
        add(cancelProcessButton).row();

        if (controller.isReadyProduct(artisanAsset.name()))
            add(collectProductButton).row();

        SmartTooltip tooltip = SmartTooltip.getInstance();

        showInfoButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                tooltip.show(artisanAsset.getDescription());
                return true;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (toActor == null || !toActor.isDescendantOf(event.getListenerActor()))
                    tooltip.hide();
            }
        });

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
    }
}
