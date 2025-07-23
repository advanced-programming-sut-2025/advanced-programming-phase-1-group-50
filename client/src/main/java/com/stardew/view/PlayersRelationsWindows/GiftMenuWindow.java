package com.stardew.view.PlayersRelationsWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.userInfo.Player;
import com.stardew.models.userInfo.RelationWithPlayers;
import com.stardew.view.windows.CloseableWindow;
import com.stardew.view.windows.SmartTooltip;

public class GiftMenuWindow extends CloseableWindow {
    public GiftMenuWindow(Stage stage, FriendshipWindow friendshipWindow, Player player, RelationWithPlayers relation) {
        super("Gift menu", stage);

        Table table = new Table();
        table.pad(20);
        table.defaults().width(200).height(50).space(20);

        TextButton sendGiftButton = new TextButton("Send Gift", GamePictureManager.skin);

        if (!relation.canGift()) {
            sendGiftButton.setDisabled(true);
            sendGiftButton.setColor(Color.DARK_GRAY);

            SmartTooltip tooltip = SmartTooltip.getInstance();
            sendGiftButton.addListener(new InputListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    tooltip.show("      Friendship level too low to gift      ");
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    tooltip.hide();
                }
            });
        } else {
            sendGiftButton.setColor(Color.ROYAL);
        }

        sendGiftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (relation.canGift()) {
                    stage.addActor(new SelectGiftToSendWindow(stage, player));
                    remove();
                }
            }
        });

        TextButton giftHistoryButton = new TextButton("Gift History", GamePictureManager.skin);
        giftHistoryButton.setColor(Color.GOLDENROD);

        giftHistoryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(new GiftHistoryWindow(stage, friendshipWindow, player));
                remove();
            }
        });

        table.add(sendGiftButton).row();
        table.add(giftHistoryButton).row();

        add(table);
        pack();

        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }
}
