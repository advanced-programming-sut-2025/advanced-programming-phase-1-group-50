package com.stardew.view.windows;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.models.stores.Store;

public class PurchaseWindow extends CloseableWindow{
    public PurchaseWindow(Stage stage,  Store store, String productName, int quantity, int price) {
        super( "Purchase window", stage);
    }
}
