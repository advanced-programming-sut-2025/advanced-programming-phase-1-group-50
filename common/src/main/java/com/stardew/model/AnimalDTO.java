package com.stardew.model;

public class AnimalDTO {
    private float x;
    private float y;
    private float stateTime;
    private AnimationID animationID;

    public AnimalDTO() {}

    public AnimalDTO(float x, float y, float stateTime, AnimationID animationID) {
        this.x = x;
        this.y = y;
        this.stateTime = stateTime;
        this.animationID = animationID;
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

    public AnimationID getAnimationID() {
        return animationID;
    }
}
