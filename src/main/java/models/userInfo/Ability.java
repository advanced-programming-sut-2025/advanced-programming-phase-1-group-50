package models.userInfo;

public class Ability {
    private int farmingLevel = 0;
    private int farmingRate = 0;
    private int miningLevel = 0;
    private int miningRate = 0;
    private int foragingLevel = 0;
    private int foragingRate = 0;
    private int fishingLevel = 0;
    private int fishingRate = 0;
    private static int maxLevel = 4;

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
            farmingLevel++;
        }
    }

    public void increaseMiningRate(int amount) {
        miningRate += amount;
        while (miningLevel < maxLevel && miningRate >= getRequiredPointsForLevel(miningLevel + 1)) {
            miningRate -= getRequiredPointsForLevel(miningLevel + 1);
            miningLevel++;
        }
    }

    public void increaseForagingRate(int amount) {
        foragingRate += amount;
        while (foragingLevel < maxLevel && foragingRate >= getRequiredPointsForLevel(foragingLevel + 1)) {
            foragingRate -= getRequiredPointsForLevel(foragingLevel + 1);
            foragingLevel++;
        }
    }

    public void increaseFishingRate(int amount) {
        fishingRate += amount;
        while (fishingLevel < maxLevel && fishingRate >= getRequiredPointsForLevel(fishingLevel + 1)) {
            fishingRate -= getRequiredPointsForLevel(fishingLevel + 1);
            fishingLevel++;
        }
    }
}
