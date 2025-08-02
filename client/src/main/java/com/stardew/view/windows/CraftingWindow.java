package com.stardew.view.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.google.gson.reflect.TypeToken;
import com.stardew.controller.CookingAndCraftingControllers.CraftingController;
import com.stardew.model.PlaceableDTO;
import com.stardew.model.Result;
import com.stardew.model.TileDTO;
import com.stardew.models.GameAssetManagers.CraftingAsset;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.GridMap.TileSelectionWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CraftingWindow extends CloseableWindow {
    private final HashMap<CraftingAsset, ImageButton> buttons = new HashMap<>();
    private final CraftingController controller = new CraftingController();
    private final int id;

    public CraftingWindow(int id, Stage stage, Map<String, String> descriptions, Set<String> ownRecipes) {
        super("Crafting Menu", stage);
        this.id = id;

        createUI(descriptions, ownRecipes);

        pad(150);
        defaults().space(20);
        int counter = 0;
        for (CraftingAsset asset : buttons.keySet()) {
            add(buttons.get(asset));
            counter++;
            if (counter % 7 == 0)
                row();
        }

        pack();
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);
    }


    private void createUI(Map<String, String> descriptions, Set<String> ownRecipes) {

        for (CraftingAsset craftingAsset : CraftingAsset.values()) {
            ImageButton imageButton = new ImageButton(craftingAsset.getStyle());
            buttons.put(craftingAsset, imageButton);

            imageButton.setDisabled(!ownRecipes.contains(craftingAsset.name()));

            SmartTooltip tooltip = SmartTooltip.getInstance();
            imageButton.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (imageButton.isDisabled()) {
//                        showResult(new Result(false, "You don't have Recipe for this"));
                        return true;
                    }

                    new Thread(() -> {
                        HashMap<String, Object> body = new HashMap<>();
                        body.put("id", id);
                        body.put("event", Event.GetMyFarmInfo);
                        Message infoMessage = new Message(body, MessageType.EVENT_IN_GAME);
                        Message response = NetworkManager.getConnection().sendAndWaitForResponse(infoMessage, 500);
                        if (response != null && response.getType() == MessageType.EVENT_IN_GAME_RESULT) {
                            ArrayList<TileDTO> tileDTOS = response.getFromBody(
                                "tiles", new TypeToken<ArrayList<TileDTO>>(){}.getType());
                            ArrayList<PlaceableDTO> placeableDTOS = response.getFromBody(
                                "placeables", new TypeToken<ArrayList<PlaceableDTO>>(){}.getType());
                            TileSelectionWindow tileSelectionWindow =
                                new TileSelectionWindow(stage, 1, 1, tileDTOS, placeableDTOS);
                            Gdx.app.postRunnable(() -> stage.addActor(tileSelectionWindow));

                            tileSelectionWindow.setOnOKCallback((selectedX, selectedY) -> {
                                new Thread(() -> {
                                    HashMap<String, Object> craftBody = new HashMap<>();
                                    craftBody.put("id", id);
                                    craftBody.put("event", Event.CraftingMachine);
                                    craftBody.put("name", craftingAsset.name());
                                    craftBody.put("x", selectedX);
                                    craftBody.put("y", selectedY);
                                    Message craftMessage = new Message(craftBody, MessageType.EVENT_IN_GAME);
                                    Message craftResponse = NetworkManager.getConnection().sendAndWaitForResponse(craftMessage, 500);
                                    if (craftResponse != null && craftResponse.getType() == MessageType.EVENT_IN_GAME_RESULT) {
                                        Result result = craftResponse.getFromBody("result", Result.class);
//                                        Gdx.app.postRunnable(() -> showResult(result));
                                    }
                                }).start();
                            });

                        }
                    }).start();
                    return true;
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    tooltip.show(descriptions.get(craftingAsset.name()));
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    tooltip.hide();
                }
            });
        }
    }
}
