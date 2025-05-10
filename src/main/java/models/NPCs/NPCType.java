package models.NPCs;

import models.manuFactor.Ingredient;
import models.mapInfo.Position;

import java.util.ArrayList;

public enum NPCType {

    Abigail("Abigail", "Gamer",
            NPCDialogues.AbigailDialogues.getDialogues(),new Position(0,0)  , 'a'),
    Sebastian("Sebastian", "Freelancer",
            NPCDialogues.SebastianDialogues.getDialogues(), new Position(0,0) ,'s'),
    Harvey("Harvey", "Town doctor",
            NPCDialogues.HarveyDialogues.getDialogues(), new Position(0,0) ,'h'),
    Leah("Leah", "Artist",
            NPCDialogues.LeahDialogues.getDialogues(),new Position(0,0) , 'l'),
    Robin("Robin", "Carpenter",
            NPCDialogues.RobinDialogues.getDialogues(),new Position(0,0) , 'r');

    //TODO
    // placing Npcs on map (in NPC) and editing initialPosition

    private final String name;
    private final String job;
    private final ArrayList<String> dialogues;
    private final Position initialPosition;
    private final char symbol;

    NPCType(String name, String job, ArrayList<String> dialogues, Position initialPosition , char symbol) {
        this.name = name;
        this.job = job;
        this.dialogues = dialogues;
        this.initialPosition = initialPosition;
        this.symbol = symbol;
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

    public char getSymbol() {
        return symbol;
    }
}