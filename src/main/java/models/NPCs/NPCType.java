package models.NPCs;

import models.manuFactor.Ingredient;
import models.mapInfo.Position;

import java.util.ArrayList;

public enum NPCType {

    Abigail("Abigail", "Gamer",
            NPCDialogues.AbigailDialogues.getDialogues(),new Position(0,0) ),
    Sebastian("Sebastian", "Freelancer",
            NPCDialogues.SebastianDialogues.getDialogues(), new Position(0,0) ),
    Harvey("Harvey", "Town doctor",
            NPCDialogues.HarveyDialogues.getDialogues(), new Position(0,0) ),
    Leah("Leah", "Artist",
            NPCDialogues.LeahDialogues.getDialogues(),new Position(0,0) ),
    Robin("Robin", "Carpenter",
            NPCDialogues.RobinDialogues.getDialogues(),new Position(0,0) );

    //TODO
    // placing Npcs on map (in NPC) and editing initialPosition

    private final String name;
    private final String job;
    private final ArrayList<String> dialogues;
    private final Position initialPosition;

    NPCType(String name, String job, ArrayList<String> dialogues, Position initialPosition) {
        this.name = name;
        this.job = job;
        this.dialogues = dialogues;
        this.initialPosition = initialPosition;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public ArrayList<String> getDialogues() {
        return dialogues;
    }

    public int getMaxFriendShipLevel() {
        return 799;
    }

    public Position getInitialPosition() {
        return initialPosition;
    }
}