package com.stardew.view.InventoryWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.windows.CloseableWindow;

public class JournalWindow extends CloseableWindow {
    public JournalWindow(Stage stage) {
        super("journal window" , stage);

        setSize(700, 500);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);
        pad(25, 10, 15, 0);
        setColor(Color.ORANGE);
        align(Align.top);


        Label.LabelStyle st = new Label.LabelStyle();
        st.font = GamePictureManager.smallFont;
        st.fontColor = Color.BLACK;
        Label journalLabel = new Label("", st);
        journalLabel.setFontScale(1.2f);

        journalLabel.setText("this is a journal window." + "\n" +
            "there is no information about this in documentation");
        add(journalLabel).left().padBottom(10).center();
        row();
    }
}
