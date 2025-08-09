package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;

import java.util.HashMap;

public class GreenhouseUIManager {
    private static Actor image;

    public static void initialize(Stage stage , int gameId) {
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            Message message = new Message(body , MessageType.GREENHOUSE_POSITION_REQUEST);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message , 500);
            if(response != null && response.getType() == MessageType.GREENHOUSE_POSITION_RESULT) {
                int x = response.getIntFromBody("x");
                int y = response.getIntFromBody("y");
                int width = response.getIntFromBody("width");
                int height = response.getIntFromBody("height");

                int xInMaP = x * GamePictureManager.TILE_SIZE;
                int yInMaP = y * GamePictureManager.TILE_SIZE;
                int widthInMap = width * GamePictureManager.TILE_SIZE;
                int heightInMap = height * GamePictureManager.TILE_SIZE;
                Gdx.app.postRunnable(() -> {
                    image = new Actor();
                    image.setBounds(xInMaP, yInMaP, widthInMap, heightInMap);
                    stage.addActor(image);
                    image.addListener(new InputListener(){
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                            stage.addActor(new BuildGreenHouseWindow(stage , gameId ));
                            return true;
                        }
                    });
                });

            }
        }).start();


    }

    public static void buildGreenhouse(){
//        image.setDrawable(new TextureRegionDrawable(GamePictureManager.progressiveGreenhouse));
    }




}
