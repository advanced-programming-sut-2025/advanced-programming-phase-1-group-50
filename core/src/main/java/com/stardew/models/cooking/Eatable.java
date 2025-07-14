package com.stardew.models.cooking;

import com.stardew.models.animals.AnimalGoodType;
import com.stardew.models.foraging.CropType;
import com.stardew.models.foraging.ForagingCrop;
import com.stardew.models.foraging.Fruit;
import com.stardew.models.manuFactor.Ingredient;

public interface Eatable extends Ingredient {

    int getEnergy();

    static boolean isEatable(String name) {
        return AnimalGoodType.getAnimalGoodTypeByName(name) != null ||
            AnimalGoodType.getAnimalGoodTypeByName(name) != null ||
            CropType.getCropTypeByName(name) != null ||
            Food.getFoodByName(name) != null ||
            Fruit.getFruitByName(name) != null ||
            ForagingCrop.getForagingCropByName(name) != null;
    }
}
