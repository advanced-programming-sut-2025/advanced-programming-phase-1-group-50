package com.stardew.view.PlayersRelationsWindows;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.models.userInfo.Player;
import com.stardew.models.userInfo.RelationWithPlayers;
import com.stardew.view.windows.CloseableWindow;

public class GiftMenuWindow extends CloseableWindow {
    public GiftMenuWindow(Stage stage, Player player, RelationWithPlayers relation) {
        super("Gift menu", stage);
    }
}
