package com.stardew.models;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;

public class Wind {
    public float x , y , speedX , speedY;
    public Animation<TextureRegion> windAnimation = GamePictureManager.windAnimation;
    public float stateTime = 0;


    public Wind(float x, float y, float SpeedX, float SpeedY) {
        this.x = x;
        this.y = y;
        this.speedX = SpeedX;
        this.speedY = SpeedY;
    }

    public void update(float delta){
        x += speedX * delta;
        y += speedY * delta;
        stateTime += delta;
    }

    public void draw(SpriteBatch batch) {
        TextureRegion frame = windAnimation.getKeyFrame(stateTime);
        batch.draw(frame, x, y);
    }

    public boolean isOffScreen(){
        return x < -40;
    }
}
