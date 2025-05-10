package models.stores;

import java.awt.*;

public class FishShop extends Market {

    public FishShop(int x, int y, int width, int height) {
        shopAssistantName = "Willy";
        startHour = 9;
        endHour = 17;
        this.bounds = new Rectangle(x, y, width, height);
        //goods
    }


    @Override
    public void removeGood() {

    }

    @Override
    public void addGood() {

    }

    @Override
    public void sellProduct() {

    }

    @Override
    public String showAllProducts() {
        return "";
    }

    @Override
    public String showAllAvailableProducts() {
        return "";
    }

    @Override
    public void purchase() {

    }
    public char getSymbol() {
        return '≈';
    }
}
