package com.stardew.view.InPersonPlayersRelationsWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.stardew.controller.PlayersRealtionController.PlayersRelationController;
import com.stardew.model.Notification.MarriageRequest;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.windows.CloseableWindow;

public class RespondMarriageWindow extends CloseableWindow {
    private final int gameId;

    public RespondMarriageWindow(int gameId,Stage stage, MarriageRequest marriageRequest) {
        super("Marriage request", stage);
        this.gameId = gameId;

        setSize(400, 300);

        getTitleTable().removeActor(closeButton);
        closeButton = null;

        String senderName = marriageRequest.getSender();
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
                PlayersRelationController.respondMarriage(gameId,marriageRequest, true);
                closeWindow();
            }
        });

        rejectButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PlayersRelationController.respondMarriage(gameId,marriageRequest, false);
                closeWindow();
            }
        });

        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }

}
