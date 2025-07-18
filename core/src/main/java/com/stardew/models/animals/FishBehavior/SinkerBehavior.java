package com.stardew.models.animals.FishBehavior;

import com.stardew.models.animals.Fish;

public class SinkerBehavior extends FishBehavior {
    private int state = 0; // 0: still, 1: down

    public SinkerBehavior() {
        this.speedUp = 15f;
        this.speedDown = 35f;
    }

    @Override
    public void update(Fish fish, float delta) {
        elapsedTime += delta;

        if (elapsedTime >= 1f) {
            elapsedTime = 0;
            state = (int) (Math.random() * 2); // 0: still, 1: down
        }

        if (state == 1) {
            fish.moveY(-speedDown * delta);
        }
    }
}

