package models.stores;

import models.tools.Tool;

import java.util.ArrayList;

public abstract class Market {
    protected String shopAssistantName;
    protected ArrayList<Tool> goods;
    protected int startHour;
    protected int endHour;



    public ArrayList<Tool> getGoods() {
        return null;
    }

    public void removeGood(Tool tool) {

    }

    public void addGood(Tool tool) {

    }

    public void sellProduct() {

    }
}
