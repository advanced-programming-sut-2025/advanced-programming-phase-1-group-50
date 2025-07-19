package com.stardew.view.PlayersRelationsWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.PlayersRealtionController.PlayersRelationController;
import com.stardew.models.BetweenPlayersGift;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.Player;
import com.stardew.view.windows.CloseableWindow;

public class GiftHistoryWindow extends CloseableWindow {
    private final Player otherPlayer;
    private final Table giftTable;
    private final FriendshipWindow friendshipWindow;

    public GiftHistoryWindow(Stage stage, FriendshipWindow friendshipWindow, Player otherPlayer) {
        super("Gifts' History", stage);
        this.otherPlayer = otherPlayer;
        this.friendshipWindow = friendshipWindow;

        pad(40);
        defaults().space(15);

        giftTable = new Table();
        giftTable.defaults().space(10);

        ScrollPane scrollPane = new ScrollPane(giftTable, GamePictureManager.skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);

        Table headerTable = new Table();
        headerTable.defaults().space(10);
        headerTable.add(new Label("Product", GamePictureManager.skin)).width(120);
        headerTable.add(new Label("ID", GamePictureManager.skin)).width(60);
        headerTable.add(new Label("Sender", GamePictureManager.skin)).width(100);
        headerTable.add(new Label("Receiver", GamePictureManager.skin)).width(100);
        headerTable.add(new Label("Rate", GamePictureManager.skin)).width(60);
        headerTable.add(new Label("Action", GamePictureManager.skin)).width(100);

        add(headerTable).row();
        add(scrollPane).width(600).height(400).row();

        fillGiftTable();

        pack();
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }

    protected void fillGiftTable() {
        giftTable.clear();
        int counter = 0;

        for (BetweenPlayersGift gift : App.getGame().getGifts()) {
            boolean isInvolved =
                (gift.getReceiver().equals(App.getGame().getCurrentPlayingPlayer()) && gift.getSender().equals(otherPlayer)) ||
                    (gift.getSender().equals(App.getGame().getCurrentPlayingPlayer()) && gift.getReceiver().equals(otherPlayer));

            if (!isInvolved) {
                continue;
            }

            counter++;

            Label productLabel = new Label(gift.getProduct().toString(), GamePictureManager.skin);
            Label idLabel = new Label(String.valueOf(gift.getId()), GamePictureManager.skin);
            Label senderLabel = new Label(gift.getSender().getUsername(), GamePictureManager.skin);
            Label receiverLabel = new Label(gift.getReceiver().getUsername(), GamePictureManager.skin);
            Label rateLabel = new Label(String.valueOf(gift.getRate()), GamePictureManager.skin);

            TextButton rateButton = new TextButton("Rate", GamePictureManager.skin);
            boolean canRate = PlayersRelationController.canRateGift(gift);
            if (!canRate) {
                rateButton.setColor(Color.DARK_GRAY);
                rateButton.setDisabled(true);
            }


            if (canRate) {
                rateButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        openRateGiftWindow(gift);
                    }
                });
            }

            giftTable.add(productLabel).width(120);
            giftTable.add(idLabel).width(60);
            giftTable.add(senderLabel).width(100);
            giftTable.add(receiverLabel).width(100);
            giftTable.add(rateLabel).width(60);
            giftTable.add(rateButton).width(100).row();
        }

        if (counter == 0) {
            Label emptyLabel = new Label("History is empty.", GamePictureManager.skin);
            emptyLabel.setWrap(true);
            giftTable.add(emptyLabel)
                .colspan(6)
                .width(380)
                .padTop(20)
                .padLeft(10)
                .padRight(10)
                .center();
            giftTable.row();
        }
    }

    private void openRateGiftWindow(BetweenPlayersGift gift) {
        stage.addActor(new RateGiftWindow(stage, friendshipWindow, this, gift));
    }
}
