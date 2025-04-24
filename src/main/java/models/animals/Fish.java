package models.animals;

import models.date.Season;
import models.manuFactor.Ingredient;

public enum Fish implements Ingredient {
    Salmon(75, Season.Fall),
    Sardine(40, Season.Fall),
    Shad(60, Season.Fall),
    BlueDiscus(120, Season.Fall),
    MidnightCarp(150, Season.Winter),
    Squid(80, Season.Winter),
    Tuna(100, Season.Winter),
    Perch(55, Season.Winter),
    Flounder(100, Season.Spring),
    Lionfish(100, Season.Spring),
    Herring(30, Season.Spring),
    Ghostfish(45, Season.Spring),
    Tilapia(75, Season.Summer),
    Dorado(100, Season.Summer),
    Sunfish(30, Season.Summer),
    RainbowTrout(65, Season.Summer),
    ;


    private final int price;
    private final Season season;


    Fish(int price, Season season) {
        this.price = price;
        this.season = season;
    }


    public int getPrice() {
        return price;
    }

    public Season getSeason() {
        return season;
    }
}
