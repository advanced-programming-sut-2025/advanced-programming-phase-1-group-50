package models.userInfo;

import models.NPCs.NPC;
import models.mapInfo.Map;
import models.mapInfo.Position;
import models.tools.Tool;

public class Player {
    private final int maxEnergy = 100;    //final
    private int energy;
    private  String username;  //final
    private  String nickname;  //final
    private final Position position = new Position(0, 0); // final
    private Ability ability;
    private Tool currentTool;
    private  final Backpack backpack = new Backpack();    //final
    private  final TrashCan trashCan = new TrashCan();
    private Map map;   //final
    public Player(String username , String nickname ){
        this.username = username;
        this.nickname = nickname;

    }

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

    public Map getMap(){
        return map;
    }

    public void setMap(Map map){
        this.map = map;
    }

    public Backpack getBackpack() {
        return backpack;
    }
}
