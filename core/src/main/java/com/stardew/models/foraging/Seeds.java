package com.stardew.models.foraging;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.date.Season;
import com.stardew.models.manuFactor.Ingredient;

import java.util.HashMap;

public enum Seeds implements Ingredient {
    JazzSeeds(Season.Spring, CropType.BlueJazz, GamePictureManager.jazzSeedsTexture),
    CarrotSeeds(Season.Spring, CropType.Carrot, GamePictureManager.carrotSeedsTexture),
    CauliflowerSeeds(Season.Spring, CropType.Cauliflower, GamePictureManager.cauliflowerSeedsTexture),
    CoffeeBean(Season.Spring, CropType.CoffeeBean, GamePictureManager.coffeeBeanTexture),
    GarlicSeeds(Season.Spring, CropType.Garlic, GamePictureManager.garlicSeedsTexture),
    BeanStarter(Season.Spring, CropType.GreenBean, GamePictureManager.beanStarterTexture),
    KaleSeeds(Season.Spring, CropType.Kale, GamePictureManager.kaleSeedsTexture),
    ParsnipSeeds(Season.Spring, CropType.Parsnip, GamePictureManager.parsnipSeedsTexture),
    PotatoSeeds(Season.Spring, CropType.Potato, GamePictureManager.potatoSeedsTexture),
    RhubarbSeeds(Season.Spring, CropType.Rhubarb, GamePictureManager.rhubarbSeedsTexture),
    StrawberrySeeds(Season.Spring, CropType.Strawberry, GamePictureManager.strawberrySeedsTexture),
    TulipBulb(Season.Spring, CropType.Tulip, GamePictureManager.tulipBulbTexture),
    RiceShoot(Season.Spring, CropType.UnMilledRice, GamePictureManager.riceShootTexture),
    BlueberrySeeds(Season.Summer, CropType.Blueberry, GamePictureManager.blueberrySeedsTexture),
    CornSeeds(Season.Summer, CropType.Corn, GamePictureManager.cornSeedsTexture),
    HopsStarter(Season.Summer, CropType.Hops, GamePictureManager.hopsStarterTexture),
    PepperSeeds(Season.Summer, CropType.HotPepper, GamePictureManager.pepperSeedsTexture),
    MelonSeeds(Season.Summer, CropType.Melon, GamePictureManager.melonSeedsTexture),
    PoppySeeds(Season.Summer, CropType.Poppy, GamePictureManager.poppySeedsTexture),
    RadishSeeds(Season.Summer, CropType.Radish, GamePictureManager.radishSeedsTexture),
    RedCabbageSeeds(Season.Summer, CropType.RedCabbage, GamePictureManager.redCabbageSeedsTexture),
    StarfruitSeeds(Season.Summer, CropType.Starfruit, GamePictureManager.starfruitSeedsTexture),
    SpangleSeeds(Season.Summer, CropType.SummerSpangle, GamePictureManager.spangleSeedsTexture),
    SummerSquashSeeds(Season.Summer, CropType.SummerSquash, GamePictureManager.summerSquashSeedsTexture),
    SunflowerSeeds(Season.Summer, CropType.Sunflower, GamePictureManager.sunflowerSeedsTexture),
    TomatoSeeds(Season.Summer, CropType.Tomato, GamePictureManager.tomatoSeedsTexture),
    WheatSeeds(Season.Summer, CropType.Wheat, GamePictureManager.wheatSeedsTexture),
    AmaranthSeeds(Season.Fall,CropType.Amaranth, GamePictureManager.amaranthSeedsTexture),
    ArtichokeSeeds(Season.Fall, CropType.Artichoke, GamePictureManager.artichokeSeedsTexture),
    BeetSeeds(Season.Fall, CropType.Beet, GamePictureManager.beetSeedsTexture),
    BokChoySeeds(Season.Fall, CropType.BokChoy, GamePictureManager.bokChoySeedsTexture),
    BroccoliSeeds(Season.Fall, CropType.Broccoli, GamePictureManager.broccoliSeedsTexture),
    CranberrySeeds(Season.Fall, CropType.Cranberries, GamePictureManager.cranberrySeedsTexture),
    EggplantSeeds(Season.Fall, CropType.Eggplant, GamePictureManager.eggplantSeedsTexture),
    FairySeeds(Season.Fall, CropType.FairyRose, GamePictureManager.fairySeedsTexture),
    GrapeStarter(Season.Fall, CropType.Grape, GamePictureManager.grapeStarterTexture),
    PumpkinSeeds(Season.Fall, CropType.Pumpkin, GamePictureManager.pumpkinSeedsTexture),
    YamSeeds(Season.Fall, CropType.Yam, GamePictureManager.yamSeedsTexture),
    RareSeed(Season.Fall, CropType.SweetGemBerry, GamePictureManager.rareSeedTexture),
    PowdermelonSeeds(Season.Winter, CropType.PowderMelon, GamePictureManager.powdermelonSeedsTexture),
    AncientSeeds(Season.Special, CropType.AncientFruit, GamePictureManager.ancientSeedsTexture),
    MixedSeeds(Season.Special, null, GamePictureManager.mixedSeedsTexture);

