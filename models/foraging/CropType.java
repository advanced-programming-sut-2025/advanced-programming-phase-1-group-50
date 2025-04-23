package models.foraging;

public enum CropType {
    BlueJazz(),
    Carrot(),
    Cauliflower(),
    CoffeeBean(),
    Garlic(),
    GreenBean(),
    Kale(),
    Parsnip(),
    Potato(),
    Rhubarb(),
    Strawberry(),
    Tulip(),
    UnmilledRice(),
    Blueberry(),
    Corn(),
    Hops(),
    HotPepper(),
    Melon(),
    Poppy(),
    Radish(),
    RedCabbage(),
    Starfruit(),
    SummerSpangle(),
    SummerSquash(),
    Sunflower(),
    Tomato(),
    Wheat(),
    Amaranth(),
    Artichoke(),
    Beet(),
    BokChoy(),
    Broccoli(),
    Cranberries(),
    Eggplant(),
    FairyRose(),
    Grape(),
    Pumpkin(),
    Yam(),
    SweetGemBerry(),
    Powdermelon(),
    AncientFruit();

//    private final Seeds source;
//    private final ArrayList<Integer> stages;
//    private final boolean oneTime;
//    private final int regrowthTime;
//    private final int baseSalePrice;
//    private final boolean isEdible;
//    private final int energy;
//    private final ArrayList<Season> Seasons;
//    private final boolean canBecomeGiant;
//
//    CropType(Seeds source, ArrayList<Integer> stages, boolean oneTime, int regrowthTime, int baseSalePrice,
//             boolean isEdible, int energy, ArrayList<Season> seasons, boolean canBecomeGiant) {
//        this.source = source;
//        this.stages = stages;
//        this.oneTime = oneTime;
//        this.regrowthTime = regrowthTime;
//        this.baseSalePrice = baseSalePrice;
//        this.isEdible = isEdible;
//        this.energy = energy;
//        Seasons = seasons;
//        this.canBecomeGiant = canBecomeGiant;
//    }
//
    public String getName() {
        return name();
    }
}
