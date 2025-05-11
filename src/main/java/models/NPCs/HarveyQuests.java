package models.NPCs;

import models.app.App;
import models.manuFactor.Ingredient;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.manuFactor.artisanGoods.ArtisanGoodType;
import models.mapInfo.NpcHome;
import models.recipes.CookingRecipe;
import models.userInfo.Coin;

import java.util.ArrayList;
import java.util.Arrays;

public class HarveyQuests {

    private final ArrayList<String> questsNames = new ArrayList<>(Arrays.asList("Delivery of 12 desired plants",
            "Delivery of a salmon", "Delivery of a bottle of wine"));

    public ArrayList<String> getQuestsNames() {
        return questsNames;
    }

    public static boolean doFirstQuest(boolean isRewardTwice) {

        boolean are12PlantAvailable = false;
        //TODO : search for plant

        if (!are12PlantAvailable) {
            return false;
        }

        for (Ingredient ingredient :
                App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Coin) {
                int value =
                        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().get(ingredient);
                if (isRewardTwice) {
                    App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(ingredient,
                            value + 1500);
                } else {
                    App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(ingredient,
                            value + 750);
                }
            }
        }

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Harvey)) {
                home.getNpc().setFirstQuestDone(true);
                break;
            }
        }

        return true;
    }

    public static boolean doSecondQuest(boolean isRewardTwice) {

        boolean isSalmonAvailable = false;
        //TODO : search for salmon

        if (!isSalmonAvailable) {
            return false;
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
        return true;
    }

    public static boolean doThirdQuest(boolean isRewardTwice) {

        boolean isWineAvailable = false;

        for (Ingredient ingredient :
                App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof ArtisanGood) {
                if (((ArtisanGood) ingredient).getType().equals(ArtisanGoodType.Wine)) {
                    int value =
                            App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().get(ingredient);
                    if (value > 0) {
                        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(ingredient,
                                value - 1);
                        isWineAvailable = true;
                        break;
                    }
                }
            }
        }

        if (!isWineAvailable) {
            return false;
        }

        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(CookingRecipe.Salad,
                App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(CookingRecipe.Salad, 0) + 5);
        if (isRewardTwice) {
            App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().put(CookingRecipe.Salad,
                    App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(CookingRecipe.Salad, 0) + 5);
        }

        //TODO : check for salad


        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Harvey)) {
                home.getNpc().setThirdQuestDone(true);
                break;
            }
        }

        return true;
    }

}
