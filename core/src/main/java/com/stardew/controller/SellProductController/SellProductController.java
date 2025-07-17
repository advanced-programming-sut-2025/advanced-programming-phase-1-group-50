package com.stardew.controller.SellProductController;

import com.stardew.models.Result;
import com.stardew.models.ShippingBin;
import com.stardew.models.app.App;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.stores.Sellable;

public class SellProductController {

    public static Result sellProduct( String productName, ShippingBin shippingBin, int amount) {

        if (!Sellable.isSellable(productName)) {
            return new Result(false, "you can't sell this product");
        }

        int price = amount * Sellable.getSellableByName(productName).getSellPrice();
        App.getGame().getCurrentPlayingPlayer().getBackpack().removeIngredients((Ingredient) Sellable.getSellableByName(productName), amount);
        shippingBin.increaseRevenue(App.getGame().getCurrentPlayingPlayer(),price);

        return new Result(true, "you have sold this product successfully");
    }

}
