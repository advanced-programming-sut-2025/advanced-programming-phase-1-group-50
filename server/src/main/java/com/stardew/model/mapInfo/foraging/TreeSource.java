package com.stardew.model.mapInfo.foraging;

import com.stardew.model.TextureID;
import com.stardew.model.mapInfo.Ingredient;

import java.util.HashMap;

public enum TreeSource implements Ingredient {
    ApricotSapling(TreeType.ApricotTree , TextureID.apricotSaplingTexture),
    CherrySapling(TreeType.CherryTree , TextureID.cherrySaplingTexture),
    BananaSapling(TreeType.BananaTree , TextureID.bananaSaplingTexture),
    MangoSapling(TreeType.MangoTree , TextureID.mangoSaplingTexture),
    OrangeSapling(TreeType.OrangeTree , TextureID.orangeSaplingTexture),
    PeachSapling(TreeType.PeachTree , TextureID.peachSaplingTexture),
    AppleSapling(TreeType.AppleTree , TextureID.appleSaplingTexture),
    PomegranateSapling(TreeType.PomegranateTree , TextureID.pomegranateSaplingTexture),
    Acorns(TreeType.OakTree , TextureID.acornTexture),
    MapleSeeds(TreeType.MapleTree , TextureID.mapleSeedsTexture),
    PineCones(TreeType.PineTree, TextureID.pineConeTexture),
    MahoganySeeds(TreeType.MahoganyTree , TextureID.mahoganySeedTexture),
    MushroomTreeSeeds(TreeType.MushroomTree , TextureID.mushroomTreeSeedTexture),
    MysticTreeSeeds(TreeType.MysticTree ,  TextureID.mysticTreeSeedTexture),;

    private TreeType treeType;
    private final static HashMap<String, TreeSource> stringToTreeSource = new HashMap<>();
    private static boolean firstInitialize = true;
    private final TextureID texture;

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

    TreeSource(TreeType treeType , TextureID texture) {
        this.treeType = treeType;
        this.texture = texture;
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

    @Override
    public TextureID getInventoryTexture() {
        return texture;
    }
}
