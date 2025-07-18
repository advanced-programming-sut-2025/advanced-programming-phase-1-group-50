package com.stardew.models.animals.FishBehavior;

import com.stardew.models.animals.Fish;

public class MixedBehavior extends FishBehavior {
    private int state = 0;

    public MixedBehavior() {
        this.speedUp = 250f;
        this.speedDown = 250f;
    }

    @Override
    public void update(Fish fish, float delta) {
        elapsedTime += delta;
        if (elapsedTime >= 1) {
            elapsedTime = 0;
            state = (int) (Math.random() * 3);
        }

        switch (state) {
            case 1 -> fish.moveY(speedUp * delta);
            case 2 -> fish.moveY(-speedDown * delta);
        }
    }
}
