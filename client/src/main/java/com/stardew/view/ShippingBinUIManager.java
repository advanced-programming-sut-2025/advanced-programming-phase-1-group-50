package com.stardew.view;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.SellProductWindow.ShippingBinWindow;

public class ShippingBinUIManager {

    private static ShippingBinUIManager instance;
    private final Stage stage;
    private final int gameId;

    private ShippingBinUIManager(Stage stage, int gameId) {
        this.stage = stage;
        this.gameId = gameId;
    }

    public static void initialize(Stage stage, int gameId) {
        instance = new ShippingBinUIManager(stage, gameId);
    }

    public static ShippingBinUIManager getInstance() {
        return instance;
    }


    public void createShippingBinUI(int shippingBinId,int x, int y) {
        int xInMap = x * GamePictureManager.TILE_SIZE;
        int yInMap = y * GamePictureManager.TILE_SIZE;
        Image image = new Image(new TextureRegionDrawable(GamePictureManager.shippingBinTexture));
        image.setSize(GamePictureManager.TILE_SIZE, GamePictureManager.TILE_SIZE);
        image.setPosition(xInMap, yInMap);
        stage.addActor(image);

        image.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.addActor(new ShippingBinWindow(gameId,shippingBinId,stage));
                return true;
            }

        });

    }
}
