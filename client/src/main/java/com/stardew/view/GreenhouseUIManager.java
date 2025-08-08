package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.models.ClientInfo.LoggedInUser;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;

import java.util.HashMap;

public class GreenhouseUIManager {
    private static Image image;

    public static void initialize(Stage stage , int gameId) {

        HashMap<String, Object> body = new HashMap<>();
        body.put("id", gameId);
        Message message = new Message(body , MessageType.GREENHOUSE_POSITION_REQUEST);
        Message response = NetworkManager.getConnection().sendAndWaitForResponse(message , 500);
        if(response != null && response.getType() == MessageType.GREENHOUSE_POSITION_RESULT) {
            int x = response.getIntFromBody("x");
            int y = response.getIntFromBody("y");
            System.out.println("Greenhouse position result: " + x + ", " + y);
            int xInMaP = x * GamePictureManager.TILE_SIZE;
            int yInMaP = y * GamePictureManager.TILE_SIZE;
//            Gdx.app.postRunnable(() -> {
                image = new Image(new TextureRegionDrawable(GamePictureManager.initialGreenhouse));
                image.setPosition(xInMaP, yInMaP);
                stage.addActor(image);
                image.addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                        stage.addActor(new BuildGreenHouseWindow(stage , gameId ));
                        return true;
                    }
                });
//            });


        }


    }

    public static void buildGreenhouse(){
        image.setDrawable(new TextureRegionDrawable(GamePictureManager.progressiveGreenhouse));
    }




}
