package models.stores;

import models.Placeable;
import models.app.App;

import java.awt.*;

public abstract class Market implements Placeable {

    protected final Rectangle bounds;
    protected final String shopAssistantName;
    protected final int startHour;
    protected final int endHour;

    public Market(Rectangle bounds, String shopAssistantName, int startHour, int endHour) {
        this.bounds = bounds;
        this.shopAssistantName = shopAssistantName;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public String getShopAssistantName() {
        return shopAssistantName;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public abstract char getSymbol();

    public boolean isOpen() {
        return App.getGame().getTime().getHour() >= startHour && App.getGame().getTime().getHour() <= endHour;
    }


//    public abstract String showAllProducts();
//
//    public abstract String showAllAvailableProducts();
//
//    public abstract void purchase();

}
