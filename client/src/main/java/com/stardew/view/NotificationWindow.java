package com.stardew.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.model.Notification.MarriageRequest;
import com.stardew.model.Notification.Notification;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.windows.CloseableWindow;

public class NotificationWindow extends CloseableWindow {
    private final NotificationManager manager;
    private static final Color MARRIAGE_COLOR = new Color(0.8f, 0.4f, 0.8f, 0.8f);
    private static final Color REGULAR_COLOR = new Color(0.6f, 0.8f, 1f, 0.8f);
    private static final Color GLASS_BG = new Color(0.95f, 0.95f, 1f, 0.6f);

    public NotificationWindow(Notification notification, Stage stage, NotificationManager manager) {
        super("Notification", stage);
        this.manager = manager;

        Pixmap pm = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pm.setColor(Color.WHITE);
        pm.fill();
        TextureRegionDrawable white = new TextureRegionDrawable(new Texture(pm));
        pm.dispose();

        setBackground(white.tint(GLASS_BG));
        getTitleTable().setColor(Color.WHITE);

        setColor(notification instanceof MarriageRequest ? MARRIAGE_COLOR : REGULAR_COLOR);

        setModal(false);
        setMovable(false);
        setResizable(false);

        setSize(250, 100);

        Table mainTable = new Table();
        mainTable.pad(8);
        mainTable.setFillParent(true);


        Label senderLabel = new Label(notification.getSender() + ": ",GamePictureManager.skin);
        senderLabel.setColor(Color.WHITE);
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
