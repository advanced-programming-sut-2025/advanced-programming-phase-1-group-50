package com.stardew.model.NPCs;

import java.util.ArrayList;
import java.util.Arrays;

public class AbigailQuests {
    private static final ArrayList<String> questsNames = new ArrayList<>(Arrays.asList("Delivery of a gold bar",
        "Delivery " +
            "of a pumpkin", "Delivery of 50 pieces of wheat"));

    public static ArrayList<String> getQuestsNames() {
        return questsNames;
    }

//
//    public static Result doFirstQuest(boolean isRewardTwice) {
//
//        boolean isGoldBarAvailable = false;
//
//        for (Ingredient ingredient :
//            App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
//            if (ingredient instanceof ArtisanGood) {
//                if (((ArtisanGood) ingredient).getType().equals(ArtisanGoodType.GoldBar)) {
//                    int value =
//                        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ingredient, 0);
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
//            return new Result(false, "You don't have enough stock for this quest.\n(You need at least 1 gold bar)");
//        }
//
//        App.getGame().getCurrentPlayingPlayer().getRelationWithAbigail().increaseFriendshipLevel();
//        if (isRewardTwice) {
//            App.getGame().getCurrentPlayingPlayer().getRelationWithAbigail().increaseFriendshipLevel();
//        }
//
//        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
//            if (home.getNpc().getType().equals(NPCType.Abigail)) {
//                home.getNpc().setFirstQuestDone(true);
//                break;
//            }
//        }
//
//        return new Result(true, "Quest done.");
//    }
//
//    public static Result doSecondQuest(boolean isRewardTwice) {
//
//        boolean isPumpkinAvailable = false;
//
//        for (Ingredient ingredient :
//            App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
//            if (ingredient instanceof Crop) {
//                if (((Crop) ingredient).getType().equals(CropType.Pumpkin)) {
//                    int value =
//                        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ingredient, 0);
//                    if (value > 0) {
//                        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(ingredient, 1);
//                        isPumpkinAvailable = true;
//                        break;
//                    }
//                }
//            }
//        }
//
//        if (!isPumpkinAvailable) {
//            return new Result(false, "You don't have enough stock for this quest.\n(You need at least 1 pumpkin)");
//        }
//
//        if (isRewardTwice) {
//            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(), 500);
//        }
//        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(), 500);
//
//        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
//            if (home.getNpc().getType().equals(NPCType.Abigail)) {
//                home.getNpc().setSecondQuestDone(true);
//                break;
//            }
//        }
//
//        return new Result(true, "Quest done.");
//    }
//
//    public static Result doThirdQuest(boolean isRewardTwice) {
//
//        boolean are50WheatAvailable = false;
//
//        for (Ingredient ingredient :
//            App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
//            if (ingredient instanceof Crop) {
//                if (((Crop) ingredient).getType().equals(CropType.Wheat)) {
//                    int value =
//                        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ingredient, 0);
//                    if (value >= 50) {
//                        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(ingredient, 50);
//                        are50WheatAvailable = true;
//                        break;
//                    }
//                }
//            }
//        }
//
//        if (!are50WheatAvailable) {
//            return new Result(false, "You don't have enough stock for this quest.\n(You need at least 50 pieces of " +
//                "wheat)");
//        }
//
//        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
//            if (home.getNpc().getType().equals(NPCType.Abigail)) {
//                home.getNpc().setThirdQuestDone(true);
//                break;
//            }
//        }
//
//        return new Result(true, "Quest done.");
//
//    }
}
