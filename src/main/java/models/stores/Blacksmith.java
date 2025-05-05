package models.stores;

import java.awt.*;

public class Blacksmith extends Market {

    public Blacksmith(int x , int y , int width, int height) {
        shopAssistantName = "Clint";
        startHour = 9;
        endHour = 16;
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
        return '⚒';
    }
}
