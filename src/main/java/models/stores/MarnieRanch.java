package models.stores;

import java.awt.*;

public class MarnieRanch extends Store {

    public MarnieRanch(int x, int y, int width, int height) {
        super(new Rectangle(x,y,width,height), "Marnie",9,16);
    }

    @Override
    public char getSymbol() {
        return '♞';
    }

//    @Override
//    public void removeGood() {
//
//    }
//
//    @Override
//    public void addGood() {
//
//    }
//
//    @Override
//    public void sellProduct() {
//
//    }
//
//    @Override
//    public String showAllProducts() {
//        return "";
//    }
//
//    @Override
//    public String showAllAvailableProducts() {
//        return "";
//    }
//
//    @Override
//    public void purchase() {
//
//    }
}
