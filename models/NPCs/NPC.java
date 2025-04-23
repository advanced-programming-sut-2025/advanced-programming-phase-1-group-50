package models.NPCs;

import models.userInfo.Player;
import models.mapInfo.Position;

import java.util.ArrayList;

public class NPC {

    private String name;
    private String job;
    private FriendshipLevel friendshipLevel = FriendshipLevel.firstLevel;
    private Position position;
    private final ArrayList<String> Dialogue = new ArrayList<>();
    private final ArrayList<Gift> favoriteGifts = new ArrayList<>();
    private int friendShipLevel = 0;
    private ArrayList<Quest> quests = new ArrayList<>();
    private final int maxFriendshipLevel = 799;

    public String getCurrentDialogue() {
        return Dialogue.get(0);
    }

    public void increaseFriendShipLevel(int level) {
        //change friendshiplevel
    }

    public boolean isFavoriteGift(Gift gift) {
        return false;
    }

    public FriendshipLevel getFriendshipLevel() {
        return friendshipLevel;
    }

    public void givingGiftToPlayer(Player player) {

    }

    public void talkToPlayer(Player player) {

    }

    public void doQuest() {

    }

}