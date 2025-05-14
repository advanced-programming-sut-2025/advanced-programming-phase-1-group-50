package controller.StoreControllers;

import models.Result;
import models.app.App;


import java.util.regex.Matcher;

public class BlackSmithController extends StoreController{


    @Override
    public Result showAllProducts() {
        String message = App.getGame().getMap().getNpcVillage().getBlacksmith().showAllProducts();
        return new Result(true, message);
    }

    @Override
    public Result showAvailableProducts() {
        String message = App.getGame().getMap().getNpcVillage().getBlacksmith().showAvailableProducts();
        return new Result(true, message);
    }

    @Override
    public Result purchaseProduct(Matcher matcher) {
        return null;
    }

}
