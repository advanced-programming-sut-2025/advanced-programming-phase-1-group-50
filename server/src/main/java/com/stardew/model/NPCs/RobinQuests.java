package com.stardew.model.NPCs;

import com.stardew.controller.GameSessionController;
import com.stardew.model.Result;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.Wood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.mapInfo.manuFactor.BeeHouse;
import com.stardew.model.userInfo.Coin;
import com.stardew.model.userInfo.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class RobinQuests {

    public static Result doFirstQuest(int gameId, Player player, boolean isRewardTwice) {

        boolean are80WoodAvailable = false;

        for (Ingredient ingredient :
            player.getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Wood) {
                int value =
                    player.getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                if (value >= 80) {
                   player.getBackpack().removeIngredients(ingredient, 80);
                    are80WoodAvailable = true;
                    break;
                }
            }
        }

        if (!are80WoodAvailable) {
            return new Result(false , "You don't have enough stock for this quest.\n(You need at least 80 pieces of wood)");
        }

        if (isRewardTwice) {
            player.getBackpack().addIngredients(new Coin(),1000);
        }
        player.getBackpack().addIngredients(new Coin(),1000);

        GameSessionController.getInstance().getGame(gameId).getNPCByType(NPCType.Robin).setFirstQuestDone(true);
        return new Result(true,"Quest done.");
    }

    public static Result doSecondQuest(int gameId,Player player,boolean isRewardTwice) {

        boolean are10IronBarAvailable = false;

        for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof ArtisanGood) {
                if (((ArtisanGood) ingredient).getType().equals(ArtisanGoodType.IronBar)) {
                    int value = player.getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                    if ( value >= 10) {
                        player.getBackpack().removeIngredients(ingredient, 10);
                        are10IronBarAvailable= true;
                        break;
                    }
                }
            }
        }

        if (!are10IronBarAvailable) {
            return new Result(false, "You don't have enough stock for this quest.\n(You need at least 10 Iron bar)");
        }

//        int numberOfRepetitions = (isRewardTwice ? 3 : 6);
//
//        for (int i=0; i<numberOfRepetitions; i++) {
//            player.getBackpack().addArtisanMachine(new BeeHouse());
//        } // TODO

        GameSessionController.getInstance().getGame(gameId).getNPCByType(NPCType.Robin).setSecondQuestDone(true);

        return new Result(true,"Quest done.");
    }

    public static Result doThirdQuest(int gameId,Player player,boolean isRewardTwice) {

        boolean are1000WoodAvailable = false;

        for (Ingredient ingredient :
            player.getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Wood) {
                int value =
                    player.getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                if (value >= 1000) {
                    player.getBackpack().removeIngredients(ingredient, 1000);
                    are1000WoodAvailable = true;
                    break;
                }
            }
        }

        if (!are1000WoodAvailable) {
            return new Result(false , "You don't have enough stock for this quest.\n(You need at least 1000 pieces of wood)");
        }

        if (isRewardTwice) {
            player.getBackpack().addIngredients(new Coin(),25000);
        }
        player.getBackpack().addIngredients(new Coin(),25000);

        GameSessionController.getInstance().getGame(gameId).getNPCByType(NPCType.Robin).setThirdQuestDone(true);

        return new Result(true,"Quest done.");
    }
}
