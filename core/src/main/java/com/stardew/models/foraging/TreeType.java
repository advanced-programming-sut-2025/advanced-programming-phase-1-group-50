package com.stardew.models.foraging;

import com.badlogic.gdx.graphics.Texture;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.date.Season;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public enum TreeType {
    ApricotTree(TreeSource.ApricotSapling, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
            Fruit.Apricot, 1, 59, true, 38, Season.Spring , GamePictureManager.appleStageTextures),
    CherryTree(TreeSource.CherrySapling, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
            Fruit.Cherry, 1, 80, true, 38, Season.Spring , GamePictureManager.appleStageTextures),
    BananaTree(TreeSource.BananaSapling, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
            Fruit.Banana, 1, 150, true, 75, Season.Summer , GamePictureManager.bananaStageTextures),
    MangoTree(TreeSource.MangoSapling, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
            Fruit.Mango, 1, 130, true, 100, Season.Summer , GamePictureManager.mangoStageTextures),
    OrangeTree(TreeSource.OrangeSapling, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
            Fruit.Orange, 1, 100, true, 38, Season.Summer , GamePictureManager.orangeTextures),
    PeachTree(TreeSource.PeachSapling, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
            Fruit.Peach, 1, 140, true, 38, Season.Summer , GamePictureManager.peachTextures),
    AppleTree(TreeSource.AppleSapling, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
            Fruit.Apple, 1, 100, true, 38, Season.Fall , GamePictureManager.appleStageTextures),
    PomegranateTree(TreeSource.PomegranateSapling, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
            Fruit.Pomegranate, 1, 140, true, 38, Season.Fall , GamePictureManager.pomegranateTextures),
    OakTree(TreeSource.Acorns, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
            Fruit.OakResin, 7, 150, false, 0, Season.Special , GamePictureManager.mysticTreeTextures),
    //TODO : tree source Acorn is for Oak tree!
    MapleTree(TreeSource.MapleSeeds, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
            Fruit.MapleSyrup, 9, 200, false, 0, Season.Special , GamePictureManager.mapleStageTextures),
    PineTree(TreeSource.PineCones, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
            Fruit.PineTar, 5, 100, false, 0, Season.Special , GamePictureManager.pineTextures),
    MahoganyTree(TreeSource.MahoganySeeds, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
            Fruit.Sap, 1, 2, true, -2, Season.Special , GamePictureManager.pineTextures),
    MushroomTree(TreeSource.MushroomTreeSeeds, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
            Fruit.CommonMushroom, 1, 40, true, 38, Season.Special , GamePictureManager.mushroomTreeTextures),
    MysticTree(TreeSource.MysticTreeSeeds, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
            Fruit.MysticSyrup, 7, 1000, true, 500, Season.Special , GamePictureManager.mysticTreeTextures),;

    private final TreeSource source;
    private final ArrayList<Integer> stages;
    private final int totalHarvestTime;
    private final Fruit fruit;
    private final int harvestCycle;
    private final int fruitBaseSellPrice;
    private final boolean isFruitEdible;
    private final int fruitEnergy;
    private final Season season;
    private final static HashMap<String, TreeType> stringToTreeType = new HashMap<>();
    private final ArrayList<Texture> stageTextures = new ArrayList<>();

    static {
        for (TreeType value : TreeType.values()) {
            stringToTreeType.put(value.name().toLowerCase(), value);
        }
    }

    TreeType(TreeSource source, ArrayList<Integer> stages, int totalHarvestTime, Fruit fruit,
             final int harvestCycle, int fruitBaseSellPrice, boolean isFruitEdible, int fruitEnergy, Season season , ArrayList<Texture> stageTextures) {
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.fruit = fruit;
        this.harvestCycle = harvestCycle;
        this.fruitBaseSellPrice = fruitBaseSellPrice;
        this.isFruitEdible = isFruitEdible;
        this.fruitEnergy = fruitEnergy;
        this.season = season;
        this.stageTextures.addAll(stageTextures);
    }

    public TreeSource getSource() {
        return source;
    }

    public ArrayList<Integer> getStages() {
        return stages;
    }

    public int getTimeForGrow(int level) {
        return stages.get(level);
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public int getHarvestCycle() {
        return harvestCycle;
    }

    public int getFruitBaseSellPrice() {
        return fruitBaseSellPrice;
    }

    public boolean isFruitEdible() {
        return isFruitEdible;
    }

    public int getFruitEnergy() {
        return fruitEnergy;
    }

    public Season getSeason() {
        return season;
    }

    public String getName(){
        return name();
    }

    public static TreeType getTreeTypeByName(String name){
        if (name == null || name.isEmpty())
            return null;
        return stringToTreeType.getOrDefault(name.toLowerCase(), null);
    }

    public ArrayList<Texture> getStageTextures() {
        return stageTextures;
    }

    public Texture getStageTextureByLevel(int level){
        return stageTextures.get(level);
    }

}
