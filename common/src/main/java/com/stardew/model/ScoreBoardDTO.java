package com.stardew.model;

public class ScoreBoardDTO {
    private final String username;
    private final int coin;
    private final int farmingLevel;
    private final int fishingLevel;
    private final int foragingLevel;
    private final int miningLevel;


    public ScoreBoardDTO(String username , int coin, int farmingLevel, int fishingLevel, int foragingLevel, int miningLevel) {
        this.username = username;
        this.coin = coin;
        this.farmingLevel = farmingLevel;
        this.fishingLevel = fishingLevel;
        this.foragingLevel = foragingLevel;
        this.miningLevel = miningLevel;
    }

    public String getUsername() {
        return username;
    }

    public int getCoin() {
        return coin;
    }

    public int getFarmingLevel() {
        return farmingLevel;
    }

    public int getFishingLevel() {
        return fishingLevel;
    }

    public int getForagingLevel() {
        return foragingLevel;
    }

    public int getMiningLevel() {
        return miningLevel;
    }
}
