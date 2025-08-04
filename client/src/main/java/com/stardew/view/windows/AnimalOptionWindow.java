package com.stardew.view.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.model.AnimalDTO;
import com.stardew.model.Result;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;

import java.util.HashMap;

public class AnimalOptionWindow extends CloseableWindow {
    private final TextButton feedButton;
    private final TextButton petButton;
    private final TextButton shepherdButton;
    private final TextButton sellButton;
    private final TextButton collectProductButton;


    public AnimalOptionWindow(Stage stage, int gameID, AnimalDTO animal, float x, float y) {
        super(" " + animal.getName(), stage);

        pad(30, 5, 20, 0);
        defaults().space(5);
        pack();
        setPosition(x, y);
        setSize(200, 385);


        feedButton = new TextButton("Feed", GamePictureManager.skin);
        petButton = new TextButton("Pet", GamePictureManager.skin);
        shepherdButton = new TextButton("Shepherd", GamePictureManager.skin);
        sellButton = new TextButton("Sell", GamePictureManager.skin);
        collectProductButton = new TextButton("Collect Product", GamePictureManager.skin);

        add(feedButton).row();
        add(petButton).row();
        add(shepherdButton).row();
        add(sellButton).row();
        add(collectProductButton).row();

        feedButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("id", gameID);
                    body.put("event", Event.FeedAnimal);
                    body.put("animalName", animal.getName());
                    Message message = new Message(body, MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                    if (response != null) {
                        Result result = response.getFromBody("result", Result.class);
//                        Gdx.app.postRunnable(() -> {
//                            if (result.getSuccessful())
//                                closeWindow();
//                            else
//                                showResult(result);
//                        });
                    }
                }).start();
            }
        });

        petButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("id", gameID);
                    body.put("event", Event.PetAnimal);
                    body.put("animalName", animal.getName());
                    Message message = new Message(body, MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                    if (response != null) {
                        Result result = response.getFromBody("result", Result.class);
//                        Gdx.app.postRunnable(() -> {
//                            if (result.getSuccessful())
//                                closeWindow();
//                            else
//                                showResult(result);
//                        });
                    }
                }).start();
            }
        });

        shepherdButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("id", gameID);
                    body.put("event", Event.ShepherdAnimal);
                    body.put("animalName", animal.getName());
                    Message message = new Message(body, MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                    if (response != null) {
                        Result result = response.getFromBody("result", Result.class);
//                        Gdx.app.postRunnable(() -> {
//                            if (result.getSuccessful())
//                                closeWindow();
//                            else
//                                showResult(result);
//                        });
                    }
                }).start();
            }
        });

        sellButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("id", gameID);
                    body.put("event", Event.SellAnimal);
                    body.put("animalName", animal.getName());
                    Message message = new Message(body, MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                    if (response != null) {
                        Result result = response.getFromBody("result", Result.class);
//                        Gdx.app.postRunnable(() -> {
//                            showResult(result);
//                            if (result.getSuccessful())
//                                closeWindow();
//                        });
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
                    body.put("event", Event.CollectAnimalProduct);
                    body.put("animalName", animal.getName());
                    Message message = new Message(body, MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                    if (response != null) {
                        Result result = response.getFromBody("result", Result.class);
//                        Gdx.app.postRunnable(() -> {
//                            showResult(result);
//                            if (result.getSuccessful())
//                                closeWindow();
//                        });
                    }
                }).start();
            }
        });
    }
}
