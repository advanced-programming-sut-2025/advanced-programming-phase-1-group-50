package models;

import models.manuFactor.Ingredient;

public class Bouquet implements Ingredient {

    @Override
    public int hashCode() {
        return 4;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Bouquet;
    }
}
