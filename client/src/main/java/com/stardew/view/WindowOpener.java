package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.view.InPersonPlayersRelationsWindows.InPersonFriendshipWindow;

public class WindowOpener {
    private static WindowOpener instance;
    private final Stage stage;
    private final int gameId;

    private WindowOpener(Stage stage, int gameId) {
        this.stage = stage;
        this.gameId = gameId;
    }

    public static void initialize(Stage stage, int gameId) {
        instance = new WindowOpener(stage, gameId);
    }

    public static WindowOpener getInstance() {
        return instance;
    }

    public void openInPersonFriendshipMenu(String otherPlayer) {
        Gdx.app.postRunnable(() -> stage.addActor(new InPersonFriendshipWindow(gameId,stage,otherPlayer)));
    }
}
