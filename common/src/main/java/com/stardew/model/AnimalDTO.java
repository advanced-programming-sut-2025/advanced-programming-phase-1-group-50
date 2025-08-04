package com.stardew.model;

public class AnimalDTO {
    private float x;
    private float y;
    private float stateTime;
    private String name;
    private String description;
    private AnimationID animationID;
    private TextureID normalTexture;

    public AnimalDTO() {}

    public AnimalDTO(float x, float y, float stateTime, String name, String description, AnimationID animationID, TextureID normalTexture) {
        this.x = x;
        this.y = y;
        this.stateTime = stateTime;
        this.name = name;
        this.description = description;
        this.animationID = animationID;
        this.normalTexture = normalTexture;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getStateTime() {
        return stateTime;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AnimationID getAnimationID() {
        return animationID;
    }

    public TextureID getNormalTexture() {
        return normalTexture;
    }
}
