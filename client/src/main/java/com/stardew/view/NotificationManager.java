package com.stardew.view;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.model.Notification.Notification;

import java.util.ArrayList;

public class NotificationManager {
    private static final float startX = 20f;
    private static final float startY = 20f;
    private static final float notificationSpacing = 10f;

    private static NotificationManager instance;
    private final Stage stage;
    private final ArrayList<NotificationWindow> activeNotifications = new ArrayList<>();

    private NotificationManager(Stage stage) {
        this.stage = stage;
    }

    public static void initialize(Stage stage) {
        if (instance == null) {
            instance = new NotificationManager(stage);
        }
    }

    public static NotificationManager getInstance() {
        return instance;
    }

    public void showNotification(Notification notification) {
        NotificationWindow window = new NotificationWindow(notification, stage, this);
        activeNotifications.add(window);
        stage.addActor(window);
        repositionWindows();
    }

    public void removeNotification(NotificationWindow window) {
        activeNotifications.remove(window);
        repositionWindows();
    }

    public void updatePositions() {
        float y = 0f;
        for (NotificationWindow window : activeNotifications) {
            window.setPosition(
                stage.getCamera().position.x + stage.getWidth() / 2 - window.getWidth() - startX,
                stage.getCamera().position.y - stage.getHeight() / 2 + startY + y
            );
            y += window.getHeight() + notificationSpacing;
        }
    }

    private void repositionWindows() {
        updatePositions();
    }
}
