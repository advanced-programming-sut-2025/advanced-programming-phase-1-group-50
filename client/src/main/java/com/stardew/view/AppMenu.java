package com.stardew.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.model.Result;

import java.util.Scanner;

public interface AppMenu {
    Stage getStage();

    default void showResult(Result result) {
        Dialog dialog = new Dialog("Result", GamePictureManager.skin);
        dialog.setColor(Color.LIGHT_GRAY);
        dialog.pad(30, 5, 20, 5);
        dialog.setBackground(GamePictureManager.windowWoodBackground);
        dialog.text(result.getMessage());
        dialog.getContentTable().getCell(dialog.getContentTable().getChildren().first())
            .getActor().setColor(result.getSuccessful() ? Color.GREEN : Color.RED);
        dialog.button(new TextButton("OK", GamePictureManager.skin));
        dialog.pack();
        dialog.setPosition(
            getStage().getCamera().position.x - dialog.getWidth() / 2,
            getStage().getCamera().position.y - dialog.getHeight() / 2);
        getStage().addActor(dialog);
    }

}
