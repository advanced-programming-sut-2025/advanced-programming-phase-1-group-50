package com.stardew.controller.PlayersRealtionController;

import com.stardew.models.BetweenPlayersGift;
import com.stardew.models.Bouquet;
import com.stardew.models.Notification.MarriageRequest;
import com.stardew.models.Notification.Notification;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.stores.Sellable;
import com.stardew.models.userInfo.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;

public class PlayersRelationController {

    public Result friendships() {

        StringBuilder message = new StringBuilder("FriendShips:");

        for (Set<Player> key : App.getGame().getRelationsBetweenPlayers().relationNetwork.keySet()) {

            message.append("\n");

            Iterator<Player> iterator = key.iterator();
            Player p1 = iterator.next();
            Player p2 = iterator.next();

            message.append(p1.getUsername()).append(" and ").append(p2.getUsername()).append(": ");
            message.append(App.getGame().getRelationsBetweenPlayers().relationNetwork.get(key).toString());

        }

        return new Result(true, message.toString());

    }

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

        int distanceSquare = (App.getGame().getCurrentPlayingPlayer().getPosition().getX() - receiver.getPosition().getX()) *
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

    public Result giftRate(Matcher matcher) {

        int rate = Integer.parseInt(matcher.group("rate"));
        int id = Integer.parseInt(matcher.group("id"));
        BetweenPlayersGift tempGift = null;

        if (rate <= 0 || rate >= 6) {
            return new Result(false, "Invalid gift rate");
        }

        for (BetweenPlayersGift gift : App.getGame().getGifts()) {
            if (gift.getId() == id) {
                tempGift = gift;
                break;
            }
        }

        if (tempGift == null) {
            return new Result(false, "Gift not found");
        }

        if (!tempGift.getReceiver().equals(App.getGame().getCurrentPlayingPlayer())) {
            return new Result(false, "you can't rate this gift");
        }

        if (tempGift.isRated()) {
            return new Result(false, "you have already rated this gift");
        }

        tempGift.setRate(rate);
        tempGift.setRated();

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(tempGift.getReceiver());
        lookUpKey.add(tempGift.getSender());

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);
        if (!tempRelation.HaveGaveGiftToday()) {
            tempRelation.changeXp((rate - 3) * 30 + 15);
        }
        tempRelation.setHaveGaveGiftToday(true);
        tempNetwork.relationNetwork.put(lookUpKey, tempRelation);

