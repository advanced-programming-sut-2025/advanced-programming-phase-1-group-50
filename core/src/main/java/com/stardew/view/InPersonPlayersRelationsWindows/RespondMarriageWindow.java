package com.stardew.view.InPersonPlayersRelationsWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Align;
import com.stardew.controller.PlayersRealtionController.PlayersRelationController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Notification.MarriageRequest;
import com.stardew.view.windows.CloseableWindow;

public class RespondMarriageWindow extends CloseableWindow {

    public RespondMarriageWindow(Stage stage, MarriageRequest marriageRequest) {
        super("Marriage request", stage);

        String senderName = marriageRequest.getSender().getUsername();
        Label messageLabel = new Label(senderName + " has proposed to you!\nDo you want to accept the marriage request?", GamePictureManager.skin);
        messageLabel.setColor(Color.BLACK);
        messageLabel.setFontScale(1.2f);
        messageLabel.setWrap(true);
        messageLabel.setAlignment(Align.top);

        TextButton acceptButton = new TextButton("Accept", GamePictureManager.skin);
        TextButton rejectButton = new TextButton("Reject", GamePictureManager.skin);

        Table buttonTable = new Table();
        buttonTable.add(acceptButton).width(130).padRight(20);
        buttonTable.add(rejectButton).width(130);

        add(messageLabel).width(300).pad(30, 10, 10, 10).row();
        add(buttonTable).center().padBottom(20);
        pack();

        acceptButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PlayersRelationController.respondMarriage(marriageRequest, true);
                closeWindow();
            }
        });

        rejectButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PlayersRelationController.respondMarriage(marriageRequest, false);
                closeWindow();
            }
        });
    }
}
