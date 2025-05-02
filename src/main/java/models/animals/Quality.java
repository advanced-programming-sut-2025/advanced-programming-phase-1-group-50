package models.animals;

public enum Quality {
    Regular(1),
    Silver(1.25),
    Gold(1.5),
    Iridium(2);

    private final double ratio;

    Quality(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }
}
