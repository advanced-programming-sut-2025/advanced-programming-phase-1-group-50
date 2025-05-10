package models.mapInfo;

import models.Placeable;
import models.app.App;
import models.date.Time;
import models.foraging.Tree;
import models.foraging.TreeType;
import models.stores.*;
import models.userInfo.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;




// need a way from farms to npc village  .....
// doors are completed , but we need ways from each farm to npc village.



public class Map {
    private Tile[][] tiles = new Tile[250][200];
    private ArrayList<Farm> farms = new ArrayList<>();
    private NpcVillage npcVillage;

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
        this.npcVillage = new NpcVillage(new Rectangle(100, 75 , 49 , 49 ) ,
                new Blacksmith(102 , 77 , 3 , 3) ,
                new CarpenterShop(106 , 81 , 3 , 3),
                new FishShop(110 , 85 , 3 ,3 ),
                new JojaMart(114 , 89 , 3, 3) ,
                new MarnieRanch(118 , 93 ,3 , 3 ) ,
                new PierreGeneralStore(122 , 97  ,3 , 3),
                new StardropSaloon(126 , 101 ,  3 ,3 ));

        
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
        for(int i=this.npcVillage.getRectangle().x ; i<this.npcVillage.getRectangle().x + this.npcVillage.getRectangle().width; i++) {
            for(int j = this.npcVillage.getRectangle().y ; j < this.npcVillage.getRectangle().y + this.npcVillage.getRectangle().height; j++) {
                tiles[i][j].setSymbol('.');
            }
        }
        for(Placeable p : this.npcVillage.getPlaceables()){
            for(int i=p.getBounds().x; i<p.getBounds().x+p.getBounds().width; i++) {
                for(int j=p.getBounds().y; j<p.getBounds().y+p.getBounds().height; j++) {
                    tiles[i][j].setWalkable(false);
                    tiles[i][j].setSymbol(p.getSymbol());
                    tiles[i][j].setPlaceable(p);
                }
            }
        }

        setBorderFarmsAndNpcVillage();
        setDoorFarmsAndNpcVillage();
        for(Farm farm : farms) {
            setWalkableDoorTrue(farm.getDoor().getBounds().x , farm.getDoor().getBounds().y ,
                    farm.getDoor().getBounds().width , farm.getDoor().getBounds().height);
        }
        for(Door  d : npcVillage.getDoors()) {
            setWalkableDoorTrue(d.getBounds().x , d.getBounds().y , d.getBounds().width , d.getBounds().height);
        }



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


    public void setBorderFarmsAndNpcVillage() {

        for(int i = 0 ; i < 200 ; i++){
            tiles[99][i].setSymbol('+');
            tiles[149][i].setSymbol('+');
        }
        for(int i =0 ; i<250 ; i++){
            tiles[i][74].setSymbol('+');
            tiles[i][124].setSymbol('+');
        }

    }
    public void setDoorFarmsAndNpcVillage() {
        farms.get(0).setDoor(new Door(99 , 37 , 1 , 3));

        farms.get(1).setDoor(new Door(149 , 37 , 1 , 3));
        farms.get(2).setDoor(new Door(99 , 155 , 1 , 3));
        farms.get(3).setDoor(new Door(149 , 155 , 1 , 3));
        npcVillage.addDoors(new Door(99 , 105 , 1 , 3));
        npcVillage.addDoors(new Door(149 , 105 , 1 , 3));
        npcVillage.addDoors(new Door(125 , 74 , 3 , 1));
        npcVillage.addDoors(new Door(125 , 124 , 3, 1));
    }




    public Tile[][] getTiles() {
        return tiles;
    }

    public ArrayList<Farm> getFarms() {
        return farms;

    }

    public NpcVillage getNpcVillage() {
        return npcVillage;
    }
    public void setWalkableDoorTrue(int x, int y , int width, int height) {
        for(int i=x ;i<x+ width ; i++){
            for(int j=y ;j<y+ height ;j++){
                tiles[i][j].setWalkable(true);
                tiles[i][j].setSymbol('d');
            }
        }
    }
}