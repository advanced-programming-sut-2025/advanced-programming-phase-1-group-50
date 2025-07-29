package com.stardew.model.animals.FishBehaviors;
import com.stardew.model.animals.Fish;

public abstract class FishBehavior {

    protected float elapsedTime = 0f;
    protected float speedUp;
    protected float speedDown;

    public abstract void update(Fish fish, float delta);
}
