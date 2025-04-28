package models.mapInfo;

import models.Placeable;
import models.waterBodies.Lake;
import models.foraging.Tree;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Farm {

    private final int type;
    private final Cottage cottage;
    private final GreenHouse greenHouse;
    private final ArrayList<Lake> lakes;
    private final ArrayList<Quarry> quarries;
    private final ArrayList<Tree> trees;
    private final Rectangle rectangle =  new Rectangle();
    private final ArrayList<Stone> stones;
    private ArrayList<Placeable> placeables = new ArrayList<>();

    public Farm( Cottage cottage, GreenHouse greenHouse, ArrayList<Lake> lakes, ArrayList<Quarry> quarries,
                 ArrayList<Tree> trees, ArrayList<Stone> stones , Rectangle rectangle , int type ) {

        this.cottage = cottage;
        placeables.add(cottage);
        this.greenHouse = greenHouse;
        placeables.add(greenHouse);
        this.lakes = lakes;
        placeables.addAll(lakes);
        this.quarries = quarries;
        placeables.addAll(quarries);
        this.trees = trees;
        placeables.addAll((Collection<? extends Placeable>) trees);
        this.stones = stones;
        placeables.addAll(stones);
        this.type = type;


    }

    public Cottage getCottage() {
        return cottage;
    }

    public ArrayList<Stone> getStones() {
        return stones;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public ArrayList<Quarry> getQuarries() {
        return quarries;
    }

    public ArrayList<Lake> getLakes() {
        return lakes;
    }

    public GreenHouse getGreenHouse() {
        return greenHouse;
    }
    public void setTilesSymbol(Tile[][] tiles) {
        for(int i = this.rectangle.x ; i < this.rectangle.x+this.rectangle.width ; i++) {
            for(int j=this.rectangle.y ; j < this.rectangle.y+this.rectangle.height ; j++) {
                tiles[i][j].setSymbol('.');
            }
        }
    }

    public ArrayList<Placeable> getPlaceables() {
        return placeables;
    }

    public int getType() {
        return type;
    }
}
