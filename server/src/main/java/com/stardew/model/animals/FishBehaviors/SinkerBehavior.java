package com.stardew.model.animals.FishBehaviors;
import com.stardew.model.animals.Fish;
public class SinkerBehavior extends FishBehavior {
    private int state = 0;   // 0: still, 1: up 2: down

    public SinkerBehavior() {
        this.speedUp = 150f;
        this.speedDown = 350f;
    }

    @Override
    public void update(Fish fish, float delta) {
        elapsedTime += delta;
        if (elapsedTime >= 1f) {
            elapsedTime = 0;
            state = (int) (Math.random() * 3);  // 0: still, 1: up 2: down
        }

        switch (state) {
            case 1 -> fish.moveY(speedUp * delta);
            case 2 -> fish.moveY(-speedDown * delta);
        }
    }
}
