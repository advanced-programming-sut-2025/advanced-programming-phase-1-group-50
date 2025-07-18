package com.stardew.models.animals.FishBehavior;

import com.stardew.models.animals.Fish;

public class DartBehavior extends FishBehavior {
    private int direction = 0;

    public DartBehavior() {
        this.speedUp = 60f;
        this.speedDown = 60f;
    }

    @Override
    public void update(Fish fish, float delta) {
        elapsedTime += delta;
        if (elapsedTime >= 0.3f) {
            elapsedTime = 0;
            direction = (int) (Math.random() * 3);
        }

        switch (direction) {
            case 1 -> fish.moveY(speedUp * delta);
            case 2 -> fish.moveY(-speedDown * delta);
        }
    }
}
