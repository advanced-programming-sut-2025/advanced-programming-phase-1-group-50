package com.stardew.models.stores;

import com.stardew.models.animals.AnimalGood;
import com.stardew.models.animals.AnimalGoodType;
import com.stardew.models.animals.Fish;
import com.stardew.models.animals.FishType;
import com.stardew.models.app.App;
import com.stardew.models.cooking.Food;
import com.stardew.models.foraging.Crop;
import com.stardew.models.foraging.CropType;
import com.stardew.models.foraging.ForagingMineral;
import com.stardew.models.foraging.Fruit;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGoodType;
import com.stardew.models.userInfo.Player;

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

    static String getNameInString(Sellable sellable) {
        if (sellable instanceof AnimalGood){
            return ((AnimalGood) sellable).getType().toString();
        }
        if (sellable instanceof Crop){
            return ((Crop) sellable).getType().toString();
        }
        if (sellable instanceof Fish){
            return ((Fish) sellable).getType().toString();
        }
        if (sellable instanceof Food){
            return sellable.toString();
        }
        if (sellable instanceof Fruit){
            return sellable.toString();
        }
        if (sellable instanceof ForagingMineral){
            return sellable.toString();
        }
        if (sellable instanceof ArtisanGood){
            return ((ArtisanGood) sellable).getType().toString();
        }
        return sellable.toString();
    }
}
