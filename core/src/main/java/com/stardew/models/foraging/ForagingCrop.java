package com.stardew.models.foraging;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.date.Season;
import com.stardew.models.manuFactor.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;

public enum ForagingCrop implements Ingredient {
    CommonMushroom(Season.Special, 40, 38, GamePictureManager.commonMushroom),
    Daffodil(Season.Spring, 30, 0, GamePictureManager.daffodil),
    Dandelion(Season.Spring, 40, 25, GamePictureManager.dandelion),
    Leek(Season.Spring, 60, 40, GamePictureManager.leek),
    Morel(Season.Spring, 150, 20, GamePictureManager.morel),
    SalmonBerry(Season.Spring, 5, 25, GamePictureManager.salmonBerry),
    SpringOnion(Season.Spring, 8, 13, GamePictureManager.springOnion),
    WildHorseradish(Season.Spring, 50, 13, GamePictureManager.wildHorseradish),
    FiddleHeadFern(Season.Summer, 90, 25, GamePictureManager.fiddleHeadFern),
    Grape(Season.Summer, 80, 38, GamePictureManager.grape),
    RedMushroom(Season.Summer, 75, -50, GamePictureManager.redMushroom),
    SpiceBerry(Season.Summer, 80, 25, GamePictureManager.spiceBerry),
    SweetPea(Season.Summer, 50, 0, GamePictureManager.sweetPea),
    Blackberry(Season.Fall, 25, 25, GamePictureManager.blackberry),
    Chanterelle(Season.Fall, 160, 75, GamePictureManager.chanterelle),
    Hazelnut(Season.Fall, 40, 38, GamePictureManager.hazelnut),
    PurpleMushroom(Season.Fall, 90, 30, GamePictureManager.purpleMushroom),
    WildPlum(Season.Fall, 80, 25, GamePictureManager.wildPlum),
    Crocus(Season.Winter, 60, 0, GamePictureManager.crocus),
    CrystalFruit(Season.Winter, 150, 63, GamePictureManager.crystalFruit),
    Holly(Season.Winter, 80, -37, GamePictureManager.holly),
    SnowYam(Season.Winter, 100, 30, GamePictureManager.snowYam),
    WinterRoot(Season.Winter, 70, 25, GamePictureManager.winterRoot);

    private final Season season;
    private final int baseSellPrice;
    private final int energy;
    private final TextureRegion texture;
    private final static HashMap<String, ForagingCrop> stringToForagingCrop = new HashMap<>();

    static {
        for (ForagingCrop value : ForagingCrop.values()) {
            stringToForagingCrop.put(value.name().toLowerCase(), value);
        }
    }

    ForagingCrop(Season season, int baseSellPrice, int energy, TextureRegion texture) {
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
    public TextureRegion getInventoryTexture() {
        return texture;
    }
}
