package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.stardew.model.Result;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.windows.CloseableWindow;

import java.util.HashMap;

public class BuildGreenHouseWindow extends CloseableWindow implements AppMenu {
    public BuildGreenHouseWindow(Stage stage , int id) {
        super("Build Greenhouse", stage);

        setSize(400, 200);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
        pad(20);
        setColor(Color.GREEN);
        align(Align.top);


        Label askLabel = new Label("Do you want to build the greenhouse?", GamePictureManager.skin);
        askLabel.setAlignment(Align.center);

        // دکمه‌ها
        TextButton yesButton = new TextButton("Yes", GamePictureManager.skin);
        yesButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("id", id);
                    body.put("event" , Event.BuildGreenhouse);
                    Message msg = new Message(body , MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(msg , 500);
                    if(response != null && response.getType() == MessageType.GREENHOUSE_BUILDING_RESULT) {
                        Result result = response.getFromBody("result", Result.class);

                        showResult(result);
                    }
                }).start();
                return true;
            }
        });

        TextButton noButton = new TextButton("No", GamePictureManager.skin);
        noButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                closeWindow();
                return true;
            }
        });


        Table buttonTable = new Table();
        buttonTable.defaults().pad(10).width(100).height(40);
        buttonTable.add(yesButton);
        buttonTable.add(noButton);


        defaults().expandX().center();
        row();
        add(askLabel).center().padBottom(20);
        row();
        add(buttonTable).center();
    }
}

