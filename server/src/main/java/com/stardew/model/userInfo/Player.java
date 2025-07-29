package com.stardew.model.userInfo;

//import com.stardew.model.InventoryItem;
//import com.stardew.model.NPCs.NPCType;
//import com.stardew.model.NPCs.RelationWithNPC;
//import com.stardew.model.Notification.MarriageRequest;
import com.stardew.model.PlayerDTO;
import com.stardew.model.Result;
//import com.stardew.model.Notification.Notification;
//import com.stardew.model.manuFactor.Ingredient;
import com.stardew.model.Tools.Axe;
import com.stardew.model.Tools.Pickaxe;
import com.stardew.model.Tools.Scythe;
import com.stardew.model.Tools.Hoe;
import com.stardew.model.Tools.WateringCan;
import com.stardew.model.mapInfo.Farm;
import com.stardew.model.mapInfo.Pair;
import com.stardew.model.mapInfo.Position;
//import com.stardew.model.mapInfo.Wood;
//import com.stardew.model.tools.*;
import com.stardew.model.gameApp.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Player {
    private final int maxEnergy = 200;
    private int energy = maxEnergy;
    private int consumedEnergyInTurn = 0;
    private  final String username;
    private  final String nickname;
//    private final Ability ability = new Ability(this);
//    private Tool currentTool;
    private final Backpack backpack = new Backpack(BackpackType.Primary);
//    private final ArrayList<Notification> notifications = new ArrayList<>();
    private Farm farm;
    private boolean isFaintedToday = false;
    private boolean isMarried = false;
    private int remainingNumsAfterMarriageRequestDenied = 0;
    private Pair<Float , Float> playerPosition = new Pair<>(15f, 8f);
    private Position currentPosition;
    private boolean isInfinite = false;
//    private RelationWithNPC relationWithAbigail;
//    private RelationWithNPC relationWithSebastian;
//    private RelationWithNPC relationWithHarvey;
//    private RelationWithNPC relationWithLeah;
//    private RelationWithNPC relationWithRobin;
    private int moveDirection = 0;
//    private float speed = 5f;
//    private float vx , vy = 0;
//    private InventoryItem currentInventoryItem = null;
//    private ArrayList<InventoryItem> inventoryItems = new ArrayList<>();
    private final User currentUser;

    public Player(User currentUser) {
        this.username = currentUser.getUsername();
        this.nickname = currentUser.getNickname();
        this.currentUser = currentUser;
//        this.currentPosition = new Position(0 , 0);
        Axe axe = new Axe();
        Hoe hoe = new Hoe();
        Pickaxe pickaxe = new Pickaxe();
        Scythe scythe = new Scythe();
        WateringCan wateringCan = new WateringCan();
        this.backpack.setPlayer(this);
        this.backpack.getTools().add(hoe);
        this.backpack.getTools().add(scythe);
        this.backpack.getTools().add(pickaxe);
        this.backpack.getTools().add(axe);
        this.backpack.getTools().add(wateringCan);
//        for(Tool tool : backpack.getTools()) {
//            if(tool instanceof Hoe) {
//                this.currentTool = tool;
//                break;
//            }
//        }
//        this.backpack.getIngredientQuantity().put(new Coin() , 20);
//        this.backpack.getIngredientQuantity().put(new Wood() , 100);
        updateInventoryItems();
//        this.relationWithAbigail = new RelationWithNPC(NPCType.Abigail);
//        this.relationWithSebastian = new RelationWithNPC(NPCType.Sebastian);
//        this.relationWithHarvey = new RelationWithNPC(NPCType.Harvey);
//        this.relationWithLeah = new RelationWithNPC(NPCType.Leah);
//        this.relationWithRobin = new RelationWithNPC(NPCType.Robin);

    }
    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void updateInventoryItems() {
//        inventoryItems.clear();
//        inventoryItems.addAll(getBackpack().getIngredientQuantity().keySet());
//        inventoryItems.addAll(backpack.getTools());
    }

//    public RelationWithNPC getRelationWithAbigail() {
//        return relationWithAbigail;
//    }

//    public void setRelationWithAbigail(RelationWithNPC relationWithAbigail) {
//        this.relationWithAbigail = relationWithAbigail;
//    }

//    public RelationWithNPC getRelationWithSebastian() {
//        return relationWithSebastian;
//    }

//    public void setRelationWithSebastian(RelationWithNPC relationWithSebastian) {
//        this.relationWithSebastian = relationWithSebastian;
//    }

//    public RelationWithNPC getRelationWithHarvey() {
//        return relationWithHarvey;
//    }

//    public void setRelationWithHarvey(RelationWithNPC relationWithHarvey) {
//        this.relationWithHarvey = relationWithHarvey;
//    }

//    public RelationWithNPC getRelationWithLeah() {
//        return relationWithLeah;
//    }

//    public void setRelationWithLeah(RelationWithNPC relationWithLeah) {
//        this.relationWithLeah = relationWithLeah;
//    }

//    public RelationWithNPC getRelationWithRobin() {
//        return relationWithRobin;
//    }

//    public void setRelationWithRobin(RelationWithNPC relationWithRobin) {
//        this.relationWithRobin = relationWithRobin;
//    }


    public Result faint() {
        isFaintedToday = true;
        // return App.getGame().nextPlayerTurn();
        return new Result(false , "");
    }

//    public Tool getCurrentTool() {
//        return currentTool;
//    }

    public void fishing(){

    }

//    public void setCurrentTool(Tool currentTool) {
//        this.currentTool = currentTool;
//    }
    public Farm getFarm(){
        return farm;
    }
    public void setFarm(Farm map){
        this.farm = map;
//        this.playerPosition = new Pair<>((float)farm.getRectangle().x , (float)farm.getRectangle().y );
    }
    public Position getPosition(){
        float x = playerPosition.getFirst();
        float y = playerPosition.getSecond();
        return new Position(((int)x), ((int)y));
//        return currentPosition;//previous
    }
//    public void setPosition(Position position){
//        this.currentPosition = position;
//    }
    public User getCurrentUser(){
        return currentUser;
    }
    public String getUsername(){
        return username;
    }
    public String getNickname(){
        return nickname;
    }
//    public Ability getAbility(){
//        return ability;
//    }


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

//        if (consumedEnergyInTurn >= 50 && this.energy > 0){
//            Result result = App.getGame().nextPlayerTurn();
//            return new Result(false,
//                    "You consumed " + consumedEnergyInTurn + " energy in your turn! The turn will be changed!\n" + result);
//        }
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

    public int getConsumedEnergyInTurn() {
        return consumedEnergyInTurn;
    }

    public void setConsumedEnergyInTurn(int consumedEnergyInTurn) {
        this.consumedEnergyInTurn = consumedEnergyInTurn;
    }

    public void setPlayerPositionInCottage(){


    }

//    public void addNotification (Notification notification) {
//        this.notifications.add(notification);
//    }

//    public ArrayList<Notification> getNotifications() {
//        return notifications;
//    }

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

//        for (Notification notification : notifications) {
//
//            if (!notification.isChecked()) {
//
//                result.append("\n").append(notification);
//
//                if (!(notification instanceof MarriageRequest)) {
//                    notification.setChecked(true);
//                }
//
//            }
//
//        }

        return result.toString();
    }

