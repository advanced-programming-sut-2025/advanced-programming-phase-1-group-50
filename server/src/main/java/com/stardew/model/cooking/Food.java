package com.stardew.model.cooking;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.model.DrawableID;
import com.stardew.model.TextureID;
import com.stardew.model.mapInfo.Eatable;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.stores.Sellable;

import java.util.HashMap;

public enum Food implements Ingredient , Sellable , Eatable {
    FriedEgg(50, 35, DrawableID.friedEggNormal),
    BakedFish(75, 100, DrawableID.friedEggNormal),
    Salad(113, 110, DrawableID.saladNormal),
    Omelet(100, 125, DrawableID.omeletNormal),
    PumpkinPie(225, 385, DrawableID.pumpkinPieNormal),
    Spaghetti(75, 120, DrawableID.spaghettiNormal),
    Pizza(150, 300, DrawableID.pizzaNormal),
    Tortilla(50, 50, DrawableID.tortillaNormal),
    MakiRoll(100, 220, DrawableID.makiRollNormal),
    TripleShotEspresso(200, 450, DrawableID.tripleShotEspressoNormal),
    Cookie(90, 140, DrawableID.cookieNormal),
    HashBrowns(90, 120, DrawableID.hashbrownsNormal),
    Pancakes(90, 80, DrawableID.pancakesNormal),
    FruitSalad(263, 450, DrawableID.fruitSaladNormal),
    RedPlate(240, 400, DrawableID.redPlateNormal),
    Bread(50, 60, DrawableID.breadNormal),
    SalmonDinner(125, 300, DrawableID.salmonDinnerNormal),
    VegetableMedley(165, 120, DrawableID.vegetableMedleyNormal),
    FarmersLunch(200, 150, DrawableID.farmersLunchNormal),
    SurvivalBurger(125, 180, DrawableID.survivalBurgerNormal),
    DishOTheSea(150, 220, DrawableID.dishOTheSeaNormal),
    SeaFoamPudding(175, 300, DrawableID.seaFoamPuddingNormal),
    JojaCola(13,25, null),
    TroutSoup(100,100, null),
    MinersTreat(125, 200, DrawableID.minersTreatNormal);

    private final int energy;
    private final int sellPrice;
    private final DrawableID textureRegion;
    private static final HashMap<String, Food> stringToFood = new HashMap<>();

    static {
        for (Food values : values()) {
            stringToFood.put(values.name().toLowerCase(), values);
        }
    }

    Food(int energy, int sellPrice, DrawableID textureRegion) {
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

    public DrawableID getTextureRegion() {
        return textureRegion;
    }

    public static Food getFoodByName(String name) {
        return stringToFood.getOrDefault(name.toLowerCase(), null);
    }

    public TextureID getInventoryTexture() {
        return null;
    }
}
