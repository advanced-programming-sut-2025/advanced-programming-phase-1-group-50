package com.stardew.model.NPCs;

import com.stardew.controller.GameSessionController;
import com.stardew.model.NPC.NPCType;
import com.stardew.model.Result;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.foraging.Crop;
import com.stardew.model.mapInfo.foraging.CropType;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.userInfo.Coin;
import com.stardew.model.userInfo.Player;

public class AbigailQuests {

    public static Result doFirstQuest(int gameId, Player player, boolean isRewardTwice) {

        boolean isGoldBarAvailable = false;

        for (Ingredient ingredient :
            player.getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof ArtisanGood) {
                if (((ArtisanGood) ingredient).getType().equals(ArtisanGoodType.GoldBar)) {
                    int value =
                        player.getBackpack().getIngredientQuantity().getOrDefault(ingredient, 0);
                    if (value > 0) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        isGoldBarAvailable = true;
                        break;
                    }
                }
            }
        }

        if (!isGoldBarAvailable) {
            return new Result(false, "You don't have enough stock for this quest.\n(You need at least 1 gold bar)");
        }

        player.getRelationWithAbigail().increaseFriendshipLevel();
        if (isRewardTwice) {
            player.getRelationWithAbigail().increaseFriendshipLevel();
        }

        GameSessionController.getInstance().getGame(gameId).getNPCByType(NPCType.Abigail).setFirstQuestDone(true);

        return new Result(true, "Quest done.");
    }

    public static Result doSecondQuest(int gameId, Player player,boolean isRewardTwice) {

        boolean isPumpkinAvailable = false;

        for (Ingredient ingredient :
            player.getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Crop) {
                if (((Crop) ingredient).getType().equals(CropType.Pumpkin)) {
                    int value =
                        player.getBackpack().getIngredientQuantity().getOrDefault
                        (ingredient, 0);
                    if (value > 0) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        isPumpkinAvailable = true;
                        break;
                    }
                }
            }
        }
        if (!isPumpkinAvailable) {
            return new Result(false, "You don't have enough stock for this quest.\n(You need at least 1 pumpkin)");
        }

        if (isRewardTwice) {
            player.getBackpack().addIngredients(new Coin(), 500);
        }

        player.getBackpack().addIngredients(new Coin(), 500);

        GameSessionController.getInstance().getGame(gameId).getNPCByType(NPCType.Abigail).setSecondQuestDone(true);

        return new Result(true, "Quest done.");
    }

    public static Result doThirdQuest(int gameId,Player player,boolean isRewardTwice) {

        boolean are50WheatAvailable = false;

        for (Ingredient ingredient :
            player.getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Crop) {
                if (((Crop) ingredient).getType().equals(CropType.Wheat)) {
                    int value =
                        player.getBackpack().getIngredientQuantity().getOrDefault
                        (ingredient, 0);
                    if (value >= 50) {
                        player.getBackpack().removeIngredients(ingredient, 50);
                        are50WheatAvailable = true;
                        break;
                    }
                }
            }
        }

        if (!are50WheatAvailable) {
            return new Result(false, "You don't have enough stock for this quest.\n(You need at least 50 pieces of " +
                "wheat)");
        }

        GameSessionController.getInstance().getGame(gameId).getNPCByType(NPCType.Abigail).setThirdQuestDone(true);

        return new Result(true, "Quest done.");

    }
}
