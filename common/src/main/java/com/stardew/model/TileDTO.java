package com.stardew.model;

public class TileDTO {
    private int x;
    private int y;
    private TextureID backgroundTextureID;
    //TODO another

    public TileDTO() {}

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
