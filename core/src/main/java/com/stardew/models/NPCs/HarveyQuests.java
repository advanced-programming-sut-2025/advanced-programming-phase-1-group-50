package com.stardew.models.NPCs;

import com.stardew.models.Result;
import com.stardew.models.animals.Fish;
import com.stardew.models.animals.FishType;
import com.stardew.models.app.App;
import com.stardew.models.cooking.Food;
import com.stardew.models.foraging.Crop;
import com.stardew.models.foraging.CropType;
import com.stardew.models.foraging.Fruit;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGoodType;
import com.stardew.models.mapInfo.NpcHome;
import com.stardew.models.userInfo.Coin;

import java.util.ArrayList;
import java.util.Arrays;

public class HarveyQuests {

    private static final ArrayList<String> questsNames = new ArrayList<>(Arrays.asList("Delivery of 12 desired plants",
            "Delivery of a salmon", "Delivery of a bottle of wine"));

    public static ArrayList<String> getQuestsNames() {
        return questsNames;
    }

    public static Result doFirstQuest(boolean isRewardTwice) {

        boolean are12PlantAvailable = false;
        for (Ingredient ingredient : App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Crop || ingredient instanceof CropType || ingredient instanceof Fruit) {
                int value = App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                if (value >= 12) {
                    App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(ingredient, 12);
                    are12PlantAvailable = true;
                    break;
                }
            }
        }

        if (!are12PlantAvailable) {
            return new Result(false,"You don't have enough stock for this quest.\n(You need at least 12 plants)");
        }

        if (isRewardTwice) {
            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(),750);
        }
        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(),750);

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Harvey)) {
                home.getNpc().setFirstQuestDone(true);
                break;
            }
        }

        return new Result(true,"Quest done.");
    }

    public static Result doSecondQuest(boolean isRewardTwice) {

        boolean isSalmonAvailable = false;

        for (Ingredient ingredient : App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Fish) {
                if (((Fish) ingredient).getType().equals(FishType.Salmon)) {
                    int value = App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                    if (value > 0) {
                        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(ingredient, 1);
                        isSalmonAvailable = true;
                        break;
                    }
                }
            }
        }

        if (!isSalmonAvailable) {
            return new Result(false,"You don't have enough stock for this quest.\n(You need at least a salmon)");
        }

        App.getGame().getCurrentPlayingPlayer().getRelationWithHarvey().increaseFriendshipLevel();
        if (isRewardTwice) {
            App.getGame().getCurrentPlayingPlayer().getRelationWithHarvey().increaseFriendshipLevel();
        }

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Harvey)) {
                home.getNpc().setSecondQuestDone(true);
                break;
            }
        }
        return new Result(true,"Quest done.");
    }

    public static Result doThirdQuest(boolean isRewardTwice) {

        boolean isWineAvailable = false;

        for (Ingredient ingredient :
                App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof ArtisanGood) {
                if (((ArtisanGood) ingredient).getType().equals(ArtisanGoodType.Wine)) {
                    int value =
                            App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                    if (value > 0) {
                        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(ingredient, 1);
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
            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(Food.Salad,5);
        }
        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(Food.Salad,5);

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Harvey)) {
                home.getNpc().setThirdQuestDone(true);
                break;
            }
        }

        return new Result(true,"Quest done.");
    }

}
