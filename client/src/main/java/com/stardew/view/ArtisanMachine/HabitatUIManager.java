package com.stardew.view.ArtisanMachine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.google.gson.reflect.TypeToken;
import com.stardew.model.AnimalDTO;
import com.stardew.model.HabitatDTO;
import com.stardew.models.GameAssetManagers.GameAssetIDManager;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.windows.HabitatWindow;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class HabitatUIManager {
    private static HabitatUIManager instance;
    private final Stage stage;
    private final int gameID;


    private HabitatUIManager(Stage stage, int gameID) {
        this.stage = stage;
        this.gameID = gameID;
    }

    public static void initialize(Stage stage, int gameID) {
        instance = new HabitatUIManager(stage, gameID);
    }

    public static HabitatUIManager getInstance() {
        return instance;
    }



    public void createHabitatUI(HabitatDTO habitat) {
        int x = habitat.getX() * GamePictureManager.TILE_SIZE;
        int y = habitat.getY() * GamePictureManager.TILE_SIZE;
        Image image = new Image(GameAssetIDManager.getTextureRegion(habitat.getTextureID()));
        image.setPosition(x, y);
        stage.addActor(image);


        image.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("id", gameID);
                    body.put("habitatID", habitat.getId());
                    body.put("event", Event.GetAnimalsInfoInHabitat);
                    Message message = new Message(body, MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                    if (response != null) {
                        Type arrayType = new TypeToken<ArrayList<AnimalDTO>>() {}.getType();
                        ArrayList<AnimalDTO> animals = response.getFromBody("animals", arrayType);
                        Gdx.app.postRunnable(() ->
                            stage.addActor(new HabitatWindow(
                                stage,
                                gameID,
                                habitat,
                                animals,
                                (x + image.getX()),
                                y + image.getY() - 50)
                            )
                        );
                    }
                }).start();
                return true;
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
}
