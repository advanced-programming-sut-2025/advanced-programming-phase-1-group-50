package com.stardew.view.ArtisanMachine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.model.HabitatDTO;
import com.stardew.models.GameAssetManagers.GameAssetIDManager;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.windows.HabitatWindow;

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
                stage.addActor(new HabitatWindow(stage, gameID, habitat, (x + image.getX()), y + image.getY() - 50));
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
