package com.stardew.model.NPC;


public enum NPCType {
    Abigail("Abigail"),
    Sebastian("Sebastian"),
    Harvey("Harvey"),
    Leah("Leah"),
    Robin("Robin");

    private final String name;

    NPCType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMaxFriendShipLevel() {
        return 799;
    }
}
