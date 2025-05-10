package models.userInfo;

import models.NPCs.NPC;
import models.NPCs.NPCType;
import models.NPCs.RelationWithNPC;
import models.mapInfo.Farm;
import models.mapInfo.Position;
import models.tools.Hoe;
import models.tools.Tool;

public class Player {
    private final int maxEnergy = 200;    //final
    private int energy;
    private final String username;  //final
    private final String nickname;  //final
    // final
    private Ability ability;
    private Tool currentTool;
    private final Backpack backpack = new Backpack(Backpack.Type.Primary);    //final
    private final TrashCan trashCan = new TrashCan();
    private Farm farm;
    private int coins;
    private int woods;//final
    private Position currentPosition;
    private boolean isInfinite = false;
    private RelationWithNPC relationWithAbigail;
    private RelationWithNPC relationWithSebastian;
    private RelationWithNPC relationWithHarvey;
    private RelationWithNPC relationWithLeah;
    private RelationWithNPC relationWithRobin;

    private final User currentUser;

    public Player(String username, String nickname, User currentUser) {
        this.username = username;
        this.nickname = nickname;
        this.currentUser = currentUser;
        this.currentPosition = new Position(0, 0);
        this.backpack.getTools().add(new Hoe());
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

    public void faint() {

    }

    public Tool getCurrentTool() {
        return currentTool;
    }

    public void talkToNPC(NPC npc) {

    }

    public void fishing() {

    }

    public void tradeWithPlayer(Player player) {

    }

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm map) {
        this.farm = map;
    }

    public Position getPosition() {
        return currentPosition;
    }

    public void setPosition(Position position) {
        this.currentPosition = position;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public Ability getAbility() {
        return ability;
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

    public int getEnergy() {
        return energy;
    }


    public void setEnergy(int energy) {
        this.energy = energy;
        if (this.energy > maxEnergy) {
            this.energy = maxEnergy;
        }
    }

    public void consumeEnergy(int energy) {
        this.energy -= energy;
        if (this.energy < 0) {
            faint();
        }
    }

    public void addEnergy(int energy) {
        if (this.isInfinite) {
            if (energy + this.energy > Integer.MAX_VALUE) {
                this.energy = Integer.MAX_VALUE;
            } else {
                this.energy += energy;
            }

        } else {
            this.energy += energy;
            this.energy = Math.min(maxEnergy, this.energy);
        }
    }

    public void setEnergyInfinite() {
        this.energy = Integer.MAX_VALUE;
        this.isInfinite = true;

    }


    //  TODO : method faint(ghash kardan)
}
