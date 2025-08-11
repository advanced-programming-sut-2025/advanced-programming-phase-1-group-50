package com.stardew.view.NPCsWindows;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.model.NPC.NPCType;
import com.stardew.models.GameAssetManagers.GamePictureManager;

public class NPCsUIManger {

    public static void createAllNPCsUI(Stage stage, int gameId) {
        createNPCsUI(stage, gameId, 129, 76, 1, 2, NPCType.Abigail);
        createNPCsUI(stage, gameId, 129, 84, 1, 2, NPCType.Harvey);
        createNPCsUI(stage, gameId, 129, 92, 1, 2, NPCType.Robin);
        createNPCsUI(stage, gameId, 129, 100, 1, 2, NPCType.Leah);
        createNPCsUI(stage, gameId, 129, 108, 1, 2, NPCType.Sebastian);
    }

    public static void createNPCsUI(Stage stage, int gameId, int x, int y, int width, int height, NPCType npcType) {
        int xInMap = x * GamePictureManager.TILE_SIZE;
        int yInMap = y * GamePictureManager.TILE_SIZE;
        int widthInMap = width * GamePictureManager.TILE_SIZE;
        int heightInMap = height * GamePictureManager.TILE_SIZE;

        Group npcGroup = new Group();
        npcGroup.setPosition(xInMap, yInMap);

        Image npcImage;
        switch (npcType) {
            case Abigail:
                npcImage = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.AbigailFullBodyTexture)));
                break;
            case Harvey:
                npcImage = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.HarveyFullBodyTexture)));
                break;
            case Robin:
                npcImage = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.RobinFullBodyTexture)));
                break;
            case Leah:
                npcImage = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.LeahFullBodyTexture)));
                break;
            case Sebastian:
                npcImage = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.SebastianFullBodyTexture)));
                break;
            default:
                return;
        }
        npcImage.setSize(widthInMap, heightInMap);
        npcGroup.addActor(npcImage);

        Image dialogIcon = new Image(new TextureRegionDrawable(new TextureRegion(GamePictureManager.dialogueTexture)));
        dialogIcon.setSize(32, 32);
        dialogIcon.setPosition(-10, heightInMap);
        npcGroup.addActor(dialogIcon);

        dialogIcon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.addActor(new TalkWithNPCWindow(stage,npcType,gameId));
                return true;
            }
        });

        npcImage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.addActor(new NPCMenuWindow(gameId, stage, npcType));
                return true;
            }
        });

        stage.addActor(npcGroup);
    }
}
