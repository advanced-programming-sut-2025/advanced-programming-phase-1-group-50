package models.userInfo;

import models.recipes.CraftingRecipes;

public class Ability {
    private int farmingLevel = 0;
    private int farmingRate = 0;
    private int miningLevel = 0;
    private int miningRate = 0;
    private int foragingLevel = 0;
    private int foragingRate = 0;
    private int fishingLevel = 0;
    private int fishingRate = 0;
    private final Player player;
    private final static int maxLevel = 4;

    public Ability(Player player) {
        this.player = player;
    }

    public static int getMaxLevel() {
        return maxLevel;
    }

    public static int getRequiredPointsForLevel(int level) {
        return 100 * level + 50;
    }

    public int getFarmingLevel() {
        return farmingLevel;
    }

    public int getFarmingRate() {
        return farmingRate;
    }

    public int getMiningLevel() {
        return miningLevel;
    }

    public int getMiningRate() {
        return miningRate;
    }

    public int getForagingLevel() {
        return foragingLevel;
    }

    public int getForagingRate() {
        return foragingRate;
    }

    public int getFishingLevel() {
        return fishingLevel;
    }

    public int getFishingRate() {
        return fishingRate;
    }

    public void increaseFarmingRate(int amount) {
        farmingRate += amount;
        while (farmingLevel < maxLevel && farmingRate >= getRequiredPointsForLevel(farmingLevel + 1)) {
            farmingRate -= getRequiredPointsForLevel(farmingLevel + 1);
            increaseFarmingLevel(1);
        }
    }

    public void increaseMiningRate(int amount) {
        miningRate += amount;
        while (miningLevel < maxLevel && miningRate >= getRequiredPointsForLevel(miningLevel + 1)) {
            miningRate -= getRequiredPointsForLevel(miningLevel + 1);
            increaseMiningLevel(1);
        }
    }

    public void increaseForagingRate(int amount) {
        foragingRate += amount;
        while (foragingLevel < maxLevel && foragingRate >= getRequiredPointsForLevel(foragingLevel + 1)) {
            foragingRate -= getRequiredPointsForLevel(foragingLevel + 1);
            increaseForagingLevel(1);
        }
    }

    public void increaseFishingRate(int amount) {
        fishingRate += amount;
        while (fishingLevel < maxLevel && fishingRate >= getRequiredPointsForLevel(fishingLevel + 1)) {
            fishingRate -= getRequiredPointsForLevel(fishingLevel + 1);
            increaseFishingLevel(1);
        }
    }

    public void increaseFarmingLevel(int amount) {
        farmingLevel += amount;
        if (farmingLevel == 1) {
            player.addRecipe(CraftingRecipes.Sprinkler);
            player.addRecipe(CraftingRecipes.BeeHouse);
        }
        else if (farmingLevel == 2) {
            player.addRecipe(CraftingRecipes.QualitySprinkler);
            player.addRecipe(CraftingRecipes.DeluxeScarecrow);
            player.addRecipe(CraftingRecipes.CheesePress);
            player.addRecipe(CraftingRecipes.PreservesJar);
        }
        else if (farmingLevel == 3) {
            player.addRecipe(CraftingRecipes.IridiumSprinkler);
            player.addRecipe(CraftingRecipes.OilMaker);
            player.addRecipe(CraftingRecipes.Keg);
            player.addRecipe(CraftingRecipes.Loom);
        }
    }

    public void increaseMiningLevel(int amount) {
        miningLevel += amount;
        if (miningLevel == 1)
            player.addRecipe(CraftingRecipes.CherryBomb);
        if (miningLevel == 2)
            player.addRecipe(CraftingRecipes.Bomb);
        if (miningLevel == 3)
            player.addRecipe(CraftingRecipes.MegaBomb);
    }

    public void increaseForagingLevel(int amount) {
        foragingLevel += amount;
        if (foragingLevel == 1)
            player.addRecipe(CraftingRecipes.CharcoalKiln);
        if (foragingLevel == 4)
            player.addRecipe(CraftingRecipes.MysticTreeSeed);
    }

    public void increaseFishingLevel(int amount) {
        fishingLevel += amount;
    }
}
