package com.stardew.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.stardew.models.Thunder;
import com.stardew.models.Wind;
import com.stardew.models.animals.GameModel;
import com.stardew.models.app.App;
import com.stardew.models.date.Weather;
import com.stardew.models.waterBodies.RainDrop;
import com.stardew.models.waterBodies.SnowDrop;

public class WeatherManager {
    private final Array<RainDrop> rainDrops = new Array<>();
    private final Array<SnowDrop> snowDrops = new Array<>();
    private final Array<Wind> winds = new Array<>();
    private float spawnTimer;
    private GameModel gameModel;
    private float thunderTimer;




    public void render(float delta) {
        if(App.getGame().getTime().getWeather().equals(Weather.Rainy)) {
            spawnTimer += delta;
            if (spawnTimer >= 0.03f) {
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
        } else if (App.getGame().getTime().getWeather().equals(Weather.Snowy)) {
            spawnTimer += delta;
            if (spawnTimer >= 0.03f) {
                spawnTimer = 0;
                float x = MathUtils.random(gameModel.getCamera().position.x - gameModel.getCamera().viewportWidth / 2,
                    gameModel.getCamera().position.x + gameModel.getCamera().viewportWidth / 2);
                float y = MathUtils.random(gameModel.getCamera().position.y + gameModel.getCamera().viewportHeight / 2,
                    gameModel.getCamera().position.y + gameModel.getCamera().viewportHeight);
                float speedY = -MathUtils.random(500, 700);
                float speedX = -MathUtils.random(20, 50);

                SnowDrop snowDrop = new SnowDrop(x, y, speedX, speedY);
                snowDrops.add(snowDrop);
                for (int i = snowDrops.size - 1; i >= 0; i--) {
                    SnowDrop r = snowDrops.get(i);
                    r.update(delta);
                    if (r.isOffScreen()) {
                        snowDrops.removeIndex(i);
                    }
                }
            }
        }
        else if(App.getGame().getTime().getWeather().equals(Weather.Stormy)){
            spawnTimer += delta;
            if (spawnTimer >= 0.03f) {
                spawnTimer = 0;
                float x = MathUtils.random(gameModel.getCamera().position.x - gameModel.getCamera().viewportWidth / 2,
                    gameModel.getCamera().position.x + gameModel.getCamera().viewportWidth / 2);


                float y = MathUtils.random(gameModel.getCamera().position.y - gameModel.getCamera().viewportHeight / 2,
                    gameModel.getCamera().position.y + gameModel.getCamera().viewportHeight / 2);
                float speedY = MathUtils.random(300, 350);
                float speedX = MathUtils.random(500, 700);

                Wind wind = new Wind(x, y, speedX, speedY);
                winds.add(wind);
                for (int i = winds.size - 1; i >= 0; i--) {
                    Wind r = winds.get(i);
                    r.update(delta);
                    if (r.isOffScreen()) {
                        winds.removeIndex(i);
                    }
                }
            }
        }
    }

    public void draw(SpriteBatch batch) {
        if(App.getGame().getTime().getWeather().equals(Weather.Rainy)) {
            for (RainDrop r : rainDrops) {
                r.draw(batch);
            }
        }
        else if (App.getGame().getTime().getWeather().equals(Weather.Snowy)) {
            for (SnowDrop r : snowDrops) {
                r.draw(batch);
            }
        } else if (App.getGame().getTime().getWeather().equals(Weather.Stormy)) {
            for(Wind r : winds) {
                r.draw(batch);
            }

        }
    }

    public void setGameModel(GameModel gameModel){
        this.gameModel = gameModel;
    }

    public void thunder(float delta , Stage stage) {
        if(App.getGame().getTime().getWeather().equals(Weather.Stormy)) {
            thunderTimer += delta;
            if(thunderTimer >= 10f) {
                thunderTimer = 0;
                Thunder thunder = new Thunder();
                float x = MathUtils.random(gameModel.getCamera().position.x - gameModel.getCamera().viewportWidth / 2,
                    gameModel.getCamera().position.x + gameModel.getCamera().viewportWidth / 2);
                float y = MathUtils.random(gameModel.getCamera().position.y - gameModel.getCamera().viewportHeight / 2,
                    gameModel.getCamera().position.y + gameModel.getCamera().viewportHeight / 2);

                thunder.setPosition(x, y);
                stage.addActor(thunder);

            }
        }
    }


}
