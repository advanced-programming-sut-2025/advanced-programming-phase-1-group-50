package com.stardew.model.mapInfo.foraging;

import com.stardew.model.TreeTextureID;
import com.stardew.model.gameApp.date.Season;
import com.stardew.model.TextureID;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum TreeType {
    ApricotTree(TreeSource.ApricotSapling, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
        Fruit.Apricot, 1, 59, true, 38, Season.Spring,
        TreeTextureID.apricotStages, TreeTextureID.apricotStage5, TextureID.apricotStage5WithFruit),
    CherryTree(TreeSource.CherrySapling, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
        Fruit.Cherry, 1, 80, true, 38, Season.Spring,
        TreeTextureID.cherryStages, TreeTextureID.cherryStage5, TextureID.cherryStage5WithFruitTexture),
    BananaTree(TreeSource.BananaSapling, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
        Fruit.Banana, 1, 150, true, 75, Season.Summer,
        TreeTextureID.bananaStages, TreeTextureID.bananaStage5, TextureID.bananaStage5WithFruit),
    MangoTree(TreeSource.MangoSapling, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
        Fruit.Mango, 1, 130, true, 100, Season.Summer,
        TreeTextureID.mangoStages, TreeTextureID.mangoStage5, TextureID.mangoStage5WithFruit),
    OrangeTree(TreeSource.OrangeSapling, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
        Fruit.Orange, 1, 100, true, 38, Season.Summer,
        TreeTextureID.orangeStages, TreeTextureID.orangeStage5, TextureID.orangeStage5WithFruit),
    PeachTree(TreeSource.PeachSapling, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
        Fruit.Peach, 1, 140, true, 38, Season.Summer,
        TreeTextureID.peachStages, TreeTextureID.peachStage5, TextureID.peachStage5WithFruit),
    AppleTree(TreeSource.AppleSapling, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
        Fruit.Apple, 1, 100, true, 38, Season.Fall,
        TreeTextureID.appleStages, TreeTextureID.appleStage5, TextureID.appleStage5WithFruit),
    PomegranateTree(TreeSource.PomegranateSapling, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
        Fruit.Pomegranate, 1, 140, true, 38, Season.Fall,
        TreeTextureID.pomegranateStages, TreeTextureID.pomegranateStage5, TextureID.pomegranateStage5WithFruit),
    //TODO different
    OakTree(TreeSource.Acorns, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
        Fruit.OakResin, 7, 150, false, 0, Season.Special,
        TreeTextureID.oakStages, TreeTextureID.oakStage5, null),
    MapleTree(TreeSource.MapleSeeds, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
        Fruit.MapleSyrup, 9, 200, false, 0, Season.Special,
        TreeTextureID.mapleStages, TreeTextureID.mapleStage5, null),
    PineTree(TreeSource.PineCones, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
        Fruit.PineTar, 5, 100, false, 0, Season.Special,
        TreeTextureID.pineStages, TreeTextureID.pineStage5, null),
    MahoganyTree(TreeSource.MahoganySeeds, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
        Fruit.Sap, 1, 2, true, -2, Season.Special,
        TreeTextureID.mahoganyStages, TreeTextureID.mahoganyStage5, null),
    MushroomTree(TreeSource.MushroomTreeSeeds, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
        Fruit.CommonMushroom, 1, 40, true, 38, Season.Special,
        TreeTextureID.mushroomStages, null, null),
    MysticTree(TreeSource.MysticTreeSeeds, new ArrayList<>(Arrays.asList(7, 7, 7, 7)), 28,
        Fruit.MysticSyrup, 7, 1000, true, 500, Season.Special,
        TreeTextureID.mysticTreeStages, null, null);

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
    private final TextureID[] stageTextures;
    private final TextureID[] stage5Textures;
    private final TextureID stage5WithFruitTexture;

    static {
        for (TreeType value : TreeType.values()) {
            stringToTreeType.put(value.name().toLowerCase(), value);
        }
    }

    TreeType(TreeSource source, ArrayList<Integer> stages, int totalHarvestTime, Fruit fruit,
             final int harvestCycle, int fruitBaseSellPrice, boolean isFruitEdible, int fruitEnergy, Season season ,
             TextureID[] stageTextures, TextureID[] stage5Textures, TextureID stage5WithFruitTexture) {
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.fruit = fruit;
        this.harvestCycle = harvestCycle;
        this.fruitBaseSellPrice = fruitBaseSellPrice;
        this.isFruitEdible = isFruitEdible;
        this.fruitEnergy = fruitEnergy;
        this.season = season;
        this.stageTextures = stageTextures;
        this.stage5Textures = stage5Textures;
        this.stage5WithFruitTexture = stage5WithFruitTexture;
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

    public TextureID[] getStageTextures() {
        return stageTextures;
    }

    public TextureID getStage5Texture(Season season) {
        if (this == MushroomTree || this == MysticTree)
            return stageTextures[4];
        else {
            switch(season){
                case Spring:
                    return stage5Textures[0];
                case Summer:
                    return stage5Textures[1];
                case Fall :
                    return stage5Textures[2];
                case Winter:
                    return stage5Textures[3];

            }

        }
        return null;
    }

    public TextureID getStage5WithFruitTexture(Season season) {
        if (this.ordinal() >= OakTree.ordinal()) {
            return getStage5Texture(season);
        } else {
            return stage5WithFruitTexture;
        }
    }

    public String getName(){
        return name();
    }

    public static TreeType getTreeTypeByName(String name){
        if (name == null || name.isEmpty())
            return null;
        return stringToTreeType.getOrDefault(name.toLowerCase(), null);
    }

}
