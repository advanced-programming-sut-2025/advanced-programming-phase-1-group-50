package controller.NPCController;

import models.NPCs.NPCFriendshipLevel;
import models.NPCs.NPCType;
import models.NPCs.RelationWithNPC;
import models.Result;
import models.app.App;
import models.mapInfo.NpcHome;

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

                home = App.getGame().getMap().getNpcHomes().get(1);

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

                home = App.getGame().getMap().getNpcHomes().get(2);

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

                home = App.getGame().getMap().getNpcHomes().get(3);

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

                home = App.getGame().getMap().getNpcHomes().get(4);

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

    public Result giftToNPC(Matcher matcher) {
        return null;
    }

    public Result friendShipNPCList() {

        String message = "Abigail: ";
        message += App.getGame().getCurrentPlayingPlayer().getRelationWithAbigail().getNpcFriendshipLevel().toString();
        message += "    ";
        message += App.getGame().getCurrentPlayingPlayer().getRelationWithAbigail().getNumericalFriendShipLevel();
        message += "\n";

        message += "Sebastian: ";
        message += App.getGame().getCurrentPlayingPlayer().getRelationWithSebastian().getNpcFriendshipLevel().toString();
        message += "    ";
        message += App.getGame().getCurrentPlayingPlayer().getRelationWithSebastian().getNumericalFriendShipLevel();

        message += "Leah: ";
        message += App.getGame().getCurrentPlayingPlayer().getRelationWithLeah().getNpcFriendshipLevel().toString();
        message += "    ";
        message += App.getGame().getCurrentPlayingPlayer().getRelationWithLeah().getNumericalFriendShipLevel();

        message += "Robin: ";
        message += App.getGame().getCurrentPlayingPlayer().getRelationWithRobin().getNpcFriendshipLevel().toString();
        message += "    ";
        message += App.getGame().getCurrentPlayingPlayer().getRelationWithRobin().getNumericalFriendShipLevel();

        message += "Harvey: ";
        message += App.getGame().getCurrentPlayingPlayer().getRelationWithHarvey().getNpcFriendshipLevel().toString();
        message += "    ";
        message += App.getGame().getCurrentPlayingPlayer().getRelationWithHarvey().getNumericalFriendShipLevel();

        return new Result(true, message);

    }

    public Result questsList() {

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {
            if (App.getGame().getMap().isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), home)) {
                return new Result(true, home.getNpc().showQuestLists());
            }
        }

        return new Result(false, "you must be near the NPCHome");

    }

    public Result finishingQuest(Matcher matcher) {

        int index = Integer.parseInt(matcher.group("index"));

        if (index <= 0 || index >= 4) {
            return new Result(false, "Invalid index");
        }

        for (NpcHome home : App.getGame().getMap().getNpcHomes()) {

            if (App.getGame().getMap().isAroundPlaceable(App.getGame().getCurrentPlayingPlayer(), home)) {

                RelationWithNPC relation = null;

                if (home.getNpc().getType().equals(NPCType.Abigail)) {

                    relation = App.getGame().getCurrentPlayingPlayer().getRelationWithAbigail();


                } else if (home.getNpc().getType().equals(NPCType.Sebastian)) {

                    relation = App.getGame().getCurrentPlayingPlayer().getRelationWithSebastian();

                } else if (home.getNpc().getType().equals(NPCType.Leah)) {

                    relation = App.getGame().getCurrentPlayingPlayer().getRelationWithLeah();

                } else if (home.getNpc().getType().equals(NPCType.Robin)) {

                    relation = App.getGame().getCurrentPlayingPlayer().getRelationWithRobin();

                } else if (home.getNpc().getType().equals(NPCType.Harvey)) {

                    relation = App.getGame().getCurrentPlayingPlayer().getRelationWithHarvey();

                }

                boolean isRewardTwice = relation.getNpcFriendshipLevel().equals(NPCFriendshipLevel.LevelTwo);

                if (index == 1) {
                    if (home.getNpc().isFirstQuestDone()) {
                        return new Result(false, "this quest is already done");
                    }
                    if (home.getNpc().doFirstQuest(isRewardTwice)) {
                        return new Result(true, "Quest done");
                    } else {
                        return new Result(false, "You don't have enough stock for this quest");
                    }

                } else if (index == 2) {
                    if (home.getNpc().isSecondQuestDone()) {
                        return new Result(false, "this quest is already done");
                    }
                    if (relation.isSecondQuestLocked()) {
                        return new Result(false, "This quest is locked");
                    }
                    if (home.getNpc().doSecondQuest(isRewardTwice)) {
                        return new Result(true, "Quest done");
                    } else {
                        return new Result(false, "You don't have enough stock for this quest");
                    }

                } else if (index == 3) {
                    if (home.getNpc().isThirdQuestDone()) {
                        return new Result(false, "this quest is already done");
                    }
                    if (relation.isThirdQuestLocked()) {
                        return new Result(false, "This quest is locked");
                    }
                    if (home.getNpc().doThirdQuest(isRewardTwice)) {
                        return new Result(true, "Quest done");
                    } else {
                        return new Result(false, "You don't have enough stock for this quest");
                    }
                }

            }

        }

        return new Result(false, "You must be near the NPCHome");

    }

}

