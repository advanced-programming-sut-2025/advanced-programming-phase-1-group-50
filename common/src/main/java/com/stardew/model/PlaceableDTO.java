package com.stardew.model;

public class PlaceableDTO {
    private int x;
    private int y;
    private int width;
    private int height;
    private TextureID textureID;

    public PlaceableDTO() {}

    public PlaceableDTO(int x, int y, int width, int height, TextureID textureID) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textureID = textureID;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TextureID getTextureID() {
        return textureID;
    }
}
