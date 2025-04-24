package models.userInfo;

import models.NPCs.NPC;
import models.mapInfo.Position;
import models.tools.Tool;

public class Player {
    private final int maxEnergy;
    private int energy;
    private final String username;
    private final String nickname;
    private final Position position;
    private Ability ability;
    private Tool currentTool;
    private final Backpack backpack;
    private final TrashCan trashCan;


    public void faint() {

    }

    public Tool getCurrentTool() {
        return currentTool;
    }

    public void talkToNPC(NPC npc) {

    }

    public void fishing(){

    }

    public void tradeWithPlayer(Player player) {

    }

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }

    public Backpack getBackpack() {
        return backpack;
    }
}
