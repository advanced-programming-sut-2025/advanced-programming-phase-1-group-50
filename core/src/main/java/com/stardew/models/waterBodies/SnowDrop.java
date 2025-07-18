package com.stardew.models.waterBodies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;

public class SnowDrop {
    public float x , y , speedX , speedY;
    public Animation<TextureRegion> snowAnimation = GamePictureManager.snowAnimation;
    public float stateTime = 0;

    public SnowDrop(float x, float y, float speedX, float speedY) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;

    }

    public void update(float delta){
        x += speedX * delta;
        y += speedY * delta;
        stateTime += delta;
    }

    public void draw(SpriteBatch batch) {
        TextureRegion frame = snowAnimation.getKeyFrame(stateTime);
        batch.draw(frame, x, y);
    }

    public boolean isOffScreen(){
        return y < -16;
    }
}
