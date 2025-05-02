package models.mapInfo;

import models.Placeable;
import models.app.App;
import models.date.Time;
import models.foraging.Tree;
import models.foraging.TreeType;
import models.userInfo.Player;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    private Tile[][] tiles = new Tile[250][200];
    private ArrayList<Farm> farms = new ArrayList<>();
    public Map(ArrayList<Farm> farms) {
        this.farms= farms;
    }
    public Tile findTile(int x, int y) {
        for(int i=0; i<tiles.length; i++) {
            for(int j=0; j<tiles.length; j++) {
                if(x == i && y == j) {
                    return tiles[i][j];
                }
            }
        }
        return null;
    }

    public void buildMap(ArrayList<Player> players) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile(new Position(i, j));
            }
        }
        farms.clear();
        for (Player player : players) {
            player.getFarm().setTilesSymbol(tiles);
            farms.add(player.getFarm());
        }
        for(int i = 0; i < farms.size(); i++){
            for(int j = i +1; j < farms.size(); j++){
                if(farms.get(i).equals(farms.get(j))){
                    System.out.println("are the same farm" + j + " " + i);
                }
            }
        }
//        for(Farm farm : farms) {
//            farm.setTilesSymbol(tiles);
//        }
//        for(int i=0 ; i<tiles.length ; i++){
//            for(int j=0 ; j<tiles[i].length ; j++){
//                tiles[i][j].setSymbol('.');
//            }
//        }
        
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

        setBorderFarms();

    }
    // this method is only for debug!!!
    public void printMap() {

        // نمایش هر فارم با توجه به موقعیت دقیق آن در نقشه
        for(int i =0 ; i<tiles.length; i++) {
            for(int j =0 ; j<tiles[i].length; j++) {
                System.out.print(tiles[i][j].getSymbol());
            }
            System.out.print("\n");
        }

    }


    public void setBorderFarms() {
        for(int i=0 ; i<100 ; i++){
            tiles[i][74].setSymbol('+');
            tiles[i][74].setWalkable(false);

        }
        for(int i=150 ; i<250 ; i++){
            tiles[i][74].setSymbol('+');
            tiles[i][74].setWalkable(false);
        }
        for(int i=0 ;i<100 ; i++){
            tiles[i][124].setSymbol('+');
            tiles[i][124].setWalkable(false);
        }
        for(int i=150 ;i<250 ; i++){
            tiles[i][124].setSymbol('+');
            tiles[i][124].setWalkable(false);
        }
        for(int j=0 ; j<75 ; j++){
            tiles[99][j].setSymbol('+');
            tiles[99][j].setWalkable(false);
        }
        for(int j=0 ; j<75 ; j++){
            tiles[149][j].setSymbol('+');
            tiles[149][j].setWalkable(false);
        }
        for(int j=125 ; j<200 ; j++){
            tiles[149][j].setSymbol('+');
            tiles[149][j].setWalkable(false);
        }
        for(int j=125 ; j<200 ; j++){
            tiles[99][j].setSymbol('+');
            tiles[99][j].setWalkable(false);
        }
    }
    public TreeType getRandomTreeType() {
        Random rand = new Random();
        TreeType[] types = TreeType.values();
        return types[rand.nextInt(types.length)];
    }
    public void generateRandomTree(Farm farm){
        Random rand = new Random();
        ArrayList<Tree> trees = new ArrayList<>();
        int numberOfTrees = rand.nextInt(30) + 5;
        while(trees.size() < numberOfTrees) {
            int x = rand.nextInt(100) + farm.getRectangle().x;
            int y = rand.nextInt(75) + farm.getRectangle().y;
            if(findTile(x, y) != null && findTile(x, y).getPlaceable() == null){

                Tree t= new Tree(getRandomTreeType() , new Time(), null ,  x , y ,1
                        ,1 );
                findTile(x, y).setPlaceable(t);
                findTile(x, y).setWalkable(false);
                findTile(x, y).setSymbol(t.getSymbol());
                trees.add(t);
            }
        }
        farm.setTrees(trees);
    }


    public Tile[][] getTiles() {
        return tiles;
    }

    public ArrayList<Farm> getFarms() {
        return farms;

    }
}