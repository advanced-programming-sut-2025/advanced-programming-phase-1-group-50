package com.stardew.model.NPCs;

import com.stardew.controller.GameSessionController;
import com.stardew.model.NPC.NPCType;
import com.stardew.model.Result;
import com.stardew.model.animals.Fish;
import com.stardew.model.animals.FishType;
import com.stardew.model.cooking.Food;
import com.stardew.model.mapInfo.foraging.Crop;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.foraging.CropType;
import com.stardew.model.mapInfo.foraging.Fruit;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.userInfo.Coin;
import com.stardew.model.userInfo.Player;

public class HarveyQuests {

    public static Result doFirstQuest(int gameId, Player player, boolean isRewardTwice) {

        boolean are12PlantAvailable = false;
        for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Crop || ingredient instanceof CropType || ingredient instanceof Fruit) {
                int value = player.getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                if (value >= 12) {
                    player.getBackpack().removeIngredients(ingredient, 12);
                    are12PlantAvailable = true;
                    break;
                }
            }
        }

        if (!are12PlantAvailable) {
            return new Result(false,"You don't have enough stock for this quest.\n(You need at least 12 plants)");
        }

        if (isRewardTwice) {
            player.getBackpack().addIngredients(new Coin(),750);
        }

        player.getBackpack().addIngredients(new Coin(),750);

        GameSessionController.getInstance().getGame(gameId).getNPCByType(NPCType.Harvey).setFirstQuestDone(true);

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

        player.getRelationWithHarvey().increaseFriendshipLevel();
        if (isRewardTwice) {
            player.getRelationWithHarvey().increaseFriendshipLevel();
        }

        GameSessionController.getInstance().getGame(gameId).getNPCByType(NPCType.Harvey).setSecondQuestDone(true);
        return new Result(true,"Quest done.");
    }

    public static Result doThirdQuest(int gameId,Player player,boolean isRewardTwice) {

        boolean isWineAvailable = false;

        for (Ingredient ingredient :
            player.getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof ArtisanGood) {
                if (((ArtisanGood) ingredient).getType().equals(ArtisanGoodType.Wine)) {
                    int value =
                        player.getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                    if (value > 0) {
                        player.getBackpack().removeIngredients(ingredient, 1);
                        isWineAvailable = true;
                        break;
                    }
                }
            }
        }

        if (!isWineAvailable) {
            return new Result(false,"You don't have enough stock for this quest.\n(You need at least a bottle of wine)");
        }

        if (isRewardTwice) {
            player.getBackpack().addIngredients(Food.Salad,5);
        }
        player.getBackpack().addIngredients(Food.Salad,5);

        GameSessionController.getInstance().getGame(gameId).getNPCByType(NPCType.Harvey).setThirdQuestDone(true);

        return new Result(true,"Quest done.");
    }

}
