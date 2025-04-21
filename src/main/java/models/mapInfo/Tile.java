package models.mapInfo;

public class Tile {
    private Position position;
    private TileType tileType;

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
    public Position getPosition() {
        return position;

    }

    public TileType getTileType() {
        return tileType;
    }
}
