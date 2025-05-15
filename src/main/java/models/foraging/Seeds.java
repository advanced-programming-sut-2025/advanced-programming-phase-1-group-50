package models.foraging;

import models.date.Season;
import models.manuFactor.Ingredient;

import java.util.HashMap;

public enum Seeds implements Ingredient {
    JazzSeeds(Season.Spring, CropType.BlueJazz),
    CarrotSeeds(Season.Spring, CropType.Carrot),
    CauliflowerSeeds(Season.Spring, CropType.Cauliflower),
    CoffeeBean(Season.Spring, CropType.CoffeeBean),
    GarlicSeeds(Season.Spring, CropType.Garlic),
    BeanStarter(Season.Spring, CropType.GreenBean),
    KaleSeeds(Season.Spring, CropType.Kale),
    ParsnipSeeds(Season.Spring, CropType.Parsnip),
    PotatoSeeds(Season.Spring, CropType.Potato),
    RhubarbSeeds(Season.Spring, CropType.Rhubarb),
    StrawberrySeeds(Season.Spring, CropType.Strawberry),
    TulipBulb(Season.Spring, CropType.Tulip),
    RiceShoot(Season.Spring, CropType.UnMilledRice),
    BlueberrySeeds(Season.Summer, CropType.Blueberry),
    CornSeeds(Season.Summer, CropType.Corn),
    HopsStarter(Season.Summer, CropType.Hops),
    PepperSeeds(Season.Summer, CropType.HotPepper),
    MelonSeeds(Season.Summer, CropType.Melon),
    PoppySeeds(Season.Summer, CropType.Poppy),
    RadishSeeds(Season.Summer, CropType.Radish),
    RedCabbageSeeds(Season.Summer, CropType.RedCabbage),
    StarfruitSeeds(Season.Summer, CropType.Starfruit),
    SpangleSeeds(Season.Summer, CropType.SummerSpangle),
    SummerSquashSeeds(Season.Summer, CropType.SummerSquash),
    SunflowerSeeds(Season.Summer, CropType.Sunflower),
    TomatoSeeds(Season.Summer, CropType.Tomato),
    WheatSeeds(Season.Summer, CropType.Wheat),
    AmaranthSeeds(Season.Fall,CropType.Amaranth ),
    ArtichokeSeeds(Season.Fall, CropType.Artichoke),
    BeetSeeds(Season.Fall, CropType.Beet),
    BokChoySeeds(Season.Fall, CropType.BokChoy),
    BroccoliSeeds(Season.Fall, CropType.Broccoli),
    CranberrySeeds(Season.Fall, CropType.Cranberries),
    EggplantSeeds(Season.Fall, CropType.Eggplant),
    FairySeeds(Season.Fall, CropType.FairyRose),
    GrapeStarter(Season.Fall, CropType.Grape),
    PumpkinSeeds(Season.Fall, CropType.Pumpkin),
    YamSeeds(Season.Fall, CropType.Yam),
    RareSeed(Season.Fall, CropType.SweetGemBerry),
    PowdermelonSeeds(Season.Winter, CropType.PowderMelon),
    AncientSeeds(Season.Special, CropType.AncientFruit),
    MixedSeeds(Season.Special, null);

    private final Season season;
    private CropType crop;
    private final static HashMap<String, Seeds> stringToSeeds = new HashMap<>();

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

    Seeds(Season season, CropType crop) {
        this.season = season;
        this.crop = crop;
    }

    public String getName(){
        return name();
    }

    public Season getSeason() {
        return season;
    }

    public CropType getCrop() {
        return crop;
    }

    public static Seeds getSeedByName(String name) {
        if (name == null || name.isEmpty())
            return null;
        return stringToSeeds.getOrDefault(name.toLowerCase(), null);
    }

}

