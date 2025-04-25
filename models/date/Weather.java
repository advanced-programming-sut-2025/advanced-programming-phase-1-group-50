package models.date;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Weather {
    Sunny(
            "sunny",
            new ArrayList<Season>(Arrays.asList(Season.Spring, Season.Summer, Season.Fall, Season.Winter))),
    Rainy(
            "rainy",
            new ArrayList<Season>(Arrays.asList(Season.Spring, Season.Summer, Season.Fall))),
    Stormy(
            "stormy",
            new ArrayList<Season>(Arrays.asList(Season.Spring, Season.Summer, Season.Fall))),
    Snowy(
            "snowy",
            new ArrayList<Season>(Arrays.asList(Season.Winter)));

    private final String name;
    private final ArrayList<Season> seasons = new ArrayList<>();

    Weather(String name, ArrayList<Season> seasons) {
        this.name = name;
        this.seasons.addAll(seasons);
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public String getName() {
        return name;
    }
}