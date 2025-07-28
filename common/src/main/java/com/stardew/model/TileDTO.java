package com.stardew.model;

public class TileDTO {
    private final int x;
    private final int y;
    private final TextureID backgroundTextureID;
    private final TextureID textureID;

    //TODO another


    public TileDTO(int x, int y, TextureID backgroundTextureID , TextureID textureID) {
        this.x = x;
        this.y = y;
        this.backgroundTextureID = backgroundTextureID;
        this.textureID = textureID;
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

    public TextureID getTextureID() {
        return textureID;
    }
}
