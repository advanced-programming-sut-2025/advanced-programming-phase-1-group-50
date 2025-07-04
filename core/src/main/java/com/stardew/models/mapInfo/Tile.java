package com.stardew.models.mapInfo;

import com.badlogic.gdx.graphics.Texture;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Placeable;
import com.stardew.models.foraging.Fertilizer;

import java.util.ArrayList;

public class Tile {
    private Position position;
    private boolean gotThunder;
    private char symbol = '#';
    private boolean walkable ;
    private Placeable placeable;
    private boolean isPlowed = false;
    private Fertilizer fertilizer = null;
    private Texture texture = GamePictureManager.farmBoulderTexture;
    public Tile(Position position) {
        this.position = position;
        this.gotThunder = false;
        this.walkable = true;

    }
    public void setPosition(Position position) {
        this.position = position;
    }


    public Position getPosition() {
        return position;

    }
    public char getSymbol() {
        return symbol;
    }
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    public boolean isGotThunder() {
        return gotThunder;
    }
    public void setGotThunder(boolean gotThunder) {
        this.gotThunder = gotThunder;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }
    public Placeable getPlaceable() {
        return placeable;
    }
    public void setPlaceable(Placeable placeable) {
        this.placeable = placeable;
        setTexture(placeable.getTexture());
    }

    public boolean isPlowed() {
        return isPlowed;
    }

    public void setPlowed(boolean plowed) {
        isPlowed = plowed;
    }

    public Fertilizer getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(Fertilizer fertilizer) {
        this.fertilizer = fertilizer;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
