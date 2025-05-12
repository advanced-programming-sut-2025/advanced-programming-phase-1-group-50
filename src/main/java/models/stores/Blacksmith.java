package models.stores;

import java.awt.*;

public class Blacksmith extends Store {

    public Blacksmith(int x, int y, int width, int height) {
        super(new Rectangle(x,y,width,height),"Clint",9,16);
    }

    @Override
    public char getSymbol() {
        return '⚒';
    }





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
