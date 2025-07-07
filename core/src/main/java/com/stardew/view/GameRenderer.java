package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.animals.GameModel;
import com.stardew.models.mapInfo.Pair;
import com.stardew.models.mapInfo.Tile;

public class GameRenderer {
    private final GameModel gameModel;
    private SpriteBatch batch;
    private int moveDirection;
    private float stateTime = 0f;

    public GameRenderer(GameModel gameModel) {
        this.gameModel = gameModel;
        batch = new SpriteBatch();
    }

    public void render(){
        batch.setProjectionMatrix(gameModel.getCamera().combined);
        batch.begin();
        renderMapTiles();
        renderPlayer();
        batch.end();
    }

    public void renderMapTiles(){
        Tile[][] tiles = gameModel.getMap().getTiles();
        float camX = gameModel.getCamera().position.x;
        float camY = gameModel.getCamera().position.y;
        float viewportWidth = gameModel.getCamera().viewportWidth;
        float viewportHeight = gameModel.getCamera().viewportHeight;

        int tileSize = GamePictureManager.TILE_SIZE;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        int startX = Math.max(0, (int) (cameraLeft / tileSize) - 2);
        int startY = Math.max(0, (int) (cameraBottom / tileSize) - 2);
        int endX = Math.min(tiles.length, (int) ((camX + viewportWidth / 2) / tileSize) + 2);
        int endY = Math.min(tiles[0].length, (int) ((camY + viewportHeight / 2) / tileSize) + 2);

        for(int x = startX; x < endX; x++){
            for(int y = startY; y < endY; y++){
                Tile tile = gameModel.getMap().findTile(x, y);
                if(tile != null){
                    float drawX = x * tileSize;
                    float drawY = y * tileSize;
                    TextureRegion  tex = tile.getTexture();
                    TextureRegion backTex = tile.getBackgroundTexture();
                    if(backTex != null){
                        batch.draw(backTex, drawX, drawY , tileSize, tileSize);
                    }
                    if(tex != null){
                        batch.draw(tex, drawX, drawY , tileSize, tileSize);
                    }
                }

            }
        }
    }

    public void renderPlayer(){

        Pair<Float , Float> pos = gameModel.getPlayerController().getPlayer().getPlayerPosition();
        moveDirection = gameModel.getPlayerController().getPlayer().getMoveDirection();

        stateTime += Gdx.graphics.getDeltaTime();

        Animation<TextureRegion> currentAnimation = GamePictureManager.playerAnimations.get(moveDirection);
        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

        batch.draw(currentFrame, pos.getFirst() * GamePictureManager.TILE_SIZE, pos.getSecond() * GamePictureManager.TILE_SIZE, GamePictureManager.TILE_SIZE, GamePictureManager.TILE_SIZE * 2);

    }

}
