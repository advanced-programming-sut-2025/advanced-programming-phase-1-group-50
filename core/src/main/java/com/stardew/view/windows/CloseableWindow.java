package com.stardew.view.windows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;

public abstract class CloseableWindow extends Window {
    protected Stage stage;
    protected ImageButton closeButton;

    public CloseableWindow(String title, Stage stage) {
        super(title, GamePictureManager.skin);

        this.stage = stage;

        setModal(true);
        setMovable(false);
        setResizable(false);

        setBackground(GamePictureManager.windowWoodBackground);

        //adding close button for the window
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageOver = new TextureRegionDrawable(GamePictureManager.closeWindow).tint(Color.RED);
        style.imageOver.setMinWidth(32);
        style.imageOver.setMinHeight(30);
        style.imageUp = GamePictureManager.closeWindow;
        style.imageDown = new TextureRegionDrawable(GamePictureManager.closeWindow).tint(Color.BROWN);
        closeButton = new ImageButton(style);
        getTitleTable().add(closeButton);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                closeWindow();
            }
        });
    }

    protected void showResult(Result result) {
        Dialog dialog = new Dialog("Result", GamePictureManager.skin);
        dialog.setColor(Color.LIGHT_GRAY);
        dialog.pad(30, 5, 20, 5);
        dialog.setBackground(GamePictureManager.windowWoodBackground);
        dialog.text(result.getMessage());
        dialog.getContentTable().getCell(dialog.getContentTable().getChildren().first())
            .getActor().setColor(result.getSuccessful() ? Color.GREEN : Color.RED);
        dialog.button(new TextButton("OK", GamePictureManager.skin));
        dialog.show(stage);
    }

    protected void closeWindow() {
        getChildren().forEach(Actor::clearListeners);
        remove();
    }
}
