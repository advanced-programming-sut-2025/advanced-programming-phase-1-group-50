package com.stardew.controller;

import com.badlogic.gdx.math.Rectangle;
import com.stardew.model.mapInfo.Pair;
import com.stardew.model.mapInfo.Tile;
import com.stardew.model.userInfo.Player;

public class PlayerController {
    private static PlayerController instance;

    private PlayerController() {}

    public static PlayerController getInstance() {
        if (instance == null)
            instance = new PlayerController();
        return instance;
    }


    public void handleMovement(Player player, Tile[][] tiles, float vx, float vy, int dir) {

        if(player.getEnergy() <= 0) return;

        float tileSize = 60;

        float newXPos = player.getPlayerPosition().getFirst() + (vx * player.getSpeed());
        float newYPos = player.getPlayerPosition().getSecond() + (vy * player.getSpeed());

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
                        return;
                    }
                }
            }
        }

        float distanceMoved =(float) Math.sqrt(vx * vx + vy * vy);
        player.setMoveDistance(player.getMoveDistance() + distanceMoved);
        if (player.getMoveDistance() > player.getDistanceByTile()) {
            player.consumeEnergy(1);
            player.setMoveDistance(0);
        }


        player.setPlayerPosition(new Pair<>(newXPos, newYPos));
        player.setMoveDirection(dir);
    }

}
