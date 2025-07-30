package com.stardew.controller;

import com.badlogic.gdx.math.Rectangle;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.GameModel;
import com.stardew.models.mapInfo.Pair;
import com.stardew.models.mapInfo.Tile;
import com.stardew.models.userInfo.Player;

public class PlayerController {
    private Player player;
    private GameModel model;
    private final Tile[][] tiles;
    private float moveDistance;
    private final float distanceByTile = 5.0f;



    public PlayerController(Player player , GameModel model) {
        this.player = player;
        this.model = model;
//        this.tiles = model.getMap().getTiles();
        this.tiles = new Tile[250][200];
    }

    public void update(float delta) {
        tryMove(delta * player.getVx() , delta * player.getVy());
    }

    public boolean tryMove(float dx, float dy) {

        if(player.getEnergy() <= 0) return false;

        if (dx == 0 && dy == 0)
            return false;

        float tileSize = GamePictureManager.TILE_SIZE;

        float newXPos = player.getPlayerPosition().getFirst() + dx;
        float newYPos = player.getPlayerPosition().getSecond() + dy;

        float playerWidth = 0.8f;
        float playerHeight = 0.5f;

        Rectangle playerRect = new Rectangle(
            newXPos * tileSize,
            newYPos * tileSize,
            playerWidth * tileSize,
            playerHeight * tileSize
        );


        int startX = Math.max(0, (int) newXPos - 1);
        int endX = Math.min(tiles.length, (int) (newXPos + playerWidth) + 2);
        int startY = Math.max(0, (int) newYPos - 1);
        int endY = Math.min(tiles[0].length, (int) (newYPos + playerHeight) + 2);

        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                Tile tile = tiles[x][y];
                if (tile != null && !tile.isWalkable()) {
                    Rectangle tileRect = new Rectangle(
                        x * tileSize,
                        y * tileSize,
                        tileSize,
                        tileSize
                    );
                    if (playerRect.overlaps(tileRect)) {
                        return false;
                    }
                }
            }
        }

        float distanceMoved =(float) Math.sqrt(dx * dx + dy * dy);
        moveDistance += distanceMoved;
        if (moveDistance > distanceByTile) {
            int times = (int)(moveDistance / distanceByTile);
            for (int i = 0; i < times; i++) {
                player.consumeEnergy(1);
            }
            moveDistance %= distanceByTile;
        }


        player.setPlayerPosition(new Pair<>(newXPos, newYPos));
        return true;
    }




    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
