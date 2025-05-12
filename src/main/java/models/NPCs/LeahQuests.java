package models.NPCs;

import models.animals.Fish;
import models.animals.FishType;
import models.app.App;
import models.manuFactor.Ingredient;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.manuFactor.artisanGoods.ArtisanGoodType;
import models.mapInfo.NpcHome;
import models.mapInfo.Wood;
import models.recipes.CookingRecipe;
import models.userInfo.Coin;

import java.util.ArrayList;
import java.util.Arrays;

public class LeahQuests {
    private static final ArrayList<String> questsNames = new ArrayList<>(Arrays.asList("Delivery of a gold bar", "Delivery " +
            "of a salmon", "Delivery of 200 pieces of wood"));

    public static ArrayList<String> getQuestsNames() {
        return questsNames;
    }

    public static boolean doFirstQuest(boolean isRewardTwice) {

        boolean isGoldBarAvailable = false;

        for (Ingredient ingredient :
                App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof ArtisanGood) {
                if (((ArtisanGood) ingredient).getType().equals(ArtisanGoodType.GoldBar)) {
                    int value =
                            App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().get(ingredient);
                    if (value > 0) {
                        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(ingredient,
                                value - 1);
                        isGoldBarAvailable = true;
                        break;
                    }
                }
            }
        }

        if (!isGoldBarAvailable) {
            return false;
        }

        for (Ingredient ingredient :
                App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Coin) {
                int value =
                        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().get(ingredient);
                if (isRewardTwice) {
                    App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(ingredient,
                            value + 1000);
                } else {
                    App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(ingredient,
                            value + 500);
                }
            }
        }

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Leah)) {
                home.getNpc().setFirstQuestDone(true);
                break;
            }
        }

        return true;
    }

    public static boolean doSecondQuest(boolean isRewardTwice) {

        boolean isSalmonAvailable = false;

        for (Ingredient ingredient : App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Fish) {
                if (((Fish) ingredient).getType().equals(FishType.Salmon)) {
                    int value = App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().get(ingredient);
                    if (value > 0) {
                        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(ingredient, value-1);
                        isSalmonAvailable = true;
                        break;
                    }
                }
            }
        }

        if (!isSalmonAvailable) {
            return false;
        }

        App.getGame().getCurrentPlayingPlayer().getBackpack().getCookingRecipes().add(CookingRecipe.SalmonDinner);

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Leah)) {
                home.getNpc().setSecondQuestDone(true);
                break;
            }
        }

        return true;
    }


    public static boolean doThirdQuest(boolean isRewardTwice) {

        boolean are200WoodAvailable = false;

        for (Ingredient ingredient :
                App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Wood) {
                int value =
                        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().get(ingredient);
                if (value >= 200) {
                    App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(ingredient,
                            value - 200);
                    are200WoodAvailable = true;
                    break;
                }
            }
        }

        if (!are200WoodAvailable) {
            return false;
        }

        // we don't need the reward in our app, so we don't add it to player inventory

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Leah)) {
                home.getNpc().setThirdQuestDone(true);
                break;
            }
        }

        return true;
    }

}
