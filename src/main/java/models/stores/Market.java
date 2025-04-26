package models.stores;

import models.tools.Tool;

import java.util.ArrayList;

public abstract class Market {
    protected String shopAssistantName;
    protected ArrayList<Tool> goods;
    protected int startHour;
    protected int endHour;

    public String getShopAssistantName() {
        return shopAssistantName;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public ArrayList<Tool> getGoods() {
        return this.goods;
    }

    public abstract void removeGood();

    public abstract void addGood();

    public abstract void sellProduct();

    public abstract String showAllProducts();

    public abstract String showAllAvailableProducts();

    public abstract void purchase();
}
