package com.stardew.model.animals.FishBehaviors;
import com.stardew.model.animals.Fish;
public class SmoothBehavior extends FishBehavior {
    private int direction = 1;
    private float speedUp = 125f;


    @Override
    public void update(Fish fish, float delta) {
        elapsedTime += delta;
        if (elapsedTime >= 2f) {
            elapsedTime = 0;
            direction *= -1;

        }
        fish.moveY(direction * speedUp * delta);
    }
}
