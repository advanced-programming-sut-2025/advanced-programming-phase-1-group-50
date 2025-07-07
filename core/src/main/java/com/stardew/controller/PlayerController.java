package com.stardew.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    public boolean tryMove(float dx , float dy ){
        int newX = (int) (player.getPlayerPosition().getFirst() + dx);
        int newY = (int) (player.getPlayerPosition().getSecond() + dy);
        if (newX < 0 || newX >= tiles.length || newY < 0 || newY >= tiles[0].length) return false;
        if (tiles[newX][newY].isWalkable()) {
            float newXPos = player.getPlayerPosition().getFirst() + dx;
            float newYPos = player.getPlayerPosition().getSecond() + dy;
            Pair<Float , Float> playerPosition = new Pair<>(newXPos, newYPos);
            player.setPlayerPosition(playerPosition);
            return true;
        }
        return false;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
