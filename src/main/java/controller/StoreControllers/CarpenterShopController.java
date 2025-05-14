package controller.StoreControllers;

import models.Result;
import models.app.App;

public class CarpenterShopController extends StoreController {
    @Override
    public Result showAllProducts() {
        String message = App.getGame().getMap().getNpcVillage().getCarpenterShop().showAllProducts();
        return new Result(true, message);
    }

    @Override
    public Result showAvailableProducts() {
        String message = App.getGame().getMap().getNpcVillage().getCarpenterShop().showAvailableProducts();
        return new Result(true, message);
    }

    @Override
    public Result PurchaseProduct(int value, String productName) {
        return App.getGame().getMap().getNpcVillage().getCarpenterShop().purchaseProduct(value, productName);
    }


}
