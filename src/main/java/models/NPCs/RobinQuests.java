package models.NPCs;

import models.app.App;
import models.manuFactor.BeeHouse;
import models.manuFactor.Ingredient;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.manuFactor.artisanGoods.ArtisanGoodType;
import models.mapInfo.NpcHome;
import models.mapInfo.Wood;
import models.userInfo.Coin;

import java.util.ArrayList;
import java.util.Arrays;

public class RobinQuests{
    private static final ArrayList<String> questsNames = new ArrayList<>(Arrays.asList("Delivery of 80 pieces of wood",
            "Delivery of 10 iron bar", "Delivery of 1000 pieces of wood"));

    public static ArrayList<String> getQuestsNames() {
        return questsNames;
    }

    public static boolean doFirstQuest(boolean isRewardTwice) {

        boolean are80WoodAvailable = false;

        for (Ingredient ingredient :
                App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Wood) {
                int value =
                        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                if (value >= 80) {
                    App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(ingredient, 80);
                    are80WoodAvailable = true;
                    break;
                }
            }
        }

        if (!are80WoodAvailable) {
            return false;
        }

        if (isRewardTwice) {
            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(),1000);
        }
        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(),1000);

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Robin)) {
                home.getNpc().setFirstQuestDone(true);
                break;
            }
        }

        return true;
    }

    public static boolean doSecondQuest(boolean isRewardTwice) {

        boolean are10IronBarAvailable = false;

        for (Ingredient ingredient : App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof ArtisanGood) {
                if (((ArtisanGood) ingredient).getType().equals(ArtisanGoodType.IronBar)) {
                    int value = App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                    if ( value >= 10) {
                        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(ingredient, 10);
                        are10IronBarAvailable= true;
                        break;
                    }
                }
            }
        }

        if (!are10IronBarAvailable) {
            return false;
        }

        int numberOfRepetitions = (isRewardTwice ? 3 : 6);

        for (int i=0; i<numberOfRepetitions; i++) {
            App.getGame().getCurrentPlayingPlayer().getBackpack().addArtisanMachine(new BeeHouse());
        }


        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Robin)) {
                home.getNpc().setSecondQuestDone(true);
                break;
            }
        }

        return true;
    }

    public static boolean doThirdQuest(boolean isRewardTwice) {

        boolean are1000WoodAvailable = false;

        for (Ingredient ingredient :
                App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().keySet()) {
            if (ingredient instanceof Wood) {
                int value =
                        App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(ingredient,0);
                if (value >= 1000) {
                    App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(ingredient, 1000);
                    are1000WoodAvailable = true;
                    break;
                }
            }
        }

        if (!are1000WoodAvailable) {
            return false;
        }

        if (isRewardTwice) {
            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(),25000);
        }
        App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new Coin(),25000);

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (home.getNpc().getType().equals(NPCType.Robin)) {
                home.getNpc().setThirdQuestDone(true);
                break;
            }
        }

        return true;
    }

}
