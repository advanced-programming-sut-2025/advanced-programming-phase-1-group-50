package com.stardew.model;

public class TileDTO {
    private final int x;
    private final int y;
    private final TextureID backgroundTextureID;
    //TODO another


    public TileDTO(int x, int y, TextureID backgroundTextureID) {
        this.x = x;
        this.y = y;
        this.backgroundTextureID = backgroundTextureID;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TextureID getBackgroundTextureID() {
        return backgroundTextureID;
    }
}
