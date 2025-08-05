package com.stardew.view.PlayersRelationsWindows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.google.gson.reflect.TypeToken;
import com.stardew.controller.PlayersRealtionController.PlayersRelationController;
import com.stardew.model.PlayersRelation.BetweenPlayersGift;
import com.stardew.models.ClientInfo.LoggedInUser;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.windows.CloseableWindow;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class GiftHistoryWindow extends CloseableWindow {
    private final String otherPlayerUsername;
    private final Table giftTable;
    private final FriendshipWindow friendshipWindow;
    private final int gameId;
    private final ArrayList<BetweenPlayersGift> allGifts = new ArrayList<>();

    public GiftHistoryWindow(int gameId,Stage stage, FriendshipWindow friendshipWindow, String otherPlayer) {
        super("Gifts' History", stage);
        this.gameId = gameId;
        this.otherPlayerUsername = otherPlayer;
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

        Label productLabel = new Label("Product", GamePictureManager.skin);
        productLabel.setFontScale(1.2f);
        productLabel.setColor(Color.BLACK);
        Label idLabel = new Label("ID", GamePictureManager.skin);
        idLabel.setFontScale(1.2f);
        idLabel.setColor(Color.BLACK);
        Label senderLabel = new Label("Sender", GamePictureManager.skin);
        senderLabel.setFontScale(1.2f);
        senderLabel.setColor(Color.BLACK);
        Label receiverLabel = new Label("Receiver", GamePictureManager.skin);
        receiverLabel.setFontScale(1.2f);
        receiverLabel.setColor(Color.BLACK);
        Label rateLabel = new Label("Rate", GamePictureManager.skin);
        rateLabel.setFontScale(1.2f);
        rateLabel.setColor(Color.BLACK);
        Label actionLabel = new Label("Action", GamePictureManager.skin);
        actionLabel.setFontScale(1.2f);
        actionLabel.setColor(Color.BLACK);

        headerTable.add(productLabel).width(120);
        headerTable.add(idLabel).width(60);
        headerTable.add(senderLabel).width(100);
        headerTable.add(receiverLabel).width(100);
        headerTable.add(rateLabel).width(60);
        headerTable.add(actionLabel).width(100);

        add(headerTable).row();
        add(scrollPane).width(600).height(400).row();

        updateAllGifts();

        pack();
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }

    public void updateAllGifts() {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id",gameId);
            body.put("event" , Event.GetBetweenPlayersGifts);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null && response.getType().equals(MessageType.GET_BETWEEN_PLAYERS_GIFT_INFO)) {
                Type giftListType = new TypeToken<ArrayList<BetweenPlayersGift>>() {}.getType();
                ArrayList<BetweenPlayersGift> receivedGifts = response.getFromBody("gifts", giftListType);
                Gdx.app.postRunnable(() -> {
                    allGifts.clear();
                    allGifts.addAll(receivedGifts);
                    fillGiftTable();
                });
            }
        }).start();

    }

    private void fillGiftTable() {
        giftTable.clear();
        int counter = 0;

        for (BetweenPlayersGift gift : allGifts) {
            boolean isInvolved =
                (gift.getReceiverUsername().equals(LoggedInUser.getUser().getUsername()) && gift.getSenderUsername().equals(otherPlayerUsername)) ||
                    (gift.getSenderUsername().equals(LoggedInUser.getUser().getUsername()) && gift.getReceiverUsername().equals(otherPlayerUsername));

            if (!isInvolved) {
                continue;
            }

            counter++;

            Label productLabel = new Label(gift.getProductName(), GamePictureManager.skin);
            productLabel.setFontScale(1.2f);
            productLabel.setColor(Color.BROWN);
            Label idLabel = new Label(String.valueOf(gift.getId()), GamePictureManager.skin);
            idLabel.setFontScale(1.2f);
            idLabel.setColor(Color.BROWN);
            Label senderLabel = new Label(gift.getSenderUsername(), GamePictureManager.skin);
            senderLabel.setFontScale(1.2f);
            senderLabel.setColor(Color.BROWN);
            Label receiverLabel = new Label(gift.getReceiverUsername(), GamePictureManager.skin);
            receiverLabel.setFontScale(1.2f);
            receiverLabel.setColor(Color.BROWN);
            Label rateLabel = new Label(String.valueOf(gift.getRate()), GamePictureManager.skin);
            rateLabel.setFontScale(1.2f);
            rateLabel.setColor(Color.BROWN);

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
            emptyLabel.setFontScale(1.2f);
            emptyLabel.setColor(Color.BLACK);
            emptyLabel.setWrap(true);
            emptyLabel.setAlignment(Align.center);

            giftTable.add(emptyLabel)
                .colspan(6)
                .width(600)
                .expandX()
                .padTop(20)
                .center();
            giftTable.row();

        }
    }

    private void openRateGiftWindow(BetweenPlayersGift gift) {
        stage.addActor(new RateGiftWindow(gameId,stage, friendshipWindow, this, gift.getId()));
    }
}
