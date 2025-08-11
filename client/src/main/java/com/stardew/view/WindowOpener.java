package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.stardew.model.Notification.MarriageRequest;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.InPersonPlayersRelationsWindows.InPersonFriendshipWindow;
import com.stardew.view.InPersonPlayersRelationsWindows.RespondMarriageWindow;

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

    public void openRespondMarriageWindow(MarriageRequest marriageRequest) {
        Gdx.app.postRunnable(() -> stage.addActor(new RespondMarriageWindow(gameId,stage,marriageRequest)));
    }

    public void rejectAnimation() {
        Gdx.app.postRunnable(() -> {
            float centerX = stage.getCamera().position.x / 2f;
            float centerY = stage.getCamera().position.y / 2f;

            Image fullHeart = new Image(GamePictureManager.heartFullIcon);
            fullHeart.setSize(150, 150);
            fullHeart.setPosition(centerX - 75, centerY - 75);
            stage.addActor(fullHeart);

            fullHeart.addAction(Actions.sequence(
                Actions.delay(0.5f),
                Actions.run(() -> {
                    for (int i = 0; i < 8; i++) {
                        Image piece = new Image(GamePictureManager.heartBrokenIcon);
                        piece.setSize(130, 130);

                        float startX = centerX - 65;
                        float startY = centerY - 65;

                        float angle = (float) (Math.random() * 360);
                        float distance = 200 + (float)(Math.random() * 100);
                        float endX = startX + (float) (Math.cos(Math.toRadians(angle)) * distance);
                        float endY = startY + (float) (Math.sin(Math.toRadians(angle)) * distance);

                        piece.setPosition(startX, startY);
                        piece.setOrigin(Align.center);
                        stage.addActor(piece);

                        float rotation = (float) (Math.random() * 360 - 180);

                        piece.addAction(Actions.parallel(
                            Actions.moveTo(endX, endY, 2.0f),
                            Actions.rotateBy(rotation, 2.0f),
                            Actions.fadeOut(2.0f)
                        ));
                    }

                    fullHeart.addAction(Actions.sequence(
                        Actions.fadeOut(0.5f),
                        Actions.removeActor()
                    ));
                })
            ));
        });
    }

    public void acceptAnimation() {
        Gdx.app.postRunnable(() -> {
            Image heartLeft = new Image(GamePictureManager.heartLeftIcon);
            Image heartRight = new Image(GamePictureManager.heartRightIcon);
            heartLeft.setSize(150, 200);
            heartRight.setSize(150, 200);

            float centerX = stage.getCamera().position.x / 2f;
            float centerY = stage.getCamera().position.y / 2f;

            heartLeft.setPosition(-150, centerY - 100);
            heartRight.setPosition(stage.getCamera().position.x + 150, stage.getCamera().position.y - 100);

            stage.addActor(heartLeft);
            stage.addActor(heartRight);

            heartLeft.addAction(Actions.sequence(
                Actions.fadeIn(1.3f),
                Actions.moveTo(centerX - 150, centerY - 100, 1f),
                Actions.moveTo(centerX - 100, centerY - 100, 0.3f),
                Actions.fadeOut(0.2f)
            ));

            heartRight.addAction(Actions.sequence(
                Actions.fadeIn(1.3f),
                Actions.moveTo(centerX , centerY - 100, 1f),
                Actions.moveTo(centerX - 50, centerY - 100, 0.3f),
                Actions.fadeOut(0.2f),
                Actions.run(() -> {
                    Image fullHeart = new Image(GamePictureManager.heartFullIcon);
                    fullHeart.setSize(200, 200);
                    fullHeart.setPosition(centerX - 75, centerY - 100);
                    fullHeart.getColor().a = 0;
                    stage.addActor(fullHeart);

                    fullHeart.addAction(Actions.sequence(
                        Actions.fadeIn(0.5f),
                        Actions.delay(1.0f),
                        Actions.fadeOut(0.5f),
                        Actions.run(() -> {
                            heartLeft.remove();
                            heartRight.remove();
                        }),
                        Actions.removeActor()
                    ));
                })
            ));
        });
    }

    public void spawnHugEmojis() {

        Gdx.app.postRunnable(() -> {
            float x = stage.getCamera().position.x / 2f ;
            float y = stage.getCamera().position.y /2f + 20;

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
        });
    }

    public void spawnRoseEmojis() {
        Gdx.app.postRunnable(() -> {
            float x = stage.getCamera().position.x / 2f ;
            float y = stage.getCamera().position.y /2f + 20;

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
        });
    }

    public void spawnRingEmojis() {
        Gdx.app.postRunnable(() -> {
            float x = stage.getCamera().position.x / 2f ;
            float y = stage.getCamera().position.y /2f + 20;

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
        });
    }
}
