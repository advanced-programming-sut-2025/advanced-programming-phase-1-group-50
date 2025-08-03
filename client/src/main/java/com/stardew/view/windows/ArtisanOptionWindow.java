package com.stardew.view.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.models.GameAssetManagers.ArtisanAsset;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.model.Result;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.ArtisanMachine.ArtisanMachinesManager;

import java.util.HashMap;

public class ArtisanOptionWindow extends CloseableWindow {
    private final TextButton cancelProcessButton;
    private final TextButton cheatFinishProcessButton;
    private final TextButton showInfoButton;
    private final TextButton collectProductButton;


    public ArtisanOptionWindow(Stage stage, ArtisanAsset artisanAsset, String machineID, int gameID, float x, float y) {
        super("options", stage);

        pad(30, 5, 20, 0);
        defaults().space(20);
        pack();
        setPosition(x, y);
        setSize(250, 400);

        showInfoButton = new TextButton("Show Information", GamePictureManager.skin);
        cheatFinishProcessButton = new TextButton("Finish_Process(cheat)", GamePictureManager.skin);
        cancelProcessButton = new TextButton("Cancel Process", GamePictureManager.skin);
        collectProductButton = new TextButton("Collect Product", GamePictureManager.skin);

        add(showInfoButton).row();
        add(cheatFinishProcessButton).row();
        add(cancelProcessButton).row();
        add(collectProductButton).row();

        collectProductButton.setVisible(false);
        visibleCollectButtonIfNeeded(machineID, gameID);

        SmartTooltip tooltip = SmartTooltip.getInstance();

        showInfoButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                tooltip.show(artisanAsset.getDescription());
                return true;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (toActor == null || !toActor.isDescendantOf(event.getListenerActor()))
                    tooltip.hide();
            }
        });

        cheatFinishProcessButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                HashMap<String, Object> body = new HashMap<>();
                body.put("id", gameID);
                body.put("machineID", machineID);
                body.put("event", Event.CheatFinishMachineProcess);
                Message message = new Message(body, MessageType.EVENT_IN_GAME);
                NetworkManager.getConnection().sendMessage(message);
                ArtisanMachinesManager.getInstance().updateMachine(machineID);
            }
        });
        cancelProcessButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("id", gameID);
                    body.put("machineID", machineID);
                    body.put("event", Event.CancelMachineProcess);
                    Message message = new Message(body, MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                    if (response != null) {
                        Result result = response.getFromBody("result", Result.class);
//                        Gdx.app.postRunnable(() -> showResult(result));
                        if (result.getSuccessful()) {
                            ArtisanMachinesManager.getInstance().updateMachine(machineID);
                        }
                    }
                }).start();
            }
        });
        collectProductButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("id", gameID);
                    body.put("machineID", machineID);
                    body.put("event", Event.CollectMachineProduct);
                    Message message = new Message(body, MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                    if (response != null) {
                        Result result = response.getFromBody("result", Result.class);
//                        Gdx.app.postRunnable(() -> showResult(result));
                        if (result.getSuccessful()) {
                            ArtisanMachinesManager.getInstance().updateMachine(machineID);
                        }
                    }
                }).start();
            }
        });
    }



    private void visibleCollectButtonIfNeeded(String machineID, int gameID) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameID);
            body.put("machineID", machineID);
            body.put("event", Event.IsReadyProduct);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null) {
                Result result = response.getFromBody("result", Result.class);
                Gdx.app.postRunnable(() -> collectProductButton.setVisible(result.getSuccessful()));
            }

        }).start();
    }
}
