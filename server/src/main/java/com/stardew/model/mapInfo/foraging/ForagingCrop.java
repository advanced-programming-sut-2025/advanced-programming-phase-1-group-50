package com.stardew.model.mapInfo.foraging;

import com.stardew.model.TextureID;
import com.stardew.model.gameApp.date.Season;
import com.stardew.model.mapInfo.Eatable;
import com.stardew.model.mapInfo.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;

public enum ForagingCrop implements Ingredient, Eatable {
    CommonMushroom(Season.Special, 40, 38, TextureID.commonMushroom),
    Daffodil(Season.Spring, 30, 0, TextureID.daffodil),
    Dandelion(Season.Spring, 40, 25, TextureID.dandelion),
    Leek(Season.Spring, 60, 40, TextureID.leek),
    Morel(Season.Spring, 150, 20, TextureID.morel),
    SalmonBerry(Season.Spring, 5, 25, TextureID.salmonBerry),
    SpringOnion(Season.Spring, 8, 13, TextureID.springOnion),
    WildHorseradish(Season.Spring, 50, 13, TextureID.wildHorseradish),
    FiddleHeadFern(Season.Summer, 90, 25, TextureID.fiddleHeadFern),
    Grape(Season.Summer, 80, 38, TextureID.grape),
    RedMushroom(Season.Summer, 75, -50, TextureID.redMushroom),
    SpiceBerry(Season.Summer, 80, 25, TextureID.spiceBerry),
    SweetPea(Season.Summer, 50, 0, TextureID.sweetPea),
    Blackberry(Season.Fall, 25, 25, TextureID.blackberry),
    Chanterelle(Season.Fall, 160, 75, TextureID.chanterelle),
    Hazelnut(Season.Fall, 40, 38, TextureID.hazelnut),
    PurpleMushroom(Season.Fall, 90, 30, TextureID.purpleMushroom),
    WildPlum(Season.Fall, 80, 25, TextureID.wildPlum),
    Crocus(Season.Winter, 60, 0, TextureID.crocus),
    CrystalFruit(Season.Winter, 150, 63, TextureID.crystalFruit),
    Holly(Season.Winter, 80, -37, TextureID.holly),
    SnowYam(Season.Winter, 100, 30, TextureID.snowYam),
    WinterRoot(Season.Winter, 70, 25, TextureID.winterRoot);

    private final Season season;
    private final int baseSellPrice;
    private final int energy;
    private final TextureID texture;
    private final static HashMap<String, ForagingCrop> stringToForagingCrop = new HashMap<>();

    static {
        for (ForagingCrop value : ForagingCrop.values()) {
            stringToForagingCrop.put(value.name().toLowerCase(), value);
        }
    }

    ForagingCrop(Season season, int baseSellPrice, int energy, TextureID texture) {
        this.season = season;
        this.baseSellPrice = baseSellPrice;
        this.energy = energy;
        this.texture = texture;
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

    @Override
    public TextureID getInventoryTexture() {
        return texture;
    }
}
