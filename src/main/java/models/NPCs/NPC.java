package models.NPCs;

import models.userInfo.Player;
import models.mapInfo.Position;

import java.util.ArrayList;

public abstract class NPC {

    protected String name;
    protected String job;
    protected FriendshipLevel friendshipLevel = FriendshipLevel.firstLevel;
    protected Position position;
    protected final ArrayList<String> dialogue = new ArrayList<>();
    protected final ArrayList<Gift> favoriteGifts = new ArrayList<>();
    protected int friendShipLevel = 0;
    protected ArrayList<Quest> quests = new ArrayList<>();
    protected final int maxFriendshipLevel = 799;

    public String getName() {
        return name;
    }

    public FriendshipLevel getFriendshipLevel() {
        return friendshipLevel;
    }

    public String getJob() {
        return job;
    }

    public Position getPosition() {
        return position;
    }

    public ArrayList<String> getDialogue() {
        return dialogue;
    }

    public ArrayList<Gift> getFavoriteGifts() {
        return favoriteGifts;
    }

    public int getFriendShipLevel() {
        return friendShipLevel;
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public abstract String getCurrentDialogue();

    public abstract void increaseFriendShipLevel();

    public abstract boolean isFavoriteGift();

    public abstract void givingGiftToPlayer();

    public abstract void talkToPlayer();

    public abstract void doQuest();

}