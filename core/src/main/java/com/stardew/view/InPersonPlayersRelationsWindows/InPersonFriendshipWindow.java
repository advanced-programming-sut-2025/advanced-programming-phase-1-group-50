package com.stardew.view.InPersonPlayersRelationsWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Align;
import com.stardew.controller.PlayersRealtionController.PlayersRelationController;
import com.stardew.models.Result;
import com.stardew.models.userInfo.Player;
import com.stardew.view.windows.CloseableWindow;
import com.stardew.models.GameAssetManagers.GamePictureManager;

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
        hugButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result temp = canHug(otherPlayer);
                if (temp.getSuccessful()) {
                    hug(otherPlayer);
                    showResult(new Result(true, "A heartfelt hug was shared."));
                    closeWindow();
                } else {
                    showResult(temp);
                }
            }
        });

        TextButton buyFlowersButton = new TextButton("Give Flowers", GamePictureManager.skin);
        buyFlowersButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result temp = canGiveFlowers(otherPlayer);
                if (temp.getSuccessful()) {
                    giveFlowers(otherPlayer);
                    showResult(new Result(true, "You handed them a beautiful flower."));
                    closeWindow();
                } else {
                    showResult(temp);
                }
            }
        });

        TextButton marryButton = new TextButton("Marry", GamePictureManager.skin);
        marryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result temp = canMarry(otherPlayer);
                if (temp.getSuccessful()) {
                    marry(otherPlayer);
                    showResult(new Result(true, "You’ve proposed. Now you wait and hope..."));
                    closeWindow();
                } else {
                    showResult(temp);
                }
            }
        });

        contentTable.defaults().width(200).height(55).pad(8);
        contentTable.add(hugButton).row();
        contentTable.add(buyFlowersButton).row();
        contentTable.add(marryButton).row();

        add(contentTable);
        pack();

        setWidth(300);
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
    }

    private void giveFlowers(Player otherPlayer) {
        PlayersRelationController.giveFlower(otherPlayer);
    }

    private void marry(Player otherPlayer) {
        PlayersRelationController.askMarriage(otherPlayer);
    }
}
