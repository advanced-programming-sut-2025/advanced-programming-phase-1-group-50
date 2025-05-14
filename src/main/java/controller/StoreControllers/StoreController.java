package controller.StoreControllers;

import models.Result;
import models.app.App;
import models.app.Menus;

import java.util.regex.Matcher;

public abstract class StoreController {

    public abstract Result showAllProducts();

    public abstract Result showAvailableProducts();

    public abstract Result purchaseProduct(Matcher matcher);

    public void exit() {
        App.setMenu(Menus.GameMenu);
    }


}
