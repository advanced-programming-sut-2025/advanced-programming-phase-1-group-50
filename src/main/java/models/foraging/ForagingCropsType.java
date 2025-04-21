package models.foraging;

public enum ForagingCropsType {
    CommonMushroom(),
    Daffodil(),
    Dandelion(),
    Leek(),
    Morel(),
    Salmonberry(),
    SpringOnion(),
    WildHorseradish(),
    FiddleheadFern(),
    Grape(),
    RedMushroom(),
    SpiceBerry(),
    SweetPea(),
    Blackberry(),
    Chanterelle(),
    Hazelnut(),
    PurpleMushroom(),
    WildPlum(),
    Crocus(),
    CrystalFruit(),
    Holly(),
    SnowYam(),
    WinterRoot();

    public String getName(){
        return name();
    }
}
