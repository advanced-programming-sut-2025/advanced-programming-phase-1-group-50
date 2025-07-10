package com.stardew.view.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.stardew.models.GameAssetManagers.GamePictureManager;

public class SmartTooltip extends Container<Label> {
    private static SmartTooltip instance;
    private final Stage stage;
    private final Label label;
    private boolean showing = false;

    private SmartTooltip(Stage stage, Skin skin) {
        super();
        this.stage = stage;
        this.label = new Label("", skin);
        this.setActor(label);

        this.setVisible(false);
        this.setBackground(GamePictureManager.woodBackground);
        this.pack();

        this.getColor().a = 0f;

        stage.addActor(this);

    }

    public static void initialize(Stage stage, Skin skin) {
        if (instance == null) {
            instance = new SmartTooltip(stage, skin);
        }
    }

    public static SmartTooltip getInstance() {
        if (instance == null) {
            throw new IllegalStateException("SmartTooltip not initialized");
        }
        return instance;
    }

    public void show(String text) {
        clearActions();

        label.setText(text);
        label.setAlignment(Align.center);
        label.setColor(Color.CYAN);
        pack();

        float maxX = stage.getViewport().getWorldWidth();

        Vector2 screen = new Vector2(Gdx.input.getX(), Gdx.input.getY());

        if (screen.x + getHeight() + 10 > maxX)
            screen.x = maxX - getHeight() - 20;
        if (screen.y < getHeight())
            screen.y = getHeight() + 20;

        Vector2 mouseStage = stage.screenToStageCoordinates(screen);
        this.setPosition(mouseStage.x + 10, mouseStage.y + 10);

        this.toFront();

        if (!showing) {
            showing = true;
            this.setVisible(true);
            this.getColor().a = 0f;
            this.addAction(Actions.fadeIn(0.2f));
        }
    }

    public void hide() {
        clearActions();

        if (showing) {
            showing = false;
            this.addAction(Actions.sequence(
                Actions.delay(0.1f),
                Actions.fadeOut(0.2f),
                Actions.visible(false)
            ));
        }
    }
}
