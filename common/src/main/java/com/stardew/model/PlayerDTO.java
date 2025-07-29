package com.stardew.model;

public class PlayerDTO {
    private float x;
    private float y;
    private int direction;
    private int energy;

    public PlayerDTO() {}

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
