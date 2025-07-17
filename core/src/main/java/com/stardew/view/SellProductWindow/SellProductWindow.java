package com.stardew.view.SellProductWindow;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.models.ShippingBin;
import com.stardew.view.windows.CloseableWindow;

public class SellProductWindow extends CloseableWindow {
    public SellProductWindow(Stage stage, ShippingBin shippingBin, String productName, int quantity, int price) {
        super("", stage);
    }
}