//    public boolean canWalkToOtherFarm (Farm farm) {
//
//        Player temp = null;
//
//        for (Player p : App.getGame().getPlayers()) {
//            if (p.getFarm().equals(farm)) {
//                temp = p;
//                break;
//            }
//        }
//
//        if (temp == null) {
//            return false;
//        }
//
//        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
//        Set<Player> lookUpKey = new HashSet<>();
//        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
//        lookUpKey.add(temp);
//
//        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);
//
//        if  (tempRelation.isMarriage()) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }

    public Pair<Float , Float> getPlayerPosition(){
        return playerPosition;
    }

    public void setPlayerPosition(Pair<Float , Float> playerPosition) {
        this.playerPosition = playerPosition;
    }

//    public void setMoveDirection(int dir){
//        this.moveDirection = dir;
//    }

//    public int getMoveDirection(){
//        return moveDirection;
//    }

//    public void setSpeed(float speed){
//        this.speed = speed;
//    }

//    public float getSpeed(){
//        return speed;
//    }

//    public void setVelocity(float vx , float vy){
//        this.vx = vx;
//        this.vy = vy;
//    }

//    public float getVx(){
//        return vx;
//    }

//    public float getVy(){
//        return vy;
//    }

//    public void setCurrentInventoryItem(InventoryItem item){
//        this.currentInventoryItem = item;
//
//        if (item instanceof Tool tool)
//            setCurrentTool(tool);
//        else
//            setCurrentTool(null);
//    }

//    public InventoryItem getCurrentInventoryItem(){
//        return currentInventoryItem;
//    }

//    public void addInventoryItem(InventoryItem item){
//        if(inventoryItems.contains(item)){
//            return;
//        }
//        this.inventoryItems.add(item);
//    }
//
//    public ArrayList<InventoryItem> getInventoryItems(){
//        return inventoryItems;
//    }
//
//    public InventoryItem[] getHotBar() {
//        InventoryItem[] hotBar = new InventoryItem[10];
//        for (int i = 0; i < 10; i++) {
//            if (i < inventoryItems.size()) {
//                hotBar[i] = inventoryItems.get(i);
//            } else {
//                hotBar[i] = null;
//            }
//        }
//        return hotBar;
//    }



//    public void swapInventoryItem(InventoryItem item1, InventoryItem item2) {
//        if (item1 == null || item2 == null || item1.equals(item2)) return;
//
//        int index1 = inventoryItems.indexOf(item1);
//        int index2 = inventoryItems.indexOf(item2);
//
//        if (index1 == -1 || index2 == -1) return;
//
//        inventoryItems.set(index1, item2);
//        inventoryItems.set(index2, item1);
//    }


//    public int getQuantityOfIngredient(InventoryItem ingredient) {
//        if(ingredient instanceof Tool) {
//            return 1;
//        }
//        else if(ingredient instanceof Ingredient){
//            return backpack.getIngredientQuantity().getOrDefault(ingredient, 0);
//        }
//        return 0;
//    }



//    public void shuffleInventoryItems() {
//        Collections.shuffle(inventoryItems);
//    }
//
//    public void removeInventoryItem(InventoryItem item) {
//        inventoryItems.remove(item);
//    }

    public PlayerDTO toDTO(){
        return new PlayerDTO(playerPosition.getFirst() , playerPosition.getSecond(), moveDirection , energy );
    }


}
