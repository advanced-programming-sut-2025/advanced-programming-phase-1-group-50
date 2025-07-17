package com.stardew.models.animals.FishBehavior;

import com.stardew.models.animals.Fish;

public abstract class FishBehavior {
    protected float elapsedTime = 0f;
    protected float speedUp;
    protected float speedDown;

    public abstract void update(Fish fish, float delta);
}
