package com.stardew.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.animals.GameModel;
import com.stardew.models.mapInfo.Pair;
import com.stardew.models.mapInfo.Tile;
import com.stardew.models.userInfo.Player;

public class PlayerController {
    private Player player;
    private GameModel model;
    private final Tile[][] tiles;


    public PlayerController(Player player , GameModel model) {
        this.player = player;
        this.model = model;
        this.tiles = model.getMap().getTiles();
    }

    public void update(float delta) {
        tryMove(delta * player.getVx() , delta * player.getVy());
    }

//    public boolean tryMove(float dx, float dy) {
//        float newXPos = player.getPlayerPosition().getFirst() + dx;
//        float newYPos = player.getPlayerPosition().getSecond() + dy;
//
//        float width = 0.8f;  // به اندازه بازیکن در tile
//        float height = 1.8f; // چون sprite دو برابر tileه
//
//        // بررسی 4 گوشه بازیکن
//        if (
//            isWalkable(newXPos, newYPos) &&
//                isWalkable(newXPos + width, newYPos) &&
//                isWalkable(newXPos, newYPos + height) &&
//                isWalkable(newXPos + width, newYPos + height)
//        ) {
//            player.setPlayerPosition(new Pair<>(newXPos, newYPos));
//            return true;
//        }
//        return false;
    //    }
    public boolean tryMove(float dx, float dy) {
        float tileSize = GamePictureManager.TILE_SIZE;

        float newXPos = player.getPlayerPosition().getFirst() + dx;
        float newYPos = player.getPlayerPosition().getSecond() + dy;

        float playerWidth = 0.8f;
        float playerHeight = 1.8f;

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


        player.setPlayerPosition(new Pair<>(newXPos, newYPos));
        return true;
    }

    private boolean isWalkable(float x, float y) {
        int tileX = (int) x;
        int tileY = (int) y;

        if (tileX < 0 || tileX >= tiles.length || tileY < 0 || tileY >= tiles[0].length) return false;
        return tiles[tileX][tileY].isWalkable();
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
