package com.stardew.models.cooking;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.stores.Sellable;

import java.util.HashMap;

public enum Food implements Ingredient, Sellable, Eatable {
    FriedEgg(50, 35, GamePictureManager.friedEgg),
    BakedFish(75, 100, GamePictureManager.bakedFish),
    Salad(113, 110, GamePictureManager.salad),
    Omelet(100, 125, GamePictureManager.omelet),
    PumpkinPie(225, 385, GamePictureManager.pumpkinPie),
    Spaghetti(75, 120, GamePictureManager.spaghetti),
    Pizza(150, 300, GamePictureManager.pizza),
    Tortilla(50, 50, GamePictureManager.tortilla),
    MakiRoll(100, 220, GamePictureManager.makiRoll),
    TripleShotEspresso(200, 450, GamePictureManager.tripleShotEspresso),
    Cookie(90, 140, GamePictureManager.cookie),
    HashBrowns(90, 120, GamePictureManager.hashbrowns),
    Pancakes(90, 80, GamePictureManager.pancakes),
    FruitSalad(263, 450, GamePictureManager.fruitSalad),
    RedPlate(240, 400, GamePictureManager.redPlate),
    Bread(50, 60, GamePictureManager.bread),
    SalmonDinner(125, 300, GamePictureManager.salmonDinner),
    VegetableMedley(165, 120, GamePictureManager.vegetableMedley),
    FarmersLunch(200, 150, GamePictureManager.farmersLunch),
    SurvivalBurger(125, 180, GamePictureManager.survivalBurger),
    DishOTheSea(150, 220, GamePictureManager.dishOTheSea),
    SeaFoamPudding(175, 300, GamePictureManager.seaFoamPudding),
    JojaCola(13,25, GamePictureManager.jojaColaTexture),
    TroutSoup(100,100, GamePictureManager.troutSoupTexture),
    MinersTreat(125, 200, GamePictureManager.minersTreat);

    private final int energy;
    private final int sellPrice;
    private final TextureRegion textureRegion;
    private static final HashMap<String, Food> stringToFood = new HashMap<>();

    static {
        for (Food values : values()) {
            stringToFood.put(values.name().toLowerCase(), values);
        }
    }

    Food(int energy, int sellPrice, TextureRegion textureRegion) {
        this.energy = energy;
        this.sellPrice = sellPrice;
        this.textureRegion = textureRegion;
    }

    public int getEnergy() {
        return energy;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public static Food getFoodByName(String name) {
        return stringToFood.getOrDefault(name.toLowerCase(), null);
    }

    public TextureRegion getInventoryTexture() {
        return textureRegion;
    }
}
