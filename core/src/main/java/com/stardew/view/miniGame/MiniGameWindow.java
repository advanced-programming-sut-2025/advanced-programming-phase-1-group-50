package com.stardew.view.miniGame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.stardew.view.windows.CloseableWindow;

public class MiniGameWindow extends CloseableWindow {



    public MiniGameWindow(Stage stage) {
        super(" Mini Game", stage);
        Label titleLabel = getTitleLabel();
        Label.LabelStyle titleLabelStyle = titleLabel.getStyle();
        titleLabelStyle.fontColor = Color.YELLOW;
        getTitleLabel().setStyle(titleLabelStyle);

        pad(25, 5, 20, 0);
        pack();
        setSize(900, 700);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);
        setColor(Color.BROWN);//TODO

    }
}
