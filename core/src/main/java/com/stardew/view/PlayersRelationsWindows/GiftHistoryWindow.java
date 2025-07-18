package com.stardew.view.PlayersRelationsWindows;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.models.userInfo.Player;
import com.stardew.view.windows.CloseableWindow;

public class GiftHistoryWindow extends CloseableWindow {
    public GiftHistoryWindow(Stage stage, Player player) {
        super("Gifts' history", stage);
    }
}
