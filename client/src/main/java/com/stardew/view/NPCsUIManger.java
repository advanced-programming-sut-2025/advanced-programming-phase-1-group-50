package com.stardew.view;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.NPCs.NPCType;
import com.stardew.view.NPCsWindows.NPCMenuWindow;

public class NPCsUIManger {
    public static void createAllNPCsUI(Stage stage, int gameId) {
        createNPCsUI(stage, gameId, 129, 76, 1, 2, NPCType.Abigail);
        createNPCsUI(stage, gameId, 129, 84, 1, 2, NPCType.Harvey);
        createNPCsUI(stage, gameId, 129, 92, 1, 2, NPCType.Robin);
        createNPCsUI(stage, gameId, 129, 100, 1, 2, NPCType.Leah);
        createNPCsUI(stage, gameId, 129, 108, 1, 2, NPCType.Sebastian);
    }

    public static void createNPCsUI(Stage stage, int gameId, int x, int y, int width, int height, NPCType npcType) {
        int xInMaP = x * GamePictureManager.TILE_SIZE;
        int yInMaP = y * GamePictureManager.TILE_SIZE;
        int widthInMap = width * GamePictureManager.TILE_SIZE;
        int heightInMap = height * GamePictureManager.TILE_SIZE;

        Image image;

        switch (npcType) {
            case NPCType.Abigail:
                image = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.AbigailFullBodyTexture)));
                break;
            case NPCType.Harvey:
                image = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.HarveyFullBodyTexture)));
                break;
            case NPCType.Robin:
                image = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.RobinFullBodyTexture)));
                break;
            case NPCType.Leah:
                image = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.LeahFullBodyTexture)));
                break;
            case NPCType.Sebastian:
                image = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.SebastianFullBodyTexture)));
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
                stage.addActor(new NPCMenuWindow(gameId,stage,npcType));
                return true;
            }
        });
    }
}
