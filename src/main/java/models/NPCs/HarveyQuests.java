package models.NPCs;

import java.util.ArrayList;
import java.util.Arrays;

public class HarveyQuests {

    private final ArrayList<String> questsNames = new ArrayList<>(Arrays.asList("Delivery of 12 desired plants",
            "Delivery of a salmon", "Delivery of a bottle of wine"));

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
