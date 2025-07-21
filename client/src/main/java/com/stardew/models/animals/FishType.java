package com.stardew.models.animals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.animals.FishBehavior.*;
import com.stardew.models.date.Season;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.userInfo.Ability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

public enum FishType implements Ingredient {
    Salmon(75, Season.Fall, GamePictureManager.salmon, MixedBehavior::new),
    Sardine(40, Season.Fall, GamePictureManager.sardine, DartBehavior::new),
    Shad(60, Season.Fall, GamePictureManager.shad, SmoothBehavior::new),
    BlueDiscus(120, Season.Fall, GamePictureManager.blueDiscus, DartBehavior::new),
    MidnightCarp(150, Season.Winter, GamePictureManager.midnightCarp, MixedBehavior::new),
    Squid(80, Season.Winter, GamePictureManager.squid, SinkerBehavior::new),
    Tuna(100, Season.Winter, GamePictureManager.tuna, SmoothBehavior::new),
    Perch(55, Season.Winter, GamePictureManager.perch, DartBehavior::new),
    Flounder(100, Season.Spring, GamePictureManager.flounder, SinkerBehavior::new),
    Lionfish(100, Season.Spring, GamePictureManager.lionfish, SmoothBehavior::new),
    Herring(30, Season.Spring, GamePictureManager.herring, DartBehavior::new),
    GhostFish(45, Season.Spring, GamePictureManager.ghostfish, MixedBehavior::new),
    Tilapia(75, Season.Summer, GamePictureManager.tilapia, MixedBehavior::new),
    Dorado(100, Season.Summer, GamePictureManager.dorado, MixedBehavior::new),
    Sunfish(30, Season.Summer, GamePictureManager.sunfish, MixedBehavior::new),
    RainbowTrout(65, Season.Summer, GamePictureManager.rainbowTrout, MixedBehavior::new),

    //LEGENDARY_FISH
    Legend(5000, Season.Spring, GamePictureManager.legend, MixedBehavior::new),
    GlacierFish(1000, Season.Winter, GamePictureManager.glacierfish, MixedBehavior::new),
    Angler(900, Season.Fall, GamePictureManager.angler, SmoothBehavior::new),
    CrimsonFish(1500, Season.Summer, GamePictureManager.crimsonfish, MixedBehavior::new);


    private final int price;
    private final Season season;
    private final TextureRegion texture;
    private final Supplier<FishBehavior> behaviorFactory;
    private final static HashMap<String, FishType> stringToFishType = new HashMap<>();

    static {
        for (FishType value : FishType.values()) {
            stringToFishType.put(value.name().toLowerCase(), value);
        }
    }


    FishType(int price, Season season, TextureRegion texture, Supplier<FishBehavior> behaviorFactory) {
        this.price = price;
        this.season = season;
        this.texture = texture;
        this.behaviorFactory = behaviorFactory;
    }


    public int getPrice() {
        return price;
    }

    public Season getSeason() {
        return season;
    }

    public FishBehavior createBehavior() {
        return behaviorFactory.get();
    }

    public static boolean isLegendary(FishType fishType) {
        return fishType == Legend || fishType == GlacierFish || fishType == Angler || fishType == CrimsonFish;
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
