package com.stardew.controller.StoreControllers;

import com.stardew.models.Result;
import com.stardew.models.app.App;


public class JojaMartController extends StoreController {
    @Override
    public Result showAllProducts() {
        //String message = App.getGame().getMap().getNpcVillage().getJojaMart().showAllProducts();
        return new Result(true, "");
    }

    @Override
    public Result showAvailableProducts() {
        //String message = App.getGame().getMap().getNpcVillage().getJojaMart().showAvailableProducts();
        return new Result(true, "");
    }

    @Override
    public Result PurchaseProduct(int value, String productName) {
        return App.getGame().getMap().getNpcVillage().getJojaMart().purchaseProduct(value, productName);
    }


}
