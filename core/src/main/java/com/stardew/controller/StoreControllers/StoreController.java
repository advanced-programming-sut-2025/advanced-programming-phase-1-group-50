package com.stardew.controller.StoreControllers;

import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.app.Menus;

import java.util.regex.Matcher;

public abstract class StoreController {

    public abstract Result showAllProducts();

    public abstract Result showAvailableProducts();

    public Result ProcessPurchaseCommand(Matcher matcher) {

        int value = 1;

        if (matcher.group("count") != null) {
            value = Integer.parseInt(matcher.group("count"));
        }

        String productName = matcher.group("productName");

        return PurchaseProduct(value, productName);

    }

    protected abstract Result PurchaseProduct(int value , String productName);

    public void exit() {
        App.setMenu(Menus.GameMenu);
    }

    public Result ShowCurrentMenu() {
        return new Result(true,"StoreMenu");
    }


}
