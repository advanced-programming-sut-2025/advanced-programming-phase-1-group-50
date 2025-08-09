package com.stardew.view.ChatWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.stardew.controller.PlayersRealtionController.PlayersRelationController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.windows.CloseableWindow;

import java.util.ArrayList;

public class ChatMessageWindow extends CloseableWindow {

    private final int gameId;
    private final ArrayList<String> players;

    private TextField messageField;
    private final Table mainContent;

    public ChatMessageWindow(Stage stage, int gameId, ArrayList<String> players) {
        super("Send Message", stage);
        this.gameId = gameId;
        this.players = players;

        padTop(20);
        padBottom(20);
        padLeft(30);
        padRight(30);

        mainContent = new Table();
        mainContent.defaults().pad(8);
        add(mainContent).expand().fill();

        createUI();
    }

    private void createUI() {
        mainContent.clearChildren();

        Label titleLabel = new Label("Write your message", GamePictureManager.skin);
        titleLabel.setFontScale(1.2f);
        titleLabel.setColor(Color.DARK_GRAY);
        titleLabel.setAlignment(Align.center);
        mainContent.add(titleLabel).colspan(2).padBottom(15).row();

        messageField = new TextField("", GamePictureManager.skin);
        messageField.setMessageText("Type your message...");
        messageField.setColor(0.95f, 0.98f, 0.95f, 1f);
        messageField.getStyle().fontColor = Color.BLACK;
        mainContent.add(messageField).colspan(2).width(400).height(40).padBottom(10).row();

        TextButton sendButton = new TextButton("Send", GamePictureManager.skin);
        sendButton.setColor(Color.FOREST);
        sendButton.getLabel().setFontScale(1.1f);
        sendButton.addAction(Actions.alpha(0.9f));

        mainContent.add(sendButton).colspan(2).width(150).height(50).padTop(10);

        pack();
        centerWindow();

        sendButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String text = messageField.getText().trim();
                if (text.isEmpty()) {
                    messageField.addAction(Actions.sequence(
                        Actions.color(Color.SCARLET, 0.1f),
                        Actions.color(Color.WHITE, 0.2f)
                    ));
                    return;
                }

                PlayersRelationController.talkToPlayer(gameId, players, text, result -> {
                    showResult(result);
                    closeWindow();
                });
            }
        });
    }

    private void centerWindow() {
        setSize(500, 250);
        invalidate();
        layout();
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }
}
