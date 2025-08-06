package com.stardew.model;

public class PlaceableDTO {
    private float x;
    private float y;
    private float width;
    private float height;
    private TextureID textureID;

    public PlaceableDTO() {}

    public PlaceableDTO(float x, float y, float width, float height, TextureID textureID) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textureID = textureID;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public TextureID getTextureID() {
        return textureID;
    }
}
