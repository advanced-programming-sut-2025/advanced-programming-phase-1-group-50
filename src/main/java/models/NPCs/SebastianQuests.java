package models.NPCs;

import java.util.ArrayList;
import java.util.Arrays;

public class SebastianQuests{
    private final ArrayList<String> questsNames = new ArrayList<>(Arrays.asList("Delivery of 50 irons", "Delivery of a pumpkin", "Delivery of 150 stones"));

    public ArrayList<String> getQuestsNames() {
        return questsNames;
    }

    public static boolean doFirstQuest(boolean isRewardTwice) {
        //TODO
        return true;
    }

    public static boolean doSecondQuest(boolean isRewardTwice) {
        //TODO
        return true;
    }

    public static boolean doThirdQuest(boolean isRewardTwice) {
        //TODO
        return true;
    }

}
