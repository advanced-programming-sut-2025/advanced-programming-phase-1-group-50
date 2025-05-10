package models.stores;

import java.awt.*;

public class CarpenterShop extends Market {

    public CarpenterShop(int x , int y, int width, int height) {
        shopAssistantName = "Robin";
        startHour = 9;
        endHour = 20;
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
        return 'w';
    }

}
