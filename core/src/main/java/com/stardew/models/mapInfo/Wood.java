package com.stardew.models.mapInfo;

import com.stardew.models.manuFactor.Ingredient;

public class Wood implements Ingredient {
    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Wood;
    }

    @Override
    public String toString() {
        return "Wood";
    }
}
