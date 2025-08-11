package com.stardew.view.StoreWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.windows.CloseableWindow;

public class StoreClosedMessageWindow extends CloseableWindow {
    public StoreClosedMessageWindow(Stage stage) {
        super("Store is Closed", stage);

        pad(50);
        defaults().space(20);

        Label message = new Label("The store is currently closed.\nPlease come back between working hours.", GamePictureManager.skin);
        message.setColor(Color.RED);
        add(message);

        pack();
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }
}
