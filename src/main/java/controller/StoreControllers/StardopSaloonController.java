package controller.StoreControllers;

import models.Result;
import models.app.App;

public class StardopSaloonController extends StoreController {
    @Override
    public Result showAllProducts() {
        String message = App.getGame().getMap().getNpcVillage().getStardopSaloon().showAllProducts();
        return new Result(true, message);
    }

    @Override
    public Result showAvailableProducts() {
        String message = App.getGame().getMap().getNpcVillage().getStardopSaloon().showAvailableProducts();
        return new Result(true, message);
    }

    @Override
    public Result PurchaseProduct(int value, String productName) {
        return App.getGame().getMap().getNpcVillage().getStardopSaloon().purchaseProduct(value, productName);
    }


}
