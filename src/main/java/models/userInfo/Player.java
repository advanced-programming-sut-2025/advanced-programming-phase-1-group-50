package models.userInfo;

import models.NPCs.NPC;
import models.NPCs.NPCType;
import models.NPCs.RelationWithNPC;
import models.Result;
import models.Notification.Notification;
import models.animals.Animal;
import models.mapInfo.Farm;
import models.mapInfo.Position;
import models.mapInfo.Wood;
import models.tools.Axe;
import models.tools.Hoe;
import models.tools.Pickaxe;
import models.tools.Tool;
import models.app.*;

import java.util.ArrayList;

public class Player {
    private final int maxEnergy = 200;
    private int energy = maxEnergy;
    private int consumedEnergyInTurn = 0;
    private  final String username;
    private  final String nickname;
    private Animal currentAnimal = null;
    private final Ability ability = new Ability(this);
    private Tool currentTool;
    private  final Backpack backpack = new Backpack(BackpackType.Primary);
    private  final TrashCan trashCan = new TrashCan();
    private final ArrayList<Notification> notifications = new ArrayList<>();
    private Farm farm;
    private boolean isFaintedToday = false;
    private boolean isMarried = false;
    private int remainingNumsAfterMarriageRequestDenied = 0;

    private Position currentPosition;
    private boolean isInfinite = false;
    private RelationWithNPC relationWithAbigail;
    private RelationWithNPC relationWithSebastian;
    private RelationWithNPC relationWithHarvey;
    private RelationWithNPC relationWithLeah;
    private RelationWithNPC relationWithRobin;


    private final User currentUser;
    public Player(String username , String nickname , User currentUser) {
        this.username = username;
        this.nickname = nickname;
        this.currentUser = currentUser;
        this.currentPosition = new Position(0 , 0);
        this.backpack.getTools().add(new Hoe());
        this.backpack.getTools().add(new Pickaxe());
        this.backpack.getTools().add(new Axe());
        for(Tool tool : backpack.getTools()) {
            if(tool instanceof Hoe) {
                this.currentTool = tool;
                break;
            }
        }
        this.backpack.getIngredientQuantity().put(new Coin() , 20);
        this.backpack.getIngredientQuantity().put(new Wood() , 100);
        this.relationWithAbigail = new RelationWithNPC(NPCType.Abigail);
        this.relationWithSebastian = new RelationWithNPC(NPCType.Sebastian);
        this.relationWithHarvey = new RelationWithNPC(NPCType.Harvey);
        this.relationWithLeah = new RelationWithNPC(NPCType.Leah);
        this.relationWithRobin = new RelationWithNPC(NPCType.Robin);

    }
    public RelationWithNPC getRelationWithAbigail() {
        return relationWithAbigail;
    }

    public void setRelationWithAbigail(RelationWithNPC relationWithAbigail) {
        this.relationWithAbigail = relationWithAbigail;
    }

    public RelationWithNPC getRelationWithSebastian() {
        return relationWithSebastian;
    }

    public void setRelationWithSebastian(RelationWithNPC relationWithSebastian) {
        this.relationWithSebastian = relationWithSebastian;
    }

    public RelationWithNPC getRelationWithHarvey() {
        return relationWithHarvey;
    }

    public void setRelationWithHarvey(RelationWithNPC relationWithHarvey) {
        this.relationWithHarvey = relationWithHarvey;
    }

    public RelationWithNPC getRelationWithLeah() {
        return relationWithLeah;
    }

    public void setRelationWithLeah(RelationWithNPC relationWithLeah) {
        this.relationWithLeah = relationWithLeah;
    }

    public RelationWithNPC getRelationWithRobin() {
        return relationWithRobin;
    }

    public void setRelationWithRobin(RelationWithNPC relationWithRobin) {
        this.relationWithRobin = relationWithRobin;
    }


    public Result faint() {
        isFaintedToday = true;
        return App.getGame().nextPlayerTurn();
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
        return currentPosition;
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
    public Ability getAbility(){
        return ability;
    }


    public Backpack getBackpack() {
        return backpack;
    }

    public int getEnergy() {
        if(isInfinite){
            return Integer.MAX_VALUE;
        }
        else {
            return energy;
        }
    }


    public void setEnergy(int energy) {
        this.energy = energy;
        if(this.energy > maxEnergy){
            this.energy = maxEnergy;
        }
    }
    public Result consumeEnergy(int energy) {
        if(isInfinite){
            return new Result(true, "");
        }
        this.energy -= energy;
        consumedEnergyInTurn += energy;

        if (consumedEnergyInTurn >= 50 && this.energy > 0){
            Result result = App.getGame().nextPlayerTurn();
            return new Result(false,
                    "You consumed " + consumedEnergyInTurn + " energy in your turn! The turn will be changed!\n" + result);
        }
        if (this.energy <= 0){
            this.energy = 0;
            Result result = faint();
            return new Result(false, "oh!! You don't have enough energy! You fainted!\n" + result);
        }

        return new Result(true, "You consumed " + consumedEnergyInTurn + " energy in your turn!");

    }

    public void addEnergy(int energy) {
        if(!isInfinite){
            this.energy += energy;
            this.energy = Math.min(this.energy, maxEnergy);
        }



    }
    public void setEnergyInfinite(){
        this.energy = Integer.MAX_VALUE;
        this.isInfinite = true;

    }

    public boolean isFaintedToday() {
        return isFaintedToday;
    }

    public void setFaintedToday(boolean faintedToday) {
        isFaintedToday = faintedToday;
    }

    public Animal getCurrentAnimal() {
        return currentAnimal;
    }

    public void setCurrentAnimal(Animal currentAnimal) {
        this.currentAnimal = currentAnimal;
    }

    public int getConsumedEnergyInTurn() {
        return consumedEnergyInTurn;
    }

    public void setConsumedEnergyInTurn(int consumedEnergyInTurn) {
        this.consumedEnergyInTurn = consumedEnergyInTurn;
    }
    public void setPlayerPositionInCottage(){


    }

    public void addNotification (Notification notification) {
        this.notifications.add(notification);
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public int getRemainingNumsAfterMarriageRequestDenied() {
        return remainingNumsAfterMarriageRequestDenied;
    }

    public void setRemainingNumsAfterMarriageRequestDenied(int remainingNumsAfterMarriageRequestDenied) {
        this.remainingNumsAfterMarriageRequestDenied = remainingNumsAfterMarriageRequestDenied;
    }
}
