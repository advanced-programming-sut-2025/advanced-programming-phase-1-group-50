package models.foraging;

public enum ForagingTreeSource {
    Acorns(),
    MapleSeeds(),
    PineCones(),
    MahoganySeeds(),
    MushroomTreeSeeds();

    private String getName() {
        return name();
    }
}
