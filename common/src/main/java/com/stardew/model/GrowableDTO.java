package com.stardew.model;

public class GrowableDTO {
    private final String description;
    private final int levelOfGrowth;



    public GrowableDTO(String description, int levelOfGrowth) {
        this.description = description;
        this.levelOfGrowth = levelOfGrowth;
    }


    public String getDescription() {
        return description;
    }

    public int getLevelOfGrowth() {
        return levelOfGrowth;
    }
}
