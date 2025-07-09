package com.stardew.models.userInfo;

import com.stardew.models.NPCs.NPCType;
import com.stardew.models.NPCs.RelationWithNPC;
import com.stardew.models.Notification.MarriageRequest;
import com.stardew.models.Result;
import com.stardew.models.Notification.Notification;
import com.stardew.models.animals.Animal;
import com.stardew.models.mapInfo.Farm;
import com.stardew.models.mapInfo.Pair;
import com.stardew.models.mapInfo.Position;
import com.stardew.models.mapInfo.Wood;
import com.stardew.models.tools.Axe;
import com.stardew.models.tools.Hoe;
import com.stardew.models.tools.Pickaxe;
import com.stardew.models.tools.Tool;
import com.stardew.models.app.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    private final ArrayList<Notification> notifications = new ArrayList<>();
    private Farm farm;
    private boolean isFaintedToday = false;
    private boolean isMarried = false;
    private int remainingNumsAfterMarriageRequestDenied = 0;
    private Pair<Float , Float> playerPosition = new Pair<>(15f, 8f);

    private Position currentPosition;
    private boolean isInfinite = false;
    private RelationWithNPC relationWithAbigail;
    private RelationWithNPC relationWithSebastian;
    private RelationWithNPC relationWithHarvey;
    private RelationWithNPC relationWithLeah;
    private RelationWithNPC relationWithRobin;


    private int moveDirection = 0;
    private float speed = 5f;
    private float vx , vy = 0;


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

    public void fishing(){

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

    public boolean isInfinite() {
        return isInfinite;
    }

    public String UncheckedNotifications() {

        StringBuilder result = new StringBuilder("Notifications:");

        for (Notification notification : notifications) {

            if (!notification.isChecked()) {

                result.append("\n").append(notification);

                if (!(notification instanceof MarriageRequest)) {
                    notification.setChecked(true);
                }

            }

        }

        return result.toString();
    }

    public boolean canWalkToOtherFarm (Farm farm) {

        Player temp = null;

        for (Player p : App.getGame().getPlayers()) {
            if (p.getFarm().equals(farm)) {
                temp = p;
                break;
            }
        }

        if (temp == null) {
            return false;
        }

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
        lookUpKey.add(temp);

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);

        if  (tempRelation.isMarriage()) {
            return true;
        } else {
            return false;
        }

    }

    public Pair<Float , Float> getPlayerPosition(){
        return playerPosition;
    }

    public void setPlayerPosition(Pair<Float , Float> playerPosition) {
        this.playerPosition = playerPosition;
    }

    public void setMoveDirection(int dir){
        this.moveDirection = dir;
    }

    public int getMoveDirection(){
        return moveDirection;
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }

    public float getSpeed(){
        return speed;
    }

    public void setVelocity(float vx , float vy){
        this.vx = vx;
        this.vy = vy;
    }

    public float getVx(){
        return vx;
    }

    public float getVy(){
        return vy;
    }

}
