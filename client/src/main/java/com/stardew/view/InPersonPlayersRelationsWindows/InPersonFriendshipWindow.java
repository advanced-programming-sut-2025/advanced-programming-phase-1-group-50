package com.stardew.view.InPersonPlayersRelationsWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.stardew.controller.PlayersRealtionController.PlayersRelationController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.userInfo.Player;
import com.stardew.view.windows.CloseableWindow;

public class InPersonFriendshipWindow extends CloseableWindow {

    public InPersonFriendshipWindow(Stage stage, Player otherPlayer) {
        super("In-person friendship", stage);

        padTop(20);
        padBottom(20);
        padLeft(30);
        padRight(30);

        Table contentTable = new Table();

        Label nameLabel = new Label("Player: " + otherPlayer.getUsername(), GamePictureManager.skin);
        nameLabel.setFontScale(1.2f);
        nameLabel.setColor(Color.BLACK);
        nameLabel.setAlignment(Align.center);
        contentTable.add(nameLabel).padBottom(20).center().row();

        TextButton hugButton = new TextButton("Hug", GamePictureManager.skin);
        hugButton.getLabel().setColor(Color.WHITE);
        hugButton.getStyle().fontColor = Color.WHITE;
        hugButton.setColor(Color.FOREST);

        Image hugIcon = new Image(GamePictureManager.hugIcon);
        hugIcon.setSize(28, 28);
        HorizontalGroup hugGroup = new HorizontalGroup().space(10);
        hugGroup.addActor(hugIcon);
        hugGroup.addActor(hugButton);

        hugButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result temp = canHug(otherPlayer);
                if (temp.getSuccessful()) {
                    hug(otherPlayer);
                    closeWindow();
                } else {
                    showResult(temp);
                }
            }
        });

        TextButton flowerButton = new TextButton("Give Flowers", GamePictureManager.skin);
        flowerButton.getLabel().setColor(Color.WHITE);
        flowerButton.getStyle().fontColor = Color.WHITE;
        flowerButton.setColor(Color.PINK);

        Image flowerIcon = new Image(GamePictureManager.roseIcon);
        flowerIcon.setSize(28, 28);
        HorizontalGroup flowerGroup = new HorizontalGroup().space(10);
        flowerGroup.addActor(flowerIcon);
        flowerGroup.addActor(flowerButton);

        flowerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result temp = canGiveFlowers(otherPlayer);
                if (temp.getSuccessful()) {
                    giveFlowers(otherPlayer);
                    closeWindow();
                } else {
                    showResult(temp);
                }
            }
        });

        TextButton marryButton = new TextButton("Marriage request", GamePictureManager.skin);
        marryButton.getLabel().setColor(Color.WHITE);
        marryButton.getStyle().fontColor = Color.WHITE;
        marryButton.setColor(Color.GOLD);

        Image ringIcon = new Image(GamePictureManager.ringIcon);
        ringIcon.setSize(28, 28);
        HorizontalGroup marryGroup = new HorizontalGroup().space(10);
        marryGroup.addActor(ringIcon);
        marryGroup.addActor(marryButton);

        marryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result temp = canMarry(otherPlayer);
                if (temp.getSuccessful()) {
                    marry(otherPlayer);
                    closeWindow();
                } else {
                    showResult(temp);
                }
            }
        });

        contentTable.defaults().pad(10);
        contentTable.add(hugGroup).left().row();
        contentTable.add(flowerGroup).left().row();
        contentTable.add(marryGroup).left().row();

        add(contentTable);
        pack();

        setWidth(350);
        invalidate();
        layout();

        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }

    private Result canHug(Player otherPlayer) {
        return PlayersRelationController.canHug(otherPlayer);
    }

    private Result canGiveFlowers(Player otherPlayer) {
        return PlayersRelationController.canGiveFlower(otherPlayer);
    }

    private Result canMarry(Player otherPlayer) {
        return PlayersRelationController.canAskMarriage(otherPlayer);
    }

    private void hug(Player otherPlayer) {
        PlayersRelationController.hug(otherPlayer);
        // player should walk together
        spawnHugEmojis(stage, getX() + getWidth() / 2, getY() + 20);
    }

    private void giveFlowers(Player otherPlayer) {
        PlayersRelationController.giveFlower(otherPlayer);
        // player should walk together
        spawnRoseEmojis(stage, getX() + getWidth() / 2, getY() + 20);
    }

    private void marry(Player otherPlayer) {
        PlayersRelationController.askMarriage(otherPlayer);
        spawnRingEmojis(stage, getX() + getWidth() / 2, getY() + 20);
    }

    private void spawnHugEmojis(Stage stage, float x, float y) {
        Texture emojiTexture = GamePictureManager.hugIcon;

        for (int i = 0; i < 10; i++) {
            int direction = (i % 2 == 0) ? 1 : -1;
            final Image emoji = new Image(emojiTexture);
            emoji.setSize(64, 64);
            emoji.setPosition(
                x + (float) (Math.random() * 800 * direction),
                y
            );
            stage.addActor(emoji);

            emoji.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.moveBy(0, 100 + (float)Math.random() * 500, 2f),
                    Actions.fadeOut(2f)
                ),
                Actions.removeActor()
            ));
        }
    }

    private void spawnRoseEmojis(Stage stage, float x, float y) {
        Texture emojiTexture = GamePictureManager.roseIcon;

        for (int i = 0; i < 10; i++) {
            int direction = (i % 2 == 0) ? 1 : -1;
            final Image emoji = new Image(emojiTexture);
            emoji.setSize(64, 64);
            emoji.setPosition(
                x + (float) (Math.random() * 800 * direction),
                y
            );
            stage.addActor(emoji);

            emoji.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.moveBy(0, 100 + (float)Math.random() * 500, 2f),
                    Actions.fadeOut(2f)
                ),
                Actions.removeActor()
            ));
        }
    }

    private void spawnRingEmojis(Stage stage, float x, float y) {
        Texture emojiTexture = GamePictureManager.ringIcon;

        for (int i = 0; i < 10; i++) {
            int direction = (i % 2 == 0) ? 1 : -1;
            final Image emoji = new Image(emojiTexture);
            emoji.setSize(64, 64);
            emoji.setPosition(
                x + (float) (Math.random() * 800 * direction),
                y
            );
            stage.addActor(emoji);

            emoji.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.moveBy(0, 100 + (float)Math.random() * 500, 2f),
                    Actions.fadeOut(2f)
                ),
                Actions.removeActor()
            ));
        }
    }

}
