package com.stardew.models.NPCs;

import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.cooking.Food;
import com.stardew.models.foraging.ForagingMineral;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.mapInfo.NpcHome;
import com.stardew.models.mapInfo.Stone;
import com.stardew.models.userInfo.Coin;

import java.util.ArrayList;
import java.util.Arrays;

public class SebastianQuests {
    private static final ArrayList<String> questsNames = new ArrayList<>(Arrays.asList("Delivery of 50 irons", "Delivery of " +
            "a pumpkin", "Delivery of 150 stones"));

    public static ArrayList<String> getQuestsNames() {
        return questsNames;
    }

    public static Result doFirstQuest(boolean isRewardTwice) {

        boolean are50IronsAvailable = false;

        for (Ingredient ingredient :
                App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient.equals(ForagingMineral.Iron)) {
                int value =
                        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                if (value >= 50) {
                    App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(ingredient, 50);
                    are50IronsAvailable = true;
                    break;
                }
            }
        }

        if (!are50IronsAvailable) {
            return new Result(false,"You don't have enough stock for this quest.\n(You need at least 50 Irons)");
        }

        if (isRewardTwice) {
            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(ForagingMineral.Diamond,2);
        }
        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(ForagingMineral.Diamond,2);

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Sebastian)) {
                home.getNpc().setFirstQuestDone(true);
                break;
            }
        }

        return new Result(true, "Quest done.");
    }

    public static Result doSecondQuest(boolean isRewardTwice) {

        boolean isPumpkinPieAvailable = false;

        for (Ingredient ingredient : App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient.equals(Food.PumpkinPie)) {
                int value = App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                if (value > 0) {
                    App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(ingredient,1);
                    isPumpkinPieAvailable = true;
                    break;
                }
            }
        }

        if (!isPumpkinPieAvailable) {
            return new Result(false,"You don't have enough stock for this quest.\n(You need at least a pumpkin pie");
        }

        if (isRewardTwice) {
            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(),5000);
        }
        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(),5000);

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Sebastian)) {
                home.getNpc().setSecondQuestDone(true);
                break;
            }
        }

        return new Result(true, "Quest done.");
    }

    public static Result doThirdQuest(boolean isRewardTwice) {

        boolean are150StonesAvailable = false;

        for (Ingredient ingredient : App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Stone) {
                int value = App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                if (value >= 150) {
                    App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(ingredient, 150);
                    are150StonesAvailable = true;
                    break;
                }
            }
        }

        if (!are150StonesAvailable) {
            return new Result(false, "You don't have enough stock for this quest.\n(You need at least 150 Stones)");
        }

        if (isRewardTwice) {
            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(ForagingMineral.Quartz,50);
        }
        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(ForagingMineral.Quartz,50);

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Sebastian)) {
                home.getNpc().setThirdQuestDone(true);
                break;
            }
        }

        return new Result(true, "Quest done.");
    }

}
