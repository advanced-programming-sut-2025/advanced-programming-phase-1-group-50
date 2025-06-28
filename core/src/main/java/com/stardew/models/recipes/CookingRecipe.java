package com.stardew.models.recipes;

import com.stardew.models.animals.AnimalGoodType;
import com.stardew.models.animals.FishType;
import com.stardew.models.cooking.Food;
import com.stardew.models.foraging.CropType;
import com.stardew.models.foraging.ForagingCrop;
import com.stardew.models.foraging.Fruit;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGoodType;

import java.util.HashMap;
import java.util.Map;

public enum CookingRecipe implements Ingredient {
    FriedEgg(new HashMap<>(Map.of(AnimalGoodType.Egg, 1))),
    BakedFish(new HashMap<>(Map.of(FishType.Sardine, 1, FishType.Salmon, 1, CropType.Wheat, 1))),
    Salad(new HashMap<>(Map.of(ForagingCrop.Leek, 1, ForagingCrop.Dandelion, 1))),
    Omelet(new HashMap<>(Map.of(AnimalGoodType.Egg, 1, AnimalGoodType.Milk, 1))),
    PumpkinPie(new HashMap<>(Map.of(CropType.Pumpkin, 1, CropType.Wheat, 1, AnimalGoodType.Milk, 1))),
    Spaghetti(new HashMap<>(Map.of(CropType.Wheat, 1, CropType.Tomato, 1))),
    Pizza(new HashMap<>(Map.of(CropType.Wheat, 1, CropType.Tomato, 1, new ArtisanGood(ArtisanGoodType.CheeseByMilk), 1))),
    Tortilla(new HashMap<>(Map.of(CropType.Corn, 1))),
    MakiRoll(new HashMap<>(Map.of(FishType.Salmon, 1, CropType.UnMilledRice, 1))),
    TripleShotEspresso(new HashMap<>(Map.of(new ArtisanGood(ArtisanGoodType.Coffee), 3))),
    Cookie(new HashMap<>(Map.of(CropType.Wheat, 1, AnimalGoodType.Egg, 1))),
    HashBrowns(new HashMap<>(Map.of(CropType.Potato, 1, new ArtisanGood(ArtisanGoodType.Oil), 1))),
    Pancakes(new HashMap<>(Map.of(CropType.Wheat, 1, AnimalGoodType.Egg, 1))),
    FruitSalad(new HashMap<>(Map.of(CropType.Blueberry, 1, CropType.Melon, 1, Fruit.Apricot, 1))),
    RedPlate(new HashMap<>(Map.of(CropType.RedCabbage, 1, CropType.Radish, 1))),
    Bread(new HashMap<>(Map.of(CropType.Wheat, 1))),
    SalmonDinner(new HashMap<>(Map.of(FishType.Salmon, 1, CropType.Amaranth, 1, CropType.Kale, 1))),
    VegetableMedley(new HashMap<>(Map.of(CropType.Tomato, 1, CropType.Beet, 1))),
    FarmersLunch(new HashMap<>(Map.of(Food.Omelet, 1, CropType.Parsnip, 1))),
    SurvivalBurger(new HashMap<>(Map.of(Food.Bread, 1, CropType.Carrot, 1, CropType.Eggplant, 1))),
    DishOTheSea(new HashMap<>(Map.of(FishType.Sardine, 2,Food.HashBrowns, 1))),
    SeaFormPudding(new HashMap<>(Map.of(FishType.Flounder, 1, FishType.MidnightCarp, 1))),
    MinersTreat(new HashMap<>(Map.of(CropType.Carrot, 1, AnimalGoodType.Milk, 1)));

    private final HashMap<Ingredient, Integer> ingredients;
    private final static HashMap<String, CookingRecipe> stringToRecipes = new HashMap<>();

    static {
        for (CookingRecipe value : CookingRecipe.values()) {
            stringToRecipes.put(value.name().toLowerCase(), value);
        }
    }

    CookingRecipe(HashMap<Ingredient, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public HashMap<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    public static CookingRecipe getRecipeByName(String name) {
        if (name == null || name.isEmpty())
            return null;
        return stringToRecipes.getOrDefault(name.toLowerCase(), null);
    }
}