    private final Season season;
    private CropType crop;
    private final TextureRegion texture;
    private final static HashMap<String, Seeds> stringToSeeds = new HashMap<>();
    private static boolean firstInitialize = true;

    static {
        for (Seeds value : Seeds.values()) {
            stringToSeeds.put(value.name().toLowerCase(), value);
        }
    }

    public static void completeInitialize() {
        JazzSeeds.crop = CropType.BlueJazz;
        CarrotSeeds.crop = CropType.Carrot;
        CauliflowerSeeds.crop = CropType.Cauliflower;
        CoffeeBean.crop = CropType.CoffeeBean;
        GarlicSeeds.crop = CropType.Garlic;
        BeanStarter.crop = CropType.GreenBean;
        KaleSeeds.crop = CropType.Kale;
        ParsnipSeeds.crop = CropType.Parsnip;
        PotatoSeeds.crop = CropType.Potato;
        RhubarbSeeds.crop = CropType.Rhubarb;
        StrawberrySeeds.crop = CropType.Strawberry;
        TulipBulb.crop = CropType.Tulip;
        RiceShoot.crop = CropType.UnMilledRice;
        BlueberrySeeds.crop = CropType.Blueberry;
        CornSeeds.crop = CropType.Corn;
        HopsStarter.crop = CropType.Hops;
        PepperSeeds.crop = CropType.HotPepper;
        MelonSeeds.crop = CropType.Melon;
        PoppySeeds.crop = CropType.Poppy;
        RadishSeeds.crop = CropType.Radish;
        RedCabbageSeeds.crop = CropType.RedCabbage;
        StarfruitSeeds.crop = CropType.Starfruit;
        SpangleSeeds.crop = CropType.SummerSpangle;
        SummerSquashSeeds.crop = CropType.SummerSquash;
        SunflowerSeeds.crop = CropType.Sunflower;
        TomatoSeeds.crop = CropType.Tomato;
        WheatSeeds.crop = CropType.Wheat;
        AmaranthSeeds.crop = CropType.Amaranth ;
        ArtichokeSeeds.crop = CropType.Artichoke;
        BeetSeeds.crop = CropType.Beet;
        BokChoySeeds.crop = CropType.BokChoy;
        BroccoliSeeds.crop = CropType.Broccoli;
        CranberrySeeds.crop = CropType.Cranberries;
        EggplantSeeds.crop = CropType.Eggplant;
        FairySeeds.crop = CropType.FairyRose;
        GrapeStarter.crop = CropType.Grape;
        PumpkinSeeds.crop = CropType.Pumpkin;
        YamSeeds.crop = CropType.Yam;
        RareSeed.crop = CropType.SweetGemBerry;
        PowdermelonSeeds.crop = CropType.PowderMelon;
        AncientSeeds.crop = CropType.AncientFruit;

    }

    Seeds(Season season, CropType crop, TextureRegion texture) {
        this.season = season;
        this.crop = crop;
        this.texture = texture;
    }

    public String getName(){
        return name();
    }

    public Season getSeason() {
        return season;
    }

    public CropType getCrop() {
        if (firstInitialize) {
            completeInitialize();
            firstInitialize = false;
        }
        return crop;
    }

    public static Seeds getSeedByName(String name) {
        if (name == null || name.isEmpty())
            return null;
        return stringToSeeds.getOrDefault(name.toLowerCase(), null);
    }

    @Override
    public TextureRegion getInventoryTexture() {
        return texture;
    }
}

