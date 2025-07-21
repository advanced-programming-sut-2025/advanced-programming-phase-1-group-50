package com.stardew.view.StoreWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Align;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.view.windows.CloseableWindow;

public class TrashCanUpgradeWindow extends CloseableWindow {

    public TrashCanUpgradeWindow(Result result, Stage stage) {
        super("Trash Can Upgrade", stage);

        pad(40);
        defaults().space(20);

        boolean success = result.getSuccessful();

        Label resultLabel = new Label(result.getMessage(), GamePictureManager.skin);
        resultLabel.setColor(success ? Color.GREEN : Color.RED);
        resultLabel.setWrap(true);
        resultLabel.setAlignment(Align.center);

        TextButton okButton = new TextButton("OK", GamePictureManager.skin);
        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                remove();
            }
        });

        add(resultLabel).width(300).padBottom(10).padLeft(20).padRight(20).row();
        add(okButton).width(100).height(40).center().row();

        pack();
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }
}
