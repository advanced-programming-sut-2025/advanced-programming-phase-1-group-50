package com.stardew.model.NPCs;

public enum NPCFriendshipLevel {
    LevelZero, LevelOne, LevelTwo, LevelThree;

    @Override
    public String toString() {
        return switch (this) {
            case LevelZero -> "LevelZero";
            case LevelOne -> "LevelOne";
            case LevelTwo -> "LevelTwo";
            case LevelThree -> "LevelThree";
            default -> "";
        };
    }
}
