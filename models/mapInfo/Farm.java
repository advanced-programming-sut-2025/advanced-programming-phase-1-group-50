package models.mapInfo;

import models.waterBodies.Lake;

import java.util.ArrayList;

public class Farm {
    private final Cottage cottage;
    private final GreenHouse greenHouse;
    private final ArrayList<Lake> lakes;
    private final ArrayList<Quarry> quarries;
    private final ArrayList<Tree> trees;
    private final ArrayList<Stone> stones;

    public Farm(Cottage cottage, GreenHouse greenHouse, ArrayList<Lake> lakes, ArrayList<Quarry> quarries,
                ArrayList<Tree> trees, ArrayList<Stone> stones) {
        this.cottage = cottage;
        this.greenHouse = greenHouse;
        this.lakes = lakes;
        this.quarries = quarries;
        this.trees = trees;
        this.stones = stones;
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
}
