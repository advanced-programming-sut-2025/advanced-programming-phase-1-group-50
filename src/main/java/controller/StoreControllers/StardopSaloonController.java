package controller.StoreControllers;

import models.Result;
import models.app.App;

import java.util.regex.Matcher;

public class StardopSaloonController extends StoreController {
    @Override
    public Result showAllProducts() {
        String message = App.getGame().getMap().getNpcVillage().getSaloon().showAllProducts();
        return new Result(true, message);
    }

    @Override
    public Result showAvailableProducts() {
        String message = App.getGame().getMap().getNpcVillage().getSaloon().showAvailableProducts();
        return new Result(true, message);
    }

    @Override
    public Result purchaseProduct(Matcher matcher) {
        return null;
    }

}
