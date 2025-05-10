package models.stores;

import java.awt.*;

public class JojaMart extends Market {


    public JojaMart(int x, int y, int width, int height) {
        shopAssistantName = "Morris";
        startHour = 9;
        endHour = 23;
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
        return 'J';
    }
}
