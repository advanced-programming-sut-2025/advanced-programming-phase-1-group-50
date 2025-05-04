package models.recipes;

import models.animals.AnimalGoodType;
import models.animals.FishType;
import models.foraging.CropType;
import models.foraging.ForagingCrop;
import models.foraging.Fruit;
import models.manuFactor.Ingredient;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.manuFactor.artisanGoods.ArtisanGoodType;

import java.util.HashMap;
import java.util.Map;

public enum CookingRecipe implements Ingredient {
    FriedEgg(new HashMap<>(Map.of(AnimalGoodType.Egg, 1)),
            50, 35),
    BakedFish(new HashMap<>(Map.of(FishType.Sardine, 1, FishType.Salmon, 1, CropType.Wheat, 1)) ,
            75, 100),
    Salad(new HashMap<>(Map.of(ForagingCrop.Leek, 1, ForagingCrop.Dandelion, 1)),
            113, 110),
    Omelet(new HashMap<>(Map.of(AnimalGoodType.Egg, 1, AnimalGoodType.Milk, 1)),
            100, 125),
    PumpkinPie(new HashMap<>(Map.of(CropType.Pumpkin, 1, CropType.Wheat, 1, AnimalGoodType.Milk, 1)),
            225, 385),
    Spaghetti(new HashMap<>(Map.of(CropType.Wheat, 1, CropType.Tomato, 1)),
            75, 120),
    Pizza(new HashMap<>(Map.of(CropType.Wheat, 1, CropType.Tomato, 1, new ArtisanGood(ArtisanGoodType.CheeseByMilk), 1)),
            150, 300),
    Tortilla(new HashMap<>(Map.of(CropType.Corn, 1)),
            50, 50),
    MakiRoll(new HashMap<>(Map.of(FishType.Salmon, 1, CropType.UnMilledRice, 1)) ,
            100, 220),
    TripleShotEspresso(new HashMap<>(Map.of(new ArtisanGood(ArtisanGoodType.Coffee), 3)),
            200, 450),
    Cookie(new HashMap<>(Map.of(CropType.Wheat, 1, AnimalGoodType.Egg, 1)),
            90, 140),
    HashBrowns(new HashMap<>(Map.of(CropType.Potato, 1, new ArtisanGood(ArtisanGoodType.Oil), 1)),
            90, 120),
    Pancakes(new HashMap<>(Map.of(CropType.Wheat, 1, AnimalGoodType.Egg, 1)),
            90, 80),
    FruitSalad(new HashMap<>(Map.of(CropType.Blueberry, 1, CropType.Melon, 1, Fruit.Apricot, 1)),
            263, 450),
    RedPlate(new HashMap<>(Map.of(CropType.RedCabbage, 1, CropType.Radish, 1)),
            240, 400),
    Bread(new HashMap<>(Map.of(CropType.Wheat, 1)),
            50, 60),
    SalmonDinner(new HashMap<>(Map.of(FishType.Salmon, 1, CropType.Amaranth, 1, CropType.Kale, 1)),
            125, 300),
    VegetableMedley(new HashMap<>(Map.of(CropType.Tomato, 1, CropType.Beet, 1)),
            165, 120),
    FarmersLunch(new HashMap<>(Map.of(Omelet, 1, CropType.Parsnip, 1)),
            200, 150),
    SurvivalBurger(new HashMap<>(Map.of(Bread, 1, CropType.Carrot, 1, CropType.Eggplant, 1)),
            125, 180),
    DishOTheSea(new HashMap<>(Map.of(FishType.Sardine, 2,HashBrowns, 1)),
            150, 220),
    SeaFormPudding(new HashMap<>(Map.of(FishType.Flounder, 1, FishType.MidnightCarp, 1)),
            175, 300),
    MinersTreat(new HashMap<>(Map.of(CropType.Carrot, 1, AnimalGoodType.Milk, 1)),
            125, 200);

    private final HashMap<Ingredient, Integer> ingredients;
    private final int energy;
    private final int sellPrice;

    CookingRecipe(HashMap<Ingredient, Integer> ingredients, int energy, int sellPrice) {
        this.ingredients = ingredients;
        this.energy = energy;
        this.sellPrice = sellPrice;
    }

    public HashMap<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    public int getEnergy() {
        return energy;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}
