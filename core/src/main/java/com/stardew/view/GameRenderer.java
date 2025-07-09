package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.animals.GameModel;
import com.stardew.models.app.App;
import com.stardew.models.foraging.Crop;
import com.stardew.models.foraging.Tree;
import com.stardew.models.mapInfo.GreenHouse;
import com.stardew.models.mapInfo.Pair;
import com.stardew.models.mapInfo.Stone;
import com.stardew.models.mapInfo.Tile;
import com.stardew.models.userInfo.Player;

import java.awt.*;

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

        renderMapTilesAndPlayer();

        //System.out.println(App.getGame().getCurrentPlayingPlayer().getPlayerPosition().getFirst() + " " + App.getGame().getCurrentPlayingPlayer().getPlayerPosition().getSecond());
        batch.end();
    }

    public void renderMapTilesAndPlayer() {
        Tile[][] tiles = gameModel.getMap().getTiles();
        float camX = gameModel.getCamera().position.x;
        float camY = gameModel.getCamera().position.y;
        float viewportWidth = gameModel.getCamera().viewportWidth;
        float viewportHeight = gameModel.getCamera().viewportHeight;

        int tileSize = GamePictureManager.TILE_SIZE;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        int startX = Math.max(0, (int) (cameraLeft / tileSize) - 3);
        int startY = Math.max(0, (int) (cameraBottom / tileSize) - 3);
        int endX = Math.min(tiles.length, (int) ((camX + viewportWidth / 2) / tileSize) + 4);
        int endY = Math.min(tiles[0].length, (int) ((camY + viewportHeight / 2) / tileSize) + 4);

        renderBackground(startX, startY, endX, endY, tileSize);

        renderPlayer();

        for(int x = startX; x < endX; x++){
            for(int y = startY; y < endY; y++){
                Tile tile = gameModel.getMap().findTile(x, y);
                if(tile != null){
                    float drawX = x * tileSize;
                    float drawY = y * tileSize;
                    TextureRegion  tex = tile.getTexture();

                    if (tex != null) {
                        if (tile.getPlaceable() instanceof Tree tree) {
//                            float adjustedX = drawX - (GamePictureManager.Tree_SIze_Width - tileSize) / 2f;
//                            float adjustedY = drawY - (GamePictureManager.Tree_SIze_Height - tileSize);
//                            batch.draw(tex, adjustedX, adjustedY, GamePictureManager.Tree_SIze_Width, GamePictureManager.Tree_SIze_Height);
                            tree.render(batch);

                        }
                        else if(tile.getPlaceable() instanceof Stone){
                            batch.draw(tex, drawX, drawY ,GamePictureManager.ROCK_SIZE , GamePictureManager.ROCK_SIZE );
                        }

                        else if (tile.getPlaceable() instanceof GreenHouse gh) {
                            int baseX = gh.getBounds().x;
                            int baseY = gh.getBounds().y;

                            int localX = x - baseX;
                            int localY = y - baseY;

                            if (localX >= 0 && localY >= 0 &&
                                localX < GamePictureManager.regions.length && localY < GamePictureManager.regions[0].length) {
                                int flippedY = GamePictureManager.regions.length - 1 - localY;
                                TextureRegion region = GamePictureManager.regions[flippedY][localX];
                                batch.draw(region, drawX, drawY, tileSize, tileSize);
                            }
                        }
                        else if (tile.getPlaceable() instanceof Crop crop)
                            crop.render(batch);


//                        else {
//                            batch.draw(tex, drawX, drawY, tileSize, tileSize);
//                        }
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

        batch.draw(currentFrame, pos.getFirst() * GamePictureManager.TILE_SIZE, pos.getSecond() * GamePictureManager.TILE_SIZE, currentFrame.getRegionWidth() * 3, currentFrame.getRegionHeight() * 3);

    }

    public void renderBackground(int startX, int startY, int endX, int endY, int tileSize) {
        for(int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                Tile tile = gameModel.getMap().findTile(x, y);
                if(tile != null) {
                    float drawX = x * tileSize;
                    float drawY = y * tileSize;
                    TextureRegion backTex = tile.getBackgroundTexture();
                    if (backTex != null) {
                        batch.draw(backTex, drawX, drawY, tileSize, tileSize);
                    }
                }
            }
        }
    }

    public void logPlayerTilePosition(Player player, int tileSize) {
        int tileX = (int)(player.getPlayerPosition().getFirst() / tileSize);
        int tileY = (int)(player.getPlayerPosition().getSecond() / tileSize);
        System.out.println("Player is currently on tile (" + tileX + ", " + tileY + ")");
    }



}
