package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.stardew.models.animals.GameModel;
import com.stardew.models.waterBodies.RainDrop;

public class WeatherManager {
    private final Array<RainDrop> rainDrops = new Array<>();
    private float spawnTimer;
    private GameModel gameModel;




    public void render(float delta) {
        spawnTimer += delta;
        if(spawnTimer >= 0.03f){
            spawnTimer = 0;
            float x = MathUtils.random(gameModel.getCamera().position.x - gameModel.getCamera().viewportWidth / 2,
                gameModel.getCamera().position.x + gameModel.getCamera().viewportWidth / 2);
            float y = MathUtils.random(gameModel.getCamera().position.y + gameModel.getCamera().viewportHeight / 2,
                gameModel.getCamera().position.y + gameModel.getCamera().viewportHeight);
            float speedY = -MathUtils.random(500, 700);
            float speedX = -MathUtils.random(20, 50);

            RainDrop rainDrop = new RainDrop(x, y, speedX, speedY);
            rainDrops.add(rainDrop);
            for (int i = rainDrops.size - 1; i >= 0; i--) {
                RainDrop r = rainDrops.get(i);
                r.update(delta);
                if (r.isOffScreen()) {
                    rainDrops.removeIndex(i);
                }
            }
        }
    }

    public void draw(SpriteBatch batch) {
        for(RainDrop r : rainDrops){
            r.draw(batch);
        }
    }

    public void setGameModel(GameModel gameModel){
        this.gameModel = gameModel;
    }
}
