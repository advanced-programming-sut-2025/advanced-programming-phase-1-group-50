package com.stardew.model;

public class PlayerDTO {
    private final float x;
    private final float y;
    private final int direction;
    private final int energy;

    public PlayerDTO(float x, float y, int direction, int energy) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.energy = energy;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public int getEnergy() {
        return energy;
    }
}
