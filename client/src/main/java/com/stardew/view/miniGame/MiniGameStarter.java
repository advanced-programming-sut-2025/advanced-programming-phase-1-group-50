package com.stardew.view.miniGame;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class MiniGameStarter {
    private static MiniGameStarter instance;
    private final Stage stage;

    private MiniGameStarter(Stage stage) { this.stage = stage; }

    public static void initialize(Stage stage) {
        instance = new MiniGameStarter(stage);
    }

    public static MiniGameStarter getInstance() { return instance; }


    public void createMiniGameWindow(int miniGameID) {
        stage.addActor(new MiniGameWindow(stage, miniGameID));
    }
}
