package com.stardew.controller.PlayersRealtionController;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.models.BetweenPlayersGift;
import com.stardew.models.Bouquet;
import com.stardew.models.Notification.MarriageRequest;
import com.stardew.models.Notification.Notification;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.stores.Sellable;
import com.stardew.models.userInfo.*;
import com.stardew.view.InPersonPlayersRelationsWindows.RespondMarriageWindow;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

public class PlayersRelationController {

    public static RelationWithPlayers getFriendshipLevelsWithPlayers(Player player) {

        if (player == null || App.getGame().getCurrentPlayingPlayer().equals(player)) {
            return null;
        }

        for (Set<Player> key : App.getGame().getRelationsBetweenPlayers().relationNetwork.keySet()) {
            if (key.contains(player) && key.contains(App.getGame().getCurrentPlayingPlayer())) {
                return App.getGame().getRelationsBetweenPlayers().relationNetwork.get(key);
            }
        }

        return null;
    }

    public Result TalkToPlayer(Matcher matcher) {

        Player receiver = null;

        for (Player p : App.getGame().getPlayers()) {
            if (p.getUsername().equals(matcher.group("username"))) {
                receiver = p;
                break;
            }
        }

        if (receiver == null) {
            return new Result(false, "Player not found");
        }

        if (receiver.equals(App.getGame().getCurrentPlayingPlayer())) {
            return new Result(false, "you can't choose yourself");
        }

        int distanceSquare =
            (App.getGame().getCurrentPlayingPlayer().getPosition().getX() - receiver.getPosition().getX()) *
                (App.getGame().getCurrentPlayingPlayer().getPosition().getX() - receiver.getPosition().getX()) +
                (App.getGame().getCurrentPlayingPlayer().getPosition().getY() - receiver.getPosition().getY()) *
                    (App.getGame().getCurrentPlayingPlayer().getPosition().getY() - receiver.getPosition().getY());

        if (distanceSquare > 2) {
            return new Result(false, "You are too far away");
        }

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(receiver);
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);
        if (!tempRelation.HaveTalkedToday()) {
            tempRelation.changeXp(20);
            tempRelation.setHaveTalkedToday(true);
        }

        if (tempRelation.isMarriage()) {
            App.getGame().getCurrentPlayingPlayer().addEnergy(50);
            receiver.addEnergy(50);
        }

        tempRelation.addDialogue(new DialoguesBetweenPlayers(App.getGame().getCurrentPlayingPlayer(), receiver,
            matcher.group("message")));
        tempNetwork.relationNetwork.put(lookUpKey, tempRelation);
        receiver.addNotification(new Notification(matcher.group("message"), App.getGame().getCurrentPlayingPlayer()));

