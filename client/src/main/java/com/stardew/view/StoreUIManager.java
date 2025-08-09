package com.stardew.view;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.controller.StoreController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.StoreWindows.StoreClosedMessageWindow;
import com.stardew.view.StoreWindows.StoreWindow;


public class StoreUIManager {

    public static void createAllStoresUI(Stage stage,int gameId) {
        createStoreUI(stage,gameId,102,77, 6, 4,"Clint");
        createStoreUI(stage,gameId,101,83, 16, 12,"Robin");
        createStoreUI(stage,gameId,110,97, 4, 4,"Willy");
        createStoreUI(stage,gameId,138,89, 4, 4,"Morris");
        createStoreUI(stage,gameId,118,116, 18, 8,"Marnie");
        createStoreUI(stage,gameId,138,97, 4, 4,"Pierre");
        createStoreUI(stage,gameId,138,110, 4, 4,"Gus");
    }

    private static void createStoreUI(Stage stage, int gameId,int x ,int y, int w, int h, String assistantName) {

        int xInMaP = x * GamePictureManager.TILE_SIZE;
        int yInMaP = y * GamePictureManager.TILE_SIZE;
        int widthInMap = w * GamePictureManager.TILE_SIZE;
        int heightInMap = h * GamePictureManager.TILE_SIZE;
        Image image;

        switch (assistantName) {
            case "Clint":
                image = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.blacksmithTexture)));
                break;
            case "Robin":
                image = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.carpenterShopTexture)));
                break;
            case "Willy":
                image = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.fishShopTexture)));
                break;
            case "Morris":
                image = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.jojaMartTexture)));
                break;
            case "Marnie":
                image = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.marnieRanchTexture)));
                break;
            case "Pierre":
                image = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.pierresShopTexture)));
                break;
            case "Gus":
                image = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.stardopSaloonTexture)));
                break;
            default:
                return;
        }

        image.setPosition(xInMaP, yInMaP);
        image.setSize(widthInMap, heightInMap);
        stage.addActor(image);

        image.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                StoreController.isStoreOpen(gameId,assistantName,result -> {
                    if (result.getSuccessful()) {
                        stage.addActor(new StoreWindow(gameId,stage,assistantName));
                    } else {
                        stage.addActor(new StoreClosedMessageWindow(stage));
                    }
                });
                return true;
            }
        });

    }
}
