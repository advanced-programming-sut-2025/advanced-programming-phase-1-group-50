package com.stardew.models.foraging;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.date.Season;
import com.stardew.models.manuFactor.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public enum CropType implements Ingredient {
    BlueJazz(Seeds.JazzSeeds,
            new ArrayList<>(Arrays.asList(1, 2, 2, 2)),
            7, true, 0, 50, true, 45,
            new ArrayList<>(List.of(Season.Spring)), false,
            GamePictureManager.blueJazzTextures, GamePictureManager.blueJazzTexture),
    Carrot(Seeds.CarrotSeeds,
            new ArrayList<>(Arrays.asList(1, 1, 1)),
            3, true, 0, 35, true, 75,
            new ArrayList<>(List.of(Season.Spring)), false,
            GamePictureManager.carrotTextures, GamePictureManager.carrotTexture),
    Cauliflower(Seeds.CauliflowerSeeds,
            new ArrayList<>(Arrays.asList(1, 2, 4, 4, 1)),
            12, true, 0, 175, true, 75,
            new ArrayList<>(List.of(Season.Spring)), true,
            GamePictureManager.cauliflowerTextures, GamePictureManager.cauliflowerTexture),
    CoffeeBean(Seeds.CoffeeBean,
            new ArrayList<>(Arrays.asList(1, 2, 2, 3, 2)),
            10, false, 2, 15, false, 0,
            new ArrayList<>(Arrays.asList(Season.Spring, Season.Summer)), false,
            GamePictureManager.coffeeTextures, GamePictureManager.coffeeBeanTexture),
    Garlic(Seeds.GarlicSeeds,
            new ArrayList<>(Arrays.asList(1, 1, 1, 1)),
            4, true, 0, 60, true, 20,
            new ArrayList<>(List.of(Season.Spring)), false,
            GamePictureManager.garlicTextures, GamePictureManager.garlicTexture),
    GreenBean(Seeds.BeanStarter,
            new ArrayList<>(Arrays.asList(1, 1, 1, 3, 4)),
            10, false, 3, 40, true, 25,
            new ArrayList<>(List.of(Season.Spring)), false,
            GamePictureManager.greenBeanTextures, GamePictureManager.greenBeanTexture),
    Kale(Seeds.KaleSeeds,
            new ArrayList<>(Arrays.asList(1 ,2, 2, 1)),
            6, true, 0, 110, true, 50,
            new ArrayList<>(List.of(Season.Spring)), false,
            GamePictureManager.kaleTextures, GamePictureManager.kaleTexture),
    Parsnip(Seeds.ParsnipSeeds,
            new ArrayList<>(Arrays.asList(1, 1, 1, 1)),
            4, true, 0, 35, true, 25,
            new ArrayList<>(List.of(Season.Spring)), false,
            GamePictureManager.parsnipTextures, GamePictureManager.parsnipTexture),
    Potato(Seeds.PotatoSeeds,
            new ArrayList<>(Arrays.asList(1, 1, 1, 2, 1)),
            6, true, 0, 80, true, 25,
            new ArrayList<>(List.of(Season.Spring)), false,
            GamePictureManager.potatoTextures, GamePictureManager.potatoTexture),
    Rhubarb(Seeds.RhubarbSeeds,
            new ArrayList<>(Arrays.asList(2, 2, 2, 3, 4)),
            13, true, 0, 220, false, 0,
            new ArrayList<>(List.of(Season.Spring)), false,
            GamePictureManager.rhubarbTextures, GamePictureManager.rhubarbTexture),
    Strawberry(Seeds.StrawberrySeeds,
            new ArrayList<>(Arrays.asList(1, 1, 2, 2, 2)),
            8, false, 4, 120, true, 50,
            new ArrayList<>(List.of(Season.Spring)), false,
            GamePictureManager.strawberryTextures, GamePictureManager.strawberryTexture),
    Tulip(Seeds.TulipBulb,
            new ArrayList<>(Arrays.asList(1, 1, 2, 2)),
            6, true, 0, 30, true, 45,
            new ArrayList<>(List.of(Season.Spring)), false,
            GamePictureManager.tulipTextures, GamePictureManager.tulipTexture),
    UnMilledRice(Seeds.RiceShoot,
            new ArrayList<>(Arrays.asList(1, 2, 2, 3)),
            8, true, 0, 30, true, 3,
            new ArrayList<>(List.of(Season.Spring)), false,
            GamePictureManager.unMilledRiceTextures, GamePictureManager.unMilledRiceTexture),
    Blueberry(Seeds.BlueberrySeeds,
            new ArrayList<>(Arrays.asList(1, 3, 3, 4, 2)),
            13, false, 4, 50, true, 25,
            new ArrayList<>(List.of(Season.Summer)), false,
            GamePictureManager.blueberryTextures, GamePictureManager.blueberryTexture),
    Corn(Seeds.CornSeeds,
            new ArrayList<>(Arrays.asList(2, 3, 3, 3, 3)),
            14, false, 4, 50, true, 25,
            new ArrayList<>(Arrays.asList(Season.Summer, Season.Fall)), false,
            GamePictureManager.cornTextures, GamePictureManager.cornTexture),
    Hops(Seeds.HopsStarter,
            new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4)),
            11, false, 1, 25, true, 45,
            new ArrayList<>(List.of(Season.Summer)), false,
            GamePictureManager.hopsTextures, GamePictureManager.hopsTexture),
    HotPepper(Seeds.PepperSeeds,
            new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)),
            5, false, 3, 40, true, 13,
            new ArrayList<>(List.of(Season.Summer)), false,
            GamePictureManager.hotPepperTextures, GamePictureManager.hotPepperTexture),
    Melon(Seeds.MelonSeeds,
            new ArrayList<>(Arrays.asList(1, 2, 3, 3, 3)),
            12, true, 0, 250, true, 113,
            new ArrayList<>(List.of(Season.Summer)), true,
            GamePictureManager.melonTextures, GamePictureManager.melonTexture),
    Poppy(Seeds.PoppySeeds,
            new ArrayList<>(Arrays.asList(1, 2, 2, 2)),
            7, true, 0, 140, true, 45,
            new ArrayList<>(List.of(Season.Summer)), false,
            GamePictureManager.poppyTextures, GamePictureManager.poppyTexture),
    Radish(Seeds.RadishSeeds,
            new ArrayList<>(Arrays.asList(2, 1, 2, 1)),
            6, true, 0, 90, true, 45,
            new ArrayList<>(List.of(Season.Summer)), false,
            GamePictureManager.radishTextures, GamePictureManager.radishTexture),
    RedCabbage(Seeds.RedCabbageSeeds,
            new ArrayList<>(Arrays.asList(2, 1, 2, 2, 2)),
            9, true, 0, 260, true, 75,
            new ArrayList<>(List.of(Season.Summer)), false,
            GamePictureManager.redCabbageTextures, GamePictureManager.redCabbageTexture),
    Starfruit(Seeds.StarfruitSeeds,
            new ArrayList<>(Arrays.asList(2, 3, 2, 3, 3)),
            13, true, 0, 750, true, 125,
            new ArrayList<>(List.of(Season.Summer)), false,
            GamePictureManager.starfruitTextures, GamePictureManager.starfruitTexture),
    SummerSpangle(Seeds.SpangleSeeds,
            new ArrayList<>(Arrays.asList(1, 2, 3, 1)),
            8, true, 0, 90, true, 45,
            new ArrayList<>(List.of(Season.Summer)), false,
            GamePictureManager.summerSpangleTextures, GamePictureManager.summerSpangleTexture),
    SummerSquash(Seeds.SummerSquashSeeds,
            new ArrayList<>(Arrays.asList(1, 1, 1, 2, 1)),
            6, false, 3, 45, true, 63,
            new ArrayList<>(List.of(Season.Summer)), false,
            GamePictureManager.summerSquashTextures, GamePictureManager.summerSquashTexture),
    Sunflower(Seeds.SunflowerSeeds,
            new ArrayList<>(Arrays.asList(1, 2, 3, 2)),
            8, true, 0, 80, true, 45,
            new ArrayList<>(Arrays.asList(Season.Summer, Season.Fall)), false,
            GamePictureManager.sunflowerTextures, GamePictureManager.sunflowerTexture),
    Tomato(Seeds.TomatoSeeds,
            new ArrayList<>(Arrays.asList(2, 2, 2, 2, 3)),
            11, false, 4, 60, true, 20,
            new ArrayList<>(List.of(Season.Summer)), false,
            GamePictureManager.tomatoTextures, GamePictureManager.tomatoTexture),
    Wheat(Seeds.WheatSeeds,
            new ArrayList<>(Arrays.asList(1, 1, 1, 1)),
            4, true, 0, 25, false, 0,
            new ArrayList<>(Arrays.asList(Season.Summer, Season.Fall)), false,
            GamePictureManager.wheatTextures, GamePictureManager.wheatTexture),
    Amaranth(Seeds.AmaranthSeeds,
            new ArrayList<>(Arrays.asList(1, 2, 2, 2)),
            7, true, 0, 150, true, 50,
            new ArrayList<>(List.of(Season.Fall)), false,
            GamePictureManager.amaranthTextures, GamePictureManager.amaranthTexture),
    Artichoke(Seeds.ArtichokeSeeds,
            new ArrayList<>(Arrays.asList(2, 2, 1, 2, 1)),
            8, true, 0, 160, true, 30,
            new ArrayList<>(List.of(Season.Fall)), false,
            GamePictureManager.artichokeTextures, GamePictureManager.artichokeTexture),
    Beet(Seeds.BeetSeeds,
            new ArrayList<>(Arrays.asList(1, 1, 2, 2)),
            6, true, 0, 100, true, 30,
            new ArrayList<>(List.of(Season.Fall)), false,
            GamePictureManager.beetTextures, GamePictureManager.beetTexture),
    BokChoy(Seeds.BokChoySeeds,
            new ArrayList<>(Arrays.asList(1, 1, 1, 1)),
            4, true, 0, 80, true, 25,
            new ArrayList<>(List.of(Season.Fall)), false,
            GamePictureManager.bokChoyTextures, GamePictureManager.bokChoyTexture),
    Broccoli(Seeds.BroccoliSeeds,
            new ArrayList<>(Arrays.asList(2, 2, 2, 2)),
            8, false, 4, 70, true, 63,
            new ArrayList<>(List.of(Season.Fall)), false,
            GamePictureManager.broccoliTextures, GamePictureManager.broccoliTexture),
    Cranberries(Seeds.CranberrySeeds,
            new ArrayList<>(Arrays.asList(1, 2, 1, 1, 2)),
            7, false, 5, 75, true, 38,
            new ArrayList<>(List.of(Season.Fall)), false,
            GamePictureManager.cranberriesTextures, GamePictureManager.cranberriesTexture),
    Eggplant(Seeds.EggplantSeeds,
            new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)),
            5, false, 5, 60, true, 20,
            new ArrayList<>(List.of(Season.Fall)), false,
            GamePictureManager.eggplantTextures, GamePictureManager.eggplantTexture),
    FairyRose(Seeds.FairySeeds,
            new ArrayList<>(Arrays.asList(1, 4, 4, 3)),
            12, true, 0, 290, true, 45,
            new ArrayList<>(List.of(Season.Fall)), false,
            GamePictureManager.fairyRoseTextures, GamePictureManager.fairyRoseTexture),
    Grape(Seeds.GrapeStarter,
            new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3)),
            10, false, 3, 80, true, 38,
            new ArrayList<>(List.of(Season.Fall)), false,
            GamePictureManager.grapeTextures, GamePictureManager.grapeTexture),
    Pumpkin(Seeds.PumpkinSeeds,
            new ArrayList<>(Arrays.asList(1, 2, 3, 4, 3)),
            13, true, 0, 320, false, 0,
            new ArrayList<>(List.of(Season.Fall)), true,
            GamePictureManager.pumpkinTextures, GamePictureManager.pumpkinTexture),
    Yam(Seeds.YamSeeds,
            new ArrayList<>(Arrays.asList(1, 3, 3, 3)),
            10, true, 0, 160, true, 45,
            new ArrayList<>(List.of(Season.Fall)), false,
            GamePictureManager.yamTextures, GamePictureManager.yamTexture),
    SweetGemBerry(Seeds.RareSeed,
            new ArrayList<>(Arrays.asList(2, 4, 6, 6, 6)),
            24, true, 0, 3000, false, 0,
            new ArrayList<>(List.of(Season.Fall)), false,
            GamePictureManager.sweetGemBerryTextures, GamePictureManager.sweetGemBerryTexture),
    PowderMelon(Seeds.PowdermelonSeeds,
            new ArrayList<>(Arrays.asList(1, 2, 1, 2, 1)),
            7, true, 0, 60, true, 63,
            new ArrayList<>(List.of(Season.Winter)), true,
            GamePictureManager.powdermelonTextures, GamePictureManager.powdermelonTexture),
    AncientFruit(Seeds.AncientSeeds,
            new ArrayList<>(Arrays.asList(2, 7, 7, 7, 5)),
            28, false, 7, 550, false, 0,
            new ArrayList<>(Arrays.asList(Season.Spring, Season.Summer, Season.Fall)), false,
            GamePictureManager.ancientFruitTextures, GamePictureManager.ancientFruitTexture),;

    private final Seeds source;
    private final ArrayList<Integer> stages;
    private final int totalHarvestTime;
    private final boolean oneTime;
    private final int regrowthTime;
    private final int baseSellPrice;
    private final boolean isEdible;
    private final int energy;
    private final ArrayList<Season> Seasons;
    private final boolean canBecomeGiant;
    private final TextureRegion[] levelsTextures;
    private final TextureRegion mainTexture;
    private final static HashMap<String, CropType> stringToCropType = new HashMap<>();

    static {
        for (CropType value : CropType.values()) {
            stringToCropType.put(value.name().toLowerCase(), value);
        }
    }

    CropType(Seeds source, ArrayList<Integer> stages, int totalHarvestTime, boolean oneTime, int regrowthTime,
             int baseSellPrice, boolean isEdible, int energy, ArrayList<Season> seasons, boolean canBecomeGiant,
             TextureRegion[] levelsTextures, TextureRegion mainTexture) {
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.oneTime = oneTime;
        this.regrowthTime = regrowthTime;
        this.baseSellPrice = baseSellPrice;
        this.isEdible = isEdible;
        this.energy = energy;
        Seasons = seasons;
        this.canBecomeGiant = canBecomeGiant;
        this.levelsTextures = levelsTextures;
        this.mainTexture = mainTexture;
    }

    public String getName() {
        return name();
    }

    public Seeds getSource() {
        return source;
    }

    public int getTimeForGrow(int level) {
        return stages.get(level);
    }

    public ArrayList<Integer> getStages() {
        return stages;
    }

    public int getNumberOfStages() {
        return stages.size();
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public boolean isOneTime() {
        return oneTime;
    }

    public int getRegrowthTime() {
        return regrowthTime;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public int getEnergy() {
        return energy;
    }

    public ArrayList<Season> getSeasons() {
        return Seasons;
    }

    public boolean CanBecomeGiant() {
        return canBecomeGiant;
    }

    public TextureRegion[] getLevelsTextures() {
        return levelsTextures;
    }

    public static CropType getCropTypeByName(String name) {
        if (name == null || name.isEmpty())
            return null;
        return stringToCropType.getOrDefault(name.toLowerCase(), null);
    }

    public TextureRegion getMainTexture(){
        return mainTexture;
    }

    public TextureRegion getInventoryTexture() {
        return mainTexture;
    }
}
