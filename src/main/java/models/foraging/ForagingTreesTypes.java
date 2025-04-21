package models.foraging;

public enum ForagingTreesTypes {
    Acorns(),
    MapleSeeds(),
    PineCones(),
    MahoganySeeds(),
    MushroomTreeSeeds();

    private String getName() {
        return name();
    }
}
