package models.stores;

import java.awt.*;

public class FishShop extends Market {

    public FishShop(int x, int y, int width, int height) {
        super(new Rectangle(x,y,width,height),"willy",9,17);
    }

    @Override
    public char getSymbol() {
        return '≈';
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
