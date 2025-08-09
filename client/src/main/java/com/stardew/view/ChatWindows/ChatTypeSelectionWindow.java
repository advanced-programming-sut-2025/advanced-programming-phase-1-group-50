package com.stardew.view.ChatWindows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.windows.CloseableWindow;

import java.util.ArrayList;

public class ChatTypeSelectionWindow extends CloseableWindow {

    private final int gameId;
    private final ArrayList<String> players = new ArrayList<>();

    private Table playerSelectionTable;
    private SelectBox<String> playerSelectBox;
    private TextButton startChatButton;
    private ButtonGroup<TextButton> chatTypeGroup;

    private Table mainContent;

    public ChatTypeSelectionWindow(Stage stage, int gameId) {
        super("Chat window", stage);
        this.gameId = gameId;

        padTop(20);
        padBottom(20);
        padLeft(30);
        padRight(30);

        mainContent = new Table();
        mainContent.defaults().pad(8);
        add(mainContent).expand().fill();

        refresh();
    }

    private void refresh() {
        new Thread(() -> {
            Gdx.app.postRunnable(this::createUI);
        }).start();
    }

    private void createUI() {
        mainContent.clearChildren();

        Label titleLabel = new Label("Select Chat Type", GamePictureManager.skin);
        titleLabel.setFontScale(1.2f);
        titleLabel.setColor(Color.BLACK);
        titleLabel.setAlignment(Align.center);
        mainContent.add(titleLabel).colspan(2).padBottom(15).row();

        chatTypeGroup = new ButtonGroup<>();
        TextButton publicChatButton = new TextButton("Public Chat", GamePictureManager.skin);
        publicChatButton.getLabel().setFontScale(1.2f);
        publicChatButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.2f));
        publicChatButton.setColor(Color.OLIVE);

        TextButton privateChatButton = new TextButton("Private Chat", GamePictureManager.skin);
        privateChatButton.getLabel().setFontScale(1.0f);
        privateChatButton.setColor(Color.ROYAL);

        chatTypeGroup.add(publicChatButton, privateChatButton);
        chatTypeGroup.setMinCheckCount(1);
        chatTypeGroup.setMaxCheckCount(1);
        publicChatButton.setChecked(true);

        Table chatTypeTable = new Table();
        chatTypeTable.add(publicChatButton).width(180).height(50).pad(5);
        chatTypeTable.add(privateChatButton).width(180).height(50).pad(5);
        mainContent.add(chatTypeTable).colspan(2).padBottom(15).row();


        playerSelectionTable = new Table();
        playerSelectionTable.setVisible(false);

        Label selectPlayerLabel = new Label("Select Player:", GamePictureManager.skin);
        selectPlayerLabel.setColor(Color.DARK_GRAY);
        playerSelectionTable.add(selectPlayerLabel).padRight(10).left();

        playerSelectBox = new SelectBox<>(GamePictureManager.skin);

        playerSelectBox.setColor(0.85f, 0.95f, 1f, 1f);
        playerSelectBox.setItems(players.toArray(new String[0]));
        playerSelectionTable.add(playerSelectBox).width(200).height(40);

        mainContent.add(playerSelectionTable).colspan(2).padBottom(15).row();

        startChatButton = new TextButton("Start Chat", GamePictureManager.skin);
        startChatButton.setColor(Color.GREEN);
        startChatButton.getLabel().setFontScale(1.1f);
        mainContent.add(startChatButton).colspan(2).width(200).height(55).padTop(10);

        pack();
        centerWindow();

        privateChatButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playerSelectionTable.setVisible(true);
                publicChatButton.getLabel().setFontScale(1.0f);
                publicChatButton.addAction(Actions.scaleTo(1.0f, 1.0f, 0.2f));
                privateChatButton.getLabel().setFontScale(1.2f);
                privateChatButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.2f));

                validateStartButton();
            }
        });

        publicChatButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playerSelectionTable.setVisible(false);
                publicChatButton.getLabel().setFontScale(1.2f);
                publicChatButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.2f));
                privateChatButton.getLabel().setFontScale(1.0f);
                privateChatButton.addAction(Actions.scaleTo(1.0f, 1.0f, 0.2f));

                validateStartButton();
            }
        });

        playerSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                validateStartButton();
            }
        });

        startChatButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleStartChat();
            }
        });

        validateStartButton();
    }

    private void validateStartButton() {
        boolean isPrivate = chatTypeGroup.getChecked() != null &&
            chatTypeGroup.getChecked().getText().toString().contains("Private");

        startChatButton.setDisabled(isPrivate &&
            (playerSelectBox.getSelected() == null ||
                playerSelectBox.getSelected().isEmpty()));
    }

    private void handleStartChat() {
        if (chatTypeGroup.getChecked().getText().toString().contains("Public")) {
            System.out.println("Starting public chat...");
        } else {
            String selectedPlayer = playerSelectBox.getSelected();
            System.out.println("Starting private chat with: " + selectedPlayer);
        }

        closeWindow();
    }

    private void centerWindow() {
        setSize(640, 400);
        invalidate();
        layout();

        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }
}
