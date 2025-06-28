package com.stardew.controller.StoreControllers;

import com.stardew.models.Result;
import com.stardew.models.app.App;

public class MarnieRanchController extends StoreController {
    @Override
    public Result showAllProducts() {
        String message = App.getGame().getMap().getNpcVillage().getMarnieRanch().showAllProducts();
        return new Result(true, message);
    }

    @Override
    public Result showAvailableProducts() {
        String message = App.getGame().getMap().getNpcVillage().getMarnieRanch().showAvailableProducts();
        return new Result(true, message);
    }

    @Override
    public Result PurchaseProduct(int value, String productName) {
        return App.getGame().getMap().getNpcVillage().getMarnieRanch().purchaseProduct(value, productName);
    }


}
