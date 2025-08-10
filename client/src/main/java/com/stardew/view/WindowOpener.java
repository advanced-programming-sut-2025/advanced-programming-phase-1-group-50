package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.models.GameAssetManagers.GamePictureManager;
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

    public void spawnHugEmojis() {

        float x = stage.getCamera().position.x / 2 ;
        float y = stage.getCamera().position.y /2 + 20;

        Texture emojiTexture = GamePictureManager.hugIcon;

        for (int i = 0; i < 10; i++) {
            int direction = (i % 2 == 0) ? 1 : -1;
            final Image emoji = new Image(emojiTexture);
            emoji.setSize(64, 64);
            emoji.setPosition(
                x + (float) (Math.random() * 800 * direction),
                y
            );
            stage.addActor(emoji);

            emoji.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.moveBy(0, 100 + (float)Math.random() * 500, 2f),
                    Actions.fadeOut(2f)
                ),
                Actions.removeActor()
            ));
        }
    }

    public void spawnRoseEmojis() {

        float x = stage.getCamera().position.x / 2 ;
        float y = stage.getCamera().position.y /2 + 20;

        Texture emojiTexture = GamePictureManager.roseIcon;

        for (int i = 0; i < 10; i++) {
            int direction = (i % 2 == 0) ? 1 : -1;
            final Image emoji = new Image(emojiTexture);
            emoji.setSize(64, 64);
            emoji.setPosition(
                x + (float) (Math.random() * 800 * direction),
                y
            );
            stage.addActor(emoji);

            emoji.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.moveBy(0, 100 + (float)Math.random() * 500, 2f),
                    Actions.fadeOut(2f)
                ),
                Actions.removeActor()
            ));
        }
    }

    public void spawnRingEmojis() {

        float x = stage.getCamera().position.x / 2 ;
        float y = stage.getCamera().position.y /2 + 20;

        Texture emojiTexture = GamePictureManager.ringIcon;

        for (int i = 0; i < 10; i++) {
            int direction = (i % 2 == 0) ? 1 : -1;
            final Image emoji = new Image(emojiTexture);
            emoji.setSize(64, 64);
            emoji.setPosition(
                x + (float) (Math.random() * 800 * direction),
                y
            );
            stage.addActor(emoji);

            emoji.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.moveBy(0, 100 + (float)Math.random() * 500, 2f),
                    Actions.fadeOut(2f)
                ),
                Actions.removeActor()
            ));
        }
    }
}
