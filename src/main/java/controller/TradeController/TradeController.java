package controller.TradeController;

import models.Result;
import models.Trade;
import models.app.App;
import models.app.Menus;
import models.manuFactor.Ingredient;
import models.stores.Sellable;
import models.userInfo.Player;

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

        if (Sellable.isSellable(matcher.group("item"))) {
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
                Sellable.getSellableByName(matcher.group("item")), Integer.parseInt(matcher.group("price"))));

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
        return null;
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

    public Result exit() {
        App.setMenu(Menus.GameMenu);
        return new Result(true, "you are in game menu now");
    }
}
