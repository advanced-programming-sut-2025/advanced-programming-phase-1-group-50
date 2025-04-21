package models.foraging;

public enum ForagingQuality {
    Regular(1),
    Silver(1.25),
    Gold(1.5),
    Iridium(2);

    private final double ratio;

    ForagingQuality(double ratio) {
        this.ratio = ratio;
    }
}
