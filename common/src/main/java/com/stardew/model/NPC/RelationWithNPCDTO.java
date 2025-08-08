package com.stardew.model.NPC;

public class RelationWithNPCDTO {
    private final NPCFriendshipLevel npcFriendshipLevel;
    private final int numericalFriendShipLevel;
    private final boolean isSecondQuestLocked;
    private final boolean isThirdQuestLocked;
    private final boolean isFirstTimeToSpeakWithNPC;
    private final boolean isFirstTimeGiftToNPC;

    public RelationWithNPCDTO(NPCFriendshipLevel npcFriendshipLevel, int numericalFriendShipLevel,
                              boolean isSecondQuestLocked, boolean isThirdQuestLocked,
                              boolean isFirstTimeToSpeakWithNPC, boolean isFirstTimeGiftToNPC) {
        this.npcFriendshipLevel = npcFriendshipLevel;
        this.numericalFriendShipLevel = numericalFriendShipLevel;
        this.isSecondQuestLocked = isSecondQuestLocked;
        this.isThirdQuestLocked = isThirdQuestLocked;
        this.isFirstTimeToSpeakWithNPC = isFirstTimeToSpeakWithNPC;
        this.isFirstTimeGiftToNPC = isFirstTimeGiftToNPC;
    }

    public boolean isFirstTimeToSpeakWithNPC() {
        return isFirstTimeToSpeakWithNPC;
    }

    public boolean isFirstTimeGiftToNPC() {
        return isFirstTimeGiftToNPC;
    }

    public boolean isThirdQuestLocked() {
        return isThirdQuestLocked;
    }

    public boolean isSecondQuestLocked() {
        return isSecondQuestLocked;
    }

    public int getNumericalFriendShipLevel() {
        return numericalFriendShipLevel;
    }

    public NPCFriendshipLevel getNpcFriendshipLevel() {
        return npcFriendshipLevel;
    }
}
