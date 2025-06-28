package com.stardew.models.foraging;

import com.stardew.models.date.Season;
import com.stardew.models.manuFactor.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;

public enum ForagingCrop implements Ingredient {
    CommonMushroom(Season.Special, 40, 38),
    Daffodil(Season.Spring, 30, 0),
    Dandelion(Season.Spring, 40, 25),
    Leek(Season.Spring, 60, 40),
    Morel(Season.Spring, 150, 20),
    SalmonBerry(Season.Spring, 5, 25),
    SpringOnion(Season.Spring, 8, 13),
    WildHorseradish(Season.Spring, 50, 13),
    FiddleHeadFern(Season.Summer, 90, 25),
    Grape(Season.Summer, 80, 38),
    RedMushroom(Season.Summer, 75, -50),
    SpiceBerry(Season.Summer, 80, 25),
    SweetPea(Season.Summer, 50, 0),
    Blackberry(Season.Fall, 25, 25),
    Chanterelle(Season.Fall, 160, 75),
    Hazelnut(Season.Fall, 40, 38),
    PurpleMushroom(Season.Fall, 90, 30),
    WildPlum(Season.Fall, 80, 25),
    Crocus(Season.Winter, 60, 0),
    CrystalFruit(Season.Winter, 150, 63),
    Holly(Season.Winter, 80, -37),
    SnowYam(Season.Winter, 100, 30),
    WinterRoot(Season.Winter, 70, 25);

    private final Season season;
    private final int baseSellPrice;
    private final int energy;
    private final static HashMap<String, ForagingCrop> stringToForagingCrop = new HashMap<>();

    static {
        for (ForagingCrop value : ForagingCrop.values()) {
            stringToForagingCrop.put(value.name().toLowerCase(), value);
        }
    }

    ForagingCrop(Season season, int baseSellPrice, int energy) {
        this.season = season;
        this.baseSellPrice = baseSellPrice;
        this.energy = energy;
    }

    public String getName(){
        return name();
    }

    public Season getSeason() {
        return season;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public int getEnergy() {
        return energy;
    }

    public static ArrayList<ForagingCrop> getCropsBySeason(Season season) {
        ArrayList<ForagingCrop> crops = new ArrayList<>();
        for (ForagingCrop crop : values()) {
            if (crop.season == season) {
                crops.add(crop);
            }
        }
        if (season != Season.Special) {
            crops.add(ForagingCrop.CommonMushroom);
        }
        return crops;
    }

    public static ForagingCrop getForagingCropByName(String name) {
        if (name == null || name.isEmpty())
            return null;
        return stringToForagingCrop.getOrDefault(name.toLowerCase(), null);
    }
}
