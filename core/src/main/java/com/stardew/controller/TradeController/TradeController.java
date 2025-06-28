package com.stardew.controller.TradeController;

import com.stardew.models.Result;
import com.stardew.models.Trade;
import com.stardew.models.app.App;
import com.stardew.models.app.Menus;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.stores.Sellable;
import com.stardew.models.userInfo.Coin;
import com.stardew.models.userInfo.Player;
import com.stardew.models.userInfo.RelationNetwork;
import com.stardew.models.userInfo.RelationWithPlayers;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

public class TradeController {

    public Result trade(Matcher matcher) {

        Player buyer = null;

        for (Player p : App.getGame().getPlayers()) {
            if (p.getUsername().equals(matcher.group("username"))) {
                buyer = p;
            }
        }

        if (buyer == null) {
            return new Result(false, "player not found");
        }

        if (!Sellable.isSellable(matcher.group("item"))) {
            return new Result(false, "Invalid item for trade");
        }

        if (Sellable.getSellableByName(matcher.group("item")) == null) {
            return new Result(false, "Not enough stock");
        }

        int amount = Integer.parseInt(matcher.group("amount"));

        if (App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity().getOrDefault((Ingredient) Sellable.getSellableByName(matcher.group("item")), 0) < amount) {
            return new Result(false, "Not enough stock");
        }

        App.getGame().addTradesIndex();
        App.getGame().addToTrades(new Trade(App.getGame().getCurrentPlayingPlayer(), buyer, amount,
                Sellable.getSellableByName(matcher.group("item")), Integer.parseInt(matcher.group("price")),App.getGame().getTradeIndex()));

        return new Result(true, "your offer with id " + App.getGame().getTradeIndex() +" successfully submitted");
    }

    public Result tradeList() {
        StringBuilder message = new StringBuilder("Requests(not responded): ");

        for (Trade trade : App.getGame().getTrades()) {
            if (trade.getBuyer().equals(App.getGame().getCurrentPlayingPlayer()) && !trade.isResponded()) {
                message.append("\n").append(trade);
            }
        }

        return new Result(true, message.toString());
    }

    public Result tradeResponse(Matcher matcher) {

        int id = Integer.parseInt(matcher.group("id"));
        Trade tempTrade = null;

        for (Trade trade : App.getGame().getTrades()) {
            if (trade.getId() == id) {
                tempTrade = trade;
                break;
            }
        }

        if (tempTrade == null) {
            return new Result(false, "Trade not found");
        }

        if (!tempTrade.getBuyer().equals(App.getGame().getCurrentPlayingPlayer())) {
            return new Result(false, "you can't respond this trade");
        }

        if (tempTrade.isResponded()) {
            return new Result(false, "Trade is already responded");
        }

        RelationNetwork tempNetwork = App.getGame().getRelationsBetweenPlayers();
        Set<Player> lookUpKey = new HashSet<>();
        lookUpKey.add(App.getGame().getCurrentPlayingPlayer());
        lookUpKey.add(tempTrade.getSeller());

        RelationWithPlayers tempRelation = tempNetwork.relationNetwork.get(lookUpKey);

        if (matcher.group("state").equals("reject")) {

            if (!tempRelation.HaveTradedToday()) {
                tempRelation.changeXp(-30);
            }

            tempRelation.setHaveTradedToday(true);
            tempTrade.setResponded(true);
            return new Result(true, "Trade rejected");
        }

        if (tempTrade.getSeller().getBackpack().getIngredientQuantity().getOrDefault((Ingredient) tempTrade.getSellable(),0) < tempTrade.getQuantity()) {

            if (!tempRelation.HaveTradedToday()) {
                tempRelation.changeXp(-30);
            }
            tempRelation.setHaveTradedToday(true);

            return new Result(false, "seller doesn't have enough stock");
        }

        if (tempTrade.getBuyer().getBackpack().getIngredientQuantity().getOrDefault(new Coin(),0) < tempTrade.getPrice()) {

            if (!tempRelation.HaveTradedToday()) {
                tempRelation.changeXp(-30);
            }

            tempRelation.setHaveTradedToday(true);

            return new Result(false, "you don't have enough money");
        }

        tempTrade.getBuyer().getBackpack().removeIngredients(new Coin(), tempTrade.getPrice());
        tempTrade.getSeller().getBackpack().addIngredients(new Coin(), tempTrade.getPrice());

        tempTrade.getSeller().getBackpack().removeIngredients((Ingredient) tempTrade.getSellable() , tempTrade.getQuantity());
        tempTrade.getBuyer().getBackpack().addIngredients((Ingredient) tempTrade.getSellable() , tempTrade.getQuantity());

        tempTrade.setResponded(true);
        tempTrade.setAccepted(true);

        if (!tempRelation.HaveTradedToday()) {
            tempRelation.changeXp(50);
        }

        if (tempRelation.isMarriage()) {
            App.getGame().getCurrentPlayingPlayer().addEnergy(50);
            tempTrade.getSeller().addEnergy(50);
        }

        tempRelation.setHaveTradedToday(true);

        return new Result(true, "Trade accepted");
    }

    public Result tradeHistory() {
        StringBuilder message = new StringBuilder("Trade history: ");

        for (Trade trade : App.getGame().getTrades()) {
            if (trade.getBuyer().equals(App.getGame().getCurrentPlayingPlayer()) || trade.getSeller().equals(App.getGame().getCurrentPlayingPlayer())) {
                message.append("\n").append(trade);
            }
        }

        return new Result(true, message.toString());
    }

    public Result ShowCurrentMenu() {
        return new Result(true, "Trade Menu");
    }

    public Result exit() {
        App.setMenu(Menus.GameMenu);
        return new Result(true, "you are in game menu now");
    }
}
