package com.stardew.view.RefrigeratorView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.google.gson.reflect.TypeToken;
import com.stardew.model.InventoryItemDTO;
import com.stardew.model.Result;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.InventoryWindows.BackpackGridActor;
import com.stardew.view.windows.CloseableWindow;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class RefrigeratorWindow extends CloseableWindow {
    private final RefrigeratorGridActor refrigeratorGridActor;
    private final BackpackGridActor backpackGridActor;
    private final ScrollPane backpackScrollPane;
    private final TextButton deliverToBackpackButton;
    private final TextButton deliverToRefrigeratorButton;
    private final TextButton eatButton;
    private final int id;


    public RefrigeratorWindow(Stage stage,
                              int id,
                              ArrayList<InventoryItemDTO> inventoryItems,
                              ArrayList<InventoryItemDTO> refrigeratorItems) {
        super("Refrigerator ", stage);
        this.id = id;

        pad(25, 5, 20, 0);
        pack();
        setSize(900, 700);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);


        refrigeratorGridActor = new RefrigeratorGridActor(refrigeratorItems);
        backpackGridActor = new BackpackGridActor(inventoryItems);
        backpackScrollPane = new ScrollPane(backpackGridActor, GamePictureManager.skin);
        deliverToBackpackButton = new TextButton("DeliverToBackpack", GamePictureManager.skin);
        deliverToRefrigeratorButton = new TextButton("DeliverToRefrigerator", GamePictureManager.skin);
        eatButton = new TextButton("Eat Item", GamePictureManager.skin);

        add(refrigeratorGridActor).expand().fill().pad(60, 40, 10, 20);
        Table table = new Table();
        table.add(deliverToBackpackButton).row();
        table.add(eatButton);
        add(table).expand().fill().pad(60, 20, 10, 20);
        row();


        backpackScrollPane.setFadeScrollBars(false);
        backpackScrollPane.setScrollingDisabled(true, false);
        backpackScrollPane.setOverscroll(true, true);

        add(backpackScrollPane).height(180).width(505).expand().fill().pad(20, 40, 80, 20);
        backpackScrollPane.layout();
        backpackScrollPane.setScrollPercentY(1f);


        add(deliverToRefrigeratorButton).pad(20, 20, 80, 20);
        row();

        deliverToBackpackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                InventoryItemDTO itemDTO = refrigeratorGridActor.getSelectedItem();
                if (itemDTO == null) {
//                    showResult(new Result(false, "Please select an item!"));
                    return;
                }
                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("item", itemDTO);
                    body.put("id", id);
                    body.put("event", Event.PickFromRefrigerator);
                    Message message = new Message(body, MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                    if (response != null && response.getType() == MessageType.EVENT_IN_GAME_RESULT) {
                        Result result = response.getFromBody("result", Result.class);
//                        Gdx.app.postRunnable(() -> showResult(result));
                        updateRefrigeratorWithMessage();
                        updateBackpackWithMessage();
                    }
                }).start();

            }
        });

        deliverToRefrigeratorButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int sx = backpackGridActor.getSelectedX();
                int sy = backpackGridActor.getSelectedY();
                InventoryItemDTO itemDTO = backpackGridActor.getInventoryItemByXAndY(sx, sy);
                if (itemDTO == null) {
//                    showResult(new Result(false, "Please select an item!"));
                    return;
                }
                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("item", itemDTO);
                    body.put("id", id);
                    body.put("event", Event.PutInRefrigerator);
                    Message message = new Message(body, MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                    if (response != null && response.getType() == MessageType.EVENT_IN_GAME_RESULT) {
                        Result result = response.getFromBody("result", Result.class);
//                        Gdx.app.postRunnable(() -> showResult(result));
                        updateRefrigeratorWithMessage();
                        updateBackpackWithMessage();
                    }
                }).start();
            }
        });

        eatButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                InventoryItemDTO itemDTO = refrigeratorGridActor.getSelectedItem();
                if (itemDTO == null) {
//                    showResult(new Result(false, "Please select an item!"));
                    return;
                }
                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("item", itemDTO);
                    body.put("id", id);
                    body.put("event", Event.EatItem);
                    Message message = new Message(body, MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                    if (response != null && response.getType() == MessageType.EVENT_IN_GAME_RESULT) {
                        Result result = response.getFromBody("result", Result.class);
//                        Gdx.app.postRunnable(() -> showResult(result));
                        updateRefrigeratorWithMessage();
                    }
                }).start();
            }
        });

    }


    private void updateRefrigeratorWithMessage() {
        Type type = new TypeToken<ArrayList<InventoryItemDTO>>() {}.getType();
        HashMap<String, Object> body2 = new HashMap<>();
        body2.put("id", id);
        body2.put("event", Event.GetRefrigeratorItems);
        Message message2 = new Message(body2, MessageType.EVENT_IN_GAME);
        Message response2 = NetworkManager.getConnection().sendAndWaitForResponse(message2, 500);
        if (response2 != null && response2.getType() == MessageType.EVENT_IN_GAME_RESULT) {
            ArrayList<InventoryItemDTO> refrigeratorItems = response2.getFromBody("refrigerator", type);
            Gdx.app.postRunnable(() -> refrigeratorGridActor.update(refrigeratorItems));
        }
    }

    private void updateBackpackWithMessage() {
        Type type = new TypeToken<ArrayList<InventoryItemDTO>>() {}.getType();
        HashMap<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("event", Event.ShowInventory);
        Message message = new Message(body, MessageType.EVENT_IN_GAME);
        Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
        if (response != null && response.getType() == MessageType.SHOW_INVENTORY_RESULT) {
            ArrayList<InventoryItemDTO> inventoryItems = response.getFromBody("inventory", type);
            Gdx.app.postRunnable(() -> backpackGridActor.updateDTO(inventoryItems));
        }

    }
}
