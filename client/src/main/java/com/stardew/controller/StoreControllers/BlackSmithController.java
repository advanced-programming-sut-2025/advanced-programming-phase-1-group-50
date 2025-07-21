package com.stardew.controller.StoreControllers;

import com.stardew.models.Result;
import com.stardew.models.app.App;

public class BlackSmithController extends StoreController{


    @Override
    public Result showAllProducts() {
        //String message = App.getGame().getMap().getNpcVillage().getBlacksmith().showAllProducts();
        return new Result(true, "");
    }

    @Override
    public Result showAvailableProducts() {
        //String message = App.getGame().getMap().getNpcVillage().getBlacksmith().showAvailableProducts();
        return new Result(true, "");
    }

    @Override
    public Result PurchaseProduct(int value, String productName) {
        return App.getGame().getMap().getNpcVillage().getBlacksmith().purchaseProduct(value, productName);
    }


}
