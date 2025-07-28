package com.stardew.model.stores;

public interface Sellable {

    int getSellPrice();

    static Sellable getSellableByName(String name) {
        if (!isSellable(name))
            return null;
        //TODO
        return null;
    }

    static boolean isSellable(String name) {
        //TODO
        return false;
    }

    static String getNameInString(Sellable sellable) {
        //TODO
        return sellable.toString();
    }


}
