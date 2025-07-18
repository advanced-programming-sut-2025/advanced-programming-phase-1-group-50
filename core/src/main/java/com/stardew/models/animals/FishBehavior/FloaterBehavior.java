package com.stardew.models.animals.FishBehavior;

import com.stardew.models.animals.Fish;

public class FloaterBehavior extends FishBehavior {
    private int state;
    public FloaterBehavior() {
        this.speedUp = 30f;

    }


    @Override
    public void update(Fish fish, float delta) {
        elapsedTime += delta;


        if (elapsedTime >= 1f) {
            elapsedTime = 0;
            state = (int) (Math.random() * 2);
        }




        if (state == 1 ) {
            fish.moveY(speedUp * delta);
        }
    }
}
