package com.stardew.view.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.model.AnimalDTO;
import com.stardew.model.HabitatDTO;
import com.stardew.model.Result;
import com.stardew.models.GameAssetManagers.GameAssetIDManager;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;

import java.util.ArrayList;
import java.util.HashMap;

public class HabitatWindow extends CloseableWindow {


    public HabitatWindow(Stage stage, int gameID, HabitatDTO habitat, float x, float y) {
        super(habitat.getSize() + " " + habitat.getType(), stage);

        pad(25, 5, 20, 0);
        defaults().space(5);
        pack();
        setPosition(x, y);

        ArrayList<AnimalDTO> animals = habitat.getAnimals();

        setSize(180, 120 + 65 * animals.size());

        TextButton showAnimalsProductsButton = new TextButton("Animals_Products", GamePictureManager.skin);
        showAnimalsProductsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("id", gameID);
                    body.put("event", Event.GetAnimalsProductsInfo);
                    body.put("habitatID", habitat.getId());
                    Message message = new Message(body, MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                    if (response != null) {
                        Result result = response.getFromBody("result", Result.class);
                        Gdx.app.postRunnable(() -> showResult(result));
                    }
                }).start();
            }
        });
        add(showAnimalsProductsButton).row();

        for (AnimalDTO animal : animals) {
            Image animalImage = new Image(GameAssetIDManager.getTextureRegion(animal.getNormalTexture()));

            SmartTooltip tooltip = SmartTooltip.getInstance();

            animalImage.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    stage.addActor(new AnimalOptionWindow(stage, gameID, animal, x + getX(), y + getY()));
                    return true;
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    tooltip.show(animal.getDescription());
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    tooltip.hide();
                }
            });
            add(animalImage).row();
        }
    }
}
