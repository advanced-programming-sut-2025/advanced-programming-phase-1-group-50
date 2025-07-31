package com.stardew.model.animals;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.ItemInventoryType;
import com.stardew.model.TextureID;
import com.stardew.model.animals.FishBehaviors.FishBehavior;
import com.stardew.model.gameApp.date.Season;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.userInfo.Ability;
import com.stardew.model.animals.FishBehaviors.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

public enum FishType implements Ingredient {
    Salmon(75, Season.Fall, TextureID.salmon, MixedBehavior::new),
    Sardine(40, Season.Fall, TextureID.sardine, DartBehavior::new),
    Shad(60, Season.Fall, TextureID.shad, SmoothBehavior::new),
    BlueDiscus(120, Season.Fall, TextureID.blueDiscus, DartBehavior::new),
    MidnightCarp(150, Season.Winter, TextureID.midnightCarp, MixedBehavior::new),
    Squid(80, Season.Winter, TextureID.squid, SinkerBehavior::new),
    Tuna(100, Season.Winter, TextureID.tuna, SmoothBehavior::new),
    Perch(55, Season.Winter, TextureID.perch, DartBehavior::new),
    Flounder(100, Season.Spring, TextureID.flounder, SinkerBehavior::new),
    Lionfish(100, Season.Spring, TextureID.lionfish, SmoothBehavior::new),
    Herring(30, Season.Spring, TextureID.herring, DartBehavior::new),
    GhostFish(45, Season.Spring, TextureID.ghostfish, MixedBehavior::new),
    Tilapia(75, Season.Summer, TextureID.tilapia, MixedBehavior::new),
    Dorado(100, Season.Summer, TextureID.dorado, MixedBehavior::new),
    Sunfish(30, Season.Summer, TextureID.sunfish, MixedBehavior::new),
    RainbowTrout(65, Season.Summer, TextureID.rainbowTrout, MixedBehavior::new),

    //LEGENDARY_FISH
    Legend(5000, Season.Spring, TextureID.legend, MixedBehavior::new),
    GlacierFish(1000, Season.Winter, TextureID.glacierfish, MixedBehavior::new),
    Angler(900, Season.Fall, TextureID.angler, SmoothBehavior::new),
    CrimsonFish(1500, Season.Summer, TextureID.crimsonfish, MixedBehavior::new);


    private final int price;
    private final Season season;
    private final TextureID texture;
    private final Supplier<FishBehavior> behaviorFactory;
    private final static HashMap<String, FishType> stringToFishType = new HashMap<>();

    static {
        for (FishType value : FishType.values()) {
            stringToFishType.put(value.name().toLowerCase(), value);
        }
    }


    FishType(int price, Season season, TextureID texture, Supplier<FishBehavior> behaviorFactory) {
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
    public TextureID getInventoryTexture() {
        return texture;
    }

    @Override
    public InventoryItemDTO toDTO() {
        return new InventoryItemDTO(getInventoryTexture() , false , 1 , ItemInventoryType.fishType , name());
    }

    @Override
    public String getId() {
        return name();
    }


}