        return new Result(true, "you rated this gift successfully");
    }

    public Result GiftHistory(Matcher matcher) {

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

        StringBuilder message = new StringBuilder("GiftHistory:");

        for (BetweenPlayersGift gift : App.getGame().getGifts()) {
            if (gift.getReceiver().equals(App.getGame().getCurrentPlayingPlayer()) && gift.getSender().equals(temp) ||
                    gift.getSender().equals(App.getGame().getCurrentPlayingPlayer()) && gift.getReceiver().equals(temp)) {
                message.append("\n");
                message.append(gift.toStringWithReceiver());
            }
        }

        return new Result(true, message.toString());
    }

    public Result hug(Matcher matcher) {
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

        int distanceSquare = (App.getGame().getCurrentPlayingPlayer().getPosition().getX() - temp.getPosition().getX()) *
                (App.getGame().getCurrentPlayingPlayer().getPosition().getX() - temp.getPosition().getX()) +
                (App.getGame().getCurrentPlayingPlayer().getPosition().getY() - temp.getPosition().getY()) *
                        (App.getGame().getCurrentPlayingPlayer().getPosition().getY() - temp.getPosition().getY());

        if (distanceSquare > 2) {
            return new Result(false, "You are too far away");
        }

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
        lookUpKey.add(temp);

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);

        if (tempRelation.canHug()) {
            if (!tempRelation.HaveHuggedToday()) {
                tempRelation.setHaveHuggedToday(true);
                tempRelation.changeXp(60);
            }

            if (tempRelation.isMarriage()) {
                App.getGame().getCurrentPlayingPlayer().addEnergy(50);
                temp.addEnergy(50);
            }

            return new Result(true, "masadigh mohtavaye mojremane");
        }

        return new Result(false, "your friendship level must be at least two");

    }

    public Result giveFlower(Matcher matcher) {
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

        int distanceSquare = (App.getGame().getCurrentPlayingPlayer().getPosition().getX() - temp.getPosition().getX()) *
                            (App.getGame().getCurrentPlayingPlayer().getPosition().getX() - temp.getPosition().getX()) +
                            (App.getGame().getCurrentPlayingPlayer().getPosition().getY() - temp.getPosition().getY()) *
                            (App.getGame().getCurrentPlayingPlayer().getPosition().getY() - temp.getPosition().getY());


        if (distanceSquare > 2) {
            return new Result(false, "You are too far away");

        }

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault(new Bouquet(),0) == 0) {
            return new Result(false, "You don't have Bouquet");
        }

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
        lookUpKey.add(temp);

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);

        if (tempRelation.canGiveFlower()) {
            tempRelation.setGaveFlower();
            tempRelation.setHaveGaveFlowerToday(true);
            App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients(new Bouquet(), 1);
            temp.getBackpack().addIngredients(new Bouquet(), 1);
            if (tempRelation.isMarriage()) {
                App.getGame().getCurrentPlayingPlayer().addEnergy(50);
                temp.addEnergy(50);
            }
            tempRelation.changeXp(0);
            return new Result(true, "che romantic");
        }

        return new Result(false, "you can't flower this player");

    }

    public Result askMarriage(Matcher matcher) {

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

        int distanceSquare = (App.getGame().getCurrentPlayingPlayer().getPosition().getX() - temp.getPosition().getX()) *
                (App.getGame().getCurrentPlayingPlayer().getPosition().getX() - temp.getPosition().getX()) +
                (App.getGame().getCurrentPlayingPlayer().getPosition().getY() - temp.getPosition().getY()) *
                        (App.getGame().getCurrentPlayingPlayer().getPosition().getY() - temp.getPosition().getY());

        if (distanceSquare > 2) {
            return new Result(false, "You are too far away");
        }

        if (App.getGame().getCurrentPlayingPlayer().getCurrentUser().getGender().equals(Gender.Female) || temp.getCurrentUser().getGender().equals(Gender.Male)) {
            return new Result(false, "Gender conflict");
        }

        if (temp.isMarried()) {
            return new Result(false, "Married and Committed");
        }

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
        lookUpKey.add(temp);

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);
        if (!tempRelation.canRequestMarriage()) {
            return new Result(false, "you can't request marriage in this friendship level");
        }

        temp.addNotification(new MarriageRequest("aroos nanam mishi?", App.getGame().getCurrentPlayingPlayer()));

        return new Result(true, "Marriage requested");

    }

    public Result respondMarriage(Matcher matcher) {

        MarriageRequest temp = null;

        for (Notification n : App.getGame().getCurrentPlayingPlayer().getNotifications()) {

            if (n instanceof MarriageRequest) {
                if (!n.isChecked() && n.getSender().getUsername().equals(matcher.group("username"))) {
                    temp = (MarriageRequest) n;
                    break;
                }
            }
        }

        if (temp == null) {
            return new Result(false, "No such marriage request");
        }

        temp.setChecked(true);

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
        lookUpKey.add(temp.getSender());

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);

        if (matcher.group("state").equals("accept")) {

            App.getGame().getCurrentPlayingPlayer().setMarried(true);
            temp.getSender().setMarried(true);

            tempRelation.setMarriage();
            tempRelation.setFriendshipLevel(FriendshipLevelsWithPlayers.LevelFour);

            return new Result(true, "You accepted the marriage request");

        } else {

            tempRelation.setFriendshipLevel(FriendshipLevelsWithPlayers.LevelZero);

            temp.getSender().setRemainingNumsAfterMarriageRequestDenied(7);
            return new Result(true, "You rejected the marriage request");
        }

    }

    public Result GiftToPLayer(Matcher matcher) {

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

        int distanceSquare = (App.getGame().getCurrentPlayingPlayer().getPosition().getX() -receiver.getPosition().getX()) *
                (App.getGame().getCurrentPlayingPlayer().getPosition().getX() - receiver.getPosition().getX()) +
                (App.getGame().getCurrentPlayingPlayer().getPosition().getY() - receiver.getPosition().getY()) *
                        (App.getGame().getCurrentPlayingPlayer().getPosition().getY() - receiver.getPosition().getY());

        if (distanceSquare > 2) {
            return new Result(false, "You are too far away");
        }

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
        lookUpKey.add(receiver);

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);

        if (tempRelation.getFriendshipLevel().equals(FriendshipLevelsWithPlayers.LevelZero)) {
            return new Result(false, "you can't gift this player at this friendship level");
        }

        if (!Sellable.isSellable(matcher.group("item"))) {
            return new Result(false, "you can't gift this item");
        }

        if (Sellable.getSellableByName(matcher.group("item")) == null) {
            return new Result(false, "Not enough stock");
        }

        int amount = Integer.parseInt(matcher.group("amount"));

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault((Ingredient) Sellable.getSellableByName(matcher.group("item")),0) < amount) {
            return new Result(false, "Not enough stock");
        }

        App.getGame().addGiftsIndex();
        BetweenPlayersGift tempGift = new BetweenPlayersGift(Sellable.getSellableByName(matcher.group("item")),App.getGame().getCurrentPlayingPlayer(),receiver,App.getGame().getGiftIndex());
        App.getGame().addToGifts(tempGift);

        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients((Ingredient) Sellable.getSellableByName(matcher.group("item")),amount);
        receiver.getBackpack().addIngredients((Ingredient) Sellable.getSellableByName(matcher.group("item")),amount);

        receiver.addNotification(new Notification("you have received a gift", App.getGame().getCurrentPlayingPlayer()));


        if (tempRelation.isMarriage()) {
            App.getGame().getCurrentPlayingPlayer().addEnergy(50);
            receiver.addEnergy(50);
        }

        return new Result(true, "He/She received your gift with id " + App.getGame().getGiftIndex());

    }

}
