package models.mapInfo;

import models.Placeable;

import java.util.ArrayList;

public class Tile {
    private Position position;
    private boolean gotThunder;
    //private TileType tileType; TODO : nemidoonam  chie in asan!!!
    private char symbol = '#';
    private boolean walkable;
    private Placeable placeable;
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
    }

}
