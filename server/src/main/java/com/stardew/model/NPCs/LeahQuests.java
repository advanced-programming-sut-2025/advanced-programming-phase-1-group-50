package com.stardew.model.NPCs;

import com.stardew.controller.GameSessionController;
import com.stardew.model.Result;
import com.stardew.model.animals.Fish;
import com.stardew.model.animals.FishType;
import com.stardew.model.mapInfo.Wood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.recipes.CookingRecipe;
import com.stardew.model.userInfo.Coin;
import com.stardew.model.userInfo.Player;

public class LeahQuests {

    public static Result doFirstQuest(int gameId, Player player, boolean isRewardTwice) {

        boolean isGoldBarAvailable = false;

        for (Ingredient ingredient :
            player.getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof ArtisanGood) {
                if (((ArtisanGood) ingredient).getType().equals(ArtisanGoodType.GoldBar)) {
                    int value =
                        player.getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                    if (value > 0) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        isGoldBarAvailable = true;
                        break;
                    }
                }
            }
        }

        if (!isGoldBarAvailable) {
            return new Result(false,"You don't have enough stock for this quest.\n(You need at least a gold bar)");
        }

        if (isRewardTwice) {
            player.getBackpack().addIngredients(new Coin(),500);
        }

        player.getBackpack().addIngredients(new Coin(),500);

        GameSessionController.getInstance().getGame(gameId).getNPCByType(NPCType.Leah).setFirstQuestDone(true);

        return new Result(true,"Quest done.");
    }

    public static Result doSecondQuest(int gameId,Player player,boolean isRewardTwice) {

        boolean isSalmonAvailable = false;

        for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Fish) {
                if (((Fish) ingredient).getType().equals(FishType.Salmon)) {
                    int value = player.getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                    if (value > 0) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        isSalmonAvailable = true;
                        break;
                    }
                }
            }
        }

        if (!isSalmonAvailable) {
            return new Result(false,"You don't have enough stock for this quest.\n(You need at least a salmon)");
        }

        player.getBackpack().getCookingRecipes().add(CookingRecipe.SalmonDinner);
        GameSessionController.getInstance().getGame(gameId).getNPCByType(NPCType.Leah).setSecondQuestDone(true);

        return new Result(true,"Quest done.");
    }


    public static Result doThirdQuest(int gameId,Player player,boolean isRewardTwice) {

        boolean are200WoodAvailable = false;

        for (Ingredient ingredient :
            player.getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Wood) {
                int value =
                    player.getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                if (value >= 200) {
                    player.getBackpack().removeIngredients(ingredient,200);
                    are200WoodAvailable = true;
                    break;
                }
            }
        }

        if (!are200WoodAvailable) {
            return new Result(false,"You don't have enough stock for this quest.\n(You need at least 200 pieces of wood)");
        }

         //we don't need the reward in our app, so we don't add it to player inventory

        GameSessionController.getInstance().getGame(gameId).getNPCByType(NPCType.Leah).setThirdQuestDone(true);

        return new Result(true,"Quest done.");
    }
}
