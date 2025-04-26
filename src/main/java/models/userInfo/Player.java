package models.userInfo;

import models.NPCs.NPC;
import models.mapInfo.Farm;
import models.mapInfo.Position;
import models.tools.Tool;

public class Player {
    private final int maxEnergy = 200;    //final
    private int energy;
    private  final String username;  //final
    private  final String nickname;  //final
    private final Position position = new Position(0, 0); // final
    private Ability ability;
    private Tool currentTool;
    private  final Backpack backpack = new Backpack();    //final
    private  final TrashCan trashCan = new TrashCan();
    private Farm farm;
    private int coins;
    private int woods;//final
    private Position currentPosition;

    private final User currentUser;
    public Player(String username , String nickname , User currentUser) {
        this.username = username;
        this.nickname = nickname;
        this.currentUser = currentUser;
        this.currentPosition = new Position(0 , 0);

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
    public Farm getFarm(){
        return farm;
    }
    public void setFarm(Farm map){
        this.farm = map;
    }
    public Position getPosition(){
        return position;
    }
    public void setPosition(Position position){
        this.currentPosition = position;
    }
    public User getCurrentUser(){
        return currentUser;
    }
    public String getUsername(){
        return username;
    }
    public String getNickname(){
        return nickname;
    }

    public int getCoins() {
        return coins;
    }

    public void minusCoins(int coins) {
        this.coins -= coins;
    }
    public int getWoods() {
        return woods;
    }
    public void minusWoods(int woods) {
        this.woods -= woods;
    }
    public Backpack getBackpack() {
        return backpack;
    }


//  TODO : method faint(ghash kardan)
}
