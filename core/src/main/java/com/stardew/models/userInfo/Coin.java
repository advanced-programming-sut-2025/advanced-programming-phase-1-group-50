package com.stardew.models.userInfo;

import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.mapInfo.Wood;

public class Coin implements Ingredient {
    @Override
    public int hashCode() {
        return 3;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Coin;
    }

    @Override
    public String toString() {
        return "Coin";
    }
}
