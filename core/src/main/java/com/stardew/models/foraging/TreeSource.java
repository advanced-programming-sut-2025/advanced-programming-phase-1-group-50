package com.stardew.models.foraging;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.manuFactor.Ingredient;

import java.util.HashMap;

public enum TreeSource implements Ingredient {
    ApricotSapling(TreeType.ApricotTree , GamePictureManager.apricotSaplingTexture),
    CherrySapling(TreeType.CherryTree , GamePictureManager.cherrySaplingTexture),
    BananaSapling(TreeType.BananaTree , GamePictureManager.bananaSaplingTexture),
    MangoSapling(TreeType.MangoTree , GamePictureManager.mangoSaplingTexture),
    OrangeSapling(TreeType.OrangeTree , GamePictureManager.orangeSaplingTexture),
    PeachSapling(TreeType.PeachTree , GamePictureManager.peachSaplingTexture),
    AppleSapling(TreeType.AppleTree , GamePictureManager.appleSaplingTexture),
    PomegranateSapling(TreeType.PomegranateTree , GamePictureManager.pomegranateSaplingTexture),
    Acorns(TreeType.OakTree , null),
    MapleSeeds(TreeType.MapleTree , GamePictureManager.mapleSaplingTexture),
    PineCones(TreeType.PineTree, GamePictureManager.pineSaplingTexture),
    MahoganySeeds(TreeType.MahoganyTree , null),
    MushroomTreeSeeds(TreeType.MushroomTree , GamePictureManager.mushroomTreeSaplingTexture),
    MysticTreeSeeds(TreeType.MysticTree ,  GamePictureManager.mysticTreeSaplingTexture),;

    private TreeType treeType;
    private final static HashMap<String, TreeSource> stringToTreeSource = new HashMap<>();
    private static boolean firstInitialize = true;
    private final Texture saplingTexture;

    static {
        for (TreeSource value : TreeSource.values()) {
            stringToTreeSource.put(value.name().toLowerCase(), value);
        }
    }

    public static void completeInitialize() {
        ApricotSapling.treeType = TreeType.ApricotTree;
        CherrySapling.treeType = TreeType.CherryTree;
        BananaSapling.treeType = TreeType.BananaTree;
        MangoSapling.treeType = TreeType.MangoTree;
        OrangeSapling.treeType = TreeType.OrangeTree;
        PeachSapling.treeType = TreeType.PeachTree;
        AppleSapling.treeType = TreeType.AppleTree;
        PomegranateSapling.treeType = TreeType.PomegranateTree;
        Acorns.treeType = TreeType.OakTree;
        MapleSeeds.treeType = TreeType.MapleTree;
        PineCones.treeType = TreeType.PineTree;
        MahoganySeeds.treeType = TreeType.MahoganyTree;
        MushroomTreeSeeds.treeType = TreeType.MushroomTree;
        MysticTreeSeeds.treeType = TreeType.MysticTree;
    }

    TreeSource(TreeType treeType , Texture saplingTexture) {
        this.treeType = treeType;
        this.saplingTexture = saplingTexture;
    }

    public TreeType getTreeType() {
        if (firstInitialize) {
            completeInitialize();
            firstInitialize = false;
        }
        return treeType;
    }

    public static TreeSource getTreeSourceByName(String treeSource) {
        if (treeSource == null || treeSource.isEmpty())
            return null;
        return stringToTreeSource.getOrDefault(treeSource.toLowerCase(), null);
    }

    public Texture getSaplingTexture() {
        return saplingTexture;
    }
}
