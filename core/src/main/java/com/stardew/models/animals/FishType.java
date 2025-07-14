package com.stardew.models.animals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.date.Season;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.userInfo.Ability;

import java.util.ArrayList;
import java.util.HashMap;

public enum FishType implements Ingredient {
    Salmon(75, Season.Fall, GamePictureManager.salmon),
    Sardine(40, Season.Fall, GamePictureManager.sardine),
    Shad(60, Season.Fall, GamePictureManager.shad),
    BlueDiscus(120, Season.Fall, GamePictureManager.blueDiscus),
    MidnightCarp(150, Season.Winter, GamePictureManager.midnightCarp),
    Squid(80, Season.Winter, GamePictureManager.squid),
    Tuna(100, Season.Winter, GamePictureManager.tuna),
    Perch(55, Season.Winter, GamePictureManager.perch),
    Flounder(100, Season.Spring, GamePictureManager.flounder),
    Lionfish(100, Season.Spring, GamePictureManager.lionfish),
    Herring(30, Season.Spring, GamePictureManager.herring),
    GhostFish(45, Season.Spring, GamePictureManager.ghostfish),
    Tilapia(75, Season.Summer, GamePictureManager.tilapia),
    Dorado(100, Season.Summer, GamePictureManager.dorado),
    Sunfish(30, Season.Summer, GamePictureManager.sunfish),
    RainbowTrout(65, Season.Summer, GamePictureManager.rainbowTrout),

    //LEGENDARY_FISH
    Legend(5000, Season.Spring, GamePictureManager.legend),
    GlacierFish(1000, Season.Winter, GamePictureManager.glacierfish),
    Angler(900, Season.Fall, GamePictureManager.angler),
    CrimsonFish(1500, Season.Summer, GamePictureManager.crimsonfish);


    private final int price;
    private final Season season;
    private final TextureRegion texture;
    private final static HashMap<String, FishType> stringToFishType = new HashMap<>();

    static {
        for (FishType value : FishType.values()) {
            stringToFishType.put(value.name().toLowerCase(), value);
        }
    }


    FishType(int price, Season season, TextureRegion texture) {
        this.price = price;
        this.season = season;
        this.texture = texture;
    }


    public int getPrice() {
        return price;
    }

    public Season getSeason() {
        return season;
    }

    public static FishType getFishTypeByName(String name) {
        return stringToFishType.getOrDefault(name.toLowerCase(), null);
    }

    private static ArrayList<FishType> getFishesBySeason(Season season) {
        ArrayList<FishType> fishes = new ArrayList<>();
        for (FishType fish : FishType.values()) {
            if (fish.equals(Legend) || fish.equals(GlacierFish) || fish.equals(Angler) || fish.equals(CrimsonFish))
                continue;
            if (fish.getSeason() == season)
                fishes.add(fish);
        }
        return fishes;
    }

    public static ArrayList<FishType> getFishesBySeason(Season season, int fishingLevel) {
        ArrayList<FishType> fishes = getFishesBySeason(season);
        if (fishingLevel == Ability.getMaxLevel()) {
            fishes.add(
                    switch (season) {
                        case Spring -> Legend;
                        case Winter -> GlacierFish;
                        case Fall -> Angler;
                        case Summer -> CrimsonFish;
                        default -> null;
                    });
        }
        return fishes;
    }


    @Override
    public TextureRegion getInventoryTexture() {
        return texture;
    }
}
