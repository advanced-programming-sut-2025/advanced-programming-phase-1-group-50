package com.stardew.model.NPCs;

import java.util.ArrayList;
import java.util.Arrays;

public enum NPCQuests {
    AbigailQuests(new ArrayList<>(Arrays.asList("Delivery of a gold bar",
        "Delivery of a pumpkin", "Delivery of 50 pieces of wheat"))),
    HarveyQuests(new ArrayList<>(Arrays.asList("Delivery of 12 desired plants",
        "Delivery of a salmon", "Delivery of a bottle of wine"))),
    RobinQuests(new ArrayList<>(Arrays.asList("Delivery of 80 pieces of wood",
        "Delivery of 10 iron bar", "Delivery of 1000 pieces of wood"))),
    LeahQuests(new ArrayList<>(Arrays.asList("Delivery of a gold bar", "Delivery of a salmon",
        "Delivery of 200 pieces of wood"))),
    SebastianQuests(new ArrayList<>(Arrays.asList("Delivery of 50 irons", "Delivery of a pumpkin",
        "Delivery of 150 stones")));

    private final ArrayList<String> questsNames;

    NPCQuests(ArrayList<String> questsNames) {
        this.questsNames = questsNames;
    }

    public ArrayList<String> getQuestsNames() {
        return questsNames;
    }
}
