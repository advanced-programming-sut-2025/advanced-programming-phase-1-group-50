package com.stardew.model.mapInfo.foraging;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.ItemInventoryType;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.date.Season;
import com.stardew.model.mapInfo.Ingredient;

import java.util.HashMap;

public enum Seeds implements Ingredient {
    JazzSeeds(Season.Spring, CropType.BlueJazz, TextureID.jazzSeedsTexture),
    CarrotSeeds(Season.Spring, CropType.Carrot, TextureID.carrotSeedsTexture),
    CauliflowerSeeds(Season.Spring, CropType.Cauliflower, TextureID.cauliflowerSeedsTexture),
    CoffeeBean(Season.Spring, CropType.CoffeeBean, TextureID.coffeeBeanTexture),
    GarlicSeeds(Season.Spring, CropType.Garlic, TextureID.garlicSeedsTexture),
    BeanStarter(Season.Spring, CropType.GreenBean, TextureID.beanStarterTexture),
    KaleSeeds(Season.Spring, CropType.Kale, TextureID.kaleSeedsTexture),
    ParsnipSeeds(Season.Spring, CropType.Parsnip, TextureID.parsnipSeedsTexture),
    PotatoSeeds(Season.Spring, CropType.Potato, TextureID.potatoSeedsTexture),
    RhubarbSeeds(Season.Spring, CropType.Rhubarb, TextureID.rhubarbSeedsTexture),
    StrawberrySeeds(Season.Spring, CropType.Strawberry, TextureID.strawberrySeedsTexture),
    TulipBulb(Season.Spring, CropType.Tulip, TextureID.tulipBulbTexture),
    RiceShoot(Season.Spring, CropType.UnMilledRice, TextureID.riceShootTexture),
    BlueberrySeeds(Season.Summer, CropType.Blueberry, TextureID.blueberrySeedsTexture),
    CornSeeds(Season.Summer, CropType.Corn, TextureID.cornSeedsTexture),
    HopsStarter(Season.Summer, CropType.Hops, TextureID.hopsStarterTexture),
    PepperSeeds(Season.Summer, CropType.HotPepper, TextureID.pepperSeedsTexture),
    MelonSeeds(Season.Summer, CropType.Melon, TextureID.melonSeedsTexture),
    PoppySeeds(Season.Summer, CropType.Poppy, TextureID.poppySeedsTexture),
    RadishSeeds(Season.Summer, CropType.Radish, TextureID.radishSeedsTexture),
    RedCabbageSeeds(Season.Summer, CropType.RedCabbage, TextureID.redCabbageSeedsTexture),
    StarfruitSeeds(Season.Summer, CropType.Starfruit, TextureID.starfruitSeedsTexture),
    SpangleSeeds(Season.Summer, CropType.SummerSpangle, TextureID.spangleSeedsTexture),
    SummerSquashSeeds(Season.Summer, CropType.SummerSquash, TextureID.summerSquashSeedsTexture),
    SunflowerSeeds(Season.Summer, CropType.Sunflower, TextureID.sunflowerSeedsTexture),
    TomatoSeeds(Season.Summer, CropType.Tomato, TextureID.tomatoSeedsTexture),
    WheatSeeds(Season.Summer, CropType.Wheat, TextureID.wheatSeedsTexture),
    AmaranthSeeds(Season.Fall,CropType.Amaranth, TextureID.amaranthSeedsTexture),
    ArtichokeSeeds(Season.Fall, CropType.Artichoke, TextureID.artichokeSeedsTexture),
    BeetSeeds(Season.Fall, CropType.Beet, TextureID.beetSeedsTexture),
    BokChoySeeds(Season.Fall, CropType.BokChoy, TextureID.bokChoySeedsTexture),
    BroccoliSeeds(Season.Fall, CropType.Broccoli, TextureID.broccoliSeedsTexture),
    CranberrySeeds(Season.Fall, CropType.Cranberries, TextureID.cranberrySeedsTexture),
    EggplantSeeds(Season.Fall, CropType.Eggplant, TextureID.eggplantSeedsTexture),
    FairySeeds(Season.Fall, CropType.FairyRose, TextureID.fairySeedsTexture),
    GrapeStarter(Season.Fall, CropType.Grape, TextureID.grapeStarterTexture),
    PumpkinSeeds(Season.Fall, CropType.Pumpkin, TextureID.pumpkinSeedsTexture),
    YamSeeds(Season.Fall, CropType.Yam, TextureID.yamSeedsTexture),
    RareSeed(Season.Fall, CropType.SweetGemBerry, TextureID.rareSeedTexture),
    PowdermelonSeeds(Season.Winter, CropType.PowderMelon, TextureID.powdermelonSeedsTexture),
    AncientSeeds(Season.Special, CropType.AncientFruit, TextureID.ancientSeedsTexture),
    MixedSeeds(Season.Special, null, TextureID.mixedSeedsTexture);

    private final Season season;
    private CropType crop;
    private final TextureID texture;
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

    Seeds(Season season, CropType crop, TextureID texture) {
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
    public TextureID getInventoryTexture() {
        return texture;
    }

    @Override
    public InventoryItemDTO toDTO() {
        return new InventoryItemDTO(getInventoryTexture() , false , 1 , ItemInventoryType.seeds , name());
    }

    @Override
    public String getId() {
        return name();
    }


}
