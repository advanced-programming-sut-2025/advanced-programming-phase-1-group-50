package com.stardew.models.tools;

public enum PoleType {
    Training(0.1),
    Bamboo(0.5 ),
    Fiberglass(0.9),
    Iridium(1.2);

    private final double effectiveness;

    PoleType(double effectiveness) {
        this.effectiveness = effectiveness;
    }

    public double getEffectiveness() {
        return effectiveness;
    }
}
