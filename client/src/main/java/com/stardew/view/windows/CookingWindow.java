package com.stardew.view.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.stardew.controller.CookingAndCraftingControllers.CookingController;
import com.stardew.model.Result;
import com.stardew.models.GameAssetManagers.CookingAsset;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CookingWindow extends CloseableWindow {
    private final HashMap<CookingAsset, ImageButton> buttons = new HashMap<>();
    private final CookingController controller = new CookingController();
    private final int id;

    public CookingWindow(int id, Stage stage, Map<String, String> descriptions, Set<String> ownRecipes) {
        super("Cooking Menu", stage);
        this.id = id;

        createUI(descriptions, ownRecipes);

        pad(150);
        defaults().space(20);
        int counter = 0;
        for (CookingAsset asset : buttons.keySet()) {
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

        for (CookingAsset cookingAsset : CookingAsset.values()) {
            ImageButton imageButton = new ImageButton(cookingAsset.getStyle());
            imageButton.setDisabled(!ownRecipes.contains(cookingAsset.name()));
            buttons.put(cookingAsset, imageButton);

            SmartTooltip tooltip = SmartTooltip.getInstance();

            imageButton.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    new Thread(() -> {
                        HashMap<String, Object> body = new HashMap<>();
                        body.put("id", id);
                        body.put("event", Event.CookingFood);
                        body.put("name", cookingAsset.name());
                        Message message = new Message(body, MessageType.EVENT_IN_GAME);
                        Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                        if (response != null && response.getType() == MessageType.EVENT_IN_GAME_RESULT) {
                            Result result = response.getFromBody("result", Result.class);
//                            Gdx.app.postRunnable(() -> showResult(result));
                        }
                    }).start();
                    return true;
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    tooltip.show(descriptions.get(cookingAsset.name()));
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    tooltip.hide();
                }
            });
        }
    }

}
