package models.mapInfo;

import models.Placeable;
import models.stores.*;

import java.awt.*;
import java.util.ArrayList;

public class NpcVillage {
    private final Rectangle rectangle;
    private final Blacksmith blacksmith;
    private final CarpenterShop carpenterShop;
    private final FishShop fishShop;
    private final  JojaMart jojaMart;
    private final MarnieRanch marnieRanch;
    private final PierreGeneralStore pierreGeneralStore;
    private final StardropSaloon StardopSaloon;
    private final ArrayList<Placeable> placeables = new ArrayList<>();
    private final ArrayList<Door> doors = new ArrayList<>();
    public NpcVillage(Rectangle rectangle , Blacksmith blacksmith , CarpenterShop carpenterShop , FishShop fishShop ,
                      JojaMart jojaMart , MarnieRanch marnieRanch , PierreGeneralStore pierreGeneralStore
            , StardropSaloon StardopSaloon) {
        this.rectangle = rectangle;

        this.blacksmith = blacksmith;
        placeables.add(this.blacksmith);
        this.carpenterShop = carpenterShop;
        placeables.add(this.carpenterShop);
        this.fishShop = fishShop;
        placeables.add(this.fishShop);
        this.jojaMart = jojaMart;
        placeables.add(this.jojaMart);
        this.marnieRanch = marnieRanch;
        placeables.add(this.marnieRanch);
        this.pierreGeneralStore = pierreGeneralStore;
        placeables.add(this.pierreGeneralStore);
        this.StardopSaloon = StardopSaloon;
        placeables.add(this.StardopSaloon);

    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public StardropSaloon getStardopSaloon() {
        return StardopSaloon;
    }

    public PierreGeneralStore getPierreGeneralStore() {
        return pierreGeneralStore;
    }

    public MarnieRanch getMarnieRanch() {
        return marnieRanch;
    }

    public JojaMart getJojaMart() {
        return jojaMart;
    }

    public FishShop getFishShop() {
        return fishShop;
    }

    public CarpenterShop getCarpenterShop() {
        return carpenterShop;
    }

    public Blacksmith getBlacksmith() {
        return blacksmith;
    }

    public ArrayList<Placeable> getPlaceables() {
        return placeables;
    }
    public void addDoors(Door door) {
        doors.add(door);
        placeables.add(door);
    }
    public ArrayList<Door> getDoors() {
        return doors;
    }
}
