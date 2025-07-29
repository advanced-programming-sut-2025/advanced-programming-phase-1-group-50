package com.stardew.model.NPCs;

import com.stardew.model.Result;
import com.stardew.model.gameApp.App;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.mapInfo.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;

public class LeahQuests {
    private static final ArrayList<String> questsNames = new ArrayList<>(Arrays.asList("Delivery of a gold bar", "Delivery " +
        "of a salmon", "Delivery of 200 pieces of wood"));

    public static ArrayList<String> getQuestsNames() {
        return questsNames;
    }

//    public static Result doFirstQuest(boolean isRewardTwice) {
//
//        boolean isGoldBarAvailable = false;
//
//        for (Ingredient ingredient :
//            App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
//            if (ingredient instanceof ArtisanGood) {
//                if (((ArtisanGood) ingredient).getType().equals(ArtisanGoodType.GoldBar)) {
//                    int value =
//                        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
//                    if (value > 0) {
//                        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(ingredient, 1);
//                        isGoldBarAvailable = true;
//                        break;
//                    }
//                }
//            }
//        }
//
//        if (!isGoldBarAvailable) {
//            return new Result(false,"You don't have enough stock for this quest.\n(You need at least a gold bar)");
//        }
//
//        if (isRewardTwice) {
//            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(),500);
//        }
//        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(),500);
//
//        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
//            if (home.getNpc().getType().equals(NPCType.Leah)) {
//                home.getNpc().setFirstQuestDone(true);
//                break;
//            }
//        }
//
//        return new Result(true,"Quest done.");
//    }
//
//    public static Result doSecondQuest(boolean isRewardTwice) {
//
//        boolean isSalmonAvailable = false;
//
//        for (Ingredient ingredient : App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
//            if (ingredient instanceof Fish) {
//                if (((Fish) ingredient).getType().equals(FishType.Salmon)) {
//                    int value = App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
//                    if (value > 0) {
//                        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(ingredient, 1);
//                        isSalmonAvailable = true;
//                        break;
//                    }
//                }
//            }
//        }
//
//        if (!isSalmonAvailable) {
//            return new Result(false,"You don't have enough stock for this quest.\n(You need at least a salmon)");
//        }
//
//        App.getGame().getCurrentPlayingPlayer().getBackpack().getCookingRecipes().add(CookingRecipe.SalmonDinner);
//
//        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
//            if (home.getNpc().getType().equals(NPCType.Leah)) {
//                home.getNpc().setSecondQuestDone(true);
//                break;
//            }
//        }
//
//        return new Result(true,"Quest done.");
//    }


//    public static Result doThirdQuest(boolean isRewardTwice) {
//
//        boolean are200WoodAvailable = false;
//
//        for (Ingredient ingredient :
//            App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
//            if (ingredient instanceof Wood) {
//                int value =
//                    App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
//                if (value >= 200) {
//                    App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(ingredient,200);
//                    are200WoodAvailable = true;
//                    break;
//                }
//            }
//        }
//
//        if (!are200WoodAvailable) {
//            return new Result(false,"You don't have enough stock for this quest.\n(You need at least 200 pieces of wood)");
//        }

        // we don't need the reward in our app, so we don't add it to player inventory

//        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
//            if (home.getNpc().getType().equals(NPCType.Leah)) {
//                home.getNpc().setThirdQuestDone(true);
//                break;
//            }
//        }

//        return new Result(true,"Quest done.");
//    }
}
