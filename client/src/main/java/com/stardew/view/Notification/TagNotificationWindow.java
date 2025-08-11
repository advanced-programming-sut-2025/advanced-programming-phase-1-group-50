package com.stardew.view.Notification;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.stardew.model.Notification.Notification;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.windows.CloseableWindow;

public class TagNotificationWindow extends CloseableWindow {

    public TagNotificationWindow(Stage stage, Notification notification) {
        super("Tag Notification", stage);

        setSize(400, 300);
        Table contentTable = new Table();
        contentTable.defaults().pad(10);

        Label senderLabel = new Label(notification.getSender() + " tagged you!", GamePictureManager.skin);
        senderLabel.setColor(Color.BROWN);
        senderLabel.setFontScale(1.2f);

        Label messageLabel = new Label("Message: " + notification.getMessage(), GamePictureManager.skin);
        messageLabel.setColor(Color.BLACK);
        messageLabel.setFontScale(0.85f);
        messageLabel.setWrap(true);

        contentTable.add(senderLabel).center().top().row();
        contentTable.add(messageLabel).width(300).center().row();

        add(contentTable).pad(20);

        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );

        stage.addActor(this);
    }
}

