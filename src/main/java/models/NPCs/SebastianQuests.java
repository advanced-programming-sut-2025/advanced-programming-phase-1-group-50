package models.NPCs;

import models.app.App;
import models.foraging.ForagingMineral;
import models.manuFactor.Ingredient;
import models.mapInfo.NpcHome;
import models.mapInfo.Stone;
import models.mapInfo.Wood;
import models.userInfo.Coin;

import java.util.ArrayList;
import java.util.Arrays;

public class SebastianQuests {
    private final ArrayList<String> questsNames = new ArrayList<>(Arrays.asList("Delivery of 50 irons", "Delivery of " +
            "a pumpkin", "Delivery of 150 stones"));

    public ArrayList<String> getQuestsNames() {
        return questsNames;
    }

    public static boolean doFirstQuest(boolean isRewardTwice) {

        boolean are50IronsAvailable = false;

        for (Ingredient ingredient :
                App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient.equals(ForagingMineral.Iron)) {
                int value =
                        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().get(ingredient);
                if (value >= 50) {
                    App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(ingredient,
                            value - 50);
                    are50IronsAvailable = true;
                    break;
                }
            }
        }

        if (!are50IronsAvailable) {
            return false;
        }

        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(ForagingMineral.Diamond,
                App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ForagingMineral.Diamond, 0) + 2);

        if (isRewardTwice) {
            App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(ForagingMineral.Diamond,
                    App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ForagingMineral.Diamond, 0) + 2);
        }

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Sebastian)) {
                home.getNpc().setFirstQuestDone(true);
                break;
            }
        }

        return true;
    }

    public static boolean doSecondQuest(boolean isRewardTwice) {

        boolean isPumpkinPieAvailable = false;

        //TODO : check for pumpkin pie

        if (!isPumpkinPieAvailable) {
            return false;
        }

        for (Ingredient ingredient :
                App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Coin) {
                int value =
                        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().get(ingredient);
                if (isRewardTwice) {
                    App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(ingredient,
                            value + 10000);
                } else {
                    App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(ingredient,
                            value + 5000);
                }
            }
        }

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Sebastian)) {
                home.getNpc().setSecondQuestDone(true);
                break;
            }
        }

        return true;
    }

    public static boolean doThirdQuest(boolean isRewardTwice) {

        boolean are150StonesAvailable = false;

        for (Ingredient ingredient : App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Stone) {
                int value = App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().get(ingredient);
                if (value >= 150) {
                    App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(ingredient,
                            value - 150);
                    are150StonesAvailable = true;
                    break;
                }
            }
        }

        if (!are150StonesAvailable) {
            return false;
        }

        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(ForagingMineral.Quartz,
                App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ForagingMineral.Quartz, 0) + 50);

        if (isRewardTwice) {
            App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(ForagingMineral.Quartz,
                    App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ForagingMineral.Quartz, 0) + 50);
        }

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Sebastian)) {
                home.getNpc().setThirdQuestDone(true);
                break;
            }
        }

        return true;
    }

}
