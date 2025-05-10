package models.NPCs;

import java.util.ArrayList;
import java.util.Arrays;

public class LeahQuests{
    private final ArrayList<String> questsNames = new ArrayList<>(Arrays.asList("Delivery of 10 hardwoods","Delivery of a salmon", "Delivery of 200 pieces of wood"));

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
