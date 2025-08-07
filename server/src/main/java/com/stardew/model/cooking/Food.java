package com.stardew.model.cooking;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.TextureID;
import com.stardew.model.mapInfo.Eatable;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.stores.Sellable;

import java.util.HashMap;

public enum Food implements Ingredient , Sellable , Eatable {
    FriedEgg(50, 35, TextureID.friedEgg),
    BakedFish(75, 100, TextureID.bakedFish),
    Salad(113, 110, TextureID.salad),
    Omelet(100, 125, TextureID.omelet),
    PumpkinPie(225, 385, TextureID.pumpkinPie),
    Spaghetti(75, 120, TextureID.spaghetti),
    Pizza(150, 300, TextureID.pizza),
    Tortilla(50, 50, TextureID.tortilla),
    MakiRoll(100, 220, TextureID.makiRoll),
    TripleShotEspresso(200, 450, TextureID.tripleShotEspresso),
    Cookie(90, 140, TextureID.cookie),
    HashBrowns(90, 120, TextureID.hashbrowns),
    Pancakes(90, 80, TextureID.pancakes),
    FruitSalad(263, 450, TextureID.fruitSalad),
    RedPlate(240, 400, TextureID.redPlate),
    Bread(50, 60, TextureID.bread),
    SalmonDinner(125, 300, TextureID.salmonDinner),
    VegetableMedley(165, 120, TextureID.vegetableMedley),
    FarmersLunch(200, 150, TextureID.farmersLunch),
    SurvivalBurger(125, 180, TextureID.survivalBurger),
    DishOTheSea(150, 220, TextureID.dishOTheSea),
    SeaFoamPudding(175, 300, TextureID.seaFoamPudding),
    JojaCola(13,25, TextureID.jojaColaTexture),
    TroutSoup(100,100, TextureID.troutSoupTexture),
    MinersTreat(125, 200, TextureID.minersTreat);

    private final int energy;
    private final int sellPrice;
    private final TextureID textureRegion;
    private static final HashMap<String, Food> stringToFood = new HashMap<>();

    static {
        for (Food values : values()) {
            stringToFood.put(values.name().toLowerCase(), values);
        }
    }

    Food(int energy, int sellPrice, TextureID textureRegion) {
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

    public static Food getFoodByName(String name) {
        return stringToFood.getOrDefault(name.toLowerCase(), null);
    }

    public TextureID getInventoryTexture() {
        return textureRegion;
    }

    @Override
    public InventoryItemDTO toDTO() {
        return new InventoryItemDTO(getInventoryTexture(), false, 1, toString(), name());
    }

    @Override
    public String getId() {
        return name();
    }


}
