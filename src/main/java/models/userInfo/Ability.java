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

    }

    public void increaseMiningRate(int amount) {

    }

    public void increaseForagingRate(int amount) {

    }

    public void increaseFishingRate(int amount) {

    }
}
