package com.stardew.models.cooking;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.stores.Sellable;

import java.util.HashMap;

public enum Food implements Ingredient, Sellable {
    FriedEgg(50, 35, GamePictureManager.friedEggNormal.getRegion()),
    BakedFish(75, 100, GamePictureManager.friedEggNormal.getRegion()),
    Salad(113, 110, GamePictureManager.saladNormal.getRegion()),
    Omelet(100, 125, GamePictureManager.omeletNormal.getRegion()),
    PumpkinPie(225, 385, GamePictureManager.pumpkinPieNormal.getRegion()),
    Spaghetti(75, 120, GamePictureManager.spaghettiNormal.getRegion()),
    Pizza(150, 300, GamePictureManager.pizzaNormal.getRegion()),
    Tortilla(50, 50, GamePictureManager.tortillaNormal.getRegion()),
    MakiRoll(100, 220, GamePictureManager.makiRollNormal.getRegion()),
    TripleShotEspresso(200, 450, GamePictureManager.tripleShotEspressoNormal.getRegion()),
    Cookie(90, 140, GamePictureManager.cookieNormal.getRegion()),
    HashBrowns(90, 120, GamePictureManager.hashbrownsNormal.getRegion()),
    Pancakes(90, 80, GamePictureManager.pancakesNormal.getRegion()),
    FruitSalad(263, 450, GamePictureManager.fruitSaladNormal.getRegion()),
    RedPlate(240, 400, GamePictureManager.redPlateNormal.getRegion()),
    Bread(50, 60, GamePictureManager.breadNormal.getRegion()),
    SalmonDinner(125, 300, GamePictureManager.salmonDinnerNormal.getRegion()),
    VegetableMedley(165, 120, GamePictureManager.vegetableMedleyNormal.getRegion()),
    FarmersLunch(200, 150, GamePictureManager.farmersLunchNormal.getRegion()),
    SurvivalBurger(125, 180, GamePictureManager.survivalBurgerNormal.getRegion()),
    DishOTheSea(150, 220, GamePictureManager.dishOTheSeaNormal.getRegion()),
    SeaFoamPudding(175, 300, GamePictureManager.seaFoamPuddingNormal.getRegion()),
    JojaCola(13,25, GamePictureManager.jojaColaTexture),
    TroutSoup(100,100, GamePictureManager.troutSoupTexture),
    MinersTreat(125, 200, GamePictureManager.minersTreatNormal.getRegion());

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
