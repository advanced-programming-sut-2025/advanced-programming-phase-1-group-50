package com.stardew.view.PlayersRelationsWindows;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.models.userInfo.Player;
import com.stardew.models.userInfo.RelationWithPlayers;
import com.stardew.view.windows.CloseableWindow;


public class SendGiftWindow extends CloseableWindow {
    public SendGiftWindow(Stage stage, Player player, RelationWithPlayers relation) {
        super("Sending a gift", stage);
    }
}
