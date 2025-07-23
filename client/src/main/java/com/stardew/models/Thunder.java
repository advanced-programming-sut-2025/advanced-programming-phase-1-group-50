package com.stardew.models;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.stardew.models.GameAssetManagers.GamePictureManager;

public class Thunder extends Actor {
    private float stateTime;
    private Animation<TextureRegion> thunderAnimation = GamePictureManager.stormAnimation;
    private boolean finished;

    public Thunder() {
        setSize(600, 800);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
        if (thunderAnimation.isAnimationFinished(stateTime)) {
            finished = true;
            remove();
        }
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (!finished) {
            TextureRegion currentFrame = thunderAnimation.getKeyFrame(stateTime);
            batch.draw(currentFrame, getX(), getY());
        }
    }

    public boolean isFinished() {
        return finished;
    }



}
