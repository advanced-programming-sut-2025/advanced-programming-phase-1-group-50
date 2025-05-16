package controller.TradeController;

import models.Result;
import models.app.App;
import models.app.Menus;

import java.util.regex.Matcher;

public class TradeController {

    public Result trade(Matcher matcher) {
        return null;
    }

    public Result tradeList() {
        return null;
    }

    public Result tradeResponse(Matcher matcher) {
        return null;
    }

    public Result tradeHistory() {
        return null;
    }

    public Result exit() {
        App.setMenu(Menus.GameMenu);
        return new Result(true,"you are in game menu now");
    }
}
