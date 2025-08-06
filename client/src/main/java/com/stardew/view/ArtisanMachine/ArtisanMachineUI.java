package com.stardew.view.ArtisanMachine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.stardew.models.GameAssetManagers.ArtisanAsset;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.windows.ArtisanOptionWindow;
import com.stardew.view.windows.ArtisanWindow;

import java.util.HashMap;

public class ArtisanMachineUI {
    private final Image mainImage;
    private final ProgressBar processBar;
    private final Image endProcessImage;
    private final Label processLabel;
    private final String machineID;
    private final int gameID;



    public ArtisanMachineUI(Stage stage, ArtisanAsset artisanAsset, String machineID, int gameID, int x, int y) {
        this.machineID = machineID;
        this.gameID = gameID;

        mainImage = new Image(artisanAsset.getDrawable());
        endProcessImage = new Image(GamePictureManager.endProcessTexture);
        processBar = new ProgressBar(0, 10, 1, false, GamePictureManager.skin);
        processLabel = new Label("0 / ", GamePictureManager.skin);

        x *= GamePictureManager.TILE_SIZE;
        y *= GamePictureManager.TILE_SIZE;

        mainImage.setPosition(x, y);
        endProcessImage.setPosition(x + mainImage.getWidth() / 2 - endProcessImage.getWidth() / 2, y + mainImage.getHeight() + 10);
        endProcessImage.setVisible(false);
        endProcessImage.setSize(40, 40);
        processBar.setPosition(x + mainImage.getWidth() / 2 - processBar.getWidth() / 2, y + mainImage.getHeight() + 10);
        processBar.setVisible(false);
        processBar.setAnimateDuration(0.5f);
        processLabel.setPosition(processBar.getX() + 5, processBar.getY() + 5);
        processLabel.setVisible(false);

        stage.addActor(mainImage);
        stage.addActor(endProcessImage);
        stage.addActor(processBar);
        stage.addActor(processLabel);

        mainImage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (button == Input.Buttons.LEFT) {
                    stage.addActor(
                        new ArtisanWindow(stage, artisanAsset, machineID, gameID, x + mainImage.getX(), y + mainImage.getY())
                    );
                    return true;
                } else if (button == Input.Buttons.RIGHT) {
                    stage.addActor(
                        new ArtisanOptionWindow(stage, artisanAsset, machineID, gameID, x + mainImage.getX(), y + mainImage.getY())
                    );
                    return true;
                }
                return false;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }

        });

    }


    public void updateMachine() {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameID);
            body.put("machineID", machineID);
            body.put("event", Event.GetMachineDetails);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null && response.getType() == MessageType.EVENT_IN_GAME_RESULT) {
                Gdx.app.postRunnable(() -> {
                    Boolean isProducing = response.getFromBody("isProducing");
                    if (!isProducing) {
                        processBar.setVisible(false);
                        processLabel.setVisible(false);
                        endProcessImage.setVisible(false);
                        return;
                    }
                    int passedTime = response.getIntFromBody("passedTime");
                    int totalProcessingTime = response.getIntFromBody("totalProcessingTime");
                    if (passedTime >= totalProcessingTime) {
                        processBar.setVisible(false);
                        processLabel.setVisible(false);
                        endProcessImage.setVisible(true);
                    } else {
                        processBar.setVisible(true);
                        processLabel.setVisible(true);
                        endProcessImage.setVisible(false);
                        processBar.setValue(passedTime);
                        processBar.setRange(0, totalProcessingTime);
                        processLabel.setText(((int)processBar.getValue()) + " / " + ((int)processBar.getMaxValue()) + " Hours");
                    }
                });
            }
        }).start();
    }

    public String getMachineID() {
        return machineID;
    }
}
