package models.stores;

import models.animals.AnimalGood;
import models.animals.AnimalGoodType;
import models.animals.Fish;
import models.animals.FishType;
import models.app.App;
import models.cooking.Food;
import models.foraging.Crop;
import models.foraging.CropType;
import models.foraging.ForagingMineral;
import models.foraging.Fruit;
import models.manuFactor.Ingredient;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.manuFactor.artisanGoods.ArtisanGoodType;
import models.userInfo.Player;

public interface Sellable {

    int getSellPrice();

    static Sellable getSellableByName(String name) {
        if (!isSellable(name))
            return null;

        Player player = App.getGame().getCurrentPlayingPlayer();

        AnimalGoodType animalGoodType = AnimalGoodType.getAnimalGoodTypeByName(name);
        if (animalGoodType != null) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof AnimalGood animalGood && animalGood.getType().equals(animalGoodType)) {
                    return animalGood;
                }
            }
            return null;
        }

        CropType cropType = CropType.getCropTypeByName(name);
        if (cropType != null) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Crop crop && crop.getType().equals(cropType)) {
                    return crop;
                }
            }
            return null;
        }

        FishType fishType = FishType.getFishTypeByName(name);
        if (fishType != null) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Fish fish && fish.getType().equals(fishType)) {
                    return fish;
                }
            }
            return null;
        }

        Food food = Food.getFoodByName(name);
        if (food != null) {
            return food;
        }

        Fruit fruit = Fruit.getFruitByName(name);
        if (fruit != null) {
            return fruit;
        }

        ForagingMineral foragingMineral = ForagingMineral.getForagingMineralByName(name);
        if (foragingMineral != null) {
            return foragingMineral;
        }

        ArtisanGoodType artisanGoodType = ArtisanGoodType.getArtisanGoodTypeByName(name);
        if (artisanGoodType != null) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof ArtisanGood artisanGood && artisanGood.getType().equals(artisanGoodType)) {
                    return artisanGood;
                }
            }
            return null;
        }

        return null;
    }

    static boolean isSellable(String name) {
        return  AnimalGoodType.getAnimalGoodTypeByName(name) != null ||
                CropType.getCropTypeByName(name) != null ||
                FishType.getFishTypeByName(name) != null ||
                Food.getFoodByName(name) != null ||
                Fruit.getFruitByName(name) != null ||
                ForagingMineral.getForagingMineralByName(name) != null ||
                ArtisanGoodType.getArtisanGoodTypeByName(name) != null;

    }
}
