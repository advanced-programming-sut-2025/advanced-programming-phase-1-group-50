package com.stardew.view.ArtisanMachine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.model.HabitatDTO;
import com.stardew.model.TextureID;
import com.stardew.models.GameAssetManagers.GameAssetIDManager;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.windows.HabitatWindow;

import java.util.ArrayList;

public class HabitatUI {
    private static final ArrayList<HabitatUI> habitatUIs = new ArrayList<>();
    private static Stage stage;
    private static int gameID;


    public static void addHabitatUI(HabitatUI habitatUI) {
        habitatUIs.add(habitatUI);
    }

    public static void initialize(Stage stage, int gameID) {
        HabitatUI.stage = stage;
        HabitatUI.gameID = gameID;
    }



    public HabitatUI(HabitatDTO habitat) {
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
