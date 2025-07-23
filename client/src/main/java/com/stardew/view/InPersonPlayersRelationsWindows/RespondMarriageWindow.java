package com.stardew.view.InPersonPlayersRelationsWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.stardew.controller.PlayersRealtionController.PlayersRelationController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Notification.MarriageRequest;
import com.stardew.view.windows.CloseableWindow;

public class RespondMarriageWindow extends CloseableWindow {

    public RespondMarriageWindow(Stage stage, MarriageRequest marriageRequest) {
        super("Marriage request", stage);
        setSize(400, 300);

        String senderName = marriageRequest.getSender().getUsername();
        Label messageLabel = new Label(senderName + " has proposed to you!\nDo you want to accept the marriage request?", GamePictureManager.skin);
        messageLabel.setColor(Color.BLACK);
        messageLabel.setFontScale(1.2f);
        messageLabel.setWrap(true);
        messageLabel.setAlignment(Align.center);

        Table mainTable = new Table();
        mainTable.setFillParent(true);

        Table textTable = new Table();
        textTable.add(messageLabel).width(350).pad(40).center();

        TextButton acceptButton = new TextButton("Accept", GamePictureManager.skin, "default");
        acceptButton.setColor(Color.FOREST);
        TextButton rejectButton = new TextButton("Reject", GamePictureManager.skin, "default");
        rejectButton.setColor(Color.RED);

        acceptButton.getLabel().setFontScale(1.2f);
        rejectButton.getLabel().setFontScale(1.2f);

        Table buttonTable = new Table();
        buttonTable.add(acceptButton).width(150).height(50).padRight(30);
        buttonTable.add(rejectButton).width(150).height(50);

        mainTable.add(textTable).center().row();
        mainTable.add(buttonTable).center().padBottom(40);

        add(mainTable);

        acceptButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PlayersRelationController.respondMarriage(marriageRequest, true);
                acceptAnimation(stage);
                closeWindow();
            }
        });

        rejectButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PlayersRelationController.respondMarriage(marriageRequest, false);
                rejectAnimation(stage);
                closeWindow();
            }
        });

        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }

    private void acceptAnimation(Stage stage) {
        Image heartLeft = new Image(GamePictureManager.heartLeftIcon);
        Image heartRight = new Image(GamePictureManager.heartRightIcon);
        heartLeft.setSize(150, 200);
        heartRight.setSize(150, 200);

        float centerX = stage.getWidth() / 2f;
        float centerY = stage.getHeight() / 2f;

        heartLeft.setPosition(-150, centerY - 100);
        heartRight.setPosition(stage.getWidth() + 150, centerY - 100);

        stage.addActor(heartLeft);
        stage.addActor(heartRight);

        heartLeft.addAction(Actions.sequence(
            Actions.fadeIn(1.3f),
            Actions.moveTo(centerX - 150, centerY - 100, 1f),
            Actions.moveTo(centerX - 100, centerY - 100, 0.3f),
            Actions.fadeOut(0.2f)
        ));

        heartRight.addAction(Actions.sequence(
            Actions.fadeIn(1.3f),
            Actions.moveTo(centerX , centerY - 100, 1f),
            Actions.moveTo(centerX - 50, centerY - 100, 0.3f),
            Actions.fadeOut(0.2f),
            Actions.run(() -> {
                Image fullHeart = new Image(GamePictureManager.heartFullIcon);
                fullHeart.setSize(200, 200);
                fullHeart.setPosition(centerX - 75, centerY - 100);
                fullHeart.getColor().a = 0;
                stage.addActor(fullHeart);

                fullHeart.addAction(Actions.sequence(
                    Actions.fadeIn(0.5f),
                    Actions.delay(1.0f),
                    Actions.fadeOut(0.5f),
                    Actions.run(() -> {
                        heartLeft.remove();
                        heartRight.remove();
                    }),
                    Actions.removeActor()
                ));
            })
        ));
    }

    private void rejectAnimation(Stage stage) {
        float centerX = stage.getWidth() / 2f;
        float centerY = stage.getHeight() / 2f;

        Image fullHeart = new Image(GamePictureManager.heartFullIcon);
        fullHeart.setSize(150, 150);
        fullHeart.setPosition(centerX - 75, centerY - 75);
        stage.addActor(fullHeart);

        fullHeart.addAction(Actions.sequence(
            Actions.delay(0.5f),
            Actions.run(() -> {
                for (int i = 0; i < 8; i++) {
                    Image piece = new Image(GamePictureManager.heartBrokenIcon);
                    piece.setSize(130, 130);

                    float startX = centerX - 65;
                    float startY = centerY - 65;

                    float angle = (float) (Math.random() * 360);
                    float distance = 200 + (float)(Math.random() * 100);
                    float endX = startX + (float) (Math.cos(Math.toRadians(angle)) * distance);
                    float endY = startY + (float) (Math.sin(Math.toRadians(angle)) * distance);

                    piece.setPosition(startX, startY);
                    piece.setOrigin(Align.center);
                    stage.addActor(piece);

                    float rotation = (float) (Math.random() * 360 - 180);

                    piece.addAction(Actions.parallel(
                        Actions.moveTo(endX, endY, 2.0f),
                        Actions.rotateBy(rotation, 2.0f),
                        Actions.fadeOut(2.0f)
                    ));
                }

                fullHeart.addAction(Actions.sequence(
                    Actions.fadeOut(0.5f),
                    Actions.removeActor()
                ));
            })
        ));
    }
}
