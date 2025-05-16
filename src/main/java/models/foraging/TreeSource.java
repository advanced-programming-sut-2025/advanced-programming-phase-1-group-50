package models.foraging;

import models.manuFactor.Ingredient;

import java.util.HashMap;

public enum TreeSource implements Ingredient {
    ApricotSapling(TreeType.ApricotTree),
    CherrySapling(TreeType.CherryTree),
    BananaSapling(TreeType.BananaTree),
    MangoSapling(TreeType.MangoTree),
    OrangeSapling(TreeType.OrangeTree),
    PeachSapling(TreeType.PeachTree),
    AppleSapling(TreeType.AppleTree),
    PomegranateSapling(TreeType.PomegranateTree),
    Acorns(TreeType.OakTree),
    MapleSeeds(TreeType.MapleTree),
    PineCones(TreeType.PineTree),
    MahoganySeeds(TreeType.MahoganyTree),
    MushroomTreeSeeds(TreeType.MushroomTree),
    MysticTreeSeeds(TreeType.MysticTree);

    private TreeType treeType;
    private final static HashMap<String, TreeSource> stringToTreeSource = new HashMap<>();
    private static boolean firstInitialize = true;

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

    TreeSource(TreeType treeType) {
        this.treeType = treeType;
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
}
