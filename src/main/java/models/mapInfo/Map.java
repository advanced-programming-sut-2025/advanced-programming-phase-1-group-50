package models.mapInfo;

import models.Placeable;
import models.userInfo.Player;

import java.util.ArrayList;

public class Map {
    private Tile[][] tiles = new Tile[250][200];
    private ArrayList<Farm> farms = new ArrayList<>();

    public void buildMap(ArrayList<Player> players) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile(new Position(i, j));
            }
        }
        for (Player player : players) {
            player.getFarm().setTilesSymbol(tiles);
            farms.add(player.getFarm());
        }
        // halgheye 4 tayii baraye set kardan false walkable ok hast ya na?
        for(Farm farm : farms) {
            for(Placeable placeable : farm.getPlaceables()) {
                for(int i=placeable.getBounds().x; i<placeable.getBounds().x+placeable.getBounds().width; i++) {
                    for(int j=placeable.getBounds().y; j<placeable.getBounds().y+placeable.getBounds().height; j++) {
                        tiles[i][j].setWalkable(false);
                        tiles[i][j].setSymbol(placeable.getSymbol());
                        tiles[i][j].setPlaceable(placeable);
                    }
                }
            }
        }
    }
    public void printMap() {
        for(Farm farm : farms) {
            for(Placeable placeable : farm.getPlaceables()) {
                for(int i = placeable.getBounds().x; i < placeable.getBounds().x+placeable.getBounds().width; i++) {
                    for(int j = placeable.getBounds().y; j < placeable.getBounds().y+placeable.getBounds().height; j++) {
                        System.out.print(tiles[i][j].getSymbol());
                    }
                }
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public ArrayList<Farm> getFarms() {
        return farms;

    }
}