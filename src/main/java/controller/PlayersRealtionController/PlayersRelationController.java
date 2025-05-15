package controller.PlayersRealtionController;

import models.BetweenPlayersGift;
import models.Notification;
import models.Result;
import models.app.App;
import models.userInfo.DialoguesBetweenPlayers;
import models.userInfo.Player;
import models.userInfo.RelationNetwork;
import models.userInfo.RelationWithPlayers;

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

            message.append(p1.getUsername()).append(" and ").append(p2.getUsername()).append(":");
            message.append(App.getGame().getRelationsBetweenPlayers().relationNetwork.get(key).toString());

        }

        return new Result(true, message.toString());

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

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(receiver);
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);
        if (!tempRelation.isHaveTalkedToday()) {
            tempRelation.changeXp(20);
        }

        tempRelation.addDialogue(new DialoguesBetweenPlayers(App.getGame().getCurrentPlayingPlayer(),receiver, matcher.group("message")));
        tempNetwork.relationNetwork.put(lookUpKey,tempRelation);
        receiver.addNotification(new Notification(matcher.group("message")));

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

        if (rate <= 0 || id >= 6) {
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

        return new Result(true, "you rated this gift successfully");
    }


}