        return new Result(true, "");
    }

    public Result talkHistory(Matcher matcher) {

        Player temp = null;

        for (Player p : App.getGame().getPlayers()) {
            if (p.getUsername().equals(matcher.group("username"))) {
                temp = p;
                break;
            }
        }

        if (temp == null) {
            return new Result(false, "Player not found");
        }

        if (temp.equals(App.getGame().getCurrentPlayingPlayer())) {
            return new Result(false, "you can't choose yourself");
        }

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(temp);
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);

        String message = "Talking History:";
        message += tempRelation.getTalkHistory();

        return new Result(true, message);

    }

    public Result GiftList() {


        StringBuilder message = new StringBuilder("GiftList:");

        for (BetweenPlayersGift gift : App.getGame().getGifts()) {
            if (gift.getReceiver().equals(App.getGame().getCurrentPlayingPlayer())) {
                message.append("\n");
                message.append(gift);
            }

        }
        return new Result(true, message.toString());
    }

    public static boolean canRateGift(BetweenPlayersGift gift) {
        return (gift.getReceiver().equals(App.getGame().getCurrentPlayingPlayer())) && (!gift.isRated());
    }

    public static void rateGift(BetweenPlayersGift gift, int rate) {
        gift.setRate(rate);
        gift.setRated();

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(gift.getReceiver());
        lookUpKey.add(gift.getSender());

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);
        if (!tempRelation.HaveGaveGiftToday()) {
            tempRelation.changeXp((rate - 3) * 30 + 15);
        }
        tempRelation.setHaveGaveGiftToday(true);
        tempNetwork.relationNetwork.put(lookUpKey, tempRelation);

    }

    public static Result canHug(Player otherPlayer) {
        if (otherPlayer == null || App.getGame().getCurrentPlayingPlayer().equals(otherPlayer)) {
            return new Result(false, "Invalid player");
        }

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
        lookUpKey.add(otherPlayer);

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);

        if (!tempRelation.canHug()) {
            return new Result(false, "your friendship level must be at least two");
        }

        return new Result(true, "");

    }

    public static void hug(Player otherPlayer) {

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
        lookUpKey.add(otherPlayer);

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);

        if (tempRelation.canHug()) {
            if (!tempRelation.HaveHuggedToday()) {
                tempRelation.setHaveHuggedToday(true);
                tempRelation.changeXp(60);
            }

            if (tempRelation.isMarriage()) {
                App.getGame().getCurrentPlayingPlayer().addEnergy(50);
                otherPlayer.addEnergy(50);
            }
        }
    }

    public static Result canGiveFlower(Player otherPlayer) {
        if (otherPlayer == null || App.getGame().getCurrentPlayingPlayer().equals(otherPlayer)) {
            return new Result(false, "Invalid player!");
        }

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(new Bouquet(),
            0) == 0) {
            return new Result(false, "You don't have any bouquet!");
        }

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
        lookUpKey.add(otherPlayer);

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);

        if (!tempRelation.canGiveFlower()) {
            return new Result(false, "you can't give flower at this friendship level");
        }

        return new Result(true, "");

    }

    public static void giveFlower(Player otherPlayer) {

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
        lookUpKey.add(otherPlayer);

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);

        tempRelation.setGaveFlower();
        tempRelation.setHaveGaveFlowerToday(true);
        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(new Bouquet(), 1);
        otherPlayer.getBackpack().addIngredients(new Bouquet(), 1);
        if (tempRelation.isMarriage()) {
            App.getGame().getCurrentPlayingPlayer().addEnergy(50);
            otherPlayer.addEnergy(50);
        }
        tempRelation.changeXp(0);

    }

    public static Result canAskMarriage(Player otherPlayer) {
        if (otherPlayer == null || App.getGame().getCurrentPlayingPlayer().equals(otherPlayer)) {
            return new Result(false, "Invalid player!");
        }

        if (App.getGame().getCurrentPlayingPlayer().getCurrentUser().getGender().equals(Gender.Female) || otherPlayer.getCurrentUser().getGender().equals(Gender.Male)) {
            return new Result(false , "Gender conflict!");
        }

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
        lookUpKey.add(otherPlayer);

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);

        if (tempRelation.isMarriage()) {
            return new Result(false , "She's your wife!!!");
        }

        if (!tempRelation.canRequestMarriage()) {
            return new Result(false, "You can't request marriage at this friendship level!");
        }

        if (otherPlayer.isMarried()) {
            return new Result(false , "She's married!");
        }

        for (Player player : App.getGame().getPlayers()) {
            for (Notification notification : player.getNotifications()) {
                if (notification instanceof MarriageRequest) {
                    if (!notification.isChecked() && notification.getSender().equals(App.getGame().getCurrentPlayingPlayer())) {
                        return new Result(false , "Del ke nist, karvansarast!");
                    }
                }
            }
        }

        for (Notification notification : otherPlayer.getNotifications()) {
            if (notification instanceof MarriageRequest && !notification.isChecked()) {
                return new Result(false , "Vaisa to saf");
            }
        }

        return new Result(true,"");
    }

    public static void askMarriage(Player otherPlayer) {
        otherPlayer.addNotification(new MarriageRequest("You are my soulmate. Every day with you feels like a dream. Will you stay by my side forever?", App.getGame().getCurrentPlayingPlayer()));
    }

    public static void checkForMarriageRequest(Stage stage) {
        MarriageRequest temp = null;

        for (Notification n : App.getGame().getCurrentPlayingPlayer().getNotifications()) {
            if (n instanceof MarriageRequest) {
                if (!n.isChecked()) {
                    temp = (MarriageRequest) n;
                    break;
                }
            }
        }

        if (temp == null) {
            return;
        }

        stage.addActor(new RespondMarriageWindow(stage, temp));
    }

    public static void respondMarriage(MarriageRequest temp,boolean accepted) {
        temp.setChecked(true);

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
        lookUpKey.add(temp.getSender());

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);

        if (accepted) {

            App.getGame().getCurrentPlayingPlayer().setMarried(true);
            temp.getSender().setMarried(true);

            tempRelation.setMarriage();
            tempRelation.setFriendshipLevel(FriendshipLevelsWithPlayers.LevelFour);

        } else {

            tempRelation.setFriendshipLevel(FriendshipLevelsWithPlayers.LevelZero);
            temp.getSender().setRemainingNumsAfterMarriageRequestDenied(7);
        }
    }

    public static Result sendGiftToPlayer(String productName, int quantity, Player receiver) {

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
        lookUpKey.add(receiver);

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);

        App.getGame().addGiftsIndex();
        BetweenPlayersGift tempGift = new BetweenPlayersGift(Sellable.getSellableByName(productName),
            App.getGame().getCurrentPlayingPlayer(), receiver, App.getGame().getGiftIndex());
        App.getGame().addToGifts(tempGift);

        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients((Ingredient) Sellable.getSellableByName(productName), quantity);
        receiver.getBackpack().addIngredients((Ingredient) Sellable.getSellableByName(productName), quantity);

        receiver.addNotification(new Notification("you have received a gift", App.getGame().getCurrentPlayingPlayer()));


        if (tempRelation.isMarriage()) {
            App.getGame().getCurrentPlayingPlayer().addEnergy(50);
            receiver.addEnergy(50);
        }

        return new Result(true, "He/She received your gift with id " + App.getGame().getGiftIndex());
    }
}
