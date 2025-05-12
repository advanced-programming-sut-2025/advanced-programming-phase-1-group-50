package models.stores;

import java.awt.*;

public class CarpenterShop extends Store {

    public CarpenterShop(int x , int y, int width, int height) {
        super(new Rectangle(x,y,width,height),"Robin",9,20);
    }

    @Override
    public char getSymbol() {
        return 'w';
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
