package com.stardew.models.animals.FishBehavior;

import com.stardew.models.animals.Fish;

public class DartBehavior extends FishBehavior {
    private int direction = 0;
    private float epsilon = 0.01f;

    public DartBehavior() {
        this.speedUp = 250f;
        this.speedDown = 250f;
    }

    @Override
    public void update(Fish fish, float delta) {
        elapsedTime += delta;
        if (elapsedTime >= 2f) {
            elapsedTime = 0;
            direction = (int) (Math.random() * 3);
        }

        float previousY = fish.getPosition().y;

        switch (direction) {
            case 1 -> fish.moveY(speedUp * delta);
            case 2 -> fish.moveY(-speedDown * delta);
        }

        float newY = fish.getPosition().y;

        if (direction != 0 && Math.abs(newY - previousY) < epsilon) {
            elapsedTime = 0;
            direction = (int) (Math.random() * 3);
        }

    }
}
