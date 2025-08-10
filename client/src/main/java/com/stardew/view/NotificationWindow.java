package com.stardew.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.stardew.model.Notification.MarriageRequest;
import com.stardew.model.Notification.Notification;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.windows.CloseableWindow;

public class NotificationWindow extends CloseableWindow {
    private final NotificationManager manager;

    public NotificationWindow(Notification notification, Stage stage, NotificationManager manager) {
        super((notification instanceof MarriageRequest) ? "Marriage Request" : "Notification" , stage);
        this.manager = manager;

        setBackground(GamePictureManager.windowWoodBackground.tint(new Color(1f,1f,1f,0.6f)));
        getTitleTable().setColor(Color.WHITE);

        setModal(false);
        setMovable(false);
        setResizable(false);

        setSize(250, 100);

        Table mainTable = new Table();
        mainTable.pad(8);
        mainTable.setFillParent(true);


        Label senderLabel = new Label(notification.getSender() + ": ",GamePictureManager.skin);
        senderLabel.setColor(Color.BROWN);
        senderLabel.setFontScale(0.8f);
        senderLabel.setWrap(false);

        Label messageLabel = new Label(notification.getMessage(), GamePictureManager.skin);
        messageLabel.setColor(Color.BLACK);
        messageLabel.setFontScale(0.8f);
        messageLabel.setWrap(true);

        Table textTable = new Table();
        textTable.add(senderLabel).width(60).left().padLeft(5).row();
        textTable.add(messageLabel).width(180).left();

        mainTable.add(textTable).expand().fill().left().pad(5);
        add(mainTable).expand().fill();


        setPosition(
            stage.getWidth() - getWidth() - 10,
            10
        );

        addAction(Actions.sequence(
            Actions.delay(3f),
            Actions.run(this::closeWindow)
        ));
    }

    @Override
    protected void closeWindow() {
        super.closeWindow();
        manager.removeNotification(this);
        remove();
    }
}
