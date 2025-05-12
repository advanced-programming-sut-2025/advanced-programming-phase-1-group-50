package models.userInfo;

import models.manuFactor.Ingredient;
import models.mapInfo.Wood;

public class Coin implements Ingredient {
    @Override
    public int hashCode() {
        return 3;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Coin;
    }
}
