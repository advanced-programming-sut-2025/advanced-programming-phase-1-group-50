package controller.TradeController;

import models.Result;
import models.Trade;
import models.app.App;
import models.app.Menus;

import java.util.regex.Matcher;

public class TradeController {

    public Result trade(Matcher matcher) {
        return null;
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
        return new Result(true,"you are in game menu now");
    }
}
