package models.animals;

import models.date.Season;
import models.manuFactor.Ingredient;

public enum LegendaryFish implements Ingredient {
    Legend(5000, Season.Spring),
    Glacierfish(1000, Season.Winter),
    Angler(900, Season.Fall),
    Crimsonfish(1500, Season.Summer);


    private final int price;
    private final Season season;


    LegendaryFish(int price, Season season) {
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
