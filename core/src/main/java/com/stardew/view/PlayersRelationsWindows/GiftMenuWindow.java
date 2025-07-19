package com.stardew.view.PlayersRelationsWindows;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.userInfo.Player;
import com.stardew.view.windows.CloseableWindow;

public class GiftMenuWindow extends CloseableWindow {
    public GiftMenuWindow(Stage stage, FriendshipWindow friendshipWindow,Player player) {
        super("Gift menu", stage);

        Table table = new Table();
        table.pad(20);
        table.defaults().width(200).height(50).space(20);

        TextButton sendGiftButton = new TextButton("Send Gift", GamePictureManager.skin);
        sendGiftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(new SelectGiftToSendWindow(stage, player));
                remove();
            }
        });

        TextButton giftHistoryButton = new TextButton("Gift History", GamePictureManager.skin);
        giftHistoryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(new GiftHistoryWindow(stage, friendshipWindow,player));
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
