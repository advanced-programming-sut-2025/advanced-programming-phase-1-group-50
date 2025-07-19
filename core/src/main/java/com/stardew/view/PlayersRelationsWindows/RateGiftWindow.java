package com.stardew.view.PlayersRelationsWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.PlayersRealtionController.PlayersRelationController;
import com.stardew.models.BetweenPlayersGift;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.windows.CloseableWindow;

public class RateGiftWindow extends CloseableWindow {
    private final Label[] starLabels = new Label[5];
    private int selectedRate = 0;

    public RateGiftWindow(Stage stage, GiftHistoryWindow giftHistoryWindow, BetweenPlayersGift gift) {
        super("Rating the gift", stage);

        pad(60);
        defaults().space(20);

        Label instructionLabel = new Label("Click on stars to rate:", GamePictureManager.skin);
        add(instructionLabel).colspan(5).row();

        Table starTable = new Table();
        for (int i = 0; i < 5; i++) {
            final int starValue = i + 1;
            Label starLabel = new Label("☆", GamePictureManager.skin);
            starLabel.setFontScale(2.5f);
            starLabel.setColor(Color.GOLD);

            starLabel.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    selectedRate = starValue;
                    refreshStars();
                }
            });

            starLabels[i] = starLabel;
            starTable.add(starLabel).pad(10);
        }

        add(starTable).colspan(5).row();

        TextButton rateButton = new TextButton("Submit Rating", GamePictureManager.skin);
        rateButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedRate > 0) {
                    PlayersRelationController.rateGift(gift, selectedRate);
                    giftHistoryWindow.fillGiftTable();
                    closeWindow();
                }
            }
        });

        add(rateButton).colspan(5).padTop(20).width(200).height(60).row();

        pack();
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }

    private void refreshStars() {
        for (int i = 0; i < 5; i++) {
            if (i < selectedRate) {
                starLabels[i].setText("★");
            } else {
                starLabels[i].setText("☆");
            }
        }
    }

    @Override
    protected void closeWindow() {
        addAction(Actions.sequence(
            Actions.parallel(
                Actions.fadeOut(0.2f),
                Actions.scaleTo(0.7f, 0.7f, 0.2f, Interpolation.fade)
            ),
            Actions.removeActor()
        ));
    }
}
