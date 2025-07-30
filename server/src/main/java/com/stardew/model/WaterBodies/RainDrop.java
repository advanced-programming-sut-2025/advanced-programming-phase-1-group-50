package com.stardew.model.WaterBodies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RainDrop {
    public float x , y , speedX , speedY;
//    public Animation<TextureRegion> rainAnimation = GamePictureManager.rainAnimation;
    public float stateTime = 0;


    public RainDrop(float x, float y, float speedX, float speedY){
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void update(float delta){
        x += speedX * delta * 4;
        y += speedY * delta * 2;
        stateTime += delta;
    }

//    public void draw(SpriteBatch batch) {
//        TextureRegion frame = rainAnimation.getKeyFrame(stateTime);
//        batch.draw(frame, x, y);
//    }

    public boolean isOffScreen(){
        return y < -16;
    }
}
