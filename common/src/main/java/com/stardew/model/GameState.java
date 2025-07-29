package com.stardew.model;

import java.util.ArrayList;

public class GameState {
    private ArrayList<TileDTO> tiles;
    private ArrayList<PlaceableDTO> placeables;
    private PlayerDTO player;

    public GameState() {}

    public GameState(ArrayList<TileDTO> tiles, ArrayList<PlaceableDTO> placeables, PlayerDTO player) {
        this.tiles = tiles;
        this.placeables = placeables;
        this.player = player;
    }

    public ArrayList<TileDTO> getTiles() {
        return tiles;
    }

    public ArrayList<PlaceableDTO> getPlaceables() {
        return placeables;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

}
