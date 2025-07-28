package com.stardew.model;

public class PlayerDTO {
    private final int x;
    private final int y;

    public PlayerDTO(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
