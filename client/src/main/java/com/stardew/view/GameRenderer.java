package com.stardew.view;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


import com.stardew.model.AnimalDTO;
import com.stardew.model.PlaceableDTO;
import com.stardew.model.PlayerDTO;
import com.stardew.model.TileDTO;
import com.stardew.models.GameAssetManagers.GameAssetIDManager;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.GameModel;


public class GameRenderer {
    private final GameModel gameModel;
    private final SpriteBatch batch;
    private final int tileSize;
    private float stateTime = 0f;


    public GameRenderer(SpriteBatch batch, GameModel gameModel) {
        this.batch = batch;
        this.gameModel = gameModel;
        this.tileSize = GamePictureManager.TILE_SIZE;
    }

    public void render(float delta) {
        renderBackground();
        renderPlayer(delta);
        renderPlaceables();
        renderAnimals();
    }


    private void renderBackground() {
        for (TileDTO tile : gameModel.getTiles()) {
            batch.draw(
                GameAssetIDManager.getTextureRegion(tile.getBackgroundTextureID()),
                tile.getX() * tileSize, tile.getY() * tileSize, tileSize, tileSize
            );
        }
    }

    private void renderPlayer(float delta) {
        stateTime += delta;
        for (PlayerDTO player : gameModel.getAllPlayers()) {
            int moveDirection = player.getDirection();
            Animation<TextureRegion> currentAnimation = GamePictureManager.playerAnimations.get(moveDirection);
            if(player.getEnergy() <= 0){
                currentAnimation = GamePictureManager.faintAnimation;
            }

            TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

            batch.draw(currentFrame,
                player.getX() * tileSize,
                player.getY() * tileSize,
                currentFrame.getRegionWidth() * 3,
                currentFrame.getRegionHeight() * 3
            );

        }

    }

    private void renderPlaceables() {
        for (PlaceableDTO placeable : gameModel.getPlaceables()) {
            batch.draw(
                GameAssetIDManager.getTextureRegion(placeable.getTextureID()),
                placeable.getX() * tileSize, placeable.getY() * tileSize ,
                placeable.getWidth() * tileSize , placeable.getHeight()  * tileSize
            );
        }
    }

    private void renderAnimals() {
        for (AnimalDTO animal : gameModel.getAnimals()) {
            Animation<TextureRegion> animation = GameAssetIDManager.getAnimation(animal.getAnimationID());
            if (animation == null) continue;
            float stateTime = animal.getStateTime();
            batch.draw(
                animation.getKeyFrame(stateTime, true),
                animal.getX() * tileSize,
                animal.getY() * tileSize,
                tileSize,
                tileSize
            );
        }
    }

}
