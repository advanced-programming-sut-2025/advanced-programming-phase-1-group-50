package com.stardew.controller.NPCController;

import com.stardew.models.NPCs.*;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.mapInfo.NpcHome;
import com.stardew.models.stores.Sellable;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class NPCController {

    public Result meetNPC(Matcher matcher) {

        NpcHome home = null;

        switch (matcher.group("NPCname")) {
            case "Abigail" -> {

                home = App.getGame().getMap().getNpcHomes().get(0);

                if (App.getGame().getMap().isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), home)) {

                    NPCFriendshipLevel temp =
                        App.getGame().getCurrentPlayingPlayer().getRelationWithAbigail().getNpcFriendshipLevel();

                    if (App.getGame().getCurrentPlayingPlayer().getRelationWithAbigail().isFirstTimeToSpeakWithNPC()) {
                        App.getGame().getCurrentPlayingPlayer().getRelationWithAbigail().setFirstTimeToSpeakWithNPC(false);
                        App.getGame().getCurrentPlayingPlayer().getRelationWithAbigail().increaseNumericalFriendShipLevel(20);
                    }

                    return new Result(true, home.getNpc().getDialogue(temp));

                } else {

                    return new Result(false, "You must be near the NPCHome");

                }
            }
            case "Sebastian" -> {

                home = App.getGame().getMap().getNpcHomes().get(4);

                if (App.getGame().getMap().isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), home)) {

                    NPCFriendshipLevel temp =
                        App.getGame().getCurrentPlayingPlayer().getRelationWithSebastian().getNpcFriendshipLevel();

                    if (App.getGame().getCurrentPlayingPlayer().getRelationWithSebastian().isFirstTimeToSpeakWithNPC()) {
                        App.getGame().getCurrentPlayingPlayer().getRelationWithSebastian().setFirstTimeToSpeakWithNPC(false);
                        App.getGame().getCurrentPlayingPlayer().getRelationWithSebastian().increaseNumericalFriendShipLevel(20);
                    }

                    return new Result(true, home.getNpc().getDialogue(temp));

                } else {

                    return new Result(false, "You must be near the NPCHome");

                }
            }
            case "Leah" -> {

                home = App.getGame().getMap().getNpcHomes().get(3);

                if (App.getGame().getMap().isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), home)) {

                    NPCFriendshipLevel temp =
                        App.getGame().getCurrentPlayingPlayer().getRelationWithLeah().getNpcFriendshipLevel();

                    if (App.getGame().getCurrentPlayingPlayer().getRelationWithLeah().isFirstTimeToSpeakWithNPC()) {
                        App.getGame().getCurrentPlayingPlayer().getRelationWithLeah().setFirstTimeToSpeakWithNPC(false);
                        App.getGame().getCurrentPlayingPlayer().getRelationWithLeah().increaseNumericalFriendShipLevel(20);
                    }

                    return new Result(true, home.getNpc().getDialogue(temp));

                } else {

                    return new Result(false, "You must be near the NPCHome");

                }
            }
            case "Robin" -> {

                home = App.getGame().getMap().getNpcHomes().get(2);

                if (App.getGame().getMap().isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), home)) {

                    NPCFriendshipLevel temp =
                        App.getGame().getCurrentPlayingPlayer().getRelationWithRobin().getNpcFriendshipLevel();

                    if (App.getGame().getCurrentPlayingPlayer().getRelationWithRobin().isFirstTimeToSpeakWithNPC()) {
                        App.getGame().getCurrentPlayingPlayer().getRelationWithRobin().setFirstTimeToSpeakWithNPC(false);
                        App.getGame().getCurrentPlayingPlayer().getRelationWithRobin().increaseNumericalFriendShipLevel(20);
                    }

                    return new Result(true, home.getNpc().getDialogue(temp));

                } else {

                    return new Result(false, "You must be near the NPCHome");

                }
            }
            case "Harvey" -> {

                home = App.getGame().getMap().getNpcHomes().get(1);

                if (App.getGame().getMap().isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), home)) {

                    NPCFriendshipLevel temp =
                        App.getGame().getCurrentPlayingPlayer().getRelationWithHarvey().getNpcFriendshipLevel();

                    if (App.getGame().getCurrentPlayingPlayer().getRelationWithHarvey().isFirstTimeToSpeakWithNPC()) {
                        App.getGame().getCurrentPlayingPlayer().getRelationWithHarvey().setFirstTimeToSpeakWithNPC(false);
                        App.getGame().getCurrentPlayingPlayer().getRelationWithHarvey().increaseNumericalFriendShipLevel(20);
                    }

                    return new Result(true, home.getNpc().getDialogue(temp));

                } else {

                    return new Result(false, "You must be near the NPCHome");

                }
            }
        }

        return new Result(false, "Not such NPC");

    }

    public static Result giftToNPC(String productName, NPC npc) {
        RelationWithNPC relation = switch (npc.getType()) {
            case NPCType.Abigail -> App.getGame().getCurrentPlayingPlayer().getRelationWithAbigail();
            case NPCType.Harvey -> App.getGame().getCurrentPlayingPlayer().getRelationWithHarvey();
            case NPCType.Robin -> App.getGame().getCurrentPlayingPlayer().getRelationWithRobin();
            case NPCType.Leah -> App.getGame().getCurrentPlayingPlayer().getRelationWithLeah();
            case Sebastian -> App.getGame().getCurrentPlayingPlayer().getRelationWithSebastian();
        };

        if (relation == null) {
            return new Result(false, "Not such NPC");
        }

        if (npc.isFavoriteGift((Ingredient) Sellable.getSellableByName(productName))) {
            relation.increaseNumericalFriendShipLevel(200);
        }

        if (relation.isFirstTimeGiftToNPC()) {
            relation.increaseNumericalFriendShipLevel(50);
            relation.setFirstTimeGiftToNPC(false);
        }

        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients((Ingredient) Sellable.getSellableByName(productName), 1);

        return new Result(true, "Your gift has been received");
    }

    public static RelationWithNPC getRelationWithNPC(NPC npc) {

        return switch (npc.getType()) {
            case NPCType.Abigail -> App.getGame().getCurrentPlayingPlayer().getRelationWithAbigail();
            case NPCType.Harvey -> App.getGame().getCurrentPlayingPlayer().getRelationWithHarvey();
            case NPCType.Robin -> App.getGame().getCurrentPlayingPlayer().getRelationWithRobin();
            case NPCType.Leah -> App.getGame().getCurrentPlayingPlayer().getRelationWithLeah();
            case Sebastian -> App.getGame().getCurrentPlayingPlayer().getRelationWithSebastian();
        };

    }

    public static ArrayList<String> getQuestsList (NPC npc) {
        return switch (npc.getType()) {
            case NPCType.Abigail -> AbigailQuests.getQuestsNames();
            case NPCType.Harvey -> HarveyQuests.getQuestsNames();
            case NPCType.Robin -> RobinQuests.getQuestsNames();
            case NPCType.Leah -> LeahQuests.getQuestsNames();
            case Sebastian -> SebastianQuests.getQuestsNames();
        };
    }

    public static Result doQuest(NPC npc , int index) {

        if (index <= 0 || index >= 4) {
            return new Result(false, "Invalid index");
        }

        RelationWithNPC relation = getRelationWithNPC(npc);
        boolean isRewardTwice = relation.getNpcFriendshipLevel().equals(NPCFriendshipLevel.LevelTwo);

        if (index == 1) {

            if (npc.doFirstQuest(isRewardTwice)) {
                return new Result(true, "Quest done");
            } else {
                return new Result(false, "You don't have enough stock for this quest");
            }

        } else if (index == 2) {

            if (npc.doSecondQuest(isRewardTwice)) {
                return new Result(true, "Quest done");
            } else {
                return new Result(false, "You don't have enough stock for this quest");
            }

        } else if (index == 3) {
            if (npc.doThirdQuest(isRewardTwice)) {
                return new Result(true, "Quest done");
            } else {
                return new Result(false, "You don't have enough stock for this quest");
            }
        }

        return new Result(false, "Not such quest");

    }

}

