package com.stardew.models.animals.FishBehavior;

import com.stardew.models.animals.Fish;

public class FloaterBehavior extends FishBehavior {
    private int state;

    public FloaterBehavior() {
        this.speedUp = 350f;
        this.speedDown = 150f;
    }


    @Override
    public void update(Fish fish, float delta) {
        elapsedTime += delta;

        if (elapsedTime >= 1f) {
            elapsedTime = 0;
            state = (int) (Math.random() * 3);
        }

        switch (state) {
            case 1 -> fish.moveY(speedUp * delta);
            case 2 -> fish.moveY(-speedDown * delta);
        }

    }
}
