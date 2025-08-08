package com.stardew.controller;

import com.stardew.model.gameApp.Game;
import com.stardew.model.mapInfo.Pair;
import com.stardew.model.mapInfo.Tile;
import com.stardew.model.userInfo.Player;
import com.stardew.network.Message;

import java.awt.*;
import java.util.ArrayList;

public class PlayerController {
    private static PlayerController instance;

    private PlayerController() {}

    public static PlayerController getInstance() {
        if (instance == null)
            instance = new PlayerController();
        return instance;
    }


    public void handleMovement(Player player, Game game, Message message) {
        if (message == null) return;

        Tile[][] tiles = game.getMap().getTiles();
        ArrayList<Player> players = game.getAllPlayers();

        Float vx = message.getFromBody("vx", Float.class);
        Float vy = message.getFromBody("vy", Float.class);
        int dir = message.getIntFromBody("dir");

        if(player.getEnergy() <= 0) return;

        int tileSize = 60;

        float newXPos = player.getPlayerPosition().getFirst() + (vx * player.getSpeed());
        float newYPos = player.getPlayerPosition().getSecond() + (vy * player.getSpeed());

        float playerWidth = 0.8f;
        float playerHeight = 0.5f;

        if (newXPos < 0 ||
            newYPos < 0 ||
            newXPos + playerWidth > tiles.length ||
            newYPos + playerHeight > tiles[0].length) {
            return;
        }

        Rectangle playerRect = new Rectangle(
            (int) (newXPos * tileSize),
            (int) (newYPos * tileSize),
            (int) (playerWidth * tileSize),
            (int) (playerHeight * tileSize)
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
                    if (playerRect.intersects(tileRect)) return;
                }
            }
        }

        for (Player other : players) {
            if (player.equals(other)) continue;
            Rectangle otherRect = new Rectangle(
                ((int) (other.getPlayerPosition().getFirst() * tileSize)),
                ((int) (other.getPlayerPosition().getSecond() * tileSize)),
                ((int) (playerWidth * tileSize)),
                tileSize
            );
            if (playerRect.intersects(otherRect)) return;
        }

        float distanceMoved =(float) Math.sqrt(vx * vx + vy * vy);
        player.setMoveDistance(player.getMoveDistance() + distanceMoved);
        if (player.getMoveDistance() > player.getDistanceByTile()) {
            player.consumeEnergy(1);
            player.setMoveDistance(0);
        }


        player.setPlayerPosition(newXPos, newYPos);
        player.setMoveDirection(dir);
    }

}
