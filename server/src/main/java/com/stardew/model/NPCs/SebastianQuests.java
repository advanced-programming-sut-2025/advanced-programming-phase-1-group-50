package com.stardew.model.NPCs;

import com.stardew.controller.GameSessionController;
import com.stardew.model.NPC.NPCType;
import com.stardew.model.Result;
import com.stardew.model.cooking.Food;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.Stone;
import com.stardew.model.mapInfo.foraging.ForagingMineral;
import com.stardew.model.userInfo.Coin;
import com.stardew.model.userInfo.Player;

public class SebastianQuests {

    public static Result doFirstQuest(int gameId, Player player, boolean isRewardTwice) {

        boolean are50IronsAvailable = false;

        for (Ingredient ingredient :
            player.getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient.equals(ForagingMineral.Iron)) {
                int value =
                    player.getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                if (value >= 50) {
                    player.getBackpack().removeIngredients(ingredient, 50);
                    are50IronsAvailable = true;
                    break;
                }
            }
        }

        if (!are50IronsAvailable) {
            return new Result(false,"You don't have enough stock for this quest.\n(You need at least 50 Irons)");
        }

        if (isRewardTwice) {
            player.getBackpack().addIngredients(ForagingMineral.Diamond,2);
        }
        player.getBackpack().addIngredients(ForagingMineral.Diamond,2);

        GameSessionController.getInstance().getGame(gameId).getNPCByType(NPCType.Sebastian).setFirstQuestDone(true);

        return new Result(true, "Quest done.");
    }

    public static Result doSecondQuest(int gameId,Player player,boolean isRewardTwice) {

        boolean isPumpkinPieAvailable = false;

        for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient.equals(Food.PumpkinPie)) {
                int value = player.getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                if (value > 0) {
                    player.getBackpack().removeIngredients(ingredient,1);
                    isPumpkinPieAvailable = true;
                    break;
                }
            }
        }

        if (!isPumpkinPieAvailable) {
            return new Result(false,"You don't have enough stock for this quest.\n(You need at least a pumpkin pie");
        }

        if (isRewardTwice) {
            player.getBackpack().addIngredients(new Coin(),5000);
        }
        player.getBackpack().addIngredients(new Coin(),5000);
        GameSessionController.getInstance().getGame(gameId).getNPCByType(NPCType.Sebastian).setSecondQuestDone(true);

        return new Result(true, "Quest done.");
    }

    public static Result doThirdQuest(int gameId,Player player,boolean isRewardTwice) {

        boolean are150StonesAvailable = false;

        for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Stone) {
                int value = player.getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                if (value >= 150) {
                    player.getBackpack().removeIngredients(ingredient, 150);
                    are150StonesAvailable = true;
                    break;
                }
            }
        }

        if (!are150StonesAvailable) {
            return new Result(false, "You don't have enough stock for this quest.\n(You need at least 150 Stones)");
        }

        if (isRewardTwice) {
            player.getBackpack().addIngredients(ForagingMineral.Quartz,50);
        }
        player.getBackpack().addIngredients(ForagingMineral.Quartz,50);
        GameSessionController.getInstance().getGame(gameId).getNPCByType(NPCType.Sebastian).setThirdQuestDone(true);

        return new Result(true, "Quest done.");
    }
}
